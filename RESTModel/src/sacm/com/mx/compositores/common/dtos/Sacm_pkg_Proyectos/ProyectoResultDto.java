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
    private Integer id_proyecto;
    private Integer id_usuario;
    private Integer id_obra;
    private String titulo_obra;
    private Integer id_estatus;
    private String estatus;
    private String nombre;
    
    
    private List<ProyectoDto> ProjectList =null;// new ArrayList<ProyectoDto>();

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

    public Integer getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(Integer id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_obra() {
        return id_obra;
    }

    public void setId_obra(Integer id_obra) {
        this.id_obra = id_obra;
    }

    public String getTitulo_obra() {
        return titulo_obra;
    }

    public void setTitulo_obra(String titulo_obra) {
        this.titulo_obra = titulo_obra;
    }

    public Integer getId_estatus() {
        return id_estatus;
    }

    public void setId_estatus(Integer id_estatus) {
        this.id_estatus = id_estatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
