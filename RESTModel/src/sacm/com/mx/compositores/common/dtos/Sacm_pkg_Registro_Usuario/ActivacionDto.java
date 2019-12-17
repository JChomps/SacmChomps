package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario;

public class ActivacionDto {
    
    @SuppressWarnings("compatibility:1464663714482560769")
    private static final long serialVersionUID = 1L;    
    
    public ActivacionDto() {
        super();
    }
    
    private String mail;
    private int id_Usuario;

    public String getPimail() {
        return mail;
    }

    public void setPimail(String pimail) {
        this.mail = pimail;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }
}
