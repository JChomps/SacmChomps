package sacm.com.mx.compositores.infraestructure.repositories;

import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.EstadoDto;
import sacm.com.mx.compositores.common.dtos.EstadoResultDto;
import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.MetadataDto;
import sacm.com.mx.compositores.common.dtos.MetadataResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmEstado implements Serializable {
    @SuppressWarnings("compatibility:-4232378700098733855")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmEstado.class);
    private static EstadoResultDto estadoResponse;

    public SacmEstado() {
        super();
    }

    public static EstadoResultDto getEstadosByPais(EstadoResultDto estadoRequest) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PRC_CONSULTA_ESTADO(?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, estadoRequest.getEstado().getIdPais());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);

            // 5. Execute the statement
            cstmt.executeUpdate();

            // print the results
            rs = (ResultSet) cstmt.getObject(4);
            List<EstadoDto> estadoList = new ArrayList<EstadoDto>();
            while (rs.next()) {
                EstadoDto estado = new EstadoDto();
                estado.setIdPais(rs.getInt(1));
                estado.setPais(rs.getString(2));
                estado.setIdEstado(rs.getInt(3));
                estado.setEstado(rs.getString(4));
                estadoList.add(estado);
            }

            // 6. Set value of dateValue property using first OUT param
            estadoResponse = new EstadoResultDto();
            estadoResponse.getHeaderResponse().setErrorCode(cstmt.getInt(2));
            estadoResponse.getHeaderResponse().setErrorMsg(cstmt.getString(3));
            estadoResponse.getEstado().setIdPais(estadoRequest.getEstado().getIdPais());
            estadoResponse.setEstadoList(estadoList);

            cstmt.close();
            rs.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            estadoResponse = new EstadoResultDto();
            estadoResponse.getHeaderResponse().setErrorCode(1);
            estadoResponse.getHeaderResponse().setErrorMsg(e.getMessage());
            return estadoResponse;
        }

        _logger.info("Finish getEstados");
        // 9. Return the result
        return estadoResponse;
    }
}
