package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.AlbumResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.CompObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.PalabraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.VersionResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmObra;

@Path("/obra")
public class ObraService {
    public ObraService() {
        super();
    }
    /*-----------------------------------------------------sacm_versiones Service-------------------------------------------------------------------*/ 
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_versiones")
    public ObraResultDto getVersionesByIdObra(ObraDto obraRequest) {
        return SacmObra.getVersionesByIdObra(obraRequest);
    }

    /*-----------------------------------------------------sacm_consulta_obra Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_obra")
    public ObraResultDto getObraByPalabra(PalabraDto palabraRequest) {
        return SacmObra.sacmConsultaObra(palabraRequest);
    }
    
    /*-----------------------------------------------------sacm_consulta_album Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_album")
    public AlbumResultDto getObraByAlbum(PalabraDto palabraRequest) {
        return SacmObra.sacmConsultaObraByAlbum(palabraRequest);
    }
    /*-----------------------------------------------------sacm_consulta_obra_album Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_obra_album")
    public ObraResultDto getObraByAlbum(ObraDto obraRequest) {
        return SacmObra.sacmConsultaObraByAlbum(obraRequest);
    }

    /*-----------------------------------------------------sacm_audio_obra Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_audio_obra")
    public ObraResultDto getObraByAudio(ObraDto obraRequest) {
        return SacmObra.ConsultaObraByAudio(obraRequest);
    }

    /*-----------------------------------------------------sacm_compartir_obra Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_compartir_obra")
    public ObraResultDto compartirObra(CompObraDto obraRequest) {
        return SacmObra.compartirObra(obraRequest);
    }
    
    /*-----------------------------------------------------sacm_consulta_tipo_audio Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_tipo_audio")
    public ObraResultDto consultaTipoAudio(ObraDto obraRequest) {
        return SacmObra.consultaTipoAudio(obraRequest);
    }
}
