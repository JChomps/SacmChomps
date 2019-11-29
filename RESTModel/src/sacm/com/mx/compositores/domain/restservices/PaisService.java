package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.PaisDto;
import sacm.com.mx.compositores.common.dtos.PaisResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmPais;

@Path("/pais")
public class PaisService {
    public PaisService() {
        super();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_cat_pais")
    public PaisResultDto getPaisesByIdPais(PaisDto paisRequest) {
        return SacmPais.getPaisesByIdPais(paisRequest);
    }
}
