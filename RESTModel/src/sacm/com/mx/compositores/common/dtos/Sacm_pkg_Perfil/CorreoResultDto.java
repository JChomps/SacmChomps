package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil;

import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class CorreoResultDto {
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<CorreoDto> Correos =null;
    public CorreoResultDto() {
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

    public List<CorreoDto> getCorreos() {
        return Correos;
    }

    public void setCorreos(List<CorreoDto> Correos) {
        this.Correos = Correos;
    }
}
