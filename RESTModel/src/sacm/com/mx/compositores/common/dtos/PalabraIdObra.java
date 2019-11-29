package sacm.com.mx.compositores.common.dtos;


import java.io.Serializable;

public class PalabraIdObra implements Serializable {
    @SuppressWarnings("compatibility:-7830055730249044691")
    private static final long serialVersionUID = 1L;
    
    public PalabraIdObra() {
        super();
    }

    private HeaderDto headerRequest;
    private HeaderDto headerResponse = new HeaderDto();
    private PalabraDto busqueda;
    

    public HeaderDto getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderRequest(HeaderDto headerRequest) {
        this.headerRequest = headerRequest;
    }

    public HeaderDto getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderDto headerResponse) {
        this.headerResponse = headerResponse;
    }

    public PalabraDto getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(PalabraDto busqueda) {
        this.busqueda = busqueda;
    }
}
