package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.ActivacionDto;
import sacm.com.mx.compositores.common.dtos.ActivacionResultDto;
import sacm.com.mx.compositores.common.dtos.PalabraDto;
import sacm.com.mx.compositores.common.dtos.SugerenciaDto;
import sacm.com.mx.compositores.common.dtos.SugerenciaResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmActivacion;
import sacm.com.mx.compositores.infraestructure.repositories.SacmSugerencia;

@Path("/sugerencia")
public class SugerenciaService {
    public SugerenciaService() {
        super();
    }
    
    @POST
    @Produces("application/json")
    @Path("sacm_consulta_sugerencia")
    public SugerenciaResultDto getAcativaCuenta(PalabraDto ActivRequest) {
        return SacmSugerencia.sacmgetSugerencia(ActivRequest);
    }
    
}
