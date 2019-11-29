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

import sacm.com.mx.compositores.common.dtos.EstadoResultDto;
import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.PaisDto;
import sacm.com.mx.compositores.common.dtos.PaisResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmPais implements Serializable {
    @SuppressWarnings("compatibility:-2008983703424230048")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmEstado.class);

    private static PaisResultDto paisResponse;

    public SacmPais() {
        super();
    }

    public static PaisResultDto getPaisesByIdPais(PaisDto paisRequest) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PRC_CONSULTA_PAIS(?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, paisRequest.getId_pais());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);

            // 5. Execute the statement
            cstmt.executeUpdate();

            rs = (ResultSet) cstmt.getObject(4);
            // print the results
            List<PaisDto> paisList = new ArrayList<PaisDto>();
            while (rs.next()) {
                PaisDto pais = new PaisDto();
                pais.setId_pais(rs.getInt(1));
                pais.setDescripcion(rs.getString(2));
                pais.setIsocode2(rs.getString(3));
                pais.setIsocode3(rs.getString(4));
                pais.setCapital(rs.getString(5));
                pais.setCodigo_tel(rs.getString(6));
                //pais.setActivo(rs.getInt(7));
                //pais.setCreadoPor(rs.getString(8));
                //pais.setFechaCreacion(rs.getDate(9));
                //pais.setModificadoPor(rs.getString(10));
                //pais.setFechaModificacion(rs.getDate(11));
                paisList.add(pais);
            }

            paisResponse = new PaisResultDto();
            // 6. Set value of dateValue property using first OUT param
            paisResponse.setResponseBD(new HeaderDto());
            paisResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            paisResponse.getResponseBD().setCodMsg(cstmt.getString(3));
            paisResponse.setPaises(paisList);

            paisResponse.setResponseService(new HeaderDto());
            paisResponse.getResponseService().setCodErr(cstmt.getInt(2));
            paisResponse.getResponseService().setCodMsg(cstmt.getString(3));

            cstmt.close();
            rs.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            paisResponse = new PaisResultDto();
            paisResponse.setResponseService(new HeaderDto());
            paisResponse.getResponseService().setCodErr(1);
            paisResponse.getResponseService().setCodMsg(e.getMessage());
            return paisResponse;
        }
        _logger.info("Finish getPaises");
        // 9. Return the result
        return paisResponse;
    }
}
