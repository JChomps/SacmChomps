package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.ActivacionDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.SexoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.SexoResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil.SolicitudResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Inicio_Sesion.UsuarioDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmSexo;
import sacm.com.mx.compositores.infraestructure.repositories.SacmSolicitudes;

@Path("/solicitud")
public class SolicitudesServices {
    public SolicitudesServices() {
        super();
    }
    
    /*-----------------------------------------------------sacm_solicitudes Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_solicitudes")
    public SolicitudResultDto getSolicitud(ActivacionDto usuarioRequest) {
        return SacmSolicitudes.getSolicitud(usuarioRequest);
    }
}
