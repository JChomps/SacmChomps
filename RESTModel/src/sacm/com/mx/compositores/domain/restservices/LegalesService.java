package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.LegalesDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.LegalesResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmLegales;

@Path("/legales")
public class LegalesService {
    public LegalesService() {
        super();
    }

    /*-----------------------------------------------------sacm_terminos_aviso Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_terminos_aviso")
    public LegalesResultDto getLegales(LegalesDto legalesRequest) {
        return SacmLegales.getLegales(legalesRequest);
    }
}
