package sacm.com.mx.compositores.infraestructure.repositories;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.PalabraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.PalabraIdObra;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CarritoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CarritoResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.CategoriaDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.RegistroDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.RegistroResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.ValidaObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.ValidaObraResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Inicio_Sesion.UsuarioDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Inicio_Sesion.UsuarioResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmCarrito {
    @SuppressWarnings("compatibility:-4232378700098733855")
    private static final long serialVersionUID = 1L;


    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmActivacion.class);
    private static PalabraIdObra agregarResponse;
    private static RegistroResultDto registroResponse;
    private static ValidaObraResultDto obraResponse;

    public SacmCarrito() {
        super();

    }
    /*---------------------------------------------------------sacm_agrega_carrito Service----------------------------------------------------------------------*/

    public static PalabraIdObra getElimina(CarritoDto carritoRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_COMPRAS.PRC_CARRITO_ELIMINA(?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, carritoRequest.getId_carrito_detalle());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();


            // 6. Set value of dateValue property using first OUT param
            agregarResponse = new PalabraIdObra();
            agregarResponse.setResponseBD(new HeaderDto());
            agregarResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            agregarResponse.getResponseBD().setCodMsg(cstmt.getString(3));

            agregarResponse.setResponseService(new HeaderDto());
            agregarResponse.getResponseService().setCodErr(cstmt.getInt(2));
            agregarResponse.getResponseService().setCodMsg(cstmt.getString(3));
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            agregarResponse = new PalabraIdObra();
            agregarResponse.setResponseService(new HeaderDto());
            agregarResponse.getResponseService().setCodErr(1);
            agregarResponse.getResponseService().setCodMsg(e.getMessage());
            return agregarResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return agregarResponse;
    }
    
    
    /*---------------------------------------------------------sacm_agregar_carrito Service----------------------------------------------------------------------*/

    public static PalabraIdObra getAgregar(PalabraDto IdRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_COMPRAS.PRC_CARRITO_AGREGA(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, IdRequest.getId_usuario());
            cstmt.setObject(2, IdRequest.getId_obra());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();


            // 6. Set value of dateValue property using first OUT param
            agregarResponse = new PalabraIdObra();
            agregarResponse.setResponseBD(new HeaderDto());
            agregarResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            agregarResponse.getResponseBD().setCodMsg(cstmt.getString(4));

            agregarResponse.setResponseService(new HeaderDto());
            agregarResponse.getResponseService().setCodErr(cstmt.getInt(3));
            agregarResponse.getResponseService().setCodMsg(cstmt.getString(4));
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            agregarResponse = new PalabraIdObra();
            agregarResponse.setResponseService(new HeaderDto());
            agregarResponse.getResponseService().setCodErr(1);
            agregarResponse.getResponseService().setCodMsg(e.getMessage());
            return agregarResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return agregarResponse;
    }
    /*---------------------------------------------------------sacm_consulta_carrito Service----------------------------------------------------------------------*/

    public static CarritoResultDto getConsulta(PalabraDto IdRequest) {
        List<CarritoDto> carritoList = new ArrayList<CarritoDto>();
        CarritoResultDto carritoResponse;
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_COMPRAS.PRC_CARRITO_CONSULTA(?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, IdRequest.getId_usuario());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);
            // 5. Execute the statement
            cstmt.executeUpdate();

            if (cstmt.getInt(2) == 0) {
                // read the results
                rs = (ResultSet) cstmt.getObject(4);
                while (rs.next()) {
                    CarritoDto carrito = new CarritoDto();
                    carrito.setId_usuario(rs.getInt(1));
                    carrito.setId_carrito(rs.getInt(2));
                    carrito.setId_carrito_detalle(rs.getInt(3));
                    carrito.setId_obra(rs.getInt(4));
                    carrito.setNumero_obra(rs.getInt(5));
                    carrito.setTitulo_obra(rs.getString(6));
                    carritoList.add(carrito);
                }
                rs.close();
            }


            // 6. Set value of dateValue property using first OUT param
            carritoResponse = new CarritoResultDto();
            carritoResponse.setResponseBD(new HeaderDto());
            carritoResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            carritoResponse.getResponseBD().setCodMsg(cstmt.getString(3));

            carritoResponse.setResponseService(new HeaderDto());
            carritoResponse.getResponseService().setCodErr(cstmt.getInt(2));
            carritoResponse.getResponseService().setCodMsg(cstmt.getString(3));
            carritoResponse.setCarrito(carritoList);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            carritoResponse = new CarritoResultDto();
            carritoResponse.setResponseService(new HeaderDto());
            carritoResponse.getResponseService().setCodErr(1);
            carritoResponse.getResponseService().setCodMsg(e.getMessage());
            return carritoResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return carritoResponse;
    }


   
    /*---------------------------------------------------------sacm_registra_solicitud Service----------------------------------------------------------------------*/

    public static RegistroResultDto registraSolicitud2(RegistroDto IdRequest) {
        List<RegistroDto> Respuesta = new ArrayList<RegistroDto>();
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;
      //  byte[] byteData = { (byte) 0x1a, (byte) 0x2b, (byte) 0x3c };

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt =
                conn.prepareCall("{call SACM_PKG_COMPRAS.PRC_REGISTRA_REQUEST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");


            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, IdRequest.getTipo_produccion());

            cstmt.setObject(2, IdRequest.getId_licenciatario());
            cstmt.setObject(3, IdRequest.getId_marca());

            cstmt.setObject(4, IdRequest.getId_carrito_detalle());
            cstmt.setObject(5, IdRequest.getArray_id_carrito_detalle());

            cstmt.setObject(6, IdRequest.getAudv_descripcion());
            cstmt.setObject(7, IdRequest.getAudv_sinopsis());
            cstmt.setObject(8, IdRequest.getAudv_presupuesto());
            cstmt.setObject(9, IdRequest.getAudv_descripcion());

            
            
            if (IdRequest.getAudv_story_board() != null) {
                String imagen =IdRequest.getAudv_story_board();
                byte[] byteData = Base64.getDecoder().decode(imagen.getBytes());
                InputStream targetStream = new ByteArrayInputStream(byteData);

                cstmt.setBlob(10, targetStream);
            } else{
                cstmt.setObject(10, null, java.sql
                                             .Types
                                             .BLOB);
            }
            
           

            cstmt.setObject(11, IdRequest.getAudv_num_capitulos());

            cstmt.setObject(12, IdRequest.getSpot_titulo());
            cstmt.setObject(13, IdRequest.getSpot_agencia());
            cstmt.setObject(14, IdRequest.getSpot_producto());
            cstmt.setObject(15, IdRequest.getSpot_campania());
            cstmt.setObject(16, IdRequest.getSpot_sinopsis());
            cstmt.setObject(17, IdRequest.getSpot_otro_tpo_uso());
            cstmt.setObject(18, IdRequest.getSpot_notas());

            cstmt.setObject(19,  IdRequest.getId_categ_proposito());
            cstmt.setObject(20, IdRequest.getId_categ_tpouso_spot());
            cstmt.setObject(21, IdRequest.getId_categ_tpouso_audv());
            cstmt.setObject(22, IdRequest.getId_categ_tiempo_uso());
            cstmt.setObject(23, IdRequest.getId_categ_creatividad());
            cstmt.setObject(24, IdRequest.getId_categ_letra());
            cstmt.setObject(25, IdRequest.getId_categ_exclusividad());
            cstmt.setObject(26, IdRequest.getId_categ_master());
            cstmt.setObject(27, IdRequest.getId_categ_vigencia());
            cstmt.setObject(28, IdRequest.getId_categ_mnf());
            
            if (IdRequest.getLetra_sample() != null) {
                String imagen =IdRequest.getLetra_sample();
                byte[] byteData = Base64.getDecoder().decode(imagen.getBytes());
                InputStream targetStream = new ByteArrayInputStream(byteData);

                cstmt.setBlob(29, targetStream);
            } else{
                cstmt.setObject(29, null, java.sql
                                             .Types
                                             .BLOB);
            }

            
            if (IdRequest.getMusica_sample() != null) {
                String imagen =IdRequest.getMusica_sample();
                byte[] byteData = Base64.getDecoder().decode(imagen.getBytes());
                InputStream targetStream = new ByteArrayInputStream(byteData);

                cstmt.setBlob(30, targetStream);
            } else{
                cstmt.setObject(30, null, java.sql
                                             .Types
                                             .BLOB);
            }
            
            
           
            

            cstmt.setObject(31, IdRequest.getArray_lifts());
            cstmt.setObject(32, IdRequest.getArray_medios());
            cstmt.setObject(33, IdRequest.getId_territorio_mex());
            cstmt.setObject(34, IdRequest.getArray_territorio_lat());
            cstmt.setObject(35, IdRequest.getArray_territorio_nta());
            cstmt.setObject(36, IdRequest.getArray_territorio_eur());
            cstmt.setObject(37, IdRequest.getArray_territorio_asi());
            cstmt.setObject(38, IdRequest.getId_territorio_www());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(39, Types.INTEGER);
            cstmt.registerOutParameter(40, Types.VARCHAR);

            cstmt.registerOutParameter(41, Types.INTEGER);
            cstmt.registerOutParameter(42, Types.INTEGER);
            cstmt.registerOutParameter(43, Types.INTEGER);
            cstmt.registerOutParameter(44, Types.INTEGER);
            cstmt.registerOutParameter(45, Types.INTEGER);
            cstmt.registerOutParameter(46, Types.INTEGER);
            cstmt.registerOutParameter(47, Types.VARCHAR);
            cstmt.registerOutParameter(48, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();

            if (cstmt.getInt(39) == 0) {
                Respuesta = new ArrayList<RegistroDto>();
                RegistroDto registro = new RegistroDto();
                registro.setId_cotizacion(cstmt.getInt(41));
                registro.setId_cotizacion(cstmt.getInt(41));
                registro.setMonto(cstmt.getDouble(42));
                registro.setSuggested_fee(cstmt.getInt(43));
                registro.setFloor_fee(cstmt.getInt(44));
                registro.setDescuento(cstmt.getInt(45));
                registro.setMonto_final(cstmt.getDouble(46));
                registro.setAutorizado_flg(cstmt.getString(47));
                registro.setAutorizado_msg(cstmt.getString(48));
                Respuesta.add(registro);
                // registroResponse.setRespuesta(new ArrayList<RegistroDto>());
                //
            }
          

            // 6. Set value of dateValue property using first OUT param
            registroResponse = new RegistroResultDto();
            registroResponse.setResponseBD(new HeaderDto());
            registroResponse.getResponseBD().setCodErr(cstmt.getInt(39));
            registroResponse.getResponseBD().setCodMsg(cstmt.getString(40));

            registroResponse.setResponseService(new HeaderDto());
            registroResponse.getResponseService().setCodErr(cstmt.getInt(39));
            registroResponse.getResponseService().setCodMsg(cstmt.getString(40));
            
            if (Respuesta.size() > 0){
                registroResponse.setRespuesta(new ArrayList<RegistroDto>());
               registroResponse.setRespuesta(Respuesta);}

            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            registroResponse = new RegistroResultDto();
            registroResponse.setResponseService(new HeaderDto());
            registroResponse.getResponseService().setCodErr(1);
            registroResponse.getResponseService().setCodMsg(e.getMessage());
            return registroResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return registroResponse;
    }
    
    /*---------------------------------------------------------sacm_vacia_carrito Service----------------------------------------------------------------------*/

    public static RegistroResultDto VaciaCarrito(UsuarioDto userRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_COMPRAS.PRC_CARRITO_VACIA(?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, userRequest.getId_usuario());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();


            // 6. Set value of dateValue property using first OUT param
            registroResponse = new RegistroResultDto();
            registroResponse.setResponseBD(new HeaderDto());
            registroResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            registroResponse.getResponseBD().setCodMsg(cstmt.getString(3));

            registroResponse.setResponseService(new HeaderDto());
            registroResponse.getResponseService().setCodErr(cstmt.getInt(2));
            registroResponse.getResponseService().setCodMsg(cstmt.getString(3));
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            registroResponse = new RegistroResultDto();
            registroResponse.setResponseService(new HeaderDto());
            registroResponse.getResponseService().setCodErr(1);
            registroResponse.getResponseService().setCodMsg(e.getMessage());
            return registroResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return registroResponse;
    }

    public static ValidaObraResultDto getValidaObras(CarritoDto carritoRequest) {
        List<ValidaObraDto> obraList = new ArrayList<ValidaObraDto>();
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_COMPRAS.PRC_VALIDA_OBRAS(?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, carritoRequest.getArray_id_carrito_detalle());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);
            // 5. Execute the statement
            cstmt.executeUpdate();

            if (cstmt.getInt(2) == 0) {
                // read the results
                rs = (ResultSet) cstmt.getObject(4);
                while (rs.next()) {
                    ValidaObraDto obra = new ValidaObraDto();
                    obra.setId_obra(rs.getInt(1));
                    obra.setNumero_obra(rs.getInt(2));
                    obra.setTitulo_obra(rs.getString(3)+ "");
                    
                    obra.setConsagrada_obra(rs.getString(4)+ "");
                    obra.setControl_obra(rs.getInt(5));
                    obra.setControl_val_obra(rs.getString(6)+ "");
                    obra.setAutorizada_obra(rs.getString(7)+ "");

                    obra.setId_participante(rs.getInt(8));
                    obra.setParticipante(rs.getString(9)+ "");
                    obra.setConsagrado_participante(rs.getString(10)+ "");
                    
                    obraList.add(obra);
                }
                rs.close();
            }


            // 6. Set value of dateValue property using first OUT param
            obraResponse = new ValidaObraResultDto();
            obraResponse.setResponseBD(new HeaderDto());
            obraResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            obraResponse.getResponseBD().setCodMsg(cstmt.getString(3));

            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(cstmt.getInt(2));
            obraResponse.getResponseService().setCodMsg(cstmt.getString(3));
            if(obraList.size()>0)
            obraResponse.setObras(obraList);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            obraResponse = new ValidaObraResultDto();
            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(1);
            obraResponse.getResponseService().setCodMsg(e.getMessage());
            return obraResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return obraResponse;
    }

    public static ValidaObraResultDto getPropiedadesObras(ValidaObraDto obraRequest) {
        List<ValidaObraDto> obraList = new ArrayList<ValidaObraDto>();
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_COMPRAS.PRC_PROPIEDADES_OBRA(?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, obraRequest.getArray_id_obras());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);
            // 5. Execute the statement
            cstmt.executeUpdate();

            if (cstmt.getInt(2) == 0) {
                // read the results
                rs = (ResultSet) cstmt.getObject(4);
                while (rs.next()) {
                    ValidaObraDto obra = new ValidaObraDto();
                    obra.setId_obra(rs.getInt(1));
                    obra.setNumero_obra(rs.getInt(2));
                    obra.setTitulo_obra(rs.getString(3)+ "");
                    obra.setActiva(rs.getString(4)+"");                    
                    obra.setConsagrada_obra(rs.getString(5)+ "");
                    obra.setAutorizada_obra(rs.getString(6)+ "");   
                    obra.setControl_obra(rs.getInt(7));
                    
                    obraList.add(obra);
                }
                rs.close();
            }


            // 6. Set value of dateValue property using first OUT param
            obraResponse = new ValidaObraResultDto();
            obraResponse.setResponseBD(new HeaderDto());
            obraResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            obraResponse.getResponseBD().setCodMsg(cstmt.getString(3));

            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(cstmt.getInt(2));
            obraResponse.getResponseService().setCodMsg(cstmt.getString(3));
            if(obraList.size()>0)
            obraResponse.setObras(obraList);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            obraResponse = new ValidaObraResultDto();
            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(1);
            obraResponse.getResponseService().setCodMsg(e.getMessage());
            return obraResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return obraResponse;
    }

    /*-----------------------------------------------------sacm_cambia_estatus_request Service-------------------------------------------------------------------*/
      public static ValidaObraResultDto CambiaEstatusRequest(RegistroDto registroRequest) {
       
        CallableStatement cstmt = null;
        Connection conn = null;
       // ResultSet rs = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_COMPRAS.PRC_CAMBIA_ESTATUS_REQUEST(?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, registroRequest.getId_cotizacion());
            cstmt.setObject(2, registroRequest.getAutoriza());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);
           
            // 5. Execute the statement
            cstmt.executeUpdate();

         

            // 6. Set value of dateValue property using first OUT param
            obraResponse = new ValidaObraResultDto();
            obraResponse.setResponseBD(new HeaderDto());
            obraResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            obraResponse.getResponseBD().setCodMsg(cstmt.getString(4));

            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(cstmt.getInt(3));
            obraResponse.getResponseService().setCodMsg(cstmt.getString(4));
            
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            obraResponse = new ValidaObraResultDto();
            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(1);
            obraResponse.getResponseService().setCodMsg(e.getMessage());
            return obraResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return obraResponse;
    }
    /*-----------------------------------------------------sacm_cambia_estatus_request Service-------------------------------------------------------------------*/
    public static ValidaObraResultDto Cancela_Solicitud(RegistroDto registroRequest) {
     
      CallableStatement cstmt = null;
      Connection conn = null;
     // ResultSet rs = null;

      try {
          conn = AppModule.getDbConexionJDBC();
          // 2. Define the PL/SQL block for the statement to invoke
          cstmt = conn.prepareCall("{call SACM_PKG_COMPRAS.PRC_CANCELA_SOLICITUD(?,?,?,?)}");

          // 3. Set the bind values of the IN parameters
          cstmt.setObject(1, registroRequest.getId_usuario());
          cstmt.setObject(2, registroRequest.getId_cotizacion());
          // 4. Register the positions and types of the OUT parameters
          cstmt.registerOutParameter(3, Types.INTEGER);
          cstmt.registerOutParameter(4, Types.VARCHAR);
         
          // 5. Execute the statement
          cstmt.executeUpdate();

       

          // 6. Set value of dateValue property using first OUT param
          obraResponse = new ValidaObraResultDto();
          obraResponse.setResponseBD(new HeaderDto());
          obraResponse.getResponseBD().setCodErr(cstmt.getInt(3));
          obraResponse.getResponseBD().setCodMsg(cstmt.getString(4));

          obraResponse.setResponseService(new HeaderDto());
          obraResponse.getResponseService().setCodErr(cstmt.getInt(3));
          obraResponse.getResponseService().setCodMsg(cstmt.getString(4));
          
          // 9. Close the JDBC CallableStatement
          cstmt.close();
          conn.close();
          conn = null;

      } catch (Exception e) {
          // a failure occurred log message;
          _logger.severe(e.getMessage());
          obraResponse = new ValidaObraResultDto();
          obraResponse.setResponseService(new HeaderDto());
          obraResponse.getResponseService().setCodErr(1);
          obraResponse.getResponseService().setCodMsg(e.getMessage());
          return obraResponse;
      }

      _logger.info("Finish Cancela Solicitud");
      // 9. Return the result
      return obraResponse;
    }
}
