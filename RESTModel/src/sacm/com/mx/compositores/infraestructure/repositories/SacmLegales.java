package sacm.com.mx.compositores.infraestructure.repositories;

import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.LegalesDto;
import sacm.com.mx.compositores.common.dtos.LegalesResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmLegales implements Serializable {
    @SuppressWarnings("compatibility:4362731770213788768")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmEstado.class);
    private static LegalesResultDto legalesResponse;

    public SacmLegales() {
        super();
    }

    public static LegalesResultDto getLegales(LegalesDto legalesRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_REGISTRO_USUARIO.PRC_CONSULTA_TERMINOS(?,?,?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            //cstmt.setObject(1, idTag);

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.registerOutParameter(2, Types.VARCHAR);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            cstmt.registerOutParameter(5, Types.INTEGER);
            cstmt.registerOutParameter(6, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();

            // 6. Set value of dateValue property using first OUT param
            legalesResponse = new LegalesResultDto();
            legalesResponse.setResponseBD(new HeaderDto());
            legalesResponse.getResponseBD().setCodErr(cstmt.getInt(5));
            legalesResponse.getResponseBD().setCodMsg(cstmt.getString(6));
            legalesResponse.setResponseService(new HeaderDto());
            legalesResponse.getResponseService().setCodErr(cstmt.getInt(5));
            legalesResponse.getResponseService().setCodMsg(cstmt.getString(6));
            legalesResponse.getTerminosPortal().setTitulo_terminos(cstmt.getString(1));
            legalesResponse.getTerminosPortal().setMensaje_terminos(cstmt.getString(2));
            legalesResponse.getTerminosPortal().setTitulo_aviso(cstmt.getString(3));
            legalesResponse.getTerminosPortal().setMensaje_aviso(cstmt.getString(4));

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            legalesResponse = new LegalesResultDto();
            legalesResponse.setResponseService(new HeaderDto());
            legalesResponse.getResponseService().setCodErr(1);
            legalesResponse.getResponseService().setCodMsg(e.getMessage());
            return legalesResponse;
        }
        _logger.info("Finish getLegales");
        // 9. Return the result
        return legalesResponse;
    }
}
