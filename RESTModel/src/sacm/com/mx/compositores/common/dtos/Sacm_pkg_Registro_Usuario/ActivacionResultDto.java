package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class ActivacionResultDto {

    @SuppressWarnings("compatibility:-8073432289962294725")
    private static final long serialVersionUID = 1L;
    
        private HeaderDto ResponseBD;
        private HeaderDto ResponseService;
    
    public ActivacionResultDto() {
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
}
