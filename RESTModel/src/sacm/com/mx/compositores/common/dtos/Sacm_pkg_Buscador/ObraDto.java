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
    private String Imag;
    private Integer version_id;
    private String version_titulo;
    private String version_descripcion;
    private String version_duracion;
    private String version_wav;
    private String version_mp3;
    private String version_aiff;
    private String version_lyric;
    private String version_type;
    private Integer carga_audio;
    private String wav;
    private String mp3;
    private String aiff;
    private String lyric;
    private Integer idTagItem;
    private Integer consagrada;
    private String obra_consagrada;
    private Integer control;   
    private Integer preAutorizacion;
    private String obra_preAutorizado;
    private Integer activo; //
    private String obra_activo;
    private Integer duracion;
    private String picture;
    private String control_str;
    private String autorizado; //
    private Integer id_participante;
    private String participante;
    private String consagrado;
    private String formato_audio;
    private String formato;
    private Integer calificacion;
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

    public Integer getCarga_audio() {
        return carga_audio;
    }

    public void setCarga_audio(Integer carga_audio) {
        this.carga_audio = carga_audio;
    }

    public String getWav() {
        return wav;
    }

    public void setWav(String wav) {
        this.wav = wav;
    }

    public String getMp3() {
        return mp3;
    }

    public void setMp3(String mp3) {
        this.mp3 = mp3;
    }

    public String getAiff() {
        return aiff;
    }

    public void setAiff(String aiff) {
        this.aiff = aiff;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public Integer getConsagrada() {
        return consagrada;
    }

    public void setConsagrada(Integer consagrada) {
        this.consagrada = consagrada;
    }

    public Integer getControl() {
        return control;
    }

    public void setControl(Integer control) {
        this.control = control;
    }

    public Integer getPreAutorizacion() {
        return preAutorizacion;
    }

    public void setPreAutorizacion(Integer preAutorizacion) {
        this.preAutorizacion = preAutorizacion;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getObra_consagrada() {
        return obra_consagrada;
    }

    public void setObra_consagrada(String obra_consagrada) {
        this.obra_consagrada = obra_consagrada;
    }

    public String getObra_preAutorizado() {
        return obra_preAutorizado;
    }

    public void setObra_preAutorizado(String obra_preAutorizado) {
        this.obra_preAutorizado = obra_preAutorizado;
    }

    public String getObra_activo() {
        return obra_activo;
    }

    public void setObra_activo(String obra_activo) {
        this.obra_activo = obra_activo;
    }

    public Integer getIdTagItem() {
        return idTagItem;
    }

    public void setIdTagItem(Integer idTagItem) {
        this.idTagItem = idTagItem;
    }

    public String getImag() {
        return Imag;
    }

    public void setImag(String Imag) {
        this.Imag = Imag;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getControl_str() {
        return control_str;
    }

    public void setControl_str(String control_str) {
        this.control_str = control_str;
    }

    public String getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(String autorizado) {
        this.autorizado = autorizado;
    }

    public Integer getId_participante() {
        return id_participante;
    }

    public void setId_participante(Integer id_participante) {
        this.id_participante = id_participante;
    }

    public String getParticipante() {
        return participante;
    }

    public void setParticipante(String participante) {
        this.participante = participante;
    }

    public String getConsagrado() {
        return consagrado;
    }

    public void setConsagrado(String consagrado) {
        this.consagrado = consagrado;
    }

    public String getFormato_audio() {
        return formato_audio;
    }

    public void setFormato_audio(String formato_audio) {
        this.formato_audio = formato_audio;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }
}
