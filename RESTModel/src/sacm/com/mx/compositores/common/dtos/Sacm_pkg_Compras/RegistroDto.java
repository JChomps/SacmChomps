package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras;

public class RegistroDto {
    public RegistroDto() {
        super();
    }
    private Integer id_licenciatario;
    private String tipo_produccion;
    private Integer id_marca;
    private Integer id_carrito_detalle;
    private String array_id_carrito_detalle;
    private String audv_titulo_produccion;
    private String audv_sinopsis;
    private Integer audv_presupuesto;
    private String audv_descripcion;
    private String audv_story_board;   //BLOB
    private Integer audv_num_capitulos ;
    private String spot_titulo;
    private String spot_agencia;
    private String spot_producto;
    private String spot_campania;
    private String spot_sinopsis;
    private String spot_otro_tpo_uso;
    private String spot_notas;
    private Integer id_categ_proposito;
    private Integer id_categ_tpouso_spot;
    private Integer id_categ_tpouso_audv;
    private Integer id_categ_tiempo_uso;
    private Integer id_categ_creatividad;
    private Integer id_categ_letra;
    private Integer id_categ_exclusividad;
    private Integer id_categ_master;
    private Integer id_categ_vigencia;
    private Integer id_categ_mnf;
    private String letra_sample;    //BLOB
    private String musica_sample;    //BLOB
    private String array_lifts;
    private String array_medios;
    private Integer id_territorio_mex;
    private String array_territorio_lat;
    private String array_territorio_nta;
    private String array_territorio_eur;
    private String array_territorio_asi;
    private Integer id_territorio_www;
    private Integer id_cotizacion;
    private Integer monto;
    private Integer suggested_fee;
    private Integer floor_fee;
    private Integer descuento;
    private Integer monto_final;
    private String autorizado_flg;
    private Integer autorizado_msg;

    public Integer getId_licenciatario() {
        return id_licenciatario;
    }

    public void setId_licenciatario(Integer id_licenciatario) {
        this.id_licenciatario = id_licenciatario;
    }

    public String getTipo_produccion() {
        return tipo_produccion;
    }

    public void setTipo_produccion(String tipo_produccion) {
        this.tipo_produccion = tipo_produccion;
    }

    public Integer getId_marca() {
        return id_marca;
    }

    public void setId_marca(Integer id_marca) {
        this.id_marca = id_marca;
    }

    public Integer getId_carrito_detalle() {
        return id_carrito_detalle;
    }

    public void setId_carrito_detalle(Integer id_carrito_detalle) {
        this.id_carrito_detalle = id_carrito_detalle;
    }

    public String getArray_id_carrito_detalle() {
        return array_id_carrito_detalle;
    }

    public void setArray_id_carrito_detalle(String array_id_carrito_detalle) {
        this.array_id_carrito_detalle = array_id_carrito_detalle;
    }

    public String getAudv_titulo_produccion() {
        return audv_titulo_produccion;
    }

    public void setAudv_titulo_produccion(String audv_titulo_produccion) {
        this.audv_titulo_produccion = audv_titulo_produccion;
    }

    public String getAudv_sinopsis() {
        return audv_sinopsis;
    }

    public void setAudv_sinopsis(String audv_sinopsis) {
        this.audv_sinopsis = audv_sinopsis;
    }

    public Integer getAudv_presupuesto() {
        return audv_presupuesto;
    }

    public void setAudv_presupuesto(Integer audv_presupuesto) {
        this.audv_presupuesto = audv_presupuesto;
    }

    public String getAudv_descripcion() {
        return audv_descripcion;
    }

    public void setAudv_descripcion(String audv_descripcion) {
        this.audv_descripcion = audv_descripcion;
    }

    public String getAudv_story_board() {
        return audv_story_board;
    }

    public void setAudv_story_board(String audv_story_board) {
        this.audv_story_board = audv_story_board;
    }

    public Integer getAudv_num_capitulos() {
        return audv_num_capitulos;
    }

    public void setAudv_num_capitulos(Integer audv_num_capitulos) {
        this.audv_num_capitulos = audv_num_capitulos;
    }

    public String getSpot_titulo() {
        return spot_titulo;
    }

    public void setSpot_titulo(String spot_titulo) {
        this.spot_titulo = spot_titulo;
    }

    public String getSpot_agencia() {
        return spot_agencia;
    }

    public void setSpot_agencia(String spot_agencia) {
        this.spot_agencia = spot_agencia;
    }

    public String getSpot_producto() {
        return spot_producto;
    }

    public void setSpot_producto(String spot_producto) {
        this.spot_producto = spot_producto;
    }

    public String getSpot_campania() {
        return spot_campania;
    }

    public void setSpot_campania(String spot_campania) {
        this.spot_campania = spot_campania;
    }

    public String getSpot_sinopsis() {
        return spot_sinopsis;
    }

    public void setSpot_sinopsis(String spot_sinopsis) {
        this.spot_sinopsis = spot_sinopsis;
    }

    public String getSpot_otro_tpo_uso() {
        return spot_otro_tpo_uso;
    }

    public void setSpot_otro_tpo_uso(String spot_otro_tpo_uso) {
        this.spot_otro_tpo_uso = spot_otro_tpo_uso;
    }

    public String getSpot_notas() {
        return spot_notas;
    }

