package sacm.com.mx.compositores.infraestructure.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil.GiroDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil.GiroResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.BeneficioDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.BeneficioResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmGiro {
    @SuppressWarnings("compatibility:-2008983703424230048")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmGiro.class);

    private static GiroResultDto giroResponse;
    public SacmGiro() {
        super();
    }

    /*--------------------------------------------------------- sacm_beneficios ----------------------------------------------------------------------*/

       public static GiroResultDto getGiro() {
           List<GiroDto> giroList = new ArrayList<GiroDto>();
           CallableStatement cstmt = null;
           ResultSet rs = null;
           Connection conn = null;       

           try {
               conn = AppModule.getDbConexionJDBC();
               // 2. Define the PL/SQL block for the statement to invoke
               cstmt = conn.prepareCall("{call SACM_PKG_REGISTRO_USUARIO.PRC_CONSULTA_GIRO(?,?,?)}");
               // 4. Register the positions and types of the OUT parameters
               cstmt.registerOutParameter(1, Types.INTEGER);
               cstmt.registerOutParameter(2, Types.VARCHAR);
               cstmt.registerOutParameter(3, -10);
               // 5. Execute the statement
               cstmt.executeUpdate();
               if(cstmt.getInt(1)==0){
               rs = (ResultSet) cstmt.getObject(3);
               // read the results
               while (rs.next()) {
                   GiroDto giro= new GiroDto();
                   giro.setId_giro(rs.getInt(1));
                   giro.setDescripcion(rs.getString(2));
                               
                   giroList.add(giro);
               }
               
               rs.close();}

               giroResponse = new GiroResultDto();
               // 6. Set value of dateValue property using OUT params
               giroResponse.setResponseBD(new HeaderDto());
               giroResponse.getResponseBD().setCodErr(cstmt.getInt(1));
               giroResponse.getResponseBD().setCodMsg(cstmt.getString(2));
               giroResponse.setResponseService(new HeaderDto());
               giroResponse.getResponseService().setCodErr(cstmt.getInt(1));
               giroResponse.getResponseService().setCodMsg(cstmt.getString(2));            
               giroResponse.setGiros(giroList);
               // 9. Close the JDBC CallableStatement
               cstmt.close();
              
               conn.close();
               conn = null;

           } catch (Exception e) {
               // a failure occurred log message;
               _logger.severe(e.getMessage());
               giroResponse = new GiroResultDto();
               giroResponse.setResponseService(new HeaderDto());
               giroResponse.getResponseService().setCodErr(1);
               giroResponse.getResponseService().setCodMsg(e.getMessage());
               return giroResponse;
           }
           _logger.info("Finish getBeneficios");
           // 9. Return the result
           return giroResponse;
       }
}
