package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.QueryParam;

import sacm.com.mx.compositores.common.dtos.EstadoResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmEstado;

@Path("/estado")
public class EstadoService {
    public EstadoService() {
        super();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("getestadosbypais")
    public EstadoResultDto getEstadosByPais(EstadoResultDto estadoRequest) {
        return SacmEstado.getEstadosByPais(estadoRequest);
    }
}
