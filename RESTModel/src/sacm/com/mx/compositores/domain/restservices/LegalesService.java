package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.LegalesDto;
import sacm.com.mx.compositores.common.dtos.LegalesResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmLegales;

@Path("/legales")
public class LegalesService {
    public LegalesService() {
        super();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("SACM_TERMINOS_AVISO")
    public LegalesResultDto getLegales(LegalesDto legalesRequest) {
        return SacmLegales.getLegales(legalesRequest);
    }
}
