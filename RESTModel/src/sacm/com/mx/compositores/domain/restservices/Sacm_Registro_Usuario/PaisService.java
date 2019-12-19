package sacm.com.mx.compositores.domain.restservices.Sacm_Registro_Usuario;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.PaisDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.PaisResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmPais;

@Path("/pais")
public class PaisService {
    public PaisService() {
        super();
    }

    /*-----------------------------------------------------sacm_cat_pais Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_cat_pais")
    public PaisResultDto getPaisesByIdPais(PaisDto paisRequest) {
        return SacmPais.getPaisesByIdPais(paisRequest);
    }
}
