package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Inicio_Sesion;

import java.io.Serializable;

public class UsuarioDto implements Serializable {
    @SuppressWarnings("compatibility:-8315199454425542670")
    private static final long serialVersionUID = 1L;

    public UsuarioDto() {
        super();
    }

    
    private String email;
    private String password;
    private Integer id_usuario;
    private Integer ID_USUARIO;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String compania;
    private String puesto;
    private Integer id_sexo;
    private Integer id_pais;
    private Integer id_estado;
    private String municipio;
    private String codigo_postal;
    private String direccion;
    private String telefono;
    private String extension;
    private String estatus;
    private String passwordNuevo;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setID_USUARIO(Integer ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }

    public Integer getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getCompania() {
        return compania;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setId_sexo(Integer id_sexo) {
        this.id_sexo = id_sexo;
    }

    public Integer getId_sexo() {
        return id_sexo;
    }

    public void setId_pais(Integer id_pais) {
        this.id_pais = id_pais;
    }

    public Integer getId_pais() {
        return id_pais;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setPasswordNuevo(String passwordNuevo) {
        this.passwordNuevo = passwordNuevo;
    }

    public String getPasswordNuevo() {
        return passwordNuevo;
    }
}
