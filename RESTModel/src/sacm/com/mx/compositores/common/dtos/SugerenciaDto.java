package sacm.com.mx.compositores.common.dtos;

import java.util.ArrayList;
import java.util.List;

public class SugerenciaDto {
    public SugerenciaDto() {
        super();
    }

    private Integer id_Obra;
    private Integer numero_obra;
    private String titulo_Obra;
    private String descripcion_Obra;
    private Integer duracion_Obra;
    private String lyric_Obra;
    private Integer id_Album;
    private String nombre_Album;    
    private String descripcion_Album; 
    private List<NombreParticipante> participante = new ArrayList<NombreParticipante>();
    private List<Tag> tagsList = new ArrayList<Tag>();


    public Integer getId_Obra() {
        return id_Obra;
    }

    public void setId_Obra(Integer id_Obra) {
        this.id_Obra = id_Obra;
    }

    public Integer getNumero_obra() {
        return numero_obra;
    }

    public void setNumero_obra(Integer numero_obra) {
        this.numero_obra = numero_obra;
    }

    public String getTitulo_Obra() {
        return titulo_Obra;
    }

    public void setTitulo_Obra(String titulo_Obra) {
        this.titulo_Obra = titulo_Obra;
    }

    public String getDescripcion_Obra() {
        return descripcion_Obra;
    }

    public void setDescripcion_Obra(String descripcion_Obra) {
        this.descripcion_Obra = descripcion_Obra;
    }

    public Integer getDuracion_Obra() {
        return duracion_Obra;
    }

    public void setDuracion_Obra(Integer duracion_Obra) {
        this.duracion_Obra = duracion_Obra;
    }

    public String getLyric_Obra() {
        return lyric_Obra;
    }

    public void setLyric_Obra(String lyric_Obra) {
        this.lyric_Obra = lyric_Obra;
    }

    public Integer getId_Album() {
        return id_Album;
    }

    public void setId_Album(Integer id_Album) {
        this.id_Album = id_Album;
    }

    public String getNombre_Album() {
        return nombre_Album;
    }

    public void setNombre_Album(String nombre_Album) {
        this.nombre_Album = nombre_Album;
    }

    public String getDescripcion_Album() {
        return descripcion_Album;
    }

    public void setDescripcion_Album(String descripcion_Album) {
        this.descripcion_Album = descripcion_Album;
    }

    public List<NombreParticipante> getParticipante() {
        return participante;
    }

    public void setParticipante(List<NombreParticipante> participante) {
        this.participante = participante;
    }

    public List<Tag> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<Tag> tagsList) {
        this.tagsList = tagsList;
    }
}
