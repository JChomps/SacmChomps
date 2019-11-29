package sacm.com.mx.compositores.common.dtos;


public class CompObraDto {
    @SuppressWarnings("compatibility:1464663714482560769")
    private static final long serialVersionUID = 1L;

    public CompObraDto() {
        super();
    }
    
    private int idUserOrigen;
    private int idUserDestino;
    private int idObra;

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
}