    public void setSpot_notas(String spot_notas) {
        this.spot_notas = spot_notas;
    }

    public Integer getId_categ_proposito() {
        return id_categ_proposito;
    }

    public void setId_categ_proposito(Integer id_categ_proposito) {
        this.id_categ_proposito = id_categ_proposito;
    }

    public Integer getId_categ_tpouso_spot() {
        return id_categ_tpouso_spot;
    }

    public void setId_categ_tpouso_spot(Integer id_categ_tpouso_spot) {
        this.id_categ_tpouso_spot = id_categ_tpouso_spot;
    }

    public Integer getId_categ_tpouso_audv() {
        return id_categ_tpouso_audv;
    }

    public void setId_categ_tpouso_audv(Integer id_categ_tpouso_audv) {
        this.id_categ_tpouso_audv = id_categ_tpouso_audv;
    }

    public Integer getId_categ_tiempo_uso() {
        return id_categ_tiempo_uso;
    }

    public void setId_categ_tiempo_uso(Integer id_categ_tiempo_uso) {
        this.id_categ_tiempo_uso = id_categ_tiempo_uso;
    }

    public Integer getId_categ_creatividad() {
        return id_categ_creatividad;
    }

    public void setId_categ_creatividad(Integer id_categ_creatividad) {
        this.id_categ_creatividad = id_categ_creatividad;
    }

    public Integer getId_categ_letra() {
        return id_categ_letra;
    }

    public void setId_categ_letra(Integer id_categ_letra) {
        this.id_categ_letra = id_categ_letra;
    }

    public Integer getId_categ_exclusividad() {
        return id_categ_exclusividad;
    }

    public void setId_categ_exclusividad(Integer id_categ_exclusividad) {
        this.id_categ_exclusividad = id_categ_exclusividad;
    }

    public Integer getId_categ_master() {
        return id_categ_master;
    }

    public void setId_categ_master(Integer id_categ_master) {
        this.id_categ_master = id_categ_master;
    }

    public Integer getId_categ_vigencia() {
        return id_categ_vigencia;
    }

    public void setId_categ_vigencia(Integer id_categ_vigencia) {
        this.id_categ_vigencia = id_categ_vigencia;
    }

    public Integer getId_categ_mnf() {
        return id_categ_mnf;
    }

    public void setId_categ_mnf(Integer id_categ_mnf) {
        this.id_categ_mnf = id_categ_mnf;
    }

    public String getLetra_sample() {
        return letra_sample;
    }

    public void setLetra_sample(String letra_sample) {
        this.letra_sample = letra_sample;
    }

    public String getMusica_sample() {
        return musica_sample;
    }

    public void setMusica_sample(String música_sample) {
        this.musica_sample = música_sample;
    }

    public String getArray_lifts() {
        return array_lifts;
    }

    public void setArray_lifts(String array_lifts) {
        this.array_lifts = array_lifts;
    }

    public String getArray_medios() {
        return array_medios;
    }

    public void setArray_medios(String array_medios) {
        this.array_medios = array_medios;
    }

    public Integer getId_territorio_mex() {
        return id_territorio_mex;
    }

    public void setId_territorio_mex(Integer id_territorio_mex) {
        this.id_territorio_mex = id_territorio_mex;
    }

    public String getArray_territorio_lat() {
        return array_territorio_lat;
    }

    public void setArray_territorio_lat(String array_territorio_lat) {
        this.array_territorio_lat = array_territorio_lat;
    }

    public String getArray_territorio_nta() {
        return array_territorio_nta;
    }

    public void setArray_territorio_nta(String array_territorio_nta) {
        this.array_territorio_nta = array_territorio_nta;
    }

    public String getArray_territorio_eur() {
        return array_territorio_eur;
    }

    public void setArray_territorio_eur(String array_territorio_eur) {
        this.array_territorio_eur = array_territorio_eur;
    }

    public String getArray_territorio_asi() {
        return array_territorio_asi;
    }

    public void setArray_territorio_asi(String array_territorio_asi) {
        this.array_territorio_asi = array_territorio_asi;
    }

    public Integer getId_territorio_www() {
        return id_territorio_www;
    }

    public void setId_territorio_www(Integer id_territorio_www) {
        this.id_territorio_www = id_territorio_www;
    }

    public Integer getId_cotizacion() {
        return id_cotizacion;
    }

    public void setId_cotizacion(Integer id_cotizacion) {
        this.id_cotizacion = id_cotizacion;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Integer getSuggested_fee() {
        return suggested_fee;
    }

    public void setSuggested_fee(Integer suggested_fee) {
        this.suggested_fee = suggested_fee;
    }

    public Integer getFloor_fee() {
        return floor_fee;
    }

    public void setFloor_fee(Integer floor_fee) {
        this.floor_fee = floor_fee;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public Integer getMonto_final() {
        return monto_final;
    }

    public void setMonto_final(Integer monto_final) {
        this.monto_final = monto_final;
    }

    public String getAutorizado_flg() {
        return autorizado_flg;
    }

    public void setAutorizado_flg(String autorizado_flg) {
        this.autorizado_flg = autorizado_flg;
    }

    public Integer getAutorizado_msg() {
        return autorizado_msg;
    }

    public void setAutorizado_msg(Integer autorizado_msg) {
        this.autorizado_msg = autorizado_msg;
    }
}
