package sacm.com.mx.compositores.infraestructure.repositories.Sacm_pkg_Consola;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CategoriaDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CategoriaItemDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CategoriaResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.ActivacionResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.EstadoResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmActivacion;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmCategoria {
    
    @SuppressWarnings("compatibility:-4232378700098733855")
    private static final long serialVersionUID = 1L;
    

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmActivacion.class);
    private static CategoriaResultDto categoriaResponse;
    
    public SacmCategoria() {
        super();
    }
    
    /*-----------------------------------------------------sacm_consulta_items_categoria -----------------------------------------------------------------------*/
    public static CategoriaResultDto ConsultaItems(CategoriaDto categoriaRequest) {
        List<CategoriaItemDto> categorias = new ArrayList<CategoriaItemDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.LOV_ITEMS_CATEGORIA(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, categoriaRequest.getId_categoria());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, -10);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(3) == 0) {
                // print the results
                rs = (ResultSet) cstmt.getObject(2);
                while (rs.next()) {
                    CategoriaItemDto categoria = new CategoriaItemDto();
                    categoria.setId_categoria_item(rs.getInt(1));
                    categoria.setEtiqueta(rs.getString(2));
                    categoria.setValor(rs.getDouble(3));
                    if(rs.getDouble(4)>=0)
                    categoria.setValor_acumualdo(rs.getDouble(4));
                    categorias.add(categoria);
                }
                rs.close();
            }
            // 6. Set value of dateValue property using first OUT param
            categoriaResponse = new CategoriaResultDto();
            categoriaResponse.setResponseBD(new HeaderDto());
            categoriaResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            categoriaResponse.getResponseBD().setCodMsg(cstmt.getString(4));
            categoriaResponse.setItems(categorias);

            categoriaResponse.setResponseService(new HeaderDto());
            categoriaResponse.getResponseService().setCodErr(cstmt.getInt(3));
            categoriaResponse.getResponseService().setCodMsg(cstmt.getString(4));
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            categoriaResponse = new CategoriaResultDto();
            categoriaResponse.setResponseService(new HeaderDto());
            categoriaResponse.getResponseService().setCodErr(1);
            categoriaResponse.getResponseService().setCodMsg(e.getMessage());
            return categoriaResponse;
        }

        _logger.info("Finish getEstados");
        // 9. Return the result
        return categoriaResponse;
    }

    /*-----------------------------------------------------sacm_consulta_categoria_padre -----------------------------------------------------------------------*/
     public static CategoriaResultDto ConsultaCategorias() {
        List<CategoriaDto> categorias = new ArrayList<CategoriaDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.LOV_CATEGORIAS(?,?,?)}");
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(1, -10);
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(2) == 0) {
                // print the results
                rs = (ResultSet) cstmt.getObject(1);
                while (rs.next()) {
                    CategoriaDto categoria = new CategoriaDto();
                    categoria.setId_categoria(rs.getInt(1));
                    categoria.setDescripcion(rs.getString(2));
                    categorias.add(categoria);
                }
                rs.close();
            }
            // 6. Set value of dateValue property using first OUT param
            categoriaResponse = new CategoriaResultDto();
            categoriaResponse.setResponseBD(new HeaderDto());
            categoriaResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            categoriaResponse.getResponseBD().setCodMsg(cstmt.getString(3));
            categoriaResponse.setCategorias(categorias);

            categoriaResponse.setResponseService(new HeaderDto());
            categoriaResponse.getResponseService().setCodErr(cstmt.getInt(2));
            categoriaResponse.getResponseService().setCodMsg(cstmt.getString(3));
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            categoriaResponse = new CategoriaResultDto();
            categoriaResponse.setResponseService(new HeaderDto());
            categoriaResponse.getResponseService().setCodErr(1);
            categoriaResponse.getResponseService().setCodMsg(e.getMessage());
            return categoriaResponse;
        }

        _logger.info("Finish getEstados");
        // 9. Return the result
        return categoriaResponse;
    }

    /*-----------------------------------------------------sacm_actualiza_item_categoria Service-------------------------------------------------------------------*/
    
    public static CategoriaResultDto ActualizaItemCategoria(CategoriaItemDto itemRequest) {
       
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.ACTUALIZA_ITEM_CATEGORIA(?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, itemRequest.getId_categoria_item());
            cstmt.setObject(2, itemRequest.getValor());
            cstmt.setObject(3, itemRequest.getValor_acumualdo());
            // 4. Register the positions and types of the OUT parameters
           
            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();
           
            // 6. Set value of dateValue property using first OUT param
            categoriaResponse = new CategoriaResultDto();
            categoriaResponse.setResponseBD(new HeaderDto());
            categoriaResponse.getResponseBD().setCodErr(cstmt.getInt(4));
            categoriaResponse.getResponseBD().setCodMsg(cstmt.getString(5));
           

            categoriaResponse.setResponseService(new HeaderDto());
            categoriaResponse.getResponseService().setCodErr(cstmt.getInt(4));
            categoriaResponse.getResponseService().setCodMsg(cstmt.getString(5));
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            categoriaResponse = new CategoriaResultDto();
            categoriaResponse.setResponseService(new HeaderDto());
            categoriaResponse.getResponseService().setCodErr(1);
            categoriaResponse.getResponseService().setCodMsg(e.getMessage());
            return categoriaResponse;
        }

        _logger.info("Finish getEstados");
        // 9. Return the result
        return categoriaResponse;
    }
}
