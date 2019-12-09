package sacm.com.mx.compositores.infraestructure.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.TreeMap;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.AlbumDto;
import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.MetadataDto;
import sacm.com.mx.compositores.common.dtos.NombreParticipante;
import sacm.com.mx.compositores.common.dtos.ObraDto;
import sacm.com.mx.compositores.common.dtos.ObraResultDto;
import sacm.com.mx.compositores.common.dtos.PalabraDto;
import sacm.com.mx.compositores.common.dtos.ParticipanteDto;
import sacm.com.mx.compositores.common.dtos.SugerenciaDto;
import sacm.com.mx.compositores.common.dtos.SugerenciaResultDto;
import sacm.com.mx.compositores.common.dtos.Tag;
import sacm.com.mx.compositores.common.dtos.TagN1;
import sacm.com.mx.compositores.common.dtos.TagN2;
import sacm.com.mx.compositores.common.dtos.TagSugerencia;
import sacm.com.mx.compositores.common.dtos.TrackInfoDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmSugerencia {
    @SuppressWarnings("compatibility:3396088568485404005")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmEstado.class);
    private static SugerenciaResultDto sugerenciaResponse;

    public SacmSugerencia() {
        super();
    }

    public static SugerenciaResultDto sacmgetSugerencia(PalabraDto palabra) {
        List<SugerenciaDto> sugerenciaListResult = new ArrayList<SugerenciaDto>();
        List<ObraDto> obraList = new ArrayList<ObraDto>();
        List<AlbumDto> albumList = new ArrayList<AlbumDto>();
        List<NombreParticipante> participanteList = new ArrayList<NombreParticipante>();
        List<TagSugerencia> tagList = new ArrayList<TagSugerencia>();

        Map<Integer, ObraDto> mapObra = new TreeMap<Integer, ObraDto>();
        Map<Integer, AlbumDto> mapAlbum = new HashMap<Integer, AlbumDto>();
        Map<Integer, NombreParticipante> mapParticipante = new HashMap<Integer, NombreParticipante>();
        Map<String, TagSugerencia> mapTag = new HashMap<String, TagSugerencia>();


        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        int valores[] = new int[7];
        for (int str : palabra.getArray_options()) {
            valores[str - 1] = 1;
        }


        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_BUSCADOR.PRC_CONSULTA_SUGERENCIAS(?,?,?,?,?,?,?,?,?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, palabra.getPalabra());
            cstmt.setObject(2, palabra.getId_album());
            cstmt.setObject(3, valores[0]);
            cstmt.setObject(4, valores[1]);
            cstmt.setObject(5, valores[2]);
            cstmt.setObject(6, valores[3]);
            cstmt.setObject(7, valores[4]);
            cstmt.setObject(8, valores[5]);
            cstmt.setObject(9, valores[6]);

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(10, Types.INTEGER);
            cstmt.registerOutParameter(11, Types.VARCHAR);
            cstmt.registerOutParameter(12, -10);

            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(10) == 0) {
                // print the results
                rs = (ResultSet) cstmt.getObject(12);


                while (rs.next()) {
                    ObraDto obra = new ObraDto();
                    AlbumDto album = new AlbumDto();
                    NombreParticipante participante = new NombreParticipante();
                    TagSugerencia tag = new TagSugerencia();

                    switch (rs.getString(1)) {
                    case "OBRA":
                        obra.setId_obra(rs.getInt(2));
                        obra.setObra_titulo(rs.getString(3));
                        obra.setObra_descripcion(rs.getString(4));
                        mapObra.put(obra.getId_obra() - 1, obra);
                        break;

                    case "ALBUM":
                        album.setId_album(rs.getInt(5));
                        album.setNombre_album(rs.getString(6));
                        album.setAlbum_descripcion(rs.getString(7));
                        mapAlbum.put(album.getId_album(), album);
                        break;

                    case "PARTICIPANTE":
                        participante.setId_participante(rs.getInt(8));
                        participante.setNombre(rs.getString(9));
                        participante.setSegundo_nombre(rs.getString(10));
                        participante.setApellido_paterno(rs.getString(11));
                        participante.setApellido_materno(rs.getString(12));
                        mapParticipante.put(participante.getId_participante(), participante);
                        break;

                    case "TAGS":
                        tag.setTag_path(rs.getString(19));
                        tag.setTag_path_name(rs.getString(20));
                        tag.setTag_path_id(rs.getString(21));
                        mapTag.put(tag.getTag_path_id(), tag);
                        break;

                    default:
                        break;
                    }
                }

                for (ObraDto value : mapObra.values()) {
                    obraList.add(value);
                }
                for (AlbumDto value : mapAlbum.values()) {
                    albumList.add(value);
                }
                for (NombreParticipante value : mapParticipante.values()) {
                    participanteList.add(value);
                }
                for (TagSugerencia value : mapTag.values()) {
                    tagList.add(value);
                }

                //   organizaSugerencia(sugerenciaListResult,sugerenciaList);
                rs.close();
            }

            // 6. Set value of dateValue property using first OUT param
            sugerenciaResponse = new SugerenciaResultDto();
            sugerenciaResponse.setResponseBD(new HeaderDto());
            sugerenciaResponse.getResponseBD().setCodErr(cstmt.getInt(10));
            sugerenciaResponse.getResponseBD().setCodMsg(cstmt.getString(11));
            sugerenciaResponse.setResponseService(new HeaderDto());
            sugerenciaResponse.getResponseService().setCodErr(cstmt.getInt(10));
            sugerenciaResponse.getResponseService().setCodMsg(cstmt.getString(11));
            sugerenciaResponse.setObras(obraList);
            sugerenciaResponse.setAlbumes(albumList);
            sugerenciaResponse.setParticipantes(participanteList);
            sugerenciaResponse.setTags(tagList);

            cstmt.close();

            conn.close();
            conn = null;


        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            sugerenciaResponse = new SugerenciaResultDto();
            sugerenciaResponse.setResponseService(new HeaderDto());
            sugerenciaResponse.getResponseService().setCodErr(1);
            sugerenciaResponse.getResponseService().setCodMsg(e.getMessage());
            return sugerenciaResponse;
        }
        _logger.info("Finish getVersiones");
        // 9. Return the result

        sugerenciaResponse.setObras(obraList);
        sugerenciaResponse.setAlbumes(albumList);
        sugerenciaResponse.setParticipantes(participanteList);
        sugerenciaResponse.setTags(tagList);
        return sugerenciaResponse;
    }

  /*  private static void organizaSugerencia(List<SugerenciaDto> sugerenciaListResult,
                                           List<SugerenciaDto> sugerenciaList) {

        List<Tag> tagList = new ArrayList<Tag>();
        List<TagN1> tagsListN1 = new ArrayList<TagN1>();
        List<TagN2> tagsListN2 = new ArrayList<TagN2>();

        Map<Integer, SugerenciaDto> map = new TreeMap<Integer, SugerenciaDto>();
        Map<Integer, Tag> mapTag = new HashMap<Integer, Tag>();
        Map<Integer, TagN1> mapTagN1 = new HashMap<Integer, TagN1>();
        Map<Integer, TagN2> mapTagN2 = new HashMap<Integer, TagN2>();

        for (SugerenciaDto str : sugerenciaList) {
            map.put(str.getId_Obra(), str);
        }

        for (SugerenciaDto value : map.values()) {
            sugerenciaListResult.add(value);
        }

        for (SugerenciaDto strTIR : sugerenciaListResult) {
            List<NombreParticipante> partList = new ArrayList<NombreParticipante>();
            Map<Integer, NombreParticipante> mapP = new TreeMap<Integer, NombreParticipante>();

            for (SugerenciaDto strTI : sugerenciaList) {

                if (strTI.getId_Obra() == strTIR.getId_Obra()) {
                    NombreParticipante part = new NombreParticipante();
                    part.setId_participante(strTI.getParticipante()
                                                 .get(0)
                                                 .getId_participante());
                    part.setNombre(strTI.getParticipante()
                                        .get(0)
                                        .getNombre());
                    part.setApellido_paterno(strTI.getParticipante()
                                                  .get(0)
                                                  .getApellido_paterno());
                    part.setApellido_materno(strTI.getParticipante()
                                                  .get(0)
                                                  .getApellido_materno());

                    mapP.put(part.getId_participante(), part);
                }
            }

            for (NombreParticipante value : mapP.values()) {
                partList.add(value);
            }


            mapTagN1 = new HashMap<Integer, TagN1>();
            tagsListN1 = new ArrayList<TagN1>();
            mapTag = new HashMap<Integer, Tag>();
            tagList = new ArrayList<Tag>();
            for (SugerenciaDto strMD : sugerenciaList) {
                if (strMD.getId_Obra() == strTIR.getId_Obra()) {
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


            for (Tag value : mapTag.values()) {
                tagList.add(value);
            }

            for (Tag strTag : tagList) {
                mapTagN1 = new HashMap<Integer, TagN1>();
                tagsListN1 = new ArrayList<TagN1>();
                for (SugerenciaDto strMD : sugerenciaList) {
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


                for (TagN1 value : mapTagN1.values()) {
                    tagsListN1.add(value);
                }

                for (TagN1 strTagN1 : tagsListN1) {
                    tagsListN2 = new ArrayList<TagN2>();
                    mapTagN2 = new HashMap<Integer, TagN2>();
                    for (SugerenciaDto strMD : sugerenciaList) {
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
                            mapTagN2.put(partN2.getId_TagN2(), partN2);
                        }

                    }

                    for (TagN2 value : mapTagN2.values()) {
                        tagsListN2.add(value);
                    }
                    strTagN1.setTagsListN2(tagsListN2);
                }

                strTag.setTagsListN1(tagsListN1);
            }


            strTIR.setTagsList(tagList);
            strTIR.setParticipante(partList);
        }
    }*/
}
