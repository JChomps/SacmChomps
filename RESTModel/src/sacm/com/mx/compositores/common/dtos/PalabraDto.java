package sacm.com.mx.compositores.common.dtos;

public class PalabraDto {
    @SuppressWarnings("compatibility:-7830055730249044691")
    private static final long serialVersionUID = 1L;
    public PalabraDto() {
        super();
    }
    
    private String palabra;

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
}
