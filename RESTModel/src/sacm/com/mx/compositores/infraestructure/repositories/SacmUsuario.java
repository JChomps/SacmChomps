package sacm.com.mx.compositores.infraestructure.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.EstadoResultDto;
import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Inicio_Sesion.UsuarioDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Inicio_Sesion.UsuarioResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmUsuario {
    public SacmUsuario() {
        super();
    }
    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmUsuario.class);
    private static UsuarioResultDto usuarioResponse;

    /**
     * @param usuarioRequest
     * @return
     */
    /*------------------------------------------------------------------- sacm_actualiza_pwd_usuario --------------------------------------------------------------------------*/
    public static UsuarioResultDto updatePassword(UsuarioDto usuarioRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_INICIO_SESION.PRC_ACTUALIZA_PWD_USUARIO(?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, usuarioRequest.getId_usuario());
            cstmt.setObject(2, usuarioRequest.getPassword());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();
            // 6. Set value of dateValue property using OUT param
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.setUpdatePWD(new UsuarioDto());
            usuarioResponse.getUpdatePWD().setID_USUARIO(new Integer(cstmt.getString(3)));
            usuarioResponse.setResponseBD(new HeaderDto());
            usuarioResponse.getResponseBD().setCodErr(cstmt.getInt(4));
            usuarioResponse.getResponseBD().setCodMsg(cstmt.getString(5));

            usuarioResponse.setResponseService(new HeaderDto());
            usuarioResponse.getResponseService().setCodErr(cstmt.getInt(4));
            usuarioResponse.getResponseService().setCodMsg(cstmt.getString(5));

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.setResponseService(new HeaderDto());
            usuarioResponse.getResponseService().setCodErr(1);
            usuarioResponse.getResponseService().setCodMsg(e.getMessage());
            return usuarioResponse;
        }
        _logger.info("Finish Update Password");
        // 9. Return the result
        return usuarioResponse;
    }

    /**
     * @param usuarioRequest
     * @return
     */
    /*------------------------------------------------------------- sacm_cambia_pwd_usuario -------------------------------------------------------------------*/
    public static UsuarioResultDto changePassword(UsuarioDto usuarioRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call PRC_USUARIO_REGISTRO.PRC_CAMBIA_PWD_USUARIO(?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, usuarioRequest.getEmail());
            cstmt.setObject(2, usuarioRequest.getPassword());
            cstmt.setObject(3, usuarioRequest.getPasswordNuevo());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(4, Types.VARCHAR);
            cstmt.registerOutParameter(5, Types.INTEGER);
            cstmt.registerOutParameter(6, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();

            // 6. Set value of dateValue property using OUT param
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.getLoginUser().setEmail(cstmt.getString(4));
            usuarioResponse.setResponseBD(new HeaderDto());
            usuarioResponse.getResponseBD().setCodErr(cstmt.getInt(5));
            usuarioResponse.getResponseBD().setCodMsg(cstmt.getString(6));

            usuarioResponse.setResponseService(new HeaderDto());
            usuarioResponse.getResponseService().setCodErr(cstmt.getInt(5));
            usuarioResponse.getResponseService().setCodMsg(cstmt.getString(6));

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.setResponseService(new HeaderDto());
            usuarioResponse.getResponseService().setCodErr(1);
            usuarioResponse.getResponseService().setCodMsg(e.getMessage());
            return usuarioResponse;
        }
        _logger.info("Finish Change Password");
        // 9. Return the result
        return usuarioResponse;
    }

    /**
     * @param usuarioRequest
     * @return
     */
    public static UsuarioResultDto restaurarPassword(UsuarioDto usuarioRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_INICIO_SESION.PRC_ENVIA_CORREO_REST_PWD(?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, usuarioRequest.getEmail());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();

            // 6. Set value of dateValue property using OUT param
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.setSendEmail(new UsuarioDto());
            usuarioResponse.getSendEmail().setID_USUARIO(new Integer(cstmt.getString(2)));
            usuarioResponse.setResponseBD(new HeaderDto());
            usuarioResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            usuarioResponse.getResponseBD().setCodMsg(cstmt.getString(4));

            usuarioResponse.setResponseService(new HeaderDto());
            usuarioResponse.getResponseService().setCodErr(cstmt.getInt(3));
            usuarioResponse.getResponseService().setCodMsg(cstmt.getString(4));

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.setResponseService(new HeaderDto());
            usuarioResponse.getResponseService().setCodErr(1);
            usuarioResponse.getResponseService().setCodMsg(e.getMessage());
            return usuarioResponse;
        }
        _logger.info("Finish Restaurar Password");
        // 9. Return the result
        return usuarioResponse;
    }

    /**
     * @param usuarioRequest
     * @return
     */
    public static UsuarioResultDto login(UsuarioDto usuarioRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_INICIO_SESION.PRC_USUARIO_REGISTRADO(?,?,?,?,?,?,?,?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, usuarioRequest.getEmail());
            cstmt.setObject(2, usuarioRequest.getPassword());

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
            UsuarioDto usuario = new UsuarioDto();
            // 6. Set value of dateValue property using first OUT param
            usuarioResponse.setResponseBD(new HeaderDto());
            usuarioResponse.getResponseBD().setCodErr(cstmt.getInt(10));
            usuarioResponse.getResponseBD().setCodMsg(cstmt.getString(11));

            usuarioResponse.setResponseService(new HeaderDto());
            usuarioResponse.getResponseService().setCodErr(cstmt.getInt(10));
            usuarioResponse.getResponseService().setCodMsg(cstmt.getString(11));
            if (cstmt.getInt(10) == 0) {
                usuario.setId_usuario(new Integer(cstmt.getString(3)));
                usuario.setEmail(cstmt.getString(4));
                usuario.setNombre(cstmt.getString(5));
                usuario.setApellido_paterno(cstmt.getString(6));
                usuario.setApellido_materno(cstmt.getString(7));
                usuario.setId_pais(new Integer(cstmt.getString(8)));
                usuario.setEstatus(cstmt.getString(9));
                usuarioResponse.setLoginUser(usuario);
            }

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.setResponseService(new HeaderDto());
            usuarioResponse.getResponseService().setCodErr(1);
            usuarioResponse.getResponseService().setCodMsg(e.getMessage());
            return usuarioResponse;
        }
        _logger.info("Finish Login");
        // 9. Return the result
        return usuarioResponse;
    }

    /**
     * @param LoginUser
     * @return
     */
    public static UsuarioResultDto registrarUsuario(UsuarioDto LoginUser) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_REGISTRO_USUARIO.PRC_USUARIO_REGISTRO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            // 3. Set the bind values of the IN parameters

            // 4. Register the positions and types of the OUT parameters
            cstmt.setObject(1, LoginUser.getNombre());
            cstmt.setObject(2, LoginUser.getApellido_paterno());
            cstmt.setObject(3, LoginUser.getApellido_materno());
            cstmt.setObject(4, LoginUser.getPassword());
            cstmt.setObject(5, LoginUser.getEmail());
            cstmt.setObject(6, LoginUser.getCompania());
            cstmt.setObject(7, LoginUser.getPuesto());
            cstmt.setObject(8, LoginUser.getId_sexo());
            cstmt.setObject(9, LoginUser.getId_pais());
            cstmt.setObject(10, LoginUser.getId_estado());
            cstmt.setObject(11, LoginUser.getMunicipio());
            cstmt.setObject(12, LoginUser.getCodigo_postal());
            cstmt.setObject(13, LoginUser.getDireccion());
            cstmt.setObject(14, LoginUser.getTelefono());
            cstmt.setObject(15, LoginUser.getExtension());
            cstmt.setObject(16, LoginUser.getEstatus());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(17, Types.INTEGER);
            cstmt.registerOutParameter(18, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();

            // 6. Set value of dateValue property using first OUT param
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.setResponseBD(new HeaderDto());
            usuarioResponse.getResponseBD().setCodErr(cstmt.getInt(17));
            usuarioResponse.getResponseBD().setCodMsg(cstmt.getString(18));

            usuarioResponse.setResponseService(new HeaderDto());
            usuarioResponse.getResponseService().setCodErr(cstmt.getInt(17));
            usuarioResponse.getResponseService().setCodMsg(cstmt.getString(18));

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.setResponseService(new HeaderDto());
            usuarioResponse.getResponseService().setCodErr(1);
            usuarioResponse.getResponseService().setCodMsg(e.getMessage());
            return usuarioResponse;
        }
        _logger.info("Finish Registrar Usuario");
        // 9. Return the result
        return usuarioResponse;
    }
}
