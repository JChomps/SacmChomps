package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TagsDto implements Serializable {
    @SuppressWarnings("compatibility:8291739700505703071")
    private static final long serialVersionUID = 1L;

    public TagsDto() {
        super();
    }
    private Integer idTag;
    private Integer idTagItem;
    private Integer idTagPadre;
    private Integer idTagHijo;
    private String tagName;
    private String descripcionTag;
    
    private Integer idTagN1;
    private String tagNameN1;
    
    private Integer idTagN2;
    private String tagNameN2;
    private String activoTag;
    
    
    private List<TagN2Dto> TagList = null;
   

  

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

   

 

    public Integer getIdTagN1() {
        return idTagN1;
    }

    public void setIdTagN1(Integer idTagN1) {
        this.idTagN1 = idTagN1;
    }

    public String getTagNameN1() {
        return tagNameN1;
    }

    public void setTagNameN1(String tagNameN1) {
        this.tagNameN1 = tagNameN1;
    }

    public Integer getIdTagN2() {
        return idTagN2;
    }

    public void setIdTagN2(Integer idTagN2) {
        this.idTagN2 = idTagN2;
    }

    public String getTagNameN2() {
        return tagNameN2;
    }

    public void setTagNameN2(String tagNameN2) {
        this.tagNameN2 = tagNameN2;
    }

    public Integer getIdTagHijo() {
        return idTagHijo;
    }

    public void setIdTagHijo(Integer idTagHijo) {
        this.idTagHijo = idTagHijo;
    }

    public String getActivo() {
        return activoTag;
    }

    public void setActivo(String activo) {
        this.activoTag = activo;
    }

   
    public String getDescripcionTag() {
        return descripcionTag;
    }

    public void setDescripcionTag(String descripcionTag) {
        this.descripcionTag = descripcionTag;
    }

    public List<TagN2Dto> getTagList() {
        return TagList;
    }

    public void setTagList(List<TagN2Dto> TagList) {
        this.TagList = TagList;
    }

    public Integer getIdTagPadre() {
        return idTagPadre;
    }

    public void setIdTagPadre(Integer idTagPadre) {
        this.idTagPadre = idTagPadre;
    }

    public Integer getIdTagItem() {
        return idTagItem;
    }

    public void setIdTagItem(Integer idTagItem) {
        this.idTagItem = idTagItem;
    }
}
