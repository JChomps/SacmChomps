package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras;

import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class ValidaObraResultDto {
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<ValidaObraDto> Obras=null;// =new ArrayList<RegistroDto>();// 
    
    public ValidaObraResultDto() {
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

    public List<ValidaObraDto> getObras() {
        return Obras;
    }

    public void setObras(List<ValidaObraDto> Obras) {
        this.Obras = Obras;
    }
}
