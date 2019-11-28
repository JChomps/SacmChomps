package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

public class MetadataDto implements Serializable {
    @SuppressWarnings("compatibility:5986417196412719825")
    private static final long serialVersionUID = 1L;

    public MetadataDto() {
        super();
    }
    private Integer idObra;
    private Integer numeroObra;
    private String tituloObra;
    private String descripcionObra;
    private Integer idTag;
    private String nameTag;
    private Integer idTagItem;
    private String nameTagItem;
    private Integer idTagPadre;
    private String estado;

    public void setIdObra(Integer idObra) {
        this.idObra = idObra;
    }

    public Integer getIdObra() {
        return idObra;
    }

    public void setNumeroObra(Integer numeroObra) {
        this.numeroObra = numeroObra;
    }

    public Integer getNumeroObra() {
        return numeroObra;
    }

    public void setTituloObra(String tituloObra) {
        this.tituloObra = tituloObra;
    }

    public String getTituloObra() {
        return tituloObra;
    }

    public void setDescripcionObra(String descripcionObra) {
        this.descripcionObra = descripcionObra;
    }

    public String getDescripcionObra() {
        return descripcionObra;
    }

    public void setIdTag(Integer idTag) {
        this.idTag = idTag;
    }

    public Integer getIdTag() {
        return idTag;
    }

    public void setNameTag(String nameTag) {
        this.nameTag = nameTag;
    }

    public String getNameTag() {
        return nameTag;
    }

    public void setIdTagItem(Integer idTagItem) {
        this.idTagItem = idTagItem;
    }

    public Integer getIdTagItem() {
        return idTagItem;
    }

    public void setNameTagItem(String nameTagItem) {
        this.nameTagItem = nameTagItem;
    }

    public String getNameTagItem() {
        return nameTagItem;
    }

    public void setIdTagPadre(Integer idTagPadre) {
        this.idTagPadre = idTagPadre;
    }

    public Integer getIdTagPadre() {
        return idTagPadre;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
