package sacm.com.mx.compositores.infraestructure.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.PalabraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.PalabraIdObra;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CarritoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CarritoResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmCarrito {
    @SuppressWarnings("compatibility:-4232378700098733855")
    private static final long serialVersionUID = 1L;


    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmActivacion.class);
    private static PalabraIdObra agregarResponse;

    public SacmCarrito() {
        super();

    }
    /*---------------------------------------------------------sacm_agrega_carrito Service----------------------------------------------------------------------*/

    public static PalabraIdObra getElimina(CarritoDto carritoRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_COMPRAS.PRC_CARRITO_ELIMINA(?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, carritoRequest.getId_carrito_detalle());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();


            // 6. Set value of dateValue property using first OUT param
            agregarResponse = new PalabraIdObra();
            agregarResponse.setResponseBD(new HeaderDto());
            agregarResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            agregarResponse.getResponseBD().setCodMsg(cstmt.getString(3));

            agregarResponse.setResponseService(new HeaderDto());
            agregarResponse.getResponseService().setCodErr(cstmt.getInt(2));
            agregarResponse.getResponseService().setCodMsg(cstmt.getString(3));
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            agregarResponse = new PalabraIdObra();
            agregarResponse.setResponseService(new HeaderDto());
            agregarResponse.getResponseService().setCodErr(1);
            agregarResponse.getResponseService().setCodMsg(e.getMessage());
            return agregarResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return agregarResponse;
    }
    /*---------------------------------------------------------sacm_agregar_carrito Service----------------------------------------------------------------------*/

    public static PalabraIdObra getAgregar(PalabraDto IdRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_COMPRAS.PRC_CARRITO_AGREGA(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, IdRequest.getId_usuario());
            cstmt.setObject(2, IdRequest.getId_obra());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();


            // 6. Set value of dateValue property using first OUT param
            agregarResponse = new PalabraIdObra();
            agregarResponse.setResponseBD(new HeaderDto());
            agregarResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            agregarResponse.getResponseBD().setCodMsg(cstmt.getString(4));

            agregarResponse.setResponseService(new HeaderDto());
            agregarResponse.getResponseService().setCodErr(cstmt.getInt(3));
            agregarResponse.getResponseService().setCodMsg(cstmt.getString(4));
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            agregarResponse = new PalabraIdObra();
            agregarResponse.setResponseService(new HeaderDto());
            agregarResponse.getResponseService().setCodErr(1);
            agregarResponse.getResponseService().setCodMsg(e.getMessage());
            return agregarResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return agregarResponse;
    }
    /*---------------------------------------------------------sacm_consulta_carrito Service----------------------------------------------------------------------*/

    public static CarritoResultDto getConsulta(PalabraDto IdRequest) {
                List<CarritoDto> carritoList = new ArrayList<CarritoDto>();
        CarritoResultDto carritoResponse;
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_COMPRAS.PRC_CARRITO_CONSULTA(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, IdRequest.getId_usuario());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);
            // 5. Execute the statement
            cstmt.executeUpdate();

            if (cstmt.getInt(2) == 0) {
                // read the results
                rs = (ResultSet) cstmt.getObject(4);
                while (rs.next()) {
                    CarritoDto carrito = new CarritoDto();
                    carrito.setId_usuario(rs.getInt(1));
                    carrito.setId_carrito(rs.getInt(2));
                    carrito.setId_carrito_detalle(rs.getInt(3));
                    carrito.setId_obra(rs.getInt(4));
                    carrito.setNumero_obra(rs.getInt(5));
                    carrito.setTitulo_obra(rs.getString(6));
                    carritoList.add(carrito);
                }
                rs.close();
            }


            // 6. Set value of dateValue property using first OUT param
            carritoResponse = new CarritoResultDto();
            carritoResponse.setResponseBD(new HeaderDto());
            carritoResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            carritoResponse.getResponseBD().setCodMsg(cstmt.getString(3));

            carritoResponse.setResponseService(new HeaderDto());
            carritoResponse.getResponseService().setCodErr(cstmt.getInt(2));
            carritoResponse.getResponseService().setCodMsg(cstmt.getString(3));
            carritoResponse.setCarrito(carritoList);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            carritoResponse = new CarritoResultDto();
            carritoResponse.setResponseService(new HeaderDto());
            carritoResponse.getResponseService().setCodErr(1);
            carritoResponse.getResponseService().setCodMsg(e.getMessage());
            return carritoResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return carritoResponse;
    }
}
