package sacm.com.mx.compositores.domain.restservices.Sacm_Compras;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CategoriaDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CategoriaResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.LicenciatarioResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.MarcasDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmCategoria;
import sacm.com.mx.compositores.infraestructure.repositories.SacmLicenciatario;

@Path("/compras")
public class LicenciatarioService {
    public LicenciatarioService() {
        super();
    }
    
    /*-----------------------------------------------------sacm_consulta_licenciatario Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_consulta_licenciatario") 
    public LicenciatarioResultDto getAgregaCarrito(MarcasDto licRequest) {
        return SacmLicenciatario.getConsultaLic(licRequest);
    }
}
