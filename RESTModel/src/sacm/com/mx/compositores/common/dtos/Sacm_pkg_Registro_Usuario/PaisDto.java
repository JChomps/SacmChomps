package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario;

import java.io.Serializable;

import java.util.Date;

public class PaisDto implements Serializable {
    @SuppressWarnings("compatibility:6273831597534316748")
    private static final long serialVersionUID = 1L;

    public PaisDto() {
        super();
    }
    private Integer id_pais;
    private String descripcion;
    private String capital;
    private String isocode2;
    private String isocode3;
    private String codigo_tel;

    public void setId_pais(Integer id_pais) {
        this.id_pais = id_pais;
    }

    public Integer getId_pais() {
        return id_pais;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCapital() {
        return capital;
    }

    public void setIsocode2(String isocode2) {
        this.isocode2 = isocode2;
    }

    public String getIsocode2() {
        return isocode2;
    }

    public void setIsocode3(String isocode3) {
        this.isocode3 = isocode3;
    }

    public String getIsocode3() {
        return isocode3;
    }

    public void setCodigo_tel(String codigo_tel) {
        this.codigo_tel = codigo_tel;
    }

    public String getCodigo_tel() {
        return codigo_tel;
    }
}
