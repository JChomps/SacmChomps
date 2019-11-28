package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

import java.util.Date;

public class PaisDto implements Serializable {
    @SuppressWarnings("compatibility:6273831597534316748")
    private static final long serialVersionUID = 1L;

    public PaisDto() {
        super();
    }
    private Integer idPais;
    private String descripcion;
    private String isoCode2;
    private String isoCode3;
    private String capital;
    private String codigoTelefono;
    private Integer activo;
    private String creadoPor;
    private Date fechaCreacion;
    private String modificadoPor;
    private Date fechaModificacion;

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIsoCode2(String isoCode2) {
        this.isoCode2 = isoCode2;
    }

    public String getIsoCode2() {
        return isoCode2;
    }

    public void setIsoCode3(String isoCode3) {
        this.isoCode3 = isoCode3;
    }

    public String getIsoCode3() {
        return isoCode3;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCapital() {
        return capital;
    }

    public void setCodigoTelefono(String codigoTelefono) {
        this.codigoTelefono = codigoTelefono;
    }

    public String getCodigoTelefono() {
        return codigoTelefono;
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
