package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TagN2;

public class TagConsolaDto {
    private Integer idTag;
    private String tagName;
    private List<TagN2ConsolaDto> tagsList =null;// new ArrayList<TagN1>();
    public TagConsolaDto() {
        super();
    }

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

    public List<TagN2ConsolaDto> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<TagN2ConsolaDto> tagsList) {
        this.tagsList = tagsList;
    }
}
