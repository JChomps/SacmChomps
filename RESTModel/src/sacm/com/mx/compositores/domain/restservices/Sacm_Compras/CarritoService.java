package sacm.com.mx.compositores.domain.restservices.Sacm_Compras;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import oracle.mds.internal.core.IdRequest;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.PalabraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.PalabraIdObra;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CarritoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CarritoResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.RegistroDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.RegistroResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.ValidaObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.ValidaObraResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Inicio_Sesion.UsuarioDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.ActivacionResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmActivacion;
import sacm.com.mx.compositores.infraestructure.repositories.SacmCarrito;

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
        return SacmCarrito.getAgregar(IdRequest);
    }
    
    /*-----------------------------------------------------sacm_elimina_carrito Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_elimina_carrito")
    public PalabraIdObra getEliminaCarrito(CarritoDto carritoRequest) {
        return SacmCarrito.getElimina(carritoRequest);
    }
    
    /*-----------------------------------------------------sacm_consulta_carrito Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_consulta_carrito")
    public CarritoResultDto getConsultaCarrito(PalabraDto IdRequest) {
        return SacmCarrito.getConsulta(IdRequest);
    }
    
    /*-----------------------------------------------------sacm_registra_solicitud Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_registra_solicitud")
    public RegistroResultDto registraSolicitud(RegistroDto IdRequest) {
        return SacmCarrito.registraSolicitud2(IdRequest);
    }
    
    /*-----------------------------------------------------sacm_vacia_carrito Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_vacia_carrito")
    public RegistroResultDto VaciaCarrito(UsuarioDto IdRequest) {
        return SacmCarrito.VaciaCarrito(IdRequest);
    }
    
    /*-----------------------------------------------------sacm_valida_obras Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_valida_obras")
    public ValidaObraResultDto getValidaObras(CarritoDto CarritoRequest) {
        return SacmCarrito.getValidaObras(CarritoRequest);
    }
    
    /*-----------------------------------------------------sacm_propiedades_obras Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_propiedades_obras")
    public ValidaObraResultDto getPropiedadesObras(ValidaObraDto obraRequest) {
        return SacmCarrito.getPropiedadesObras(obraRequest);
    }
    
    /*-----------------------------------------------------sacm_cambia_estatus_request Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_cambia_estatus_request")
    public ValidaObraResultDto CambiaEstatusRequest(RegistroDto registroRequest) {
        return SacmCarrito.CambiaEstatusRequest(registroRequest);
    }
    
}
