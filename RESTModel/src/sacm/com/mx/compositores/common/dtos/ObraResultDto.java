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
    private ObraDto obra= new ObraDto();
    private List<ObraDto> Versiones = new ArrayList<ObraDto>();

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

    public void setVersiones(List<ObraDto> Versiones) {
        this.Versiones = Versiones;
    }

    public List<ObraDto> getVersiones() {
        return Versiones;
    }

    public ObraDto getObra() {
        return obra;
    }

    public void setObra(ObraDto obra) {
        this.obra = obra;
    }
}
