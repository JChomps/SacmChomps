package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

import java.util.Date;

public class EstadoDto implements Serializable {
    @SuppressWarnings("compatibility:1464663714482560769")
    private static final long serialVersionUID = 1L;

    public EstadoDto() {
        super();
    }
    private Integer id_pais;
    private String pais;
    private Integer id_estado;
    private String estado;
    private Integer activo;

    public void setId_pais(Integer id_pais) {
        this.id_pais = id_pais;
    }

    public Integer getId_pais() {
        return id_pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPais() {
        return pais;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getActivo() {
        return activo;
    }
}
