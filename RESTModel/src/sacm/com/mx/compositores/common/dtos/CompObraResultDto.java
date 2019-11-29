package sacm.com.mx.compositores.common.dtos;


public class CompObraResultDto {
    @SuppressWarnings("compatibility:-8073432289962294725")
    private static final long serialVersionUID = 1L;
    
    public CompObraResultDto() {
        super();
    }
    
    private HeaderDto headerRequest;
    private HeaderDto headerResponse = new HeaderDto();
    private CompObraDto compartir;

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

    public CompObraDto getCompartir() {
        return compartir;
    }

    public void setCompartir(CompObraDto compartir) {
        this.compartir = compartir;
    }
}
