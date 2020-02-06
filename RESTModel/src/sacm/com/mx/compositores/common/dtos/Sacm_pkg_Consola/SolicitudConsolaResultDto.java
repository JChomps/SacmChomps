package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola;

import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class SolicitudConsolaResultDto {
    public SolicitudConsolaResultDto() {
        super();
    }
    
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<SolicitudConsolaDto> Solicitudes =null;

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


    public List<SolicitudConsolaDto> getSolicitudes() {
        return Solicitudes;
    }

    public void setSolicitudes(List<SolicitudConsolaDto> Solicitudes) {
        this.Solicitudes = Solicitudes;
    }
}
