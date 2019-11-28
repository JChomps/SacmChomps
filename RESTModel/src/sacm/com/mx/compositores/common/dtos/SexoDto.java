package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

import java.util.Date;

public class SexoDto implements Serializable {
    @SuppressWarnings("compatibility:-1041108575463771118")
    private static final long serialVersionUID = 1L;

    public SexoDto() {
        super();
    }
    private Integer idSexo;
    private String identificador;
    private String descripcion;
    private Integer activo;
    private String creadoPor;
    private Date fechaCreacion;
    private String modificadoPor;
    private Date fechaModificacion;

    public void setIdSexo(Integer idSexo) {
        this.idSexo = idSexo;
    }

    public Integer getIdSexo() {
        return idSexo;
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

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }
}
