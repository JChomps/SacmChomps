package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

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
    @Path("getpaisesbyidpais")
    public PaisResultDto getPaisesByIdPais(PaisResultDto paisRequest) {
        return SacmPais.getPaisesByIdPais(paisRequest);
    }
}
