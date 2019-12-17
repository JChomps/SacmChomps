package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;

public class VersionDto {
    public VersionDto() {
        super();
    }
    private Integer version_id;
    private String version_titulo;
    private String version_descripcion;
    private String version_duracion;
    private String version_wav;
    private String version_mp3;
    private String version_aiff;
    private String version_lyric;
    private String version_type;

    public Integer getVersion_id() {
        return version_id;
    }

    public void setVersion_id(Integer version_id) {
        this.version_id = version_id;
    }

    public String getVersion_titulo() {
        return version_titulo;
    }

    public void setVersion_titulo(String version_titulo) {
        this.version_titulo = version_titulo;
    }

    public String getVersion_descripcion() {
        return version_descripcion;
    }

    public void setVersion_descripcion(String version_descripcion) {
        this.version_descripcion = version_descripcion;
    }

    public String getVersion_duracion() {
        return version_duracion;
    }

    public void setVersion_duracion(String version_duracion) {
        this.version_duracion = version_duracion;
    }

    public String getVersion_wav() {
        return version_wav;
    }

    public void setVersion_wav(String version_wav) {
        this.version_wav = version_wav;
    }

    public String getVersion_mp3() {
        return version_mp3;
    }

    public void setVersion_mp3(String version_mp3) {
        this.version_mp3 = version_mp3;
    }

    public String getVersion_aiff() {
        return version_aiff;
    }

    public void setVersion_aiff(String version_aiff) {
        this.version_aiff = version_aiff;
    }

    public String getVersion_lyric() {
        return version_lyric;
    }

    public void setVersion_lyric(String version_lyric) {
        this.version_lyric = version_lyric;
    }

    public String getVersion_type() {
        return version_type;
    }

    public void setVersion_type(String version_type) {
        this.version_type = version_type;
    }
}
