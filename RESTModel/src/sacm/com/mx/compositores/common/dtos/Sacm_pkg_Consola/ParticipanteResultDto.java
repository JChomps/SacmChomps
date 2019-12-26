package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.AlbumDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.NombreParticipanteDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ParticipanteDto;

public class ParticipanteResultDto {
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<NombreParticipanteDto> Participantes = null;//new ArrayList<AlbumDto>();
    public ParticipanteResultDto() {
        super();
    }

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

    public List<NombreParticipanteDto> getParticipantes() {
        return Participantes;
    }

    public void setParticipantes(List<NombreParticipanteDto> Participantes) {
        this.Participantes = Participantes;
    }
}
