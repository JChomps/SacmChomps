package sacm.com.mx.compositores.common.dtos;

public class ActivacionResultDto {

    @SuppressWarnings("compatibility:-8073432289962294725")
    private static final long serialVersionUID = 1L;
    
   // Cabeceras para errores
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
