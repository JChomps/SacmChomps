package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario;

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
    private String titulo_terminos;
    private String mensaje_terminos;
    private String titulo_aviso;
    private String mensaje_aviso;

    public void setId_legal(Integer id_legal) {
        this.id_legal = id_legal;
    }

    public Integer getId_legal() {
        return id_legal;
    }

    public void setTitulo_terminos(String titulo_terminos) {
        this.titulo_terminos = titulo_terminos;
    }

    public String getTitulo_terminos() {
        return titulo_terminos;
    }

    public void setMensaje_terminos(String mensaje_terminos) {
        this.mensaje_terminos = mensaje_terminos;
    }

    public String getMensaje_terminos() {
        return mensaje_terminos;
    }

    public void setTitulo_aviso(String titulo_aviso) {
        this.titulo_aviso = titulo_aviso;
    }

    public String getTitulo_aviso() {
        return titulo_aviso;
    }

    public void setMensaje_aviso(String mensaje_aviso) {
        this.mensaje_aviso = mensaje_aviso;
    }

    public String getMensaje_aviso() {
        return mensaje_aviso;
    }
}
