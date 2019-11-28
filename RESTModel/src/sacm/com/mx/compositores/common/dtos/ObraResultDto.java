package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class ObraResultDto implements Serializable {
    @SuppressWarnings("compatibility:-1701967989758779376")
    private static final long serialVersionUID = 1L;

    public ObraResultDto() {
        super();
    }
    private  HeaderDto headerRequest;
    private  HeaderDto headerResponse = new HeaderDto();
    private  ObraDto obra = new ObraDto();
    private  List<ObraDto> obraList = new ArrayList<ObraDto>();

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

    public void setObra(ObraDto obra) {
        this.obra = obra;
    }

    public ObraDto getObra() {
        return obra;
    }

    public void setObraList(List<ObraDto> obraList) {
        this.obraList = obraList;
    }

    public List<ObraDto> getObraList() {
        return obraList;
    }
}
