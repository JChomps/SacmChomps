package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.QuickResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmQuick;


@Path("/quickSearch")
public class QuickService {
    public QuickService() {
        super();
    }
    /*-----------------------------------------------------sacm_cat_quick Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_quick_search")
    public QuickResultDto getSexoByIdSexo() {
        return SacmQuick.getQuickSerach();
    }
}
