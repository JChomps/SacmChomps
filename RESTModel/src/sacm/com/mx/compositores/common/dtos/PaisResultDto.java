package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class PaisResultDto implements Serializable {
    @SuppressWarnings("compatibility:-7830055730249044691")
    private static final long serialVersionUID = 1L;

    public PaisResultDto() {
        super();
    }
    private  HeaderDto headerRequest;
    private  HeaderDto headerResponse = new HeaderDto();
    private  PaisDto pais = new PaisDto();
    private  List<PaisDto> paisList = new ArrayList<PaisDto>();

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

    public void setPais(PaisDto pais) {
        this.pais = pais;
    }

    public PaisDto getPais() {
        return pais;
    }

    public void setPaisList(List<PaisDto> paisList) {
        this.paisList = paisList;
    }

    public List<PaisDto> getPaisList() {
        return paisList;
    }
}
