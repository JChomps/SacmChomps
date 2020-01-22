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

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.EstadoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.EstadoResultDto;
import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.MetadataDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.MetadataResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmEstado implements Serializable {
    @SuppressWarnings("compatibility:-4232378700098733855")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmEstado.class);
    private static EstadoResultDto estadoResponse;

    public SacmEstado() {
        super();
    }
    /*-----------------------------------------------------sacm_cat_estado -----------------------------------------------------------------------*/
    public static EstadoResultDto getEstadosByPais(EstadoDto estadoRequest) {
        List<EstadoDto> estados = new ArrayList<EstadoDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_REGISTRO_USUARIO.PRC_CONSULTA_ESTADO(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, estadoRequest.getId_pais());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);     
            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(2) == 0) {
                // print the results
                rs = (ResultSet) cstmt.getObject(4);
                while (rs.next()) {
                    EstadoDto estado = new EstadoDto();
                    estado.setId_pais(rs.getInt(1));
                    estado.setPais(rs.getString(2));
                    estado.setId_estado(rs.getInt(3));
                    estado.setEstado(rs.getString(4));
                    estados.add(estado);
                }
                rs.close();
            }
            // 6. Set value of dateValue property using first OUT param
            estadoResponse = new EstadoResultDto();
            estadoResponse.setResponseBD(new HeaderDto());
            estadoResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            estadoResponse.getResponseBD().setCodMsg(cstmt.getString(3));
            estadoResponse.setEstados(estados);

            estadoResponse.setResponseService(new HeaderDto());
            estadoResponse.getResponseService().setCodErr(cstmt.getInt(2));
            estadoResponse.getResponseService().setCodMsg(cstmt.getString(3));
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            estadoResponse = new EstadoResultDto();
            estadoResponse.setResponseService(new HeaderDto());
            estadoResponse.getResponseService().setCodErr(1);
            estadoResponse.getResponseService().setCodMsg(e.getMessage());
            return estadoResponse;
        }

        _logger.info("Finish getEstados");
        // 9. Return the result
        return estadoResponse;
    }
}
