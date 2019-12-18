package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;


public class ProyectoResultDto {
    public ProyectoResultDto() {
        super();
    }
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<ProyectoDto> ProjectList = new ArrayList<ProyectoDto>();
    private List<ProyectoDto> sdfsdfsd = null;

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

    public List<ProyectoDto> getProjectList() {
        return ProjectList;
    }

    public void setProjectList(List<ProyectoDto> ProjectList) {
        this.ProjectList = ProjectList;
    }
}
