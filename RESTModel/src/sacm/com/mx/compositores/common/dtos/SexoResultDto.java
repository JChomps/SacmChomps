package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class SexoResultDto implements Serializable {
    @SuppressWarnings("compatibility:3030932796539455279")
    private static final long serialVersionUID = 1L;

    public SexoResultDto() {
        super();
    }
    private HeaderDto headerRequest;
    private HeaderDto headerResponse = new HeaderDto();
    private SexoDto sexo = new SexoDto();
    private List<SexoDto> sexoList = new ArrayList<SexoDto>();

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

    public void setSexo(SexoDto sexo) {
        this.sexo = sexo;
    }

    public SexoDto getSexo() {
        return sexo;
    }

    public void setSexoList(List<SexoDto> sexoList) {
        this.sexoList = sexoList;
    }

    public List<SexoDto> getSexoList() {
        return sexoList;
    }
}
