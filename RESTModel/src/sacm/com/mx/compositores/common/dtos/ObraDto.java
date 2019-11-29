package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

public class ObraDto implements Serializable {
    @SuppressWarnings("compatibility:-4925587381889895210")
    private static final long serialVersionUID = 1L;

    public ObraDto() {
        super();
    }
    private Integer id_obra;
    private Integer obra_numero;
    private Integer obra_id_album;
    private String obra_titulo;
    private String obra_descripcion;
    private Integer versión_id;
    private String versión_titulo;
    private String versión_descripcion;
    private String versión_duracion;
    private String versión_wav;
    private String versión_mp3;
    private String versión_aiff;
    private String versión_lyric;
    private String versión_type;

    public void setId_obra(Integer id_obra) {
        this.id_obra = id_obra;
    }

    public Integer getId_obra() {
        return id_obra;
    }

    public void setObra_numero(Integer obra_numero) {
        this.obra_numero = obra_numero;
    }

    public Integer getObra_numero() {
        return obra_numero;
    }

    public void setObra_id_album(Integer obra_id_album) {
        this.obra_id_album = obra_id_album;
    }

    public Integer getObra_id_album() {
        return obra_id_album;
    }

    public void setObra_titulo(String obra_titulo) {
        this.obra_titulo = obra_titulo;
    }

    public String getObra_titulo() {
        return obra_titulo;
    }

    public void setObra_descripcion(String obra_descripcion) {
        this.obra_descripcion = obra_descripcion;
    }

    public String getObra_descripcion() {
        return obra_descripcion;
    }

    public void setVersión_id(Integer versión_id) {
        this.versión_id = versión_id;
    }

    public Integer getVersión_id() {
        return versión_id;
    }

    public void setVersión_titulo(String versión_titulo) {
        this.versión_titulo = versión_titulo;
    }

    public String getVersión_titulo() {
        return versión_titulo;
    }

    public void setVersión_descripcion(String versión_descripcion) {
        this.versión_descripcion = versión_descripcion;
    }

    public String getVersión_descripcion() {
        return versión_descripcion;
    }

    public void setVersión_duracion(String versión_duracion) {
        this.versión_duracion = versión_duracion;
    }

    public String getVersión_duracion() {
        return versión_duracion;
    }

    public void setVersión_wav(String versión_wav) {
        this.versión_wav = versión_wav;
    }

    public String getVersión_wav() {
        return versión_wav;
    }

    public void setVersión_mp3(String versión_mp3) {
        this.versión_mp3 = versión_mp3;
    }

    public String getVersión_mp3() {
        return versión_mp3;
    }

    public void setVersión_aiff(String versión_aiff) {
        this.versión_aiff = versión_aiff;
    }

    public String getVersión_aiff() {
        return versión_aiff;
    }

    public void setVersión_lyric(String versión_lyric) {
        this.versión_lyric = versión_lyric;
    }

    public String getVersión_lyric() {
        return versión_lyric;
    }

    public void setVersión_type(String versión_type) {
        this.versión_type = versión_type;
    }

    public String getVersión_type() {
        return versión_type;
    }
}
