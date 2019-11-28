package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import sacm.com.mx.compositores.common.dtos.LegalesResultDto;
import sacm.com.mx.compositores.common.dtos.TagsResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmLegales;
import sacm.com.mx.compositores.infraestructure.repositories.SacmTags;

@Path("/legales")
public class LegalesService {
    public LegalesService() {
        super();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("getterminos")
    public LegalesResultDto getLegales(LegalesResultDto legalesRequest) {
        return SacmLegales.getLegales(legalesRequest);
    }
}
