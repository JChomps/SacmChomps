package sacm.com.mx.compositores.infraestructure.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.NombreParticipanteDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.LicenciatarioResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.MarcasDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil.CorreoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil.CorreoResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil.GiroDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos.ProyectoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos.ProyectoResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmPerfil {
    @SuppressWarnings("compatibility:-4232378700098733855")
    private static final long serialVersionUID = 1L;


    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmActivacion.class);
    private static CorreoResultDto correoResponse;
    private static LicenciatarioResultDto licenciatarioResponse;
    private static ProyectoResultDto ProyectoResponse;

    public SacmPerfil() {
        super();
    }

    /*---------------------------------------------------------sacm_envia_correo_contacto Service----------------------------------------------------------------------*/

    public static CorreoResultDto setCorreoContacto(CorreoDto correoRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;


        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PERFIL_CLIENTE.PRC_CORREO_CONTACTO(?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, correoRequest.getCorreo());
            cstmt.setObject(2, correoRequest.getCuerpoCorreo());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();


            // 6. Set value of dateValue property using first OUT param
            correoResponse = new CorreoResultDto();
            correoResponse.setResponseBD(new HeaderDto());
            correoResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            correoResponse.getResponseBD().setCodMsg(cstmt.getString(4));

            correoResponse.setResponseService(new HeaderDto());
            correoResponse.getResponseService().setCodErr(cstmt.getInt(3));
            correoResponse.getResponseService().setCodMsg(cstmt.getString(4));

            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            correoResponse = new CorreoResultDto();
            correoResponse.setResponseService(new HeaderDto());
            correoResponse.getResponseService().setCodErr(1);
            correoResponse.getResponseService().setCodMsg(e.getMessage());
            return correoResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return correoResponse;

    }

    public static LicenciatarioResultDto RegistroLicenciatario(MarcasDto marcasRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;


        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt =
                conn.prepareCall("{call SACM_PKG_REGISTRO_USUARIO.PRC_REGISTRA_LICENCIATARIO(?,?,?,?,?,?,?,?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, marcasRequest.getId_usuario());
            cstmt.setObject(2, marcasRequest.getId_licenciatario());
            cstmt.setObject(3, marcasRequest.getId_marca());
            cstmt.setObject(4, marcasRequest.getRfc());
            cstmt.setObject(5, marcasRequest.getId_estado());
            cstmt.setObject(6, marcasRequest.getMunicipio());
            cstmt.setObject(7, marcasRequest.getDireccion());
            cstmt.setObject(8, marcasRequest.getCodigo_postal());
            cstmt.setObject(9, marcasRequest.getPuesto());


            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(10, Types.INTEGER);
            cstmt.registerOutParameter(11, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();


            // 6. Set value of dateValue property using first OUT param
            licenciatarioResponse = new LicenciatarioResultDto();
            licenciatarioResponse.setResponseBD(new HeaderDto());
            licenciatarioResponse.getResponseBD().setCodErr(cstmt.getInt(10));
            licenciatarioResponse.getResponseBD().setCodMsg(cstmt.getString(11));

            licenciatarioResponse.setResponseService(new HeaderDto());
            licenciatarioResponse.getResponseService().setCodErr(cstmt.getInt(10));
            licenciatarioResponse.getResponseService().setCodMsg(cstmt.getString(11));

            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            licenciatarioResponse = new LicenciatarioResultDto();
            licenciatarioResponse.setResponseService(new HeaderDto());
            licenciatarioResponse.getResponseService().setCodErr(1);
            licenciatarioResponse.getResponseService().setCodMsg(e.getMessage());
            return licenciatarioResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return licenciatarioResponse;

    }

    /*-----------------------------------------------------sacm_valida_compra_obra Service-------------------------------------------------------------------*/
    public static ProyectoResultDto ValidaObraCompra(ProyectoDto datoRequest) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        ProyectoResponse = new ProyectoResultDto();
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PERFIL_CLIENTE.PRC_VALIDA_OBRA_COMPRA(?,?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, datoRequest.getId_usuario());
            cstmt.setObject(2, datoRequest.getId_obra());


            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            cstmt.registerOutParameter(5, -10);

            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(3) == 0) {
                // print the results
                rs = (ResultSet) cstmt.getObject(5);

                while (rs.next()) {
                    ProyectoResponse.setId_usuario(rs.getInt(1));
                    ProyectoResponse.setId_obra(rs.getInt(2));
                    ProyectoResponse.setTitulo_obra(rs.getString(3));
                    ProyectoResponse.setId_estatus(rs.getInt(4));
                    ProyectoResponse.setEstatus(rs.getString(5));
                }
                rs.close();
            }


            // 6. Set value of dateValue property using first OUT param
            
            ProyectoResponse.setResponseBD(new HeaderDto());
            ProyectoResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            ProyectoResponse.getResponseBD().setCodMsg(cstmt.getString(4));

            ProyectoResponse.setResponseService(new HeaderDto());
            ProyectoResponse.getResponseService().setCodErr(cstmt.getInt(3));
            ProyectoResponse.getResponseService().setCodMsg(cstmt.getString(4));

            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            ProyectoResponse = new ProyectoResultDto();
            ProyectoResponse.setResponseService(new HeaderDto());
            ProyectoResponse.getResponseService().setCodErr(1);
            ProyectoResponse.getResponseService().setCodMsg(e.getMessage());
            return ProyectoResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return ProyectoResponse;
    }
}
