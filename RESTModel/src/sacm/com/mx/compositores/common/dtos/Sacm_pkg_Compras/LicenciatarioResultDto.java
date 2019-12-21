package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class LicenciatarioResultDto {
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<MarcasDto> Licenciatarios = new ArrayList<MarcasDto>();
    private List<MarcasDto> Marcas = new ArrayList<MarcasDto>();
    public LicenciatarioResultDto() {
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

    public List<MarcasDto> getLicenciatarios() {
        return Licenciatarios;
    }

    public void setLicenciatarios(List<MarcasDto> Licenciatarios) {
        this.Licenciatarios = Licenciatarios;
    }

    public List<MarcasDto> getMarcas() {
        return Marcas;
    }

    public void setMarcas(List<MarcasDto> Marcas) {
        this.Marcas = Marcas;
    }
}
