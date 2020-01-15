package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;

public class TagN2Dto {
    
    private Integer idTag;  
    private String tagName;
    private String activoTag;
    private String descripcionTag;
    
    public TagN2Dto() {
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

    public String getActivoTag() {
        return activoTag;
    }

    public void setActivoTag(String activoTag) {
        this.activoTag = activoTag;
    }

    public String getDescripcionTag() {
        return descripcionTag;
    }

    public void setDescripcionTag(String descripcionTag) {
        this.descripcionTag = descripcionTag;
    }
}
