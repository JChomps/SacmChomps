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
    @Path("updatepassword")
    public UsuarioResultDto updatePassword(UsuarioResultDto usuarioRequest) {
        return SacmUsuario.updatePassword(usuarioRequest);
    }


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("changepassword")
    public UsuarioResultDto changePassword(UsuarioResultDto usuarioRequest) {
        return SacmUsuario.changePassword(usuarioRequest);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("login")
    public UsuarioResultDto login(UsuarioResultDto usuario) {
        return SacmUsuario.login(usuario);
    } 

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("registrarusuario")
    public UsuarioResultDto registrarUsuario(UsuarioResultDto usuario) {
        return SacmUsuario.registrarUsuario(usuario);
    } 
}
