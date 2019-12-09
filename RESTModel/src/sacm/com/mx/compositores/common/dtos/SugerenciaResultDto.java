package sacm.com.mx.compositores.common.dtos;

import java.util.ArrayList;
import java.util.List;

public class SugerenciaResultDto {
    public SugerenciaResultDto() {
        super();
    }
    
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<SugerenciaDto> obras= new ArrayList<SugerenciaDto>();

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

    public List<SugerenciaDto> getObras() {
        return obras;
    }

    public void setObras(List<SugerenciaDto> obras) {
        this.obras = obras;
    }
}
