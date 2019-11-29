package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.EstadoDto;
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
    @Path("SACM_CAT_ESTADO")
    public EstadoResultDto getEstadosByPais(EstadoDto estadoRequest) {
        return SacmEstado.getEstadosByPais(estadoRequest);
    }
}
