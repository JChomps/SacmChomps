package sacm.com.mx.compositores.domain.restservices.Sacm_Consola;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.AlbumResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.NombreParticipanteDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.ParticipanteResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.TagConsolaResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TagsDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TagsResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.VersionDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.VersionResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.ValidaObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.ValidaObraResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.CalificacionDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.CalificacionResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.LogueoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.LogueoResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.TagConsolaDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Inicio_Sesion.UsuarioDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Inicio_Sesion.UsuarioResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil.SolicitudResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmCarrito;
import sacm.com.mx.compositores.infraestructure.repositories.SacmConsola;
import sacm.com.mx.compositores.infraestructure.repositories.SacmSolicitudes;


@Path("/consola")
public class ConsolaService {
    public ConsolaService() {
        super();
    }
    
    
    /*-----------------------------------------------------sacm_consulta_obra_consola Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_obra_consola")
    public ObraResultDto ConsultaObraConsola(ObraDto obraRequest) {
        return SacmConsola.getObra(obraRequest);
    }
    
    /*-----------------------------------------------------sacm_consulta_participante_obra_consola Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_participante_obra_consola")
    public ParticipanteResultDto ConsultaParticipanteObraConsola(ObraDto obraRequest) {
        return SacmConsola.getParticipanteObra(obraRequest);
    }
    
    /*-----------------------------------------------------sacm_consulta_version_obra_consola Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_version_obra_consola")
    public VersionResultDto ConsultaVersionObraConsola(ObraDto obraRequest) {
        return SacmConsola.getVersionObra(obraRequest);
    }
    
    
    /*-----------------------------------------------------sacm_inserta_obra_consola Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_inserta_obra_consola")
    public ObraResultDto InsertaObraConsola(ObraDto obraRequest) {
        return SacmConsola.InsertaObra(obraRequest);
    }
    /*-----------------------------------------------------sacm_actualiza_obra_consola Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_actualiza_obra_consola")
    public ObraResultDto ActualizaObraConsola(ObraDto obraRequest) {
        return SacmConsola.ActualizaObra(obraRequest);
    }
    
    /*-----------------------------------------------------sacm_consulta_tag_consola Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_tag_consola")
    public TagConsolaResultDto ConsultaTagConsola(ObraDto obraRequest) {
        return SacmConsola.ConsultaTagObra(obraRequest);
    }
    
    /*-----------------------------------------------------sacm_actualiza_usuario_consola Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_actualiza_usuario_consola")
    public UsuarioResultDto ConsultaTagConsola(UsuarioDto usuarioRequest) {
        return SacmConsola.ActualizaUsuario(usuarioRequest);
    }
    
    /*-----------------------------------------------------sacm_consulta_usuario_consola Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_usuario_consola")
    public UsuarioResultDto sacm_consulta_usuario_consola(UsuarioDto usuarioRequest) {
        return SacmConsola.ConsultaUsuario(usuarioRequest);
    }
    
    /*-----------------------------------------------------sacm_consulta_solicitud_consola Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_solicitud_consola")
    public SolicitudResultDto sacm_consulta_solicitud_consola(UsuarioDto usuarioRequest) {
        return SacmSolicitudes.getSolicitudConsola (usuarioRequest);
    }
    
    /*-----------------------------------------------------sacm_lov_album_consola Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_lov_album_consola")
    public AlbumResultDto sacm_consulta_album_consola() {
        return SacmConsola.ConsultaAlbum ();
    }
    
    /*-----------------------------------------------------sacm_lov_tag_consola Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_lov_tag_consola")
    public TagConsolaResultDto consultaTag(TagConsolaDto tagRequest) {
        return SacmConsola.ConsultaLovTag (tagRequest);
    }
    
    /*-----------------------------------------------------sacm_inserta_tag_obra_consola Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_inserta_tag_obra_consola")
    public ParticipanteResultDto InsertaTagObra(ObraDto obraRequest) {
        return SacmConsola.insertaTagObra (obraRequest);
    }
    
    /*-----------------------------------------------------sacm_lov_participantes_consola Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_lov_participantes_consola")
    public ParticipanteResultDto lovParticipantes() {
        return SacmConsola.LovParticipantes ();
    }
    
    /*-----------------------------------------------------sacm_lov_inserta_participantes_consola Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_inserta_participantes_consola")
    public ParticipanteResultDto lovInsertaParticipantes(NombreParticipanteDto participanteRequest) {
        return SacmConsola.LovInsertaParticipantes (participanteRequest);
    }
    
    /*-----------------------------------------------------sacm_lov_obras_consola Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_lov_obras_consola")
    public ObraResultDto LovObras() {
        return SacmConsola.LovObras();
    }
    
    /*-----------------------------------------------------sacm_consulta_logueos_mes Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_logueos_mes")
    public LogueoResultDto ConsultaLogueo(LogueoDto LogRequest) {
        return SacmConsola.ConsultaLogueo(LogRequest);
    }
    
    /*-----------------------------------------------------sacm_califica_obra Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_califica_obra")
    public CalificacionResultDto CalificaObra(CalificacionDto calRequest) {
        return SacmConsola.CalificaObra(calRequest);
    }
    
    /*-----------------------------------------------------sacm_verifica_calificacion Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_verifica_calificacion")
    public CalificacionResultDto VerificaCalificacion(CalificacionDto calRequest) {
        return SacmConsola.VerificaCalificacion(calRequest);
    }
    
    /*-----------------------------------------------------sacm_consulta_calificacion Service-------------------------------------------------------------------*/
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_calificacion")
    public CalificacionResultDto ConsultaCalificacion(CalificacionDto calRequest) {
        return SacmConsola.ConsultaCalificacion(calRequest);
    }
    
