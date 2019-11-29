package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

import java.util.Date;

public class TagsDto implements Serializable {
    @SuppressWarnings("compatibility:8291739700505703071")
    private static final long serialVersionUID = 1L;

    public TagsDto() {
        super();
    }
    private Integer idTag;
    private String tagName;
    private Integer idTagN1;
    private String tagNameN1;
    private Integer idTagN2;
    private String tagNameN2;

    public void setIdTag(Integer idTag) {
        this.idTag = idTag;
    }

    public Integer getIdTag() {
        return idTag;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setIdTagN1(Integer idTagN1) {
        this.idTagN1 = idTagN1;
    }

    public Integer getIdTagN1() {
        return idTagN1;
    }

    public void setTagNameN1(String tagNameN1) {
        this.tagNameN1 = tagNameN1;
    }

    public String getTagNameN1() {
        return tagNameN1;
    }

    public void setIdTagN2(Integer idTagN2) {
        this.idTagN2 = idTagN2;
    }

    public Integer getIdTagN2() {
        return idTagN2;
    }

    public void setTagNameN2(String tagNameN2) {
        this.tagNameN2 = tagNameN2;
    }

    public String getTagNameN2() {
        return tagNameN2;
    }
}