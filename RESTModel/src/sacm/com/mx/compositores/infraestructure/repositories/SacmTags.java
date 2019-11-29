package sacm.com.mx.compositores.infraestructure.repositories;

import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.EstadoResultDto;
import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.TagsDto;
import sacm.com.mx.compositores.common.dtos.TagsResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmTags implements Serializable {
    @SuppressWarnings("compatibility:5838653610898359998")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmEstado.class);
    private static TagsResultDto tagsResponse;

    public SacmTags() {
        super();
    }

    public static TagsResultDto getTagsByIdTag(TagsResultDto tagsRequest) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PRC_CONSULTA_TAGS(?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, tagsRequest.getTag().getIdTag());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);

            // 5. Execute the statement
            cstmt.executeUpdate();

            rs = (ResultSet) cstmt.getObject(4);
            // print the results
            List<TagsDto> tagsList = new ArrayList<TagsDto>();
            while (rs.next()) {
                TagsDto tag = new TagsDto();
                tag.setIdTag(rs.getInt(1));
                tag.setTagName(rs.getString(2));
                tag.setIdTagN1(rs.getInt(3));
                tag.setTagNameN1(rs.getString(4));
                tag.setIdTagN2(rs.getInt(5));
                tag.setTagNameN2(rs.getString(6));
                tagsList.add(tag);
            }

            // 6. Set value of dateValue property using first OUT param
            tagsResponse = new TagsResultDto();
            tagsResponse.getHeaderResponse().setCodErr(cstmt.getInt(2));
            tagsResponse.getHeaderResponse().setCodMsg(cstmt.getString(3));
            tagsResponse.getTag().setIdTag(tagsRequest.getTag().getIdTag());
            tagsResponse.setTagsList(tagsList);

            cstmt.close();
            rs.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            tagsResponse = new TagsResultDto();
            tagsResponse.getHeaderResponse().setCodErr(1);
            tagsResponse.getHeaderResponse().setCodMsg(e.getMessage());
            return tagsResponse;
        }
        _logger.info("Finish getEstados");
        // 9. Return the result
        return tagsResponse;
    }
}
