package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola;

import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class CalificacionResultDto {
    
    private Integer calificacion;
    private String calificado;   
    private Integer promedio;
    private List<CalificacionDto> obras= null;
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
   
        
    public CalificacionResultDto() {
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

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getCalificado() {
        return calificado;
    }

    public void setCalificado(String calificado) {
        this.calificado = calificado;
    }

    public Integer getPromedio() {
        return promedio;
    }

    public void setPromedio(Integer promedio) {
        this.promedio = promedio;
    }

    public List<CalificacionDto> getObras() {
        return obras;
    }

    public void setObras(List<CalificacionDto> obras) {
        this.obras = obras;
    }
}
