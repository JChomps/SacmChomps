package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras;

public class CategoriaItemDto {
    public CategoriaItemDto() {
        super();
    }
    
    private Integer id_categoria_item;
    private String etiqueta;
    private Double valor;
    private Double valor_acumualdo;

    public Integer getId_categoria_item() {
        return id_categoria_item;
    }

    public void setId_categoria_item(Integer id_categoria_item) {
        this.id_categoria_item = id_categoria_item;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValor_acumualdo() {
        return valor_acumualdo;
    }

    public void setValor_acumualdo(Double valor_acumualdo) {
        this.valor_acumualdo = valor_acumualdo;
    }
}
