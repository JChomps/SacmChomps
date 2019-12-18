package sacm.com.mx.compositores.domain.restservices.Sacm_ComprasProyectos;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos.ProyectoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos.ProyectoPadreDto;
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
}
