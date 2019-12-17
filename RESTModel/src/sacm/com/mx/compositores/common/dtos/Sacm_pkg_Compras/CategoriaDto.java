package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDto {
    public CategoriaDto() {
        super();
    }
    private Integer id_categoria;
    private String descripcion;
    private List<CategoriaItemDto> items = new ArrayList<CategoriaItemDto>();

    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<CategoriaItemDto> getItems() {
        return items;
    }

    public void setItems(List<CategoriaItemDto> items) {
        this.items = items;
    }
}
