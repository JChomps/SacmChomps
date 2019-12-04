package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class TrackInfoDto implements Serializable {
    @SuppressWarnings("compatibility:8331476752143004815")
    private static final long serialVersionUID = 1L;

    public TrackInfoDto() {
        super();
    }
    private Integer idObra;
    private Integer numeroObra;
    private String tituloObra;
    private String descripcionObra;
    private Integer idAlbum;
    private String nombreAlbum;
    private List<ParticipanteDto> participante = new ArrayList<ParticipanteDto>();
    

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

    public void setIdAlbum(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Integer getIdAlbum() {
        return idAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }


    public List<ParticipanteDto> getParticipante() {
        return participante;
    }

    public void setParticipante(List<ParticipanteDto> participante) {
        this.participante = participante;
    }
}
