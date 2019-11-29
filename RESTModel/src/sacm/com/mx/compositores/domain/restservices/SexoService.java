package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.SexoDto;
import sacm.com.mx.compositores.common.dtos.SexoResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmSexo;

@Path("/sexo")
public class SexoService {
    public SexoService() {
        super();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_cat_sexo")
    public SexoResultDto getSexoByIdSexo(SexoDto sexoRequest) {
        return SacmSexo.getSexoByIdSexo(sexoRequest);
    }
}
