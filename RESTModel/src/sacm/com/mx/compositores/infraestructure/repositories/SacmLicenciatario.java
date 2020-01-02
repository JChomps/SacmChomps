package sacm.com.mx.compositores.infraestructure.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

import oracle.adf.share.logging.ADFLogger;

import oracle.mds.internal.core.IdRequest;

import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CarritoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CarritoResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.LicenciatarioResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.MarcasDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmLicenciatario {
    @SuppressWarnings("compatibility:-4232378700098733855")
    private static final long serialVersionUID = 1L;


    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmActivacion.class);
    private static LicenciatarioResultDto licResponse;
    public SacmLicenciatario() {
        super();
    }
    /*---------------------------------------------------------sacm_consulta_carrito Service----------------------------------------------------------------------*/

    public static LicenciatarioResultDto getConsultaLic(MarcasDto marcRequest) {
        List<MarcasDto> licList = new ArrayList<MarcasDto>();
        List<MarcasDto> marcaList = new ArrayList<MarcasDto>();
        CarritoResultDto carritoResponse;
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_COMPRAS.PRC_CONSULTA_LICENCIATARIOS(?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, marcRequest.getId_usuario());
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
                    MarcasDto marca = new MarcasDto();
                    
                    marca.setNombre(rs.getString(3));
                    marca.setDescripcion(rs.getString(4));
                    
                    marca.setRfc(rs.getString(5));
                    marca.setId_estado(rs.getInt(6));
                    marca.setEstado(rs.getString(7));
                    marca.setMunicipio(rs.getString(8));
                    marca.setDireccion(rs.getString(9));
                    marca.setCodigo_postal(rs.getString(10));
                    marca.setPuesto(rs.getString(11)); 
                    marca.setId_categoria(rs.getInt(12));
                    marca.setValor(rs.getDouble(13));
                   
                    marca.setPersona(rs.getString(11));
                    if(rs.getInt(1)>0){
                            marca.setId_licenciatario(rs.getInt(1));
                            licList.add(marca);
                        }
                    if(rs.getInt(2)>0){
                        marca.setId_marca(rs.getInt(2));
                        marcaList.add(marca);}
                    
                   
                }
                rs.close();
            }


            // 6. Set value of dateValue property using first OUT param
            licResponse = new LicenciatarioResultDto();
            licResponse.setResponseBD(new HeaderDto());
            licResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            licResponse.getResponseBD().setCodMsg(cstmt.getString(3));

            licResponse.setResponseService(new HeaderDto());
            licResponse.getResponseService().setCodErr(cstmt.getInt(2));
            licResponse.getResponseService().setCodMsg(cstmt.getString(3));
            licResponse.setMarcas(marcaList);
            licResponse.setLicenciatarios(licList);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            licResponse = new LicenciatarioResultDto();
            licResponse.setResponseService(new HeaderDto());
            licResponse.getResponseService().setCodErr(1);
            licResponse.getResponseService().setCodMsg(e.getMessage());
            return licResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return licResponse;
    }


    
}
