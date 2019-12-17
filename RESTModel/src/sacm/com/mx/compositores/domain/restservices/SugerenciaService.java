package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.ActivacionDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.ActivacionResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.PalabraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.SugerenciaDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.SugerenciaResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmActivacion;
import sacm.com.mx.compositores.infraestructure.repositories.SacmSugerencia;

@Path("/sugerencia")
public class SugerenciaService {
    public SugerenciaService() {
        super();
    }
    
    /*-----------------------------------------------------sacm_consulta_sugerencia Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_consulta_sugerencia")
    public SugerenciaResultDto getAcativaCuenta(PalabraDto ActivRequest) {
        return SacmSugerencia.sacmgetSugerencia(ActivRequest);
    }
    
}
