package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario;

import java.io.Serializable;

import java.util.Date;

public class SexoDto implements Serializable {
    @SuppressWarnings("compatibility:-1041108575463771118")
    private static final long serialVersionUID = 1L;

    public SexoDto() {
        super();
    }
    private Integer id_sexo;
    private String identificador;
    private String descripcion;
    private Integer activo;

    public void setId_sexo(Integer id_sexo) {
        this.id_sexo = id_sexo;
    }

    public Integer getId_sexo() {
        return id_sexo;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getActivo() {
        return activo;
    }
}
