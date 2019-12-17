package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class VersionResultDto {
    @SuppressWarnings("compatibility:-1701967989758779376")
    private static final long serialVersionUID = 1L;

    public VersionResultDto() {
        super();
    }
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
   
    private List<Obra> Obras = new ArrayList<Obra>();

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

   

    public List<Obra> getObras() {
        return Obras;
    }

    public void setObras(List<Obra> Obras) {
        this.Obras = Obras;
    }
}
