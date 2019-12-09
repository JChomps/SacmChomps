package sacm.com.mx.compositores.common.dtos;

public class TagN2 {
    public TagN2() {
        super();
    }
    private String idTagN2;
    private String tagNameN2;

    public String getId_TagN2() {
        return idTagN2;
    }

    public void setId_TagN2(String idTagN2) {
        if(idTagN2!="0")
        this.idTagN2 = idTagN2;
        else
        this.idTagN2 =null;
    }

    public String getNombreTagN2() {
        return tagNameN2;
    }

    public void setNombreTagN2(String nombreTagN2) {
        this.tagNameN2 = nombreTagN2;
    }

   
}
