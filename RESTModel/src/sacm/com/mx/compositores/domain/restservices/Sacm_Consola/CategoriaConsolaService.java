package sacm.com.mx.compositores.domain.restservices.Sacm_Consola;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CategoriaDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CategoriaItemDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CategoriaResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmConsola;
import sacm.com.mx.compositores.infraestructure.repositories.Sacm_pkg_Consola.SacmCategoria;


@Path("/categoriaConsola")
public class CategoriaConsolaService {
    public CategoriaConsolaService() {
        super();
    }
    
    /*-----------------------------------------------------sacm_consulta_items_categoria Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_items_categoria")
    public CategoriaResultDto ConsultaItems(CategoriaDto categoriaRequest) {
        return SacmCategoria.ConsultaItems(categoriaRequest);
    }
    /*-----------------------------------------------------sacm_consulta_categoria_padre Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_categoria_padre")
    public CategoriaResultDto ConsultaCategorias() {
        return SacmCategoria.ConsultaCategorias();
    }
    
    /*-----------------------------------------------------sacm_actualiza_item_categoria Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_actualiza_item_categoria")
    public CategoriaResultDto ActualizaItemCategoria(CategoriaItemDto itemRequest) {
        return SacmCategoria.ActualizaItemCategoria(itemRequest);
    }
    
}

