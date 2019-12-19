package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class ObraDto implements Serializable {
    @SuppressWarnings("compatibility:-4925587381889895210")
    private static final long serialVersionUID = 1L;

    public ObraDto() {
        super();
    }
    private Integer id_obra;
    private Integer id_album;
    private String obra_nombre;
    private Integer obra_numero;    
    private Integer obra_id_album;
    private String obra_titulo;
    private String obra_descripcion;
    private String imagen;
    private Integer version_id;
    private String version_titulo;
    private String version_descripcion;
    private String version_duracion;
    private String version_wav;
    private String version_mp3;
    private String version_aiff;
    private String version_lyric;
    private String version_type;
    private List<VersionDto> versiones = null;// new ArrayList<VersionDto>();
   

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

    public void setVersion_id(Integer version_id) {
        this.version_id = version_id;
    }

    public Integer getVersion_id() {
        return version_id;
    }

    public void setVersion_titulo(String version_titulo) {
        this.version_titulo = version_titulo;
    }

    public String getVersion_titulo() {
        return version_titulo;
    }

    public void setVersion_descripcion(String version_descripcion) {
        this.version_descripcion = version_descripcion;
    }

    public String getVersion_descripcion() {
        return version_descripcion;
    }

    public void setVersion_duracion(String version_duracion) {
        this.version_duracion = version_duracion;
    }

    public String getVersion_duracion() {
        return version_duracion;
    }

    public void setVersion_wav(String version_wav) {
        this.version_wav = version_wav;
    }

    public String getVersion_wav() {
        return version_wav;
    }

    public void setVersion_mp3(String version_mp3) {
        this.version_mp3 = version_mp3;
    }

    public String getVersion_mp3() {
        return version_mp3;
    }

    public void setVersion_aiff(String version_aiff) {
        this.version_aiff = version_aiff;
    }

    public String getVersion_aiff() {
        return version_aiff;
    }

    public void setVersion_lyric(String version_lyric) {
        this.version_lyric = version_lyric;
    }

    public String getVersion_lyric() {
        return version_lyric;
    }

    public void setVersion_type(String version_type) {
        this.version_type = version_type;
    }

    public String getVersion_type() {
        return version_type;
    }

    public String get_Imagen() {
        return imagen;
    }

    public void set_Imagen(String imagen) {
        this.imagen = imagen;
    }

   

    public String getObra_nombre() {
        return obra_nombre;
    }

    public void setObra_nombre(String obra_nombre) {
        this.obra_nombre = obra_nombre;
    }

    public Integer getId_album() {
        return id_album;
    }

    public void setId_album(Integer id_album) {
        this.id_album = id_album;
    }

    public List<VersionDto> getVersiones() {
        return versiones;
    }

    public void setVersiones(List<VersionDto> versiones) {
        this.versiones = versiones;
    }
}
