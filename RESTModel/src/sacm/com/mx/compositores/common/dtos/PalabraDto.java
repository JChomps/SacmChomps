package sacm.com.mx.compositores.common.dtos;

import java.util.ArrayList;
import java.util.List;

public class PalabraDto {
    @SuppressWarnings("compatibility:-7830055730249044691")
    private static final long serialVersionUID = 1L;
    public PalabraDto() {
        super();
    }
    
    private String palabra;
    private int id_album;
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
}
