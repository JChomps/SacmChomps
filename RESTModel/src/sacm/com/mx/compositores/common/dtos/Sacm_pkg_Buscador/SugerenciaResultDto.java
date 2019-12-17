package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class SugerenciaResultDto {
    public SugerenciaResultDto() {
        super();
    }
    
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<ObraDto> Obras= new ArrayList<ObraDto>();
    private List<AlbumDto> Albumes= new ArrayList<AlbumDto>();
    private List<NombreParticipanteDto> Participantes= new ArrayList<NombreParticipanteDto>();
    private List<TagSugerencia> Tags= new ArrayList<TagSugerencia>();

    public HeaderDto getResponseBD() {
        return ResponseBD;
    }

    public void setResponseBD(HeaderDto ResponseBD) {
        this.ResponseBD = ResponseBD;
    }

    public HeaderDto getResponseService() {
        return ResponseService;
    }

    public void setResponseService(HeaderDto ResponseService) {
        this.ResponseService = ResponseService;
    }

    public List<ObraDto> getObras() {
        return Obras;
    }

    public void setObras(List<ObraDto> Obras) {
        this.Obras = Obras;
    }

    public List<AlbumDto> getAlbumes() {
        return Albumes;
    }

    public void setAlbumes(List<AlbumDto> Albumes) {
        this.Albumes = Albumes;
    }

    public List<NombreParticipanteDto> getParticipantes() {
        return Participantes;
    }

    public void setParticipantes(List<NombreParticipanteDto> Participantes) {
        this.Participantes = Participantes;
    }

    public List<TagSugerencia> getTags() {
        return Tags;
    }

    public void setTags(List<TagSugerencia> Tags) {
        this.Tags = Tags;
    }
}
