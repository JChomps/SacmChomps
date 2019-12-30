package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil;

public class GiroDto {
    private Integer id_giro;
    private String descripcion;
    
    public GiroDto() {
        super();
    }

    public Integer getId_giro() {
        return id_giro;
    }

    public void setId_giro(Integer id_giro) {
        this.id_giro = id_giro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
