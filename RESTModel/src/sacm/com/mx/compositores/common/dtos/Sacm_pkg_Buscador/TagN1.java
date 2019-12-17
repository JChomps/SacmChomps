package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TagN1 {
    public TagN1() {
        super();
    }
    
  

    private Integer idTag = 2    ;
    private String tagName= "121";
    private List<TagN2> tagsList = new ArrayList<TagN2>();

   

    public Integer getIdTag() {
        return idTag;
    }

    public void setIdTag(Integer idTag) {
        this.idTag = idTag;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<TagN2> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<TagN2> tagsList) {
        this.tagsList = tagsList;
    }
}
