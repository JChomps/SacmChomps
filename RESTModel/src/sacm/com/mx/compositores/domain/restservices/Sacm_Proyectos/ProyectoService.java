package sacm.com.mx.compositores.domain.restservices.Sacm_Proyectos;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos.ProyectoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos.ProyectoResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos.SubProyectoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos.SubProyectoResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmProyecto;

@Path("/Proyecto")
public class ProyectoService {
    public ProyectoService() {
        super();
    }
    
    /*-----------------------------------------------------sacm_elimina_proyecto_padre Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_elimina_proyecto_padre")
    public ProyectoResultDto EliminaProyecto( ProyectoDto projectRequest) {
        return SacmProyecto.getEliminaProyecto(projectRequest);
    }
    
    /*-----------------------------------------------------sacm_elimina_proyecto_hijo Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_elimina_proyecto_hijo")
    public SubProyectoResultDto EliminaSubProyecto( SubProyectoDto projectRequest) {
        return SacmProyecto.getEliminaSubProyecto(projectRequest);
    }
    
    /*-----------------------------------------------------sacm_crea_proyecto Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_crea_proyecto")
    public ProyectoResultDto CreaProyecto( ProyectoDto projectRequest) {
        return SacmProyecto.getCreaProyecto(projectRequest);
    }
    
    /*-----------------------------------------------------sacm_crea_subproyecto Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_crea_subproyecto")
    public ProyectoResultDto CreaSubProyecto( SubProyectoDto projectRequest) {
        return SacmProyecto.getCreaSubProyecto(projectRequest);
    }
    
    /*-----------------------------------------------------sacm_consulta_proyecto_subp Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_proyecto_subp")
    public ProyectoResultDto CronsultaProyecto( SubProyectoDto projectRequest) {
        return SacmProyecto.getConsultaProyecto(projectRequest);
    }
    
    /*-----------------------------------------------------sacm_mueve_proyecto Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_mueve_proyecto")
    public ProyectoResultDto MueveProyecto(ProyectoDto projectRequest) {
        return SacmProyecto.MueveProyecto(projectRequest);
    }
    
    /*-----------------------------------------------------sacm_agrega_obra_proyecto Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_agrega_obra_proyecto")
    public ProyectoResultDto AgregaObra(ProyectoDto projectRequest) {
        return SacmProyecto.AgregaObra(projectRequest);
    }
    
    /*-----------------------------------------------------sacm_consulta_inbox Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_inbox")
    public ObraResultDto ConsultaInbox(ProyectoDto projectRequest) {
        return SacmProyecto.ConsultaInbox(projectRequest);
    }
    
    /*-----------------------------------------------------sacm_consulta_shared Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_shared")
    public ObraResultDto ConsultaShared(ProyectoDto projectRequest) {
        return SacmProyecto.ConsultaShared(projectRequest);
    }
    
    /*-----------------------------------------------------sacm_consulta_proyecto Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_proyecto")
    public ProyectoResultDto ConsultaProyecto(ProyectoDto projectRequest) {
        return SacmProyecto.ConsultaProyecto(projectRequest);
    }
}
