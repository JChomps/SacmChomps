package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

import java.util.Date;

public class EstadoDto implements Serializable {
    @SuppressWarnings("compatibility:1464663714482560769")
    private static final long serialVersionUID = 1L;

    public EstadoDto() {
        super();
    }
    private Integer idEstado;
    private Integer idPais;
    private String estado;
    private String pais;
    private Integer activo;
    private String creadoPor;
    private Date fechaCreacion;
    private String modificadoPor;
    private Date fechaModificacion;

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPais() {
        return pais;
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
