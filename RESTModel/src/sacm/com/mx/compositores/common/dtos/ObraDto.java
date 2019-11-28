package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

public class ObraDto implements Serializable {
    @SuppressWarnings("compatibility:-4925587381889895210")
    private static final long serialVersionUID = 1L;

    public ObraDto() {
        super();
    }
    private Integer idObra;
    private Integer numeroObra;
    private Integer idObraAlbum;
    private String tituloObra;
    private String descripcionObra;
    private Integer idVersion;
    private String tituloVersion;
    private String descripcionVersion;
    private String duracionVersion;
    private String wavVersion;
    private String mp3Version;
    private String aiffVersion;
    private String lyricVersion;
    private String typeVersion;

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

    public void setIdObraAlbum(Integer idObraAlbum) {
        this.idObraAlbum = idObraAlbum;
    }

    public Integer getIdObraAlbum() {
        return idObraAlbum;
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

    public void setIdVersion(Integer idVersion) {
        this.idVersion = idVersion;
    }

    public Integer getIdVersion() {
        return idVersion;
    }

    public void setTituloVersion(String tituloVersion) {
        this.tituloVersion = tituloVersion;
    }

    public String getTituloVersion() {
        return tituloVersion;
    }

    public void setDescripcionVersion(String descripcionVersion) {
        this.descripcionVersion = descripcionVersion;
    }

    public String getDescripcionVersion() {
        return descripcionVersion;
    }

    public void setDuracionVersion(String duracionVersion) {
        this.duracionVersion = duracionVersion;
    }

    public String getDuracionVersion() {
        return duracionVersion;
    }

    public void setWavVersion(String wavVersion) {
        this.wavVersion = wavVersion;
    }

    public String getWavVersion() {
        return wavVersion;
    }

    public void setMp3Version(String mp3Version) {
        this.mp3Version = mp3Version;
    }

    public String getMp3Version() {
        return mp3Version;
    }

    public void setAiffVersion(String aiffVersion) {
        this.aiffVersion = aiffVersion;
    }

    public String getAiffVersion() {
        return aiffVersion;
    }

    public void setLyricVersion(String lyricVersion) {
        this.lyricVersion = lyricVersion;
    }

    public String getLyricVersion() {
        return lyricVersion;
    }

    public void setTypeVersion(String typeVersion) {
        this.typeVersion = typeVersion;
    }

    public String getTypeVersion() {
        return typeVersion;
    }
}
