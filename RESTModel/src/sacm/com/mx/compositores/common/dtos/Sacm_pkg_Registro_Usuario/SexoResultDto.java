package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class SexoResultDto implements Serializable {
    @SuppressWarnings("compatibility:3030932796539455279")
    private static final long serialVersionUID = 1L;

    public SexoResultDto() {
        super();
    }
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<SexoDto> Sexos = new ArrayList<SexoDto>();

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

    public void setSexos(List<SexoDto> Sexos) {
        this.Sexos = Sexos;
    }

    public List<SexoDto> getSexos() {
        return Sexos;
    }
}
