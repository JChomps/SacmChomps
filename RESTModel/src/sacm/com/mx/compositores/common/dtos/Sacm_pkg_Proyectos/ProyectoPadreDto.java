package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class ProyectoPadreDto {
    public ProyectoPadreDto() {
        super();
    }
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private Integer id_proyecto;

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

    public Integer getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(Integer id_proyecto) {
        this.id_proyecto = id_proyecto;
    }
}
