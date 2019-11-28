package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

public class UsuarioDto implements Serializable {
    @SuppressWarnings("compatibility:-8315199454425542670")
    private static final long serialVersionUID = 1L;

    public UsuarioDto() {
        super();
    }

    private Integer idUsuario;
    private String nombre;
    private String paterno;
    private String materno;
    private String password;
    private String passwordNuevo;
    private String email;
    private String compania;
    private String puesto;
    private Integer idSexo;
    private Integer idPais;
    private Integer idEstado;
    private String municipio;
    private String codigoPostal;
    private String direccion;
    private String telefono;
    private String extension;
    private String estatus;

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPasswordNuevo(String passwordNuevo) {
        this.passwordNuevo = passwordNuevo;
    }

    public String getPasswordNuevo() {
        return passwordNuevo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
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

    public void setIdSexo(Integer idSexo) {
        this.idSexo = idSexo;
    }

    public Integer getIdSexo() {
        return idSexo;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCodigoPostal() {
        return codigoPostal;
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
}
