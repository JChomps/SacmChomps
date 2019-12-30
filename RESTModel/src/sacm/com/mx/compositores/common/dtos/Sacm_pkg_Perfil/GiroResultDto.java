package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil;

import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class GiroResultDto {
    
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<GiroDto> Giros =null;
    
    public GiroResultDto() {
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

    public List<GiroDto> getGiros() {
        return Giros;
    }

    public void setGiros(List<GiroDto> Giros) {
        this.Giros = Giros;
    }
}
