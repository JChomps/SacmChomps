package sacm.com.mx.compositores.domain.restservices.Sacm_Compras;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CategoriaDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CategoriaResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmCategoria;

@Path("/categorias")
public class CategoriaService {
    public CategoriaService() {
        super();
    }
    /*-----------------------------------------------------sacm_consulta_categorias Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_consulta_categorias")
    public CategoriaResultDto getAgregaCarrito(CategoriaDto CategoriaRequest) {
        return SacmCategoria.getConsultaCat(CategoriaRequest);
    }
}
