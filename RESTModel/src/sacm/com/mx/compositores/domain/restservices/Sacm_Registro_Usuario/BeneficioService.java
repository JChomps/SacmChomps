package sacm.com.mx.compositores.domain.restservices.Sacm_Registro_Usuario;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.BeneficioDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.BeneficioResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmBeneficio;

@Path("/beneficio")
public class BeneficioService {
    public BeneficioService() {
        super();
    }    
    /*-----------------------------------------------------sacm_beneficios Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_beneficios")
    public BeneficioResultDto getAcativaCuenta(BeneficioDto beneficioRequest) {
        return SacmBeneficio.getBeneficios();
    }
}
