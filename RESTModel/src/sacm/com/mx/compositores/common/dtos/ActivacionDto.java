package sacm.com.mx.compositores.common.dtos;

public class ActivacionDto {
    
    @SuppressWarnings("compatibility:1464663714482560769")
    private static final long serialVersionUID = 1L;    
    
    public ActivacionDto() {
        super();
    }
    
    private String mail;

    public String getPimail() {
        return mail;
    }

    public void setPimail(String pimail) {
        this.mail = pimail;
    }
    }
