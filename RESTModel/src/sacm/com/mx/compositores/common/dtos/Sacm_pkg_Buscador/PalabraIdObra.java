package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;


import java.io.Serializable;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class PalabraIdObra implements Serializable {
    @SuppressWarnings("compatibility:-7830055730249044691")
    private static final long serialVersionUID = 1L;
    
    public PalabraIdObra() {
        super();
    }

    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private PalabraDto busqueda;    

  
    public PalabraDto getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(PalabraDto busqueda) {
        this.busqueda = busqueda;
    }

    public HeaderDto getResponseBD() {
        return ResponseBD;
    }

    public void setResponseBD(HeaderDto ResponseBD) {
        this.ResponseBD = ResponseBD;
    }

    public HeaderDto getResponseService() {
        return ResponseService;
    }

    public void setResponseService(HeaderDto ResponseService) {
        this.ResponseService = ResponseService;
    }
}
