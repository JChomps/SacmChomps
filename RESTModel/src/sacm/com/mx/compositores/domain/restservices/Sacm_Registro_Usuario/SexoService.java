package sacm.com.mx.compositores.domain.restservices.Sacm_Registro_Usuario;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.SexoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.SexoResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmSexo;

@Path("/sexo")
public class SexoService {
    public SexoService() {
        super();
    }

    /*-----------------------------------------------------sacm_cat_sexo Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_cat_sexo")
    public SexoResultDto getSexoByIdSexo(SexoDto sexoRequest) {
        return SacmSexo.getSexoByIdSexo(sexoRequest);
    }
}
