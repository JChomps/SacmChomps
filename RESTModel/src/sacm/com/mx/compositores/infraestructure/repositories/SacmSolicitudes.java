package sacm.com.mx.compositores.infraestructure.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Base64;

import java.util.List;

import java.util.Map;
import java.util.TreeMap;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.ActivacionDto;
import sacm.com.mx.compositores.common.dtos.CotizacionDto;
import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.AlbumDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.AlbumResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.RegistroResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Inicio_Sesion.UsuarioDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil.SolicitudDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil.SolicitudResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmSolicitudes {
    @SuppressWarnings("compatibility:-4232378700098733855")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmActivacion.class);
    private static SolicitudResultDto SolicitudResponse;

    public SacmSolicitudes() {
        super();
    }
    /*---------------------------------------------------------sacm_consulta_solicitud_consola Service----------------------------------------------------------------------*/

    public static SolicitudResultDto getSolicitud(ActivacionDto usuarioRequest) {
        List<SolicitudDto> solicitudListResult = new ArrayList<SolicitudDto>();
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PERFIL_CLIENTE.PRC_CONSULTA_SOLICITUDES(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, usuarioRequest.getId_Usuario());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);

            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(2) == 0) {
                List<SolicitudDto> solicitudList = new ArrayList<SolicitudDto>();
                // print the results
                rs = (ResultSet) cstmt.getObject(4);
                while (rs.next()) {
                    SolicitudDto solicitud = new SolicitudDto();
                    CotizacionDto cotizacion = new CotizacionDto();
                    ObraDto obra = new ObraDto();
                    solicitud.setTipo(rs.getString(1));
                    solicitud.setTitle(rs.getString(2));

                    cotizacion.setId_cotizacion(rs.getInt(3));
                    cotizacion.setTipo_cotizacion(rs.getString(4));
                    cotizacion.setId_estatus(rs.getInt(5));
                    cotizacion.setEstatus(rs.getString(6));
                    cotizacion.setFecha_cotizacion(rs.getString(7));
                    cotizacion.setId_usuario(rs.getInt(8));
                    cotizacion.setTipo_produccion(rs.getString(9));
                    cotizacion.setId_licenciatario(rs.getInt(10));
                    cotizacion.setLicenciatario(rs.getString(11));
                    cotizacion.setId_marca(rs.getInt(12));
                    cotizacion.setMarca(rs.getString(13));
                    cotizacion.setId_carrito(rs.getInt(14));
                    cotizacion.setId_carrito_ind(rs.getInt(15));
                    cotizacion.setId_carrito_pqt(rs.getInt(16));

                    obra.setId_obra(rs.getInt(17));
                    obra.setObra_numero(rs.getInt(18));
                    obra.setObra_titulo(rs.getString(19));
                    obra.setObra_descripcion(rs.getString(20));

                    cotizacion.getObras().add(obra);
                    solicitud.getItems().add(cotizacion);

                    solicitudList.add(solicitud);
                }

                OrganizaSolicitud(solicitudListResult, solicitudList);


                rs.close();
            }
            // 6. Set value of dateValue property using first OUT param
            SolicitudResponse = new SolicitudResultDto();
            SolicitudResponse.setResponseBD(new HeaderDto());
            SolicitudResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            SolicitudResponse.getResponseBD().setCodMsg(cstmt.getString(3));
            SolicitudResponse.setResponseService(new HeaderDto());
            SolicitudResponse.getResponseService().setCodErr(cstmt.getInt(2));
            SolicitudResponse.getResponseService().setCodMsg(cstmt.getString(3));
            if(solicitudListResult.size()>0)
            SolicitudResponse.setSolicitudes(solicitudListResult);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;
        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            SolicitudResponse = new SolicitudResultDto();
            SolicitudResponse.setResponseService(new HeaderDto());
            SolicitudResponse.getResponseService().setCodErr(1);
            SolicitudResponse.getResponseService().setCodMsg(e.getMessage());
            return SolicitudResponse;
        }
        _logger.info("Finish getConsultaAlbum");
        // 9. Return the result
        
        return SolicitudResponse;
    }
    
    /*---------------------------------------------------------sacm_consulta_solicitud_consola Service----------------------------------------------------------------------*/

    public static SolicitudResultDto getSolicitudConsola(UsuarioDto usuarioRequest) {
        List<SolicitudDto> solicitudListResult = new ArrayList<SolicitudDto>();
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.CONSULTA_SOLICITUDES(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, usuarioRequest.getId_usuario());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, -10);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);
           

            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(3) == 0) {
                List<SolicitudDto> solicitudList = new ArrayList<SolicitudDto>();
                // print the results
                rs = (ResultSet) cstmt.getObject(2);
                while (rs.next()) {
                    SolicitudDto solicitud = new SolicitudDto();
                    CotizacionDto cotizacion = new CotizacionDto();
                    ObraDto obra = new ObraDto();
                    solicitud.setTipo(rs.getString(1));
                    solicitud.setTitle(rs.getString(2));

                    cotizacion.setId_cotizacion(rs.getInt(3));
                    cotizacion.setTipo_cotizacion(rs.getString(4));                    
                    cotizacion.setId_estatus(rs.getInt(5));
                    cotizacion.setEstatus(rs.getString(6));                    
                    cotizacion.setFecha_cotizacion(rs.getString(7));
                    
                    cotizacion.setId_usuario(rs.getInt(8));
                    cotizacion.setTipo_produccion(rs.getString(9));
                    cotizacion.setId_licenciatario(rs.getInt(10));
                    cotizacion.setLicenciatario(rs.getString(11));
                    
                    cotizacion.setId_marca(rs.getInt(12));
                    cotizacion.setMarca(rs.getString(13));
                    cotizacion.setId_carrito(rs.getInt(14));
                    cotizacion.setId_carrito_ind(rs.getInt(15));
                    cotizacion.setId_carrito_pqt(rs.getInt(16) );
                    
                    obra.setId_obra(rs.getInt(17));
                    obra.setObra_numero(rs.getInt(18));
                    obra.setObra_titulo(rs.getString(19));
                    obra.setObra_descripcion(rs.getString(20));

                    cotizacion.getObras().add(obra);
                    solicitud.getItems().add(cotizacion);

                    solicitudList.add(solicitud);
                }

                OrganizaSolicitud(solicitudListResult, solicitudList);


                rs.close();
            }
            // 6. Set value of dateValue property using first OUT param
            SolicitudResponse = new SolicitudResultDto();
            SolicitudResponse.setResponseBD(new HeaderDto());
            SolicitudResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            SolicitudResponse.getResponseBD().setCodMsg(cstmt.getString(4));
            SolicitudResponse.setResponseService(new HeaderDto());
            SolicitudResponse.getResponseService().setCodErr(cstmt.getInt(3));
            SolicitudResponse.getResponseService().setCodMsg(cstmt.getString(4));
            if(solicitudListResult.size()>0)
            SolicitudResponse.setSolicitudes(solicitudListResult);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;
        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            SolicitudResponse = new SolicitudResultDto();
            SolicitudResponse.setResponseService(new HeaderDto());
            SolicitudResponse.getResponseService().setCodErr(1);
            SolicitudResponse.getResponseService().setCodMsg(e.getMessage());
            return SolicitudResponse;
        }
        _logger.info("Finish getConsultaAlbum");
        // 9. Return the result
        
        return SolicitudResponse;
    }


   
    


    private static void OrganizaSolicitud(List<SolicitudDto> solicitudListResult, List<SolicitudDto> solicitudList) {
        List<CotizacionDto> cotizacionList = new ArrayList<CotizacionDto>();

        Map<String, SolicitudDto> mapSolicitud = new TreeMap<String, SolicitudDto>();
        Map<Integer, CotizacionDto> mapCotizacion = new TreeMap<Integer, CotizacionDto>();

        for (SolicitudDto value : solicitudList) {
            mapSolicitud.put(value.getTipo(), value);
        }

        for (SolicitudDto value : mapSolicitud.values()) {
            solicitudListResult.add(value);
        }


        for (SolicitudDto slt : solicitudListResult) {
            mapCotizacion = new TreeMap<Integer, CotizacionDto>();
            cotizacionList = new ArrayList<CotizacionDto>();
            for (SolicitudDto strTL : solicitudList) {
                if (strTL.getTipo().equals(slt.getTipo())) {
                    CotizacionDto cot = new CotizacionDto();
                    cot.setId_cotizacion(strTL.getItems()
                                              .get(0)
                                              .getId_cotizacion());
                    cot.setTipo_cotizacion(strTL.getItems()
                                                .get(0)
                                                .getTipo_cotizacion());
                    cot.setId_estatus(strTL.getItems()
                                           .get(0)
                                           .getId_estatus());
                    cot.setEstatus(strTL.getItems()
                                        .get(0)
                                        .getEstatus());
                    cot.setFecha_cotizacion(strTL.getItems()
                                                 .get(0)
                                                 .getFecha_cotizacion());
                    cot.setId_usuario(strTL.getItems()
                                           .get(0)
                                           .getId_usuario());
                    cot.setTipo_produccion(strTL.getItems()
                                                .get(0)
                                                .getTipo_produccion());
                    cot.setId_licenciatario(strTL.getItems()
                                                 .get(0)
                                                 .getId_licenciatario());
                    cot.setLicenciatario(strTL.getItems()
                                              .get(0)
                                              .getLicenciatario());
                    cot.setId_marca(strTL.getItems()
                                         .get(0)
                                         .getId_marca());
                    cot.setMarca(strTL.getItems()
                                      .get(0)
                                      .getMarca());
                    cot.setId_carrito(strTL.getItems()
                                           .get(0)
                                           .getId_carrito());
                    cot.setId_carrito_ind(strTL.getItems()
                                               .get(0)
                                               .getId_carrito_ind());
                    cot.setId_carrito_pqt(strTL.getItems()
                                               .get(0)
                                               .getId_carrito_pqt());


                    mapCotizacion.put(cot.getId_cotizacion(), cot);
                }
            }
            for (CotizacionDto value : mapCotizacion.values()) {
                cotizacionList.add(value);
            }
            //Organización y eliminación de elementos Tag nivel 2 repetidos
            OrganizaObras(cotizacionList, solicitudList);

            slt.setItems(cotizacionList);

        }


    }

    private static void OrganizaObras(List<CotizacionDto> cotizacionList, List<SolicitudDto> solicitudList) {
        List<ObraDto> obrasList = new ArrayList<ObraDto>();
        for (CotizacionDto strC : cotizacionList) {
            obrasList = new ArrayList<ObraDto>();
            for (SolicitudDto strTL : solicitudList) {
                if (strC.getId_cotizacion() == strTL.getItems()
                                                    .get(0)
                                                    .getId_cotizacion()) {
                    ObraDto obra = new ObraDto();
                    obra.setId_obra(strTL.getItems()
                                         .get(0)
                                         .getObras()
                                         .get(0)
                                         .getId_obra());
                    obra.setObra_numero(strTL.getItems()
                                             .get(0)
                                             .getObras()
                                             .get(0)
                                             .getObra_numero());
                    obra.setObra_titulo(strTL.getItems()
                                             .get(0)
                                             .getObras()
                                             .get(0)
                                             .getObra_titulo());
                    obra.setObra_descripcion(strTL.getItems()
                                                  .get(0)
                                                  .getObras()
                                                  .get(0)
                                                  .getObra_descripcion());
                    strC.getObras().add(obra);
                }
            }
        }

    }
}
