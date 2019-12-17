package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class CarritoResultDto {
    public CarritoResultDto() {
        super();
    }
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<CarritoDto> Carrito = new ArrayList<CarritoDto>();

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

    public List<CarritoDto> getCarrito() {
        return Carrito;
    }

    public void setCarrito(List<CarritoDto> Carrito) {
        this.Carrito = Carrito;
    }
}
