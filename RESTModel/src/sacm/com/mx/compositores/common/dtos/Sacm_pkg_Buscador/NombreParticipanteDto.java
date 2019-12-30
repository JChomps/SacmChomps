package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;

public class NombreParticipanteDto {
    public NombreParticipanteDto() {
        super();
    }
    private Integer id_participante;
    private Integer id_obra;
    private String nombre;
    private String segundo_nombre;
    private String apellido_materno;
    private String apellido_paterno;
    private String email;
    private String consagrado;
    private String activo;

    public int getId_participante() {
        return id_participante;
    }

    public void setId_participante(int id_participante) {
        this.id_participante = id_participante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getSegundo_nombre() {
        return segundo_nombre;
    }

    public void setSegundo_nombre(String segundo_nombre) {
        this.segundo_nombre = segundo_nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConsagrado() {
        return consagrado;
    }

    public void setConsagrado(String consagrado) {
        this.consagrado = consagrado;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Integer getId_obra() {
        return id_obra;
    }

    public void setId_obra(Integer id_obra) {
        this.id_obra = id_obra;
    }
}
