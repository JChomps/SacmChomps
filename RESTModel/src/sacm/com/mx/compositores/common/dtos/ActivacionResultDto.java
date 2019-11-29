package sacm.com.mx.compositores.common.dtos;


public class ActivacionResultDto {

    @SuppressWarnings("compatibility:-8073432289962294725")
    private static final long serialVersionUID = 1L;
    
    private ActivacionDto activacion= new ActivacionDto();
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService = new HeaderDto();
    
    public ActivacionResultDto() {
        super();
    }

    public HeaderDto getHeaderRequest() {
        return ResponseBD;
    }

    public void setHeaderRequest(HeaderDto headerRequest) {
        this.ResponseBD = headerRequest;
    }

    public HeaderDto getHeaderResponse() {
        return ResponseService;
    }

    public void setHeaderResponse(HeaderDto headerResponse) {
        this.ResponseService = headerResponse;
    }

    public ActivacionDto getActivacion() {
        return activacion;
    }

    public void setActivacion(ActivacionDto activacion) {
        this.activacion = activacion;
    }
    }
