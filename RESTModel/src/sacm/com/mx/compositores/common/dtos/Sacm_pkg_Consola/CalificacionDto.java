package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola;

public class CalificacionDto {
    private Integer id_usuario;
    private Integer id_obra;
    private Integer calificacion;
    public CalificacionDto() {
        super();
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

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }
}
