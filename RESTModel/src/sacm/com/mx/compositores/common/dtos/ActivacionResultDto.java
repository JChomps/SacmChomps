package sacm.com.mx.compositores.common.dtos;

public class ActivacionResultDto {

    @SuppressWarnings("compatibility:-8073432289962294725")
    private static final long serialVersionUID = 1L;
    
    private ActivacionDto activacion= new ActivacionDto();
    private HeaderDto headerRequest;
    private HeaderDto headerResponse = new HeaderDto();
    
    public ActivacionResultDto() {
        super();
    }

    public HeaderDto getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderRequest(HeaderDto headerRequest) {
        this.headerRequest = headerRequest;
    }

    public HeaderDto getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderDto headerResponse) {
        this.headerResponse = headerResponse;
    }

    public ActivacionDto getActivacion() {
        return activacion;
    }

    public void setActivacion(ActivacionDto activacion) {
        this.activacion = activacion;
    }
    }