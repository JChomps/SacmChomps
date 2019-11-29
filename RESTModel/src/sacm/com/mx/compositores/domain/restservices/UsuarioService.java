package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.UsuarioDto;
import sacm.com.mx.compositores.common.dtos.UsuarioResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmUsuario;

@Path("/usuario")
public class UsuarioService {
    public UsuarioService() {
        super();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("SACM_ACTUALIZA_PWD_USUARIO")
    public UsuarioResultDto updatePassword(UsuarioDto usuarioRequest) {
        return SacmUsuario.updatePassword(usuarioRequest);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("changepassword")
    public UsuarioResultDto changePassword(UsuarioDto usuarioRequest) {
        return SacmUsuario.changePassword(usuarioRequest);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("SACM_ENVIA_CORREO_ACT_PWD")
    public UsuarioResultDto restaurarPassword(UsuarioDto usuarioRequest) {
        return SacmUsuario.restaurarPassword(usuarioRequest);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("SACM_LOGIN_USER")
    public UsuarioResultDto login(UsuarioDto usuarioRequest) {
        return SacmUsuario.login(usuarioRequest);
    } 

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("SACM_REGISTRO_USER")
    public UsuarioResultDto registrarUsuario(UsuarioDto usuarioRequest) {
        return SacmUsuario.registrarUsuario(usuarioRequest);
    } 
}
