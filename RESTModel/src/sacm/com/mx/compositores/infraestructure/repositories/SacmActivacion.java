package sacm.com.mx.compositores.infraestructure.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.ActivacionDto;
import sacm.com.mx.compositores.common.dtos.ActivacionResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmActivacion {
    @SuppressWarnings("compatibility:-4232378700098733855")
    private static final long serialVersionUID = 1L;
    

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmActivacion.class);
    private static ActivacionResultDto activacionResponse;

    public SacmActivacion() {
        super();
    }


    public static ActivacionResultDto getActivacionResult(ActivacionDto activacionRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PRC_USUARIO_ACTIVA_CTA(?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, activacionRequest.getPimail());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();         
            

            // 6. Set value of dateValue property using first OUT param
            activacionResponse = new ActivacionResultDto();
            activacionResponse.getHeaderResponse().setCodErr(cstmt.getInt(2));
            activacionResponse.getHeaderResponse().setCodMsg(cstmt.getString(3));

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            activacionResponse = new ActivacionResultDto();
            activacionResponse.getHeaderResponse().setCodErr(1);
            activacionResponse.getHeaderResponse().setCodMsg(e.getMessage());
            return activacionResponse;
        }

        _logger.info("Finish getActivacionResult");
        // 9. Return the result
        return activacionResponse;
    }
    
    }
