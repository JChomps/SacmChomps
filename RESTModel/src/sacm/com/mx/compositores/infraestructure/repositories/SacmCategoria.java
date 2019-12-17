package sacm.com.mx.compositores.infraestructure.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.TreeMap;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.Tag;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TagN1;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TagN2;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TagsDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TagsResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CategoriaDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CategoriaItemDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CategoriaResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmCategoria {
    @SuppressWarnings("compatibility:5838653610898359998")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmCategoria.class);
    private static CategoriaResultDto categoriaResponse;

    public SacmCategoria() {
        super();
    }

    /*-------------------------------------------------------------- sacm_cat_tags --------------------------------------------------------------------------*/
    public static CategoriaResultDto getConsultaCat(CategoriaDto CategoriaRequest) {
        List<CategoriaDto> categoriaListResult = new ArrayList<CategoriaDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_COMPRAS.PRC_REQUEST_CATEGORIAS(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, CategoriaRequest.getId_categoria());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);
            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(2) == 0) {
                rs = (ResultSet) cstmt.getObject(4);
                List<CategoriaDto> categoriaList = new ArrayList<CategoriaDto>();
                while (rs.next()) {
                    CategoriaDto categoria = new CategoriaDto();
                    CategoriaItemDto categoriaItem = new CategoriaItemDto();
                    //Asignamiento de valores al objeto Categoría
                    categoria.setId_categoria(rs.getInt(1));
                    categoria.setDescripcion(rs.getString(2));
                    //Asignamiento de valores al objeto Categoría Item
                    categoriaItem.setId_categoria_item(rs.getInt(3));
                    categoriaItem.setEtiqueta(rs.getString(4));
                    categoriaItem.setValor(rs.getDouble(5));
                    categoriaItem.setValor_acumualdo(rs.getDouble(6));
                    categoria.getItems().add(categoriaItem);
                    categoriaList.add(categoria);
                }
                organizaList(categoriaListResult, categoriaList);
                rs.close();
            }
            // 6. Set value of dateValue property using first OUT param
            categoriaResponse = new CategoriaResultDto();
            categoriaResponse.setResponseBD(new HeaderDto());
            categoriaResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            categoriaResponse.getResponseBD().setCodMsg(cstmt.getString(3));
            categoriaResponse.setResponseService(new HeaderDto());
            categoriaResponse.getResponseService().setCodErr(cstmt.getInt(2));
            categoriaResponse.getResponseService().setCodMsg(cstmt.getString(3));
            categoriaResponse.setCategorias(categoriaListResult);
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

    private static void organizaList(List<CategoriaDto> categoriaListResult, List<CategoriaDto> categoriaList) {
        Map<Integer, CategoriaItemDto> categoriaItemMap = new TreeMap<Integer, CategoriaItemDto>();
        Map<Integer, CategoriaDto> categoriaMap = new HashMap<Integer, CategoriaDto>();
        List<CategoriaItemDto> categoriaItemList = new ArrayList<CategoriaItemDto>();

        // Creación de MAp para eliminar elementos Tag repetidos
        for (CategoriaDto str : categoriaList) {
            categoriaMap.put(str.getId_categoria(), str);
        }
        for (CategoriaDto value : categoriaMap.values()) {
            categoriaListResult.add(value);
        }

        //Organización y eliminación de elementos Tag nivel 1 repetidos
        for (CategoriaDto strTLR : categoriaListResult) {
            categoriaItemMap = new TreeMap<Integer, CategoriaItemDto>();
            categoriaItemList = new ArrayList<CategoriaItemDto>();
            for (CategoriaDto strTL : categoriaList) {
                if (strTL.getId_categoria() == strTLR.getId_categoria()) {
                    CategoriaItemDto catItem = new CategoriaItemDto();
                    catItem.setId_categoria_item(strTL.getItems()
                                                      .get(0)
                                                      .getId_categoria_item());
                    catItem.setEtiqueta(strTL.getItems()
                                             .get(0)
                                             .getEtiqueta());
                    catItem.setValor(strTL.getItems()
                                          .get(0)
                                          .getValor());
                    catItem.setValor_acumualdo(strTL.getItems()
                                                    .get(0)
                                                    .getValor_acumualdo());

                    categoriaItemMap.put(catItem.getId_categoria_item(), catItem);
                }
            }
            for (CategoriaItemDto value : categoriaItemMap.values()) {
                categoriaItemList.add(value);
            }
            strTLR.setItems(categoriaItemList);

        }


    }
}
