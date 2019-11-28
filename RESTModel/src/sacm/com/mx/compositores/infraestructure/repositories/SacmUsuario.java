package sacm.com.mx.compositores.infraestructure.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.EstadoResultDto;
import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.UsuarioDto;
import sacm.com.mx.compositores.common.dtos.UsuarioResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmUsuario {
    public SacmUsuario() {
        super();
    }
    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmUsuario.class);
    private static UsuarioResultDto usuarioResponse;
    private static UsuarioDto usuario;

    public static UsuarioResultDto updatePassword(UsuarioResultDto usuarioRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PRC_ACTUALIZA_PWD_USUARIO(?,?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, usuarioRequest.getUsuario().getIdUsuario());
            cstmt.setObject(2, usuarioRequest.getUsuario().getPassword());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();

            // 6. Set value of dateValue property using OUT param
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.getUsuario().setIdUsuario(new Integer(cstmt.getString(3)));
            usuarioResponse.getHeaderResponse().setErrorCode(cstmt.getInt(4));
            usuarioResponse.getHeaderResponse().setErrorMsg(cstmt.getString(5));

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.getHeaderResponse().setErrorCode(1);
            usuarioResponse.getHeaderResponse().setErrorMsg(e.getMessage());
            return usuarioResponse;
        }
        _logger.info("Finish Update Password");
        // 9. Return the result
        return usuarioResponse;
    }

    public static UsuarioResultDto changePassword(UsuarioResultDto usuarioRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PRC_CAMBIA_PWD_USUARIO(?,?,?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, usuarioRequest.getUsuario().getEmail());
            cstmt.setObject(2, usuarioRequest.getUsuario().getPassword());
            cstmt.setObject(3, usuarioRequest.getUsuario().getPasswordNuevo());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(4, Types.VARCHAR);
            cstmt.registerOutParameter(5, Types.INTEGER);
            cstmt.registerOutParameter(6, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();

            // 6. Set value of dateValue property using OUT param
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.getUsuario().setEmail(cstmt.getString(4));
            usuarioResponse.getHeaderResponse().setErrorCode(cstmt.getInt(5));
            usuarioResponse.getHeaderResponse().setErrorMsg(cstmt.getString(6));

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.getHeaderResponse().setErrorCode(1);
            usuarioResponse.getHeaderResponse().setErrorMsg(e.getMessage());
            return usuarioResponse;
        }
        _logger.info("Finish Change Password");
        // 9. Return the result
        return usuarioResponse;
    }

    public static UsuarioResultDto login(UsuarioResultDto usuarioRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PRC_USUARIO_REGISTRADO(?,?,?,?,?,?,?,?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, usuarioRequest.getUsuario().getEmail());
            cstmt.setObject(2, usuarioRequest.getUsuario().getPassword());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            cstmt.registerOutParameter(5, Types.VARCHAR);
            cstmt.registerOutParameter(6, Types.VARCHAR);
            cstmt.registerOutParameter(7, Types.VARCHAR);
            cstmt.registerOutParameter(8, Types.INTEGER);
            cstmt.registerOutParameter(9, Types.VARCHAR);
            cstmt.registerOutParameter(10, Types.INTEGER);
            cstmt.registerOutParameter(11, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();

            usuarioResponse = new UsuarioResultDto();
            usuario = new UsuarioDto();
            // 6. Set value of dateValue property using first OUT param
            usuarioResponse.getHeaderResponse().setErrorCode(cstmt.getInt(10));
            usuarioResponse.getHeaderResponse().setErrorMsg(cstmt.getString(11));
            if (cstmt.getInt(10) == 0) {
                usuario.setIdUsuario(new Integer(cstmt.getString(3)));
                usuario.setEmail(cstmt.getString(4));
                usuario.setNombre(cstmt.getString(5));
                usuario.setPaterno(cstmt.getString(6));
                usuario.setMaterno(cstmt.getString(7));
                usuario.setIdPais(new Integer(cstmt.getString(8)));
                usuario.setEstatus(cstmt.getString(9));
                usuarioResponse.setUsuario(usuario);
            }

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.getHeaderResponse().setErrorCode(1);
            usuarioResponse.getHeaderResponse().setErrorMsg(e.getMessage());
            return usuarioResponse;
        }
        _logger.info("Finish Login");
        // 9. Return the result
        return usuarioResponse;
    }

    public static UsuarioResultDto registrarUsuario(UsuarioResultDto usuarioRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PRC_USUARIO_REGISTRO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            // 3. Set the bind values of the IN parameters

            // 4. Register the positions and types of the OUT parameters
            cstmt.setObject(1, usuarioRequest.getUsuario().getNombre());
            cstmt.setObject(2, usuarioRequest.getUsuario().getPaterno());
            cstmt.setObject(3, usuarioRequest.getUsuario().getMaterno());
            cstmt.setObject(4, usuarioRequest.getUsuario().getPassword());
            cstmt.setObject(5, usuarioRequest.getUsuario().getEmail());
            cstmt.setObject(6, usuarioRequest.getUsuario().getCompania());
            cstmt.setObject(7, usuarioRequest.getUsuario().getPuesto());
            cstmt.setObject(8, usuarioRequest.getUsuario().getIdSexo());
            cstmt.setObject(9, usuarioRequest.getUsuario().getIdPais());
            cstmt.setObject(10, usuarioRequest.getUsuario().getIdEstado());
            cstmt.setObject(11, usuarioRequest.getUsuario().getMunicipio());
            cstmt.setObject(12, usuarioRequest.getUsuario().getCodigoPostal());
            cstmt.setObject(13, usuarioRequest.getUsuario().getDireccion());
            cstmt.setObject(14, usuarioRequest.getUsuario().getTelefono());
            cstmt.setObject(15, usuarioRequest.getUsuario().getExtension());
            cstmt.setObject(16, usuarioRequest.getUsuario().getEstatus());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(17, Types.INTEGER);
            cstmt.registerOutParameter(18, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();

            // 6. Set value of dateValue property using first OUT param
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.getHeaderResponse().setErrorCode(cstmt.getInt(17));
            usuarioResponse.getHeaderResponse().setErrorMsg(cstmt.getString(18));

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.getHeaderResponse().setErrorCode(1);
            usuarioResponse.getHeaderResponse().setErrorMsg(e.getMessage());
            return usuarioResponse;
        }
        _logger.info("Finish Registrar Usuario");
        // 9. Return the result
        return usuarioResponse;
    }
}
