package sacm.com.mx.compositores.domain.restfulservices;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import oracle.wsm.metadata.annotation.PolicyReference;
import oracle.wsm.metadata.annotation.PolicySet;

import sacm.com.mx.compositores.domain.restservices.EstadoService;
import sacm.com.mx.compositores.domain.restservices.LegalesService;
import sacm.com.mx.compositores.domain.restservices.MetadaService;
import sacm.com.mx.compositores.domain.restservices.ObraService;
import sacm.com.mx.compositores.domain.restservices.PaisService;
import sacm.com.mx.compositores.domain.restservices.SexoService;
import sacm.com.mx.compositores.domain.restservices.TagsService;
import sacm.com.mx.compositores.domain.restservices.TestService;
import sacm.com.mx.compositores.domain.restservices.TrancInfoService;
import sacm.com.mx.compositores.domain.restservices.UsuarioService;

@ApplicationPath("resources")
public class GenericApplication extends Application {
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();

        // Register root resources.
        classes.add(ObraService.class);
        classes.add(PaisService.class);
        classes.add(EstadoService.class);
        classes.add(TestService.class);
        classes.add(SexoService.class);
        classes.add(TrancInfoService.class);
        classes.add(MetadaService.class);
        classes.add(UsuarioService.class);
        classes.add(LegalesService.class);
        classes.add(TagsService.class);

        // Register provider classes.

        return classes;
    }
}
