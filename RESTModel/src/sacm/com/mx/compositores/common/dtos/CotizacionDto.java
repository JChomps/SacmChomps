package sacm.com.mx.compositores.common.dtos;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraDto;

public class CotizacionDto {
    public CotizacionDto() {
        super();
    }
    private Integer id_cotizacion;
    private String tipo_cotizacion;
    private Integer id_estatus;
    private String estatus;
    private String fecha_cotizacion;
    private Integer id_usuario;
    private String tipo_produccion;
    private Integer id_licenciatario;
    private String  licenciatario;
    private Integer id_marca;
    private String marca;
    private Integer id_carrito;
    private Integer id_carrito_ind;
    private Integer id_carrito_pqt;
    private List<ObraDto> Obras = new ArrayList<ObraDto>();
    
    private String nombre;


    public Integer getId_cotizacion() {
        return id_cotizacion;
    }

    public void setId_cotizacion(Integer id_cotizacion) {
        this.id_cotizacion = id_cotizacion;
    }

    public String getTipo_cotizacion() {
        return tipo_cotizacion;
    }

    public void setTipo_cotizacion(String tipo_cotizacion) {
        this.tipo_cotizacion = tipo_cotizacion;
    }

    public Integer getId_estatus() {
        return id_estatus;
    }

    public void setId_estatus(Integer id_estatus) {
        this.id_estatus = id_estatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getFecha_cotizacion() {
        return fecha_cotizacion;
    }

    public void setFecha_cotizacion(String fecha_cotizacion) {
        this.fecha_cotizacion = fecha_cotizacion;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTipo_produccion() {
        return tipo_produccion;
    }

    public void setTipo_produccion(String tipo_produccion) {
        this.tipo_produccion = tipo_produccion;
    }

    public Integer getId_licenciatario() {
        return id_licenciatario;
    }

    public void setId_licenciatario(Integer id_licenciatario) {
        this.id_licenciatario = id_licenciatario;
    }

    public String getLicenciatario() {
        return licenciatario;
    }

    public void setLicenciatario(String licenciatario) {
        this.licenciatario = licenciatario;
    }

    public Integer getId_marca() {
        return id_marca;
    }

    public void setId_marca(Integer id_marca) {
        this.id_marca = id_marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getId_carrito() {
        return id_carrito;
    }

    public void setId_carrito(Integer id_carrito) {
        this.id_carrito = id_carrito;
    }

    public Integer getId_carrito_ind() {
        return id_carrito_ind;
    }

    public void setId_carrito_ind(Integer id_carrito_ind) {
        this.id_carrito_ind = id_carrito_ind;
    }

    public Integer getId_carrito_pqt() {
        return id_carrito_pqt;
    }

    public void setId_carrito_pqt(Integer id_carrito_pqt) {
        this.id_carrito_pqt = id_carrito_pqt;
    }

    public List<ObraDto> getObras() {
        return Obras;
    }

    public void setObras(List<ObraDto> Obras) {
        this.Obras = Obras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
