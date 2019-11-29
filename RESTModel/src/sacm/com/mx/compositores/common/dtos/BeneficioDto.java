package sacm.com.mx.compositores.common.dtos;


public class BeneficioDto {
    public BeneficioDto() {
        super();
    }
    
    private int idBeneficio;
    private String titulo;
    private String descripcion;

    public int getIdBeneficio() {
        return idBeneficio;
    }

    public void setIdBeneficio(int idBeneficio) {
        this.idBeneficio = idBeneficio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
