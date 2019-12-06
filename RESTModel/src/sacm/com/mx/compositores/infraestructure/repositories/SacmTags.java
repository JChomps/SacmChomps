package sacm.com.mx.compositores.infraestructure.repositories;

import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import java.util.TreeMap;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.EstadoResultDto;
import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.ObraResultDto;
import sacm.com.mx.compositores.common.dtos.ParticipanteDto;
import sacm.com.mx.compositores.common.dtos.Tag;
import sacm.com.mx.compositores.common.dtos.TagN1;
import sacm.com.mx.compositores.common.dtos.TagN2;
import sacm.com.mx.compositores.common.dtos.TagsDto;
import sacm.com.mx.compositores.common.dtos.TagsResultDto;
import sacm.com.mx.compositores.common.dtos.TrackInfoDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmTags implements Serializable {
    @SuppressWarnings("compatibility:5838653610898359998")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmEstado.class);
    private static TagsResultDto tagsResponse;

    public SacmTags() {
        super();
    }

    public static TagsResultDto getTagsByIdTag(TagsDto tagsRequest) {
        List<Tag> tagListResult = new ArrayList<Tag>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        
        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_BUSCADOR.PRC_CONSULTA_TAGS(?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, tagsRequest.getIdTag());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);

            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(2) == 0) {

                rs = (ResultSet) cstmt.getObject(4);
                List<Tag> tagList = new ArrayList<Tag>();               

                while (rs.next()) {
                    Tag tag = new Tag();
                    TagN1 tagN1 = new TagN1();
                    TagN2 tagN2 = new TagN2();

                    tag.setIdTag(rs.getInt(1));
                    tag.setTagName(rs.getString(2));
                    tagN1.setId_TagN1(rs.getInt(3));
                    tagN1.setNombre_TagN1(rs.getString(4));

                    tagN2.setId_TagN2(rs.getInt(5));
                    tagN2.setNombreTagN2(rs.getString(6));
                    tagN1.getTagsListN2().add(tagN2);

                    tag.getTagsListN1().add(tagN1);
                    tagList.add(tag);
                }
                
                organizaList(tagListResult, tagList);
                rs.close();
            }

            // 6. Set value of dateValue property using first OUT param
            tagsResponse = new TagsResultDto();
            tagsResponse.setResponseBD(new HeaderDto());
            tagsResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            tagsResponse.getResponseBD().setCodMsg(cstmt.getString(3));
            tagsResponse.setResponseService(new HeaderDto());
            tagsResponse.getResponseService().setCodErr(cstmt.getInt(2));
            tagsResponse.getResponseService().setCodMsg(cstmt.getString(3));
            tagsResponse.setTagsList(tagListResult);

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            tagsResponse = new TagsResultDto();
            tagsResponse.setResponseService(new HeaderDto());
            tagsResponse.getResponseService().setCodErr(1);
            tagsResponse.getResponseService().setCodMsg(e.getMessage());
            return tagsResponse;
        }
        _logger.info("Finish getEstados");
        // 9. Return the result
        return tagsResponse;
    }


    private static void organizaList(List<Tag> tagListResult, List<Tag> tagList) {
        Map<Integer, TagN1> mapN1 = new TreeMap<Integer, TagN1>();
        Map<Integer, Tag> map = new HashMap<Integer, Tag>();
        List<TagN1> tagsListN1 = new ArrayList<TagN1>();
        List<TagN2> tagsListN2 = new ArrayList<TagN2>();

        for (Tag str : tagList) {
            map.put(str.getIdTag(), str);
        }

        for (Tag value : map.values()) {
            tagListResult.add(value);
        }

        for (Tag strTLR : tagListResult) {
            mapN1 = new TreeMap<Integer, TagN1>();
            tagsListN1 = new ArrayList<TagN1>();
            for (Tag strTL : tagList) {
                if (strTL.getIdTag() == strTLR.getIdTag()) {
                    TagN1 partN1 = new TagN1();
                    partN1.setId_TagN1(strTL.getTagsListN1()
                                            .get(0)
                                            .getId_TagN1());
                    partN1.setNombre_TagN1(strTL.getTagsListN1()
                                                .get(0)
                                                .getNombre_TagN1());
                    mapN1.put(partN1.getId_TagN1(), partN1);
                }

            }

            for (TagN1 value : mapN1.values()) {
                tagsListN1.add(value);
            }

            for (TagN1 strN1 : tagsListN1) {
                tagsListN2 = new ArrayList<TagN2>();
                for (Tag strTL : tagList) {
                    if (strN1.getId_TagN1() == strTL.getTagsListN1()
                                                    .get(0)
                                                    .getId_TagN1()) {
                        TagN2 partN2 = new TagN2();
                        partN2.setId_TagN2(strTL.getTagsListN1()
                                                .get(0)
                                                .getTagsListN2()
                                                .get(0)
                                                .getId_TagN2());
                        partN2.setNombreTagN2(strTL.getTagsListN1()
                                                   .get(0)
                                                   .getTagsListN2()
                                                   .get(0)
                                                   .getNombreTagN2());
                        strN1.getTagsListN2().add(partN2);
                    }

                }
            }
            strTLR.setTagsListN1(tagsListN1);

        }
    }
}
