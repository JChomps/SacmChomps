package sacm.com.mx.compositores.infraestructure.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.LegalesResultDto;
import sacm.com.mx.compositores.common.dtos.MetadataDto;
import sacm.com.mx.compositores.common.dtos.MetadataResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmMetadata {
    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmMetadata.class);

    private static MetadataResultDto metadataResponse;

    public SacmMetadata() {
        super();
    }

    public static MetadataResultDto getMetadata(MetadataDto metadataRequest) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PRC_CONSULTA_METADATA(?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, metadataRequest.getIdObra());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);

            // 5. Execute the statement
            cstmt.executeUpdate();

            rs = (ResultSet) cstmt.getObject(4);
            // print the results
            List<MetadataDto> metadataList = new ArrayList<MetadataDto>();
            while (rs.next()) {
                MetadataDto metadata = new MetadataDto();
                metadata.setIdObra(rs.getInt(1));
                metadata.setNumeroObra(rs.getInt(2));
                metadata.setTituloObra(rs.getString(3));
                metadata.setDescripcionObra(rs.getString(4));
                metadata.setIdTag(rs.getInt(5));
                metadata.setNameTag(rs.getString(6));
                metadata.setIdTagItem(rs.getInt(7));
                metadata.setNameTagItem(rs.getString(8));
                metadata.setEstado(rs.getString(9));
                metadataList.add(metadata);
            }

            metadataResponse = new MetadataResultDto();
            // 6. Set value of dateValue property using first OUT param
            metadataResponse.getHeaderResponse().setCodErr(cstmt.getInt(2));
            metadataResponse.getHeaderResponse().setCodMsg(cstmt.getString(3));
            metadataResponse.getMetadata().setIdObra(metadataRequest.getIdObra());
            metadataResponse.setMetadataList(metadataList);

            cstmt.close();
            rs.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            metadataResponse = new MetadataResultDto();
            metadataResponse.getHeaderResponse().setCodErr(1);
            metadataResponse.getHeaderResponse().setCodMsg(e.getMessage());
            return metadataResponse;
        }
        _logger.info("Finish getmetadatas");
        // 9. Return the result
        return metadataResponse;
    }
}
