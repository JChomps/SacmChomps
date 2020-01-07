package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;

import java.util.ArrayList;
import java.util.List;

public class PalabraDto {
    public PalabraDto() {
        super();
    }
    
    private String palabra;
    private Integer id_usuario;
    private Integer id_album;
    private Integer id_obra;
    private List<Integer> array_options = new ArrayList<Integer>();
    private List<Integer> Search = new ArrayList<Integer>();
   // private String search;

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

   

    public List<Integer> getArray_options() {
        return array_options;
    }

    public void setArray_options(List<Integer> array_options) {
        this.array_options = array_options;
    }

    
   

   

    public List<Integer> getSearch() {
        return Search;
    }

    public void setSearch(List<Integer> Search) {
        this.Search = Search;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_album() {
        return id_album;
    }

    public void setId_album(Integer id_album) {
        this.id_album = id_album;
    }

    public Integer getId_obra() {
        return id_obra;
    }

    public void setId_obra(Integer id_obra) {
        this.id_obra = id_obra;
    }
}
