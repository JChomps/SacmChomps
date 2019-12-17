package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario;

import java.io.Serializable;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class LegalesResultDto implements Serializable {
    public LegalesResultDto() {
        super();
    }

    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private LegalesDto TerminosPortal = new LegalesDto();

    public void setResponseBD(HeaderDto ResponseBD) {
        this.ResponseBD = ResponseBD;
    }

    public HeaderDto getResponseBD() {
        return ResponseBD;
    }

    public void setResponseService(HeaderDto ResponseService) {
        this.ResponseService = ResponseService;
    }

    public HeaderDto getResponseService() {
        return ResponseService;
    }

    public void setTerminosPortal(LegalesDto TerminosPortal) {
        this.TerminosPortal = TerminosPortal;
    }

    public LegalesDto getTerminosPortal() {
        return TerminosPortal;
    }
}
