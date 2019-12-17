package sacm.com.mx.compositores.domain.restservices.Sacm_Compras;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.PalabraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.PalabraIdObra;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.ActivacionResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmActivacion;
import sacm.com.mx.compositores.infraestructure.repositories.SacmAgregarCarrito;

@Path("/carrito")
public class CarritoService {
    public CarritoService() {
        super();
    }
    
    /*-----------------------------------------------------sacm_agrega_carrito Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_agrega_carrito")
    public PalabraIdObra getAcativaCuenta(PalabraDto IdRequest) {
        return SacmAgregarCarrito.getAgregar(IdRequest);
    }
    
}
