package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import sacm.com.mx.compositores.common.dtos.CompObraDto;
import sacm.com.mx.compositores.common.dtos.ObraDto;
import sacm.com.mx.compositores.common.dtos.ObraResultDto;
import sacm.com.mx.compositores.common.dtos.PalabraDto;
import sacm.com.mx.compositores.common.dtos.VersionResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmObra;

@Path("/obra")
public class ObraService {
    public ObraService() {
        super();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_versiones")
    public VersionResultDto getVersionesByIdObra(ObraDto obraRequest) {
        return SacmObra.getVersionesByIdObra(obraRequest);
    }


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_obra")
    public ObraResultDto getObraByPalabra(PalabraDto palabraRequest) {
        return SacmObra.sacmConsultaObra(palabraRequest);
    }
    
   

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_consulta_obra_album")
    public ObraResultDto getObraByAlbum(PalabraDto palabraRequest) {
        return SacmObra.sacmConsultaObraByAlbum(palabraRequest);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_audio_obra")
    public ObraResultDto getObraByAudio(ObraDto obraRequest) {
        return SacmObra.ConsultaObraByAudio(obraRequest);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_compartir_obra")
    public ObraResultDto compartirObra(CompObraDto obraRequest) {
        return SacmObra.compartirObra(obraRequest);
    }
}
