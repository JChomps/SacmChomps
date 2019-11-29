package sacm.com.mx.compositores.domain.restservices;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.BeneficioDto;
import sacm.com.mx.compositores.common.dtos.BeneficioResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmBeneficio;

@Path("/beneficio")
public class BeneficioService {
    public BeneficioService() {
        super();
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_beneficios")
    public BeneficioResultDto getAcativaCuenta(BeneficioDto beneficioRequest) {
        return SacmBeneficio.getBeneficios(beneficioRequest);
    }
}
