package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tag {
    public Tag() {
        super();
    }
    
    private Integer idTag;
    private String tagName;
    private List<TagN1> tagsListN1 = new ArrayList<TagN1>();
    private List<TagN1> tagsList = new ArrayList<TagN1>();
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


    public List<TagN1> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<TagN1> tagsList) {
        this.tagsList = tagsList;
    }
}
