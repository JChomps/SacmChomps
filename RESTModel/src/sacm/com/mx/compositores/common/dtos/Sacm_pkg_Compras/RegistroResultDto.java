package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class RegistroResultDto {
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<RegistroDto> Respuesta=null;// =new ArrayList<RegistroDto>();// 
    
    
    public RegistroResultDto() {
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


    public List<RegistroDto> getRespuesta() {
        return Respuesta;
    }

    public void setRespuesta(List<RegistroDto> Respuesta) {
        this.Respuesta = Respuesta;
    }
}
