package sacm.com.mx.compositores.domain.restservices.Sacm_Consola;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmConsola;


@Path("/Consola")
public class ConsolaService {
    public ConsolaService() {
        super();
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_obra_consola")
    public ObraResultDto getMetadata2(ObraDto obraRequest) {
        return SacmConsola.getObra(obraRequest);
    }
}
