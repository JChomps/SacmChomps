package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.EstadoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.EstadoResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmEstado;

@Path("/estado")
public class EstadoService {
    public EstadoService() {
        super();
    }

    /*-----------------------------------------------------sacm_cat_estado Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_cat_estado")
    public EstadoResultDto getEstadosByPais(EstadoDto estadoRequest) {
        return SacmEstado.getEstadosByPais(estadoRequest);
    }
}
