package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;


public class CategoriaResultDto {
    public CategoriaResultDto() {
        super();
    }
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<CategoriaDto> Categorias =null;// new ArrayList<CategoriaDto>();
    private List<CategoriaItemDto> items = null;


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

    public List<CategoriaDto> getCategorias() {
        return Categorias;
    }

    public void setCategorias(List<CategoriaDto> Categorias) {
        this.Categorias = Categorias;
    }

    public List<CategoriaItemDto> getItems() {
        return items;
    }

    public void setItems(List<CategoriaItemDto> items) {
        this.items = items;
    }
}
