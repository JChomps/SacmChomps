package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

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
    private List<Tag> tagsList = new ArrayList<Tag>();

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

    public List<Tag> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<Tag> tagsList) {
        this.tagsList = tagsList;
    }
}
