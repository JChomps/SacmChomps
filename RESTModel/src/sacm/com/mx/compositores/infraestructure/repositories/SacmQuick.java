package sacm.com.mx.compositores.infraestructure.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.QuickResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TagsDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;


public class SacmQuick {
    public SacmQuick() {
        super();
    }
    @SuppressWarnings("compatibility:5838653610898359998")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmQuick.class);
    private static QuickResultDto quickResponse;
    
    /*-------------------------------------------------------------- sacm_QuickS --------------------------------------------------------------------------*/
    public static QuickResultDto getQuickSerach() {
        List<TagsDto> quickListResult = new ArrayList<TagsDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;        
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_BUSCADOR.PRC_CONSULTA_QUICKSEARCH(?,?,?)}");
            // 3. Set the bind values of the IN parameters
            
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.registerOutParameter(2, Types.VARCHAR);
            cstmt.registerOutParameter(3, -10);
            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(1) == 0) {
                rs = (ResultSet) cstmt.getObject(3);
               
                while (rs.next()) {
                    TagsDto tag = new TagsDto();
                    //Asignamiento de valores al objeto Tag
                    tag.setTagName(rs.getString(1));
                    tag.setIdTag(rs.getInt(2));                    
                   
                    quickListResult.add(tag);
                }                
             
                rs.close();
            }
            // 6. Set value of dateValue property using first OUT param
            quickResponse = new QuickResultDto();
            quickResponse.setResponseBD(new HeaderDto());
            quickResponse.getResponseBD().setCodErr(cstmt.getInt(1));
            quickResponse.getResponseBD().setCodMsg(cstmt.getString(2));
            quickResponse.setResponseService(new HeaderDto());
            quickResponse.getResponseService().setCodErr(cstmt.getInt(1));
            quickResponse.getResponseService().setCodMsg(cstmt.getString(2));
            quickResponse.setQuick(quickListResult);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            quickResponse = new QuickResultDto();
            quickResponse.setResponseService(new HeaderDto());
            quickResponse.getResponseService().setCodErr(1);
            quickResponse.getResponseService().setCodMsg(e.getMessage());
            return quickResponse;
        }
        _logger.info("Finish getQuick");
        // 9. Return the result
        return quickResponse;
    }

}
