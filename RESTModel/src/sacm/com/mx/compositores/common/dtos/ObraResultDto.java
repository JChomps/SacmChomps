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
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<ObraDto> obras= new ArrayList<ObraDto>();
     
   // private List<ObraDto> Versiones = new ArrayList<ObraDto>();

    public void setResponseBD(HeaderDto ResponseBD) {
        this.ResponseBD = ResponseBD;
    }

    public HeaderDto getResponseBD() {
        return ResponseBD;
    }

    public void setResponseService(HeaderDto ResponseService) {
        this.ResponseService = ResponseService;
    }

    public HeaderDto getResponseService() {
        return ResponseService;
    }


    public List<ObraDto> getObras() {
        return obras;
    }

    public void setObras(List<ObraDto> obras) {
        this.obras = obras;
    }
}
