package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;


public class CompObraDto {
    @SuppressWarnings("compatibility:1464663714482560769")
    private static final long serialVersionUID = 1L;

    public CompObraDto() {
        super();
    }
    
    private Integer idUserOrigen;
    private Integer idUserDestino;
    private String email_origen;
    private String email_destino;
    private Integer idObra;

    public int getIdUserOrigen() {
        return idUserOrigen;
    }

    public void setIdUserOrigen(int idUserOrigen) {
        this.idUserOrigen = idUserOrigen;
    }

    public int getIdUserDestino() {
        return idUserDestino;
    }

    public void setIdUserDestino(int idUserDestino) {
        this.idUserDestino = idUserDestino;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public String getEmail_origen() {
        return email_origen;
    }

    public void setEmail_origen(String email_origen) {
        this.email_origen = email_origen;
    }

    public String getEmail_destino() {
        return email_destino;
    }

    public void setEmail_destino(String email_destino) {
        this.email_destino = email_destino;
    }
}
