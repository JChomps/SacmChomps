package sacm.com.mx.compositores.domain.restservices.Sacm_Registro_Usuario;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil.GiroResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmGiro;

@Path("/giro")
public class GiroService {
    public GiroService() {
        super();
    }
    
    /*-----------------------------------------------------sacm_cat_estado Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_cat_giro")
    public GiroResultDto getEstadosByPais( ) {
        return SacmGiro.getGiro();
    }
}
