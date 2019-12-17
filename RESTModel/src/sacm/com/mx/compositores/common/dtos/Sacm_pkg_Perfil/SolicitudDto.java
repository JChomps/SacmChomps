package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.CotizacionDto;

public class SolicitudDto {
    private String tipo;
    private String title;
    private List<CotizacionDto> Items = new ArrayList<CotizacionDto>();

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CotizacionDto> getItems() {
        return Items;
    }

    public void setItems(List<CotizacionDto> Items) {
        this.Items = Items;
    }
}
