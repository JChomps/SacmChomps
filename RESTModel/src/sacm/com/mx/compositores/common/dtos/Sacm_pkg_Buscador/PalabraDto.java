package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;

import java.util.ArrayList;
import java.util.List;

public class PalabraDto {
    public PalabraDto() {
        super();
    }
    
    private String palabra;
    private int id_usuario;
    private int id_album;
    private int id_obra;
    private List<Integer> array_options = new ArrayList<Integer>();
    private String search;

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getId_album() {
        return id_album;
    }

    public void setId_album(int id_album) {
        this.id_album = id_album;
    }

    public List<Integer> getArray_options() {
        return array_options;
    }

    public void setArray_options(List<Integer> array_options) {
        this.array_options = array_options;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_obra() {
        return id_obra;
    }

    public void setId_obra(int id_obra) {
        this.id_obra = id_obra;
    }
}
