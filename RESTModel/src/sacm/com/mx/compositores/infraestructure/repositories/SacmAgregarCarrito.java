package sacm.com.mx.compositores.infraestructure.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.PalabraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.PalabraIdObra;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.ActivacionDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.ActivacionResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmAgregarCarrito {
    @SuppressWarnings("compatibility:-4232378700098733855")
    private static final long serialVersionUID = 1L;
    

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmActivacion.class);
    private static PalabraIdObra agregarResponse;
    public SacmAgregarCarrito() {
        super();
        
    }
    /*---------------------------------------------------------sacm_agrega_carrito Service----------------------------------------------------------------------*/

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
       
       }
