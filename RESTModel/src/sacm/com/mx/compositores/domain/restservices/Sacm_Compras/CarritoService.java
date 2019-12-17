package sacm.com.mx.compositores.domain.restservices.Sacm_Compras;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.PalabraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.PalabraIdObra;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CarritoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CarritoResultDto;
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
    public PalabraIdObra getAgregaCarrito(PalabraDto IdRequest) {
        return SacmAgregarCarrito.getAgregar(IdRequest);
    }
    
    /*-----------------------------------------------------sacm_elimina_carrito Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_elimina_carrito")
    public PalabraIdObra getEliminaCarrito(CarritoDto carritoRequest) {
        return SacmAgregarCarrito.getElimina(carritoRequest);
    }
    
    /*-----------------------------------------------------sacm_consulta_carrito Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_consulta_carrito")
    public CarritoResultDto getConsultaCarrito(PalabraDto IdRequest) {
        return SacmAgregarCarrito.getConsulta(IdRequest);
    }
    
}
