package sacm.com.mx.compositores.infraestructure.repositories;

import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.BeneficioDto;
import sacm.com.mx.compositores.common.dtos.BeneficioResultDto;
import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.ObraResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmBeneficio implements Serializable {
    @SuppressWarnings("compatibility:-2008983703424230048")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmEstado.class);

    private static BeneficioResultDto beneficioResponse;
    
    public SacmBeneficio() {
        super();
    }
 /*--------------------------------------------------------- sacm_beneficios ----------------------------------------------------------------------*/

    public static BeneficioResultDto getBeneficios(BeneficioDto beneficioRequest) {
        List<BeneficioDto> beneficioList = new ArrayList<BeneficioDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;       

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_BUSCADOR.PRC_CONSULTA_BENEFICIOS(?,?,?)}");
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.registerOutParameter(2, Types.VARCHAR);
            cstmt.registerOutParameter(3, -10);
            // 5. Execute the statement
            cstmt.executeUpdate();
            if(cstmt.getInt(1)==0)
            rs = (ResultSet) cstmt.getObject(3);
            // read the results
            while (rs.next()) {
                BeneficioDto beneficio= new BeneficioDto();
                beneficio.setIdBeneficio(rs.getInt(1));
                beneficio.setTitulo(rs.getString(2));
                beneficio.setDescripcion(rs.getString(3));                
                beneficioList.add(beneficio);
            }

            beneficioResponse = new BeneficioResultDto();
            // 6. Set value of dateValue property using OUT params
            beneficioResponse.setResponseBD(new HeaderDto());
            beneficioResponse.getResponseBD().setCodErr(cstmt.getInt(1));
            beneficioResponse.getResponseBD().setCodMsg(cstmt.getString(2));
            beneficioResponse.setResponseService(new HeaderDto());
            beneficioResponse.getResponseService().setCodErr(cstmt.getInt(1));
            beneficioResponse.getResponseService().setCodMsg(cstmt.getString(2));            
            beneficioResponse.setBeneficioList(beneficioList);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            rs.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            beneficioResponse = new BeneficioResultDto();
            beneficioResponse.setResponseService(new HeaderDto());
            beneficioResponse.getResponseService().setCodErr(1);
            beneficioResponse.getResponseService().setCodMsg(e.getMessage());
            return beneficioResponse;
        }
        _logger.info("Finish getBeneficios");
        // 9. Return the result
        return beneficioResponse;
    }
}
