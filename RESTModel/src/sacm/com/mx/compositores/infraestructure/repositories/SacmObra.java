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
import sacm.com.mx.compositores.common.dtos.ObraDto;
import sacm.com.mx.compositores.common.dtos.ObraResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmObra implements Serializable {
    @SuppressWarnings("compatibility:3396088568485404005")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmEstado.class);
    private static ObraResultDto obraResponse;

    public SacmObra() {
        super();
    }

    public static ObraResultDto getVersionesByIdObra(ObraResultDto obraRequest) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PRC_CONSULTA_VERSIONES(?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, obraRequest.getObra().getIdObra());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);

            // 5. Execute the statement
            cstmt.executeUpdate();

            // print the results
            rs = (ResultSet) cstmt.getObject(4);
            List<ObraDto> obraList = new ArrayList<ObraDto>();
            while (rs.next()) {
                ObraDto obra = new ObraDto();
                obra.setIdObra(rs.getInt(1));
                obra.setNumeroObra(rs.getInt(2));
                obra.setIdObraAlbum(rs.getInt(3));
                obra.setTituloObra(rs.getString(4));
                obra.setDescripcionObra(rs.getString(5));
                obra.setIdVersion(rs.getInt(6));
                obra.setTituloVersion(rs.getString(7));
                obra.setDescripcionVersion(rs.getString(8));
                obra.setDuracionVersion(rs.getString(9));
                obra.setWavVersion(rs.getObject(10) == null ? null : rs.getString(10));
                obra.setMp3Version(rs.getObject(11) == null ? null : rs.getString(11));
                obra.setAiffVersion(rs.getObject(12) == null ? null : rs.getString(12));
                obra.setLyricVersion(rs.getObject(13) == null ? null : rs.getString(13));
                obra.setTypeVersion(rs.getString(14));
                obraList.add(obra);
            }

            // 6. Set value of dateValue property using first OUT param
            obraResponse = new ObraResultDto();
            obraResponse.getHeaderResponse().setErrorCode(cstmt.getInt(2));
            obraResponse.getHeaderResponse().setErrorMsg(cstmt.getString(3));
            obraResponse.setObraList(obraList);

            cstmt.close();
            rs.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            obraResponse = new ObraResultDto();
            obraResponse.getHeaderResponse().setErrorCode(1);
            obraResponse.getHeaderResponse().setErrorMsg(e.getMessage());
            return obraResponse;
        }
        _logger.info("Finish getVersiones");
        // 9. Return the result
        return obraResponse;
    }
}
