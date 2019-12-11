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

    /*-----------------------------------------------------sacm_actualiza_pwd_usuario Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_actualiza_pwd_usuario")
    public UsuarioResultDto updatePassword(UsuarioDto usuarioRequest) {
        return SacmUsuario.updatePassword(usuarioRequest);
    }

    /*-----------------------------------------------------sacm_cambia_pwd_usuario Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_cambia_pwd_usuario")
    public UsuarioResultDto changePassword(UsuarioDto usuarioRequest) {
        return SacmUsuario.changePassword(usuarioRequest);
    }

    /*-----------------------------------------------------sacm_envia_correo_pwd Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_envia_correo_pwd")
    public UsuarioResultDto restaurarPassword(UsuarioDto usuarioRequest) {
        return SacmUsuario.restaurarPassword(usuarioRequest);
    }

    /*-----------------------------------------------------sacm_login_user Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_login_user")
    public UsuarioResultDto login(UsuarioDto usuarioRequest) {
        return SacmUsuario.login(usuarioRequest);
    } 

    /*-----------------------------------------------------sacm_registro_user Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_registro_user")
    public UsuarioResultDto registrarUsuario(UsuarioDto usuarioRequest) {
        return SacmUsuario.registrarUsuario(usuarioRequest);
    } 
}
