package sacm.com.mx.compositores.infraestructure.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.TreeMap;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.LegalesResultDto;
import sacm.com.mx.compositores.common.dtos.MetadataDto;
import sacm.com.mx.compositores.common.dtos.MetadataResultDto;
import sacm.com.mx.compositores.common.dtos.Tag;
import sacm.com.mx.compositores.common.dtos.TagN1;
import sacm.com.mx.compositores.common.dtos.TagN2;
import sacm.com.mx.compositores.common.dtos.TagsResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmMetadata {
    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmMetadata.class);

    private static MetadataResultDto metadataResponse;

    public SacmMetadata() {
        super();
    }

    /*---------------------------------------------------------- sacm_metadata -----------------------------------------------------------------------*/

    public static MetadataResultDto getMetadata(MetadataDto metadataRequest) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        List<MetadataDto> metadataListResult = new ArrayList<MetadataDto>();
        List<MetadataDto> metadataList = new ArrayList<MetadataDto>();

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_BUSCADOR.PRC_CONSULTA_METADATA(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, metadataRequest.getIdObra());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);
            // 5. Execute the statement
            cstmt.executeUpdate();
            // read the results
            if (cstmt.getInt(2) == 0) {
                rs = (ResultSet) cstmt.getObject(4);
                while (rs.next()) {
                    MetadataDto metadata = new MetadataDto();
                    Tag tag = new Tag();
                    TagN1 tagN1 = new TagN1();
                    TagN2 tagN2 = new TagN2();
                    metadata.setIdObra(rs.getInt(1));
                    metadata.setNumeroObra(rs.getInt(2));
                    metadata.setTituloObra(rs.getString(3));
                    metadata.setDescripcionObra(rs.getString(4));
                    //lectura de  Tag
                    tag.setIdTag(rs.getInt(5));
                    tag.setTagName(rs.getString(6));
                    // Leer Tag nivel 1
                    tagN1.setId_TagN1(rs.getInt(7));
                    tagN1.setNombre_TagN1(rs.getString(8));
                    //Lectura de Tag nivel 2
                    tagN2.setId_TagN2(rs.getString(9));
                    tagN2.setNombreTagN2(rs.getString(10));
                    //Se agrega el objeto Tag de nivel 2 dentro del objeto Tag nivel 1
                    tagN1.getTagsListN2().add(tagN2);
                    //Se agrega el objeto Tag de nivel 1 dentro del objeto Tag 
                    tag.getTagsListN1().add(tagN1);
                    //Se agrega el objeto Tag dentro del objeto Metadata
                    metadata.getTagsList().add(tag);

                    metadataList.add(metadata);
                }
                
                // Método para organizar los objetos anidados
                OrganizaMetadata(metadataListResult, metadataList);
                rs.close();
            }
            metadataResponse = new MetadataResultDto();
            // 6. Set value of dateValue property using OUT param
            metadataResponse.setResponseBD(new HeaderDto());
            metadataResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            metadataResponse.getResponseBD().setCodMsg(cstmt.getString(3));
            metadataResponse.setResponseService(new HeaderDto());
            metadataResponse.getResponseService().setCodErr(cstmt.getInt(2));
            metadataResponse.getResponseService().setCodMsg(cstmt.getString(3));
            //metadataResponse.getMetadata().setIdObra(metadataRequest.getIdObra());
            metadataResponse.setMetadataList(metadataListResult);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            metadataResponse = new MetadataResultDto();
            metadataResponse.setResponseService(new HeaderDto());
            metadataResponse.getResponseService().setCodErr(1);
            metadataResponse.getResponseService().setCodMsg(e.getMessage());
            return metadataResponse;
        }
        _logger.info("Finish getMetadatas");
        // 10. Return the result
        return metadataResponse;
    }

    private static void OrganizaMetadata(List<MetadataDto> metadataListResult, List<MetadataDto> metadataList) {
        List<Tag> tagList = new ArrayList<Tag>();       
        Map<Integer, MetadataDto> map = new HashMap<Integer, MetadataDto>();
        Map<Integer, Tag> mapTag = new HashMap<Integer, Tag>();
       
        //Conversión de ListMEtadata a MapMetadata para eliminar los objetos Metadata repetidos
        for (MetadataDto str : metadataList) {
            map.put(str.getIdObra(), str);
        }
        //Conversión de Map a List 
        for (MetadataDto value : map.values()) {
            metadataListResult.add(value);
        }
        //Ordenamiento de datos
        for (MetadataDto strMDR : metadataListResult) {
            //Ordenamiento de tags dentro de objetos Metadata correrspondientes 
            mapTag = new HashMap<Integer, Tag>();
            tagList = new ArrayList<Tag>();
            for (MetadataDto strMD : metadataList) {
                if (strMD.getIdObra() == strMDR.getIdObra()) {
                    Tag parTag = new Tag();
                    parTag.setIdTag(strMD.getTagsList()
                                         .get(0)
                                         .getIdTag());
                    parTag.setTagName(strMD.getTagsList()
                                           .get(0)
                                           .getTagName());
                    mapTag.put(parTag.getIdTag(), parTag);
                }
            }
            //Eliminación de objetos Tag repetidos
            for (Tag value : mapTag.values()) {
                tagList.add(value);
            }

            //Ordenamiento de tags de nivel 1 dentro de objetos Tag correrspondientes 
            OrganizaTags(metadataList,tagList);
            
            //Agregar lista de objetos tag  al objetoMetadata correspondiente
            strMDR.setTagsList(tagList);
        }
    }

    private static void OrganizaTags(List<MetadataDto> metadataList, List<Tag> tagList) {
        List<TagN1> tagsListN1 = new ArrayList<TagN1>();        
        Map<Integer, TagN1> mapTagN1 = new HashMap<Integer, TagN1>();
        for (Tag strTag : tagList) {
            mapTagN1 = new HashMap<Integer, TagN1>();
            tagsListN1 = new ArrayList<TagN1>();
            for (MetadataDto strMD : metadataList) {
                if (strTag.getIdTag() == strMD.getTagsList()
                                              .get(0)
                                              .getIdTag()) {
                    TagN1 partN1 = new TagN1();
                    partN1.setId_TagN1(strMD.getTagsList()
                                            .get(0)
                                            .getTagsListN1()
                                            .get(0)
                                            .getId_TagN1());
                    partN1.setNombre_TagN1(strMD.getTagsList()
                                                .get(0)
                                                .getTagsListN1()
                                                .get(0)
                                                .getNombre_TagN1());
                    mapTagN1.put(partN1.getId_TagN1(), partN1);
                }
            }
            // Eliminación de objetos Tag de nivel 1
            for (TagN1 value : mapTagN1.values()) {
                tagsListN1.add(value);
            }
            OrganizarTagsN1(tagsListN1,metadataList);
            //Agregar lista de objetos tag nivel 1 al objeto tag correspondiente
            strTag.setTagsListN1(tagsListN1);
        }
    }

    private static void OrganizarTagsN1(List<TagN1> tagsListN1, List<MetadataDto> metadataList) {
        List<TagN2> tagsListN2 = new ArrayList<TagN2>();
        //Ordenamiento de tags de nivel 2 dentro de objetos Tag de nivel 2correrspondientes 
        for (TagN1 strTagN1 : tagsListN1) {
            tagsListN2 = new ArrayList<TagN2>();
            for (MetadataDto strMD : metadataList) {
                if (strTagN1.getId_TagN1() == strMD.getTagsList()
                                                   .get(0)
                                                   .getTagsListN1()
                                                   .get(0)
                                                   .getId_TagN1()) {
                    TagN2 partN2 = new TagN2();
                    partN2.setId_TagN2(strMD.getTagsList()
                                            .get(0)
                                            .getTagsListN1()
                                            .get(0)
                                            .getTagsListN2()
                                            .get(0)
                                            .getId_TagN2());
                    partN2.setNombreTagN2(strMD.getTagsList()
                                               .get(0)
                                               .getTagsListN1()
                                               .get(0)
                                               .getTagsListN2()
                                               .get(0)
                                               .getNombreTagN2());
                    tagsListN2.add(partN2);
                }
            }
            //Agregar lista de objetos tag nivel 2 al objeto tag nivel 1 correspondiente
            strTagN1.setTagsListN2(tagsListN2);
        }
    }
}
