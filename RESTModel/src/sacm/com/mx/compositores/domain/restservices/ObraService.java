package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import sacm.com.mx.compositores.common.dtos.ObraResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmObra;

@Path("/obra")
public class ObraService {
    public ObraService() {
        super();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("getversionbyidobra")
    public ObraResultDto getVersionesByIdObra(ObraResultDto obraRequest) {
        return SacmObra.getVersionesByIdObra(obraRequest);
    }
}
