package sacm.com.mx.compositores.common.dtos;

public class AlbumDto {
    public AlbumDto() {
        super();
    }
    
    private Integer id_album;
    private String nombre_album;
    private String descripcion_album;
    private String imagen_album;

    public Integer getId_album() {
        return id_album;
    }

    public void setId_album(Integer id_album) {
        this.id_album = id_album;
    }

    public String getNombre_album() {
        return nombre_album;
    }

    public void setNombre_album(String nombre_album) {
        this.nombre_album = nombre_album;
    }

    public String getAlbum_descripcion() {
        return descripcion_album;
    }

    public void setAlbum_descripcion(String album_descripcion) {
        this.descripcion_album = album_descripcion;
    }

    public String getImagen_album() {
        return imagen_album;
    }

    public void setImagen_album(String imagen_album) {
        this.imagen_album = imagen_album;
    }
}