    /*-----------------------------------------------------sacm_inserta_version_consola Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_inserta_version_consola")
    public CalificacionResultDto InsertaVersiones(VersionDto versionRequest) {
        return SacmConsola.InsertaVersiones(versionRequest);
    }
    
    /*-----------------------------------------------------sacm_reporte_calificacion Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_reporte_calificacion")
    public CalificacionResultDto ReporteCalificacion() {
        return SacmConsola.ReporteCalificacion();
    }
    
    /*-----------------------------------------------------sacm_consulta_items_usuario Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_consulta_items_usuario")
    public UsuarioResultDto getItemsUsuarios() {
        return SacmConsola.getItemsUsuarios();
    }
    
    /*-----------------------------------------------------sacm_reporte_solicitudes Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_reporte_solicitudes")
    public SolicitudResultDto ReporteSolicitudes(UsuarioDto userRequest) {
        return SacmConsola.ReporteSolicitudes(userRequest);
    }
    
    /*-----------------------------------------------------sacm_consulta_tag Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_consulta_tag")
    public TagsResultDto getTags(TagsDto tagRequest) {
        return SacmConsola.getTags(tagRequest);
    }
    
    /*-----------------------------------------------------sacm_consulta_tag_item Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_consulta_tag_item")
    public TagsResultDto getTagItem(TagsDto tagRequest) {
        return SacmConsola.getTagItem(tagRequest);
    }
    
    /*-----------------------------------------------------sacm_actualiza_tag Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_actualiza_tag")
    public ValidaObraResultDto actualizaTag(TagsDto tagRequest) {
        return SacmConsola.actualizaTag(tagRequest);
    }
    
    /*-----------------------------------------------------sacm_inserta_tag Service-------------------------------------------------------------------*/
    @POST       
    @Produces("application/json")
    @Path("sacm_inserta_tag")
    public ValidaObraResultDto insertaTag(TagsDto tagRequest) {
        return SacmConsola.insertaTag(tagRequest);
    }
    
    /*-----------------------------------------------------sacm_inserta_tag_item Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_inserta_tag_item")
    public ValidaObraResultDto insertaTagItem(TagsDto tagRequest) {
        return SacmConsola.insertaTagItem(tagRequest);
    }
    
    /*-----------------------------------------------------sacm_actualiza_tag_item Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_actualiza_tag_item")
    public ValidaObraResultDto actualizaTagItem(TagsDto tagRequest) {
        return SacmConsola.actualizaTagItem(tagRequest);
    }
    
    /*-----------------------------------------------------sacm_lov_tags Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_lov_tags")
    public TagsResultDto getTags() {
        return SacmConsola.getTags();
    }
    
   
  
}
