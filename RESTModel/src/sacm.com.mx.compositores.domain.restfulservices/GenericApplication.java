package sacm.com.mx.compositores.domain.restfulservices;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import oracle.wsm.metadata.annotation.PolicyReference;
import oracle.wsm.metadata.annotation.PolicySet;

import sacm.com.mx.compositores.domain.restservices.Sacm_Registro_Usuario.ActivacionService;
import sacm.com.mx.compositores.domain.restservices.Sacm_Registro_Usuario.BeneficioService;
import sacm.com.mx.compositores.domain.restservices.Sacm_Registro_Usuario.EstadoService;
import sacm.com.mx.compositores.domain.restservices.Sacm_Registro_Usuario.LegalesService;
import sacm.com.mx.compositores.domain.restservices.MetadaService;
import sacm.com.mx.compositores.domain.restservices.ObraService;
import sacm.com.mx.compositores.domain.restservices.Sacm_Registro_Usuario.PaisService;
import sacm.com.mx.compositores.domain.restservices.QuickService;
import sacm.com.mx.compositores.domain.restservices.Sacm_Compras.CarritoService;
import sacm.com.mx.compositores.domain.restservices.Sacm_Compras.CategoriaService;
import sacm.com.mx.compositores.domain.restservices.Sacm_Compras.LicenciatarioService;
import sacm.com.mx.compositores.domain.restservices.Sacm_Consola.ConsolaService;
import sacm.com.mx.compositores.domain.restservices.Sacm_Perfil.PerfilService;
import sacm.com.mx.compositores.domain.restservices.Sacm_Proyectos.ProyectoService;
import sacm.com.mx.compositores.domain.restservices.Sacm_Registro_Usuario.GiroService;
import sacm.com.mx.compositores.domain.restservices.Sacm_Registro_Usuario.SexoService;
import sacm.com.mx.compositores.domain.restservices.SolicitudesServices;
import sacm.com.mx.compositores.domain.restservices.SugerenciaService;
import sacm.com.mx.compositores.domain.restservices.TagsService;
import sacm.com.mx.compositores.domain.restservices.TestService;
import sacm.com.mx.compositores.domain.restservices.TrancInfoService;
import sacm.com.mx.compositores.domain.restservices.UsuarioService;

@ApplicationPath("resources")
public class GenericApplication extends Application {
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();

        // Register root resources.
        classes.add(ActivacionService.class);
        classes.add(BeneficioService.class);
        classes.add(CarritoService.class);
        classes.add(CategoriaService.class); 
        classes.add(ConsolaService.class); 
        classes.add(EstadoService.class);
        classes.add(GiroService.class);
        classes.add(LegalesService.class);
        classes.add(LicenciatarioService.class);
        classes.add(MetadaService.class);
        classes.add(ObraService.class);
        classes.add(PaisService.class);
        classes.add(PerfilService.class);
        classes.add(ProyectoService.class);
        classes.add(QuickService.class);
        classes.add(SexoService.class);
        classes.add(SolicitudesServices.class);
        classes.add(SugerenciaService.class); 
        classes.add(TagsService.class);
        classes.add(TrancInfoService.class);       
        classes.add(UsuarioService.class);
        
       
       

        // Register provider classes.

        return classes;
    }
}
