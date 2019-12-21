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
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.PalabraDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmConsola {

    @SuppressWarnings("compatibility:3396088568485404005")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmEstado.class);
    private static ObraResultDto obraResponse;


    public SacmConsola() {
        super();
    }

    /*------------------------------------------------------------- sacm_consulta_obra_consola Service ------------------------------------------------------------------------------*/
    public static ObraResultDto getObra(ObraDto obraRequest) {
        List<ObraDto> obraList = new ArrayList<ObraDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
       
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_BUSCADOR.PRC_CONSULTA_OBRA_PALABRA(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, obraRequest.getObra_numero());
            cstmt.setObject(2, obraRequest.getObra_titulo());
            
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(13, -10);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(12, Types.VARCHAR);
            
            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(11) == 0) {
                // print the results
                rs = (ResultSet) cstmt.getObject(13);
                byte[] bdata;
                while (rs.next()) {
                    ObraDto obra = new ObraDto();
                    obra.setId_obra(rs.getInt(1));
                    obra.setObra_numero(rs.getInt(2));
                    obra.setObra_id_album(rs.getInt(3));
                    //Conversión de BLOB a Base64
                    bdata = (rs.getObject(4) == null ? null : rs.getBlob(4).getBytes(1, (int) rs.getBlob(4).length()));
                    obra.set_Imagen(rs.getObject(4) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    obra.setObra_titulo(rs.getString(5));
                    obra.setObra_descripcion(rs.getString(6));
                    obra.setVersion_duracion(rs.getString(7));
                    bdata = (rs.getObject(8) == null ? null : rs.getBlob(8).getBytes(1, (int) rs.getBlob(8).length()));
                    obra.setVersion_wav(rs.getObject(8) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    bdata = (rs.getObject(9) == null ? null : rs.getBlob(9).getBytes(1, (int) rs.getBlob(9).length()));
                    obra.setVersion_mp3(rs.getObject(9) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    bdata =
                        (rs.getObject(10) == null ? null : rs.getBlob(10).getBytes(1, (int) rs.getBlob(10).length()));
                    obra.setVersion_aiff(rs.getObject(10) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    obra.setVersion_lyric(rs.getObject(11) == null ? null : rs.getString(11));
                    obraList.add(obra);
                }
                rs.close();
            }

            // 6. Set value of dateValue property using first OUT param
            obraResponse = new ObraResultDto();
            obraResponse.setResponseBD(new HeaderDto());
            obraResponse.getResponseBD().setCodErr(cstmt.getInt(11));
            obraResponse.getResponseBD().setCodMsg(cstmt.getString(12));
            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(cstmt.getInt(11));
            obraResponse.getResponseService().setCodMsg(cstmt.getString(12));
            obraResponse.setObras(obraList);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;
        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            obraResponse = new ObraResultDto();
            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(1);
            obraResponse.getResponseService().setCodMsg(e.getMessage());
            return obraResponse;
        }
        _logger.info("Finish getConsultaObra");
        // 9. Return the result
        obraResponse.setObras(obraList);
        return obraResponse;
    }

}
