package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class SubProyectoResultDto {
    public SubProyectoResultDto() {
        super();
    }
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<SubProyectoDto> ProjectList = new ArrayList<SubProyectoDto>();

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

    public List<SubProyectoDto> getProjectList() {
        return ProjectList;
    }

    public void setProjectList(List<SubProyectoDto> ProjectList) {
        this.ProjectList = ProjectList;
    }
}
