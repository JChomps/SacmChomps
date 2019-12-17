package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class PaisResultDto implements Serializable {
    @SuppressWarnings("compatibility:-7830055730249044691")
    private static final long serialVersionUID = 1L;

    public PaisResultDto() {
        super();
    }
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<PaisDto> Paises = new ArrayList<PaisDto>();

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

    public void setPaises(List<PaisDto> Paises) {
        this.Paises = Paises;
    }

    public List<PaisDto> getPaises() {
        return Paises;
    }
}
