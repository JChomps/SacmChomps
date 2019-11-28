package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

public class LegalesResultDto implements Serializable {
    public LegalesResultDto() {
        super();
    }

    private HeaderDto headerRequest;
    private HeaderDto headerResponse = new HeaderDto();
    private LegalesDto legal = new LegalesDto();

    public void setHeaderRequest(HeaderDto headerRequest) {
        this.headerRequest = headerRequest;
    }

    public HeaderDto getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderResponse(HeaderDto headerResponse) {
        this.headerResponse = headerResponse;
    }

    public HeaderDto getHeaderResponse() {
        return headerResponse;
    }

    public void setLegal(LegalesDto legal) {
        this.legal = legal;
    }

    public LegalesDto getLegal() {
        return legal;
    }
}
