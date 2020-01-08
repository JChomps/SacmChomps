package sacm.com.mx.compositores.domain.restservices.Sacm_Perfil;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.LicenciatarioResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.MarcasDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil.CorreoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil.CorreoResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos.ProyectoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos.ProyectoResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.ActivacionDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.ActivacionResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmActivacion;
import sacm.com.mx.compositores.infraestructure.repositories.SacmPerfil;

@Path("/perfil")
public class PerfilService {
    public PerfilService() {
        super();
    }
    
    /*-----------------------------------------------------sacm_envia_correo_contacto Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_envia_correo_contacto")
    public CorreoResultDto EnvioCorreoContacto(CorreoDto correoRequest) {
        return SacmPerfil.setCorreoContacto(correoRequest);
    }
    
    /*-----------------------------------------------------sacm_registro_licenciatario Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_registro_licenciatario")
    public LicenciatarioResultDto  RegistroLicenciatario(MarcasDto marcasRequest) {
        return SacmPerfil.RegistroLicenciatario(marcasRequest);
    }
    
    /*-----------------------------------------------------sacm_valida_compra_obra Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_valida_compra_obra")
    public ProyectoResultDto  ValidaObraCompra(ProyectoDto datoRequest) {
        return SacmPerfil.ValidaObraCompra(datoRequest);
    }
}
