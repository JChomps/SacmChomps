package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

import java.sql.Clob;

import java.util.Date;

public class LegalesDto implements Serializable {
    @SuppressWarnings("compatibility:7384503130806884086")
    private static final long serialVersionUID = 1L;

    public LegalesDto() {
        super();
    }
    private Integer id_legal;
    private String tituloTerminos;
    private String mensajeTerminos;
    private String tituloAviso;
    private String mensajeAviso;

    public void setId_legal(Integer id_legal) {
        this.id_legal = id_legal;
    }

    public Integer getId_legal() {
        return id_legal;
    }

    public void setTituloTerminos(String tituloTerminos) {
        this.tituloTerminos = tituloTerminos;
    }

    public String getTituloTerminos() {
        return tituloTerminos;
    }

    public void setMensajeTerminos(String mensajeTerminos) {
        this.mensajeTerminos = mensajeTerminos;
    }

    public String getMensajeTerminos() {
        return mensajeTerminos;
    }

    public void setTituloAviso(String tituloAviso) {
        this.tituloAviso = tituloAviso;
    }

    public String getTituloAviso() {
        return tituloAviso;
    }

    public void setMensajeAviso(String mensajeAviso) {
        this.mensajeAviso = mensajeAviso;
    }

    public String getMensajeAviso() {
        return mensajeAviso;
    }
}
