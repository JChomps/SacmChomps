package sacm.com.mx.compositores.common.dtos;

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
   // private Map<String, TagN1> tagsMapN1 = new HashMap<String, TagN1>();
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


    public List<TagN1> getTagsListN1() {
        return tagsListN1;
    }

    public void setTagsListN1(List<TagN1> tagsListN1) {
        this.tagsListN1 = tagsListN1;
    }
}
