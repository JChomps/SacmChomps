package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class EstadoResultDto implements Serializable {
    @SuppressWarnings("compatibility:-8073432289962294725")
    private static final long serialVersionUID = 1L;

    public EstadoResultDto() {
        super();
    }
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<EstadoDto> estados = new ArrayList<EstadoDto>();

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

    public void setEstados(List<EstadoDto> estados) {
        this.estados = estados;
    }

    public List<EstadoDto> getEstados() {
        return estados;
    }
}
