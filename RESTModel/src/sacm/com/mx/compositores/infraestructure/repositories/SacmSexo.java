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

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.EstadoResultDto;
import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.SexoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.SexoResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmSexo implements Serializable {
    @SuppressWarnings("compatibility:3214242050595178386")
    private static final long serialVersionUID = 1L;
    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmEstado.class);
    private static SexoResultDto sexoResponse;

    public SacmSexo() {
        super();
    }

    /*-----------------------------------------------------sacm_cat_sexo Service-------------------------------------------------------------------*/
    public static SexoResultDto getSexoByIdSexo(SexoDto sexoRequest) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_REGISTRO_USUARIO.PRC_CONSULTA_SEXO(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, sexoRequest.getId_sexo());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);
            // 5. Execute the statement
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(4);
            // print the results
            List<SexoDto> SexoList = new ArrayList<SexoDto>();
            while (rs.next()) {
                SexoDto sexo = new SexoDto();
                sexo.setId_sexo(rs.getInt(1));
                sexo.setIdentificador(rs.getString(2));
                sexo.setDescripcion(rs.getString(3));
                SexoList.add(sexo);
            }
            // 6. Set value of dateValue property using first OUT param
            sexoResponse = new SexoResultDto();
            sexoResponse.setResponseBD(new HeaderDto());
            sexoResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            sexoResponse.getResponseBD().setCodMsg(cstmt.getString(3));
            sexoResponse.setSexos(SexoList);
            sexoResponse.setResponseService(new HeaderDto());
            sexoResponse.getResponseService().setCodErr(cstmt.getInt(2));
            sexoResponse.getResponseService().setCodMsg(cstmt.getString(3));
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            rs.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            sexoResponse = new SexoResultDto();
            sexoResponse.setResponseService(new HeaderDto());
            sexoResponse.getResponseService().setCodErr(1);
            sexoResponse.getResponseService().setCodMsg(e.getMessage());
            return sexoResponse;
        }
        _logger.info("Finish getEstados");
        // 9. Return the result
        return sexoResponse;
    }
}
