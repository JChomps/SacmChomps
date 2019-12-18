package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class ProyectoPadreResultDto {
    public ProyectoPadreResultDto() {
        super();
    }
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private Integer id_proyecto;
    private String nombre;
    private List<SubProyectoDto> SubProjectList = new ArrayList<SubProyectoDto>();

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<SubProyectoDto> getSubProjectList() {
        return SubProjectList;
    }

    public void setSubProjectList(List<SubProyectoDto> SubProjectList) {
        this.SubProjectList = SubProjectList;
    }
}
