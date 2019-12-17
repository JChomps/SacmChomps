package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class SolicitudResultDto {
    public SolicitudResultDto() {
        super();
    }
    
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<SolicitudDto> Solicitudes = new ArrayList<SolicitudDto>();

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

    public List<SolicitudDto> getSolicitudes() {
        return Solicitudes;
    }

    public void setSolicitudes(List<SolicitudDto> Solicitudes) {
        this.Solicitudes = Solicitudes;
    }
}
