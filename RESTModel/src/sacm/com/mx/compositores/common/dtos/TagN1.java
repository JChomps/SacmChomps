package sacm.com.mx.compositores.common.dtos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TagN1 {
    public TagN1() {
        super();
    }
    
    private int idTagN1;
    private String tagNameN1;
    private List<TagN2> tagsListN2 = new ArrayList<TagN2>();
   // private Map<String, TagN2> tagsMapN2 = new HashMap<String, TagN2>();

   

    public int getId_TagN1() {
        return idTagN1;
    }

    public void setId_TagN1(int id_TagN1) {
        this.idTagN1 = id_TagN1;
    }

    public String getNombre_TagN1() {
        return tagNameN1;
    }

    public void setNombre_TagN1(String nombre_TagN2) {
        this.tagNameN1 = nombre_TagN2;
    }


    public List<TagN2> getTagsListN2() {
        return tagsListN2;
    }

    public void setTagsListN2(List<TagN2> tagsListN2) {
        this.tagsListN2 = tagsListN2;
    }
}
