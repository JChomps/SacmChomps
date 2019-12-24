package sacm.com.mx.compositores.infraestructure.repositories;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.TreeMap;


import javax.sql.rowset.serial.SerialBlob;

import oracle.adf.share.logging.ADFLogger;


import oracle.mds.internal.core.IdRequest;

import sacm.com.mx.compositores.common.dtos.Consola.ParticipanteResultDto;
import sacm.com.mx.compositores.common.dtos.Consola.TagConsolaDto;
import sacm.com.mx.compositores.common.dtos.Consola.TagConsolaResultDto;
import sacm.com.mx.compositores.common.dtos.Consola.TagN2ConsolaDto;
import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.NombreParticipanteDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.VersionDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.VersionResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Inicio_Sesion.UsuarioDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Inicio_Sesion.UsuarioResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmConsola {

    @SuppressWarnings("compatibility:3396088568485404005")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmEstado.class);
    private static ObraResultDto obraResponse;
    private static ParticipanteResultDto participanteResult;
    private static VersionResultDto versionResult;
    private static TagConsolaResultDto tagsResponse;
    private static UsuarioResultDto usuarioResponse;


    public SacmConsola() {
        super();
    }

    /*------------------------------------------------------------- sacm_consulta_obra_consola Service ------------------------------------------------------------------------------*/
    public static ObraResultDto getObra(ObraDto obraRequest) {
        List<ObraDto> obraList = new ArrayList<ObraDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        Short a=0 ;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.CONSULTA_OBRA(?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
           //= new Boolean(true);
            cstmt.setObject(1, obraRequest.getObra_numero());
            cstmt.setObject(2, obraRequest.getObra_titulo());
            cstmt.setObject(3,obraRequest.getCarga_audio());
           

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(4, -10);
            cstmt.registerOutParameter(5, Types.INTEGER);
            cstmt.registerOutParameter(6, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();
             if (cstmt.getInt(5) == 0) {
                // print the results
                rs = (ResultSet) cstmt.getObject(4);
                byte[] bdata;
                while (rs.next()) {
                    ObraDto obra = new ObraDto();
                    obra.setId_obra(rs.getInt(1));
                    obra.setObra_numero(rs.getInt(2));
                    obra.setObra_titulo(rs.getString(3));
                    obra.setObra_descripcion(rs.getString(4));
                    
                    if(rs.getObject(5) != null){
                    bdata = (rs.getObject(5) == null ? null : rs.getBlob(5).getBytes(1, (int) rs.getBlob(5).length()));
                    obra.setVersion_wav(rs.getObject(5) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    }else{
                            obra.setVersion_wav("");
                        }
                    
                    
                    if(rs.getObject(6) != null){
                    bdata = (rs.getObject(6) == null ? null : rs.getBlob(6).getBytes(1, (int) rs.getBlob(6).length()));
                    obra.setVersion_mp3(rs.getObject(6) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    }else{
                            obra.setVersion_mp3("");
                        }
                    
                    
                    if(rs.getObject(7) != null){
                    bdata =(rs.getObject(7) == null ? null : rs.getBlob(7).getBytes(1, (int) rs.getBlob(7).length()));
                    obra.setVersion_aiff(rs.getObject(7) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    }else{
                            obra.setVersion_aiff("");
                        }
                    obra.setVersion_lyric(rs.getObject(8) == null ? "" : rs.getString(8));

                    obraList.add(obra);
                }
                rs.close();
            }

            // 6. Set value of dateValue property using first OUT param
            obraResponse = new ObraResultDto();
            obraResponse.setResponseBD(new HeaderDto());
            obraResponse.getResponseBD().setCodErr(cstmt.getInt(5));
            obraResponse.getResponseBD().setCodMsg(cstmt.getString(6));
            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(cstmt.getInt(5));
            obraResponse.getResponseService().setCodMsg(cstmt.getString(6));
            obraResponse.setObras(obraList);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;
        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            obraResponse = new ObraResultDto();
            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(1);
            obraResponse.getResponseService().setCodMsg(e.getMessage());
            return obraResponse;
        }
        _logger.info("Finish getConsultaObra");
        // 9. Return the result
        obraResponse.setObras(obraList);
        return obraResponse;
    }


    /*------------------------------------------------------------- sacm_consulta_participante_obra_consola Service ------------------------------------------------------------------------------*/
    public static ParticipanteResultDto getParticipanteObra(ObraDto obraRequest) {
        List<NombreParticipanteDto> participanteList = new ArrayList<NombreParticipanteDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.CONSULTA_PARTICIPANTE_OBRA(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, obraRequest.getId_obra());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, -10);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(3) == 0) {
                // print the results
                rs = (ResultSet) cstmt.getObject(2);

                while (rs.next()) {
                    NombreParticipanteDto participante = new NombreParticipanteDto();
                    participante.setId_participante(rs.getInt(1));
                    participante.setNombre(rs.getString(2));
                    participante.setEmail(rs.getString(3));
                    participante.setActivo(rs.getString(4));
                    participanteList.add(participante);
                }
                rs.close();
            }

            // 6. Set value of dateValue property using first OUT param
            participanteResult = new ParticipanteResultDto();
            participanteResult.setResponseBD(new HeaderDto());
            participanteResult.getResponseBD().setCodErr(cstmt.getInt(3));
            participanteResult.getResponseBD().setCodMsg(cstmt.getString(4));
            participanteResult.setResponseService(new HeaderDto());
            participanteResult.getResponseService().setCodErr(cstmt.getInt(3));
            participanteResult.getResponseService().setCodMsg(cstmt.getString(4));
            participanteResult.setParticipantes(participanteList);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;
        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            participanteResult = new ParticipanteResultDto();
            participanteResult.setResponseService(new HeaderDto());
            participanteResult.getResponseService().setCodErr(1);
            participanteResult.getResponseService().setCodMsg(e.getMessage());
            return participanteResult;
        }
        _logger.info("Finish getConsultaObra");
        // 9. Return the result
        participanteResult.setParticipantes(participanteList);
        return participanteResult;
    }

    /*------------------------------------------------------------- sacm_consulta_version_obra_consola Service ------------------------------------------------------------------------------*/
    public static VersionResultDto getVersionObra(ObraDto obraRequest) {
        List<VersionDto> versionesList = new ArrayList<VersionDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.CONSULTA_VERSION_OBRA(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, obraRequest.getId_obra());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, -10);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(3) == 0) {
                // print the results
                rs = (ResultSet) cstmt.getObject(2);

                while (rs.next()) {
                    VersionDto version = new VersionDto();
                    version.setVersion(rs.getString(1));

                    versionesList.add(version);
                }
                rs.close();
            }

            // 6. Set value of dateValue property using first OUT param
            versionResult = new VersionResultDto();
            versionResult.setResponseBD(new HeaderDto());
            versionResult.getResponseBD().setCodErr(cstmt.getInt(3));
            versionResult.getResponseBD().setCodMsg(cstmt.getString(4));
            versionResult.setResponseService(new HeaderDto());
            versionResult.getResponseService().setCodErr(cstmt.getInt(3));
            versionResult.getResponseService().setCodMsg(cstmt.getString(4));
            versionResult.setVersiones(versionesList);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;
        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            versionResult = new VersionResultDto();
            versionResult.setResponseService(new HeaderDto());
            versionResult.getResponseService().setCodErr(1);
            versionResult.getResponseService().setCodMsg(e.getMessage());
            return versionResult;
        }
        _logger.info("Finish getConsultaObra");
        // 9. Return the result
        versionResult.setVersiones(versionesList);
        return versionResult;
    }
    /*---------------------------------------------------------sacm_actualiza_obra_consola Service----------------------------------------------------------------------*/

    public static ObraResultDto InsertaObra(ObraDto obraRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        byte[] byteData = { (byte) 0x1a, (byte) 0x2b, (byte) 0x3c };

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.INSERTA_OBRA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, obraRequest.getId_obra());
            cstmt.setObject(2, obraRequest.getId_album());

            byteData = (obraRequest.get_Imagen() == null ? null : Base64.getDecoder().decode(obraRequest.get_Imagen()));
            cstmt.setObject(3, byteData, java.sql
                                             .Types
                                             .BLOB);

            //cstmt.setObject(2, obraRequest.get_Imagen());
            cstmt.setObject(4, obraRequest.getObra_numero());
            cstmt.setObject(5, obraRequest.getObra_titulo());
            cstmt.setObject(6, obraRequest.getObra_descripcion());

            byteData = (obraRequest.getWav() == null ? null : Base64.getDecoder().decode(obraRequest.getWav()));
            cstmt.setObject(7, byteData, java.sql
                                             .Types
                                             .BLOB);
            //cstmt.setObject(6, obraRequest.getWav());

            byteData = (obraRequest.getMp3() == null ? null : Base64.getDecoder().decode(obraRequest.getMp3()));
            cstmt.setObject(8, byteData, java.sql
                                             .Types
                                             .BLOB);
            // cstmt.setObject(7, obraRequest.getMp3());

            byteData = (obraRequest.getAiff() == null ? null : Base64.getDecoder().decode(obraRequest.getAiff()));
            cstmt.setObject(9, byteData, java.sql
                                             .Types
                                             .BLOB);
            // cstmt.setObject(8, obraRequest.getAiff());


            cstmt.setObject(10, obraRequest.getLyric());
            cstmt.setObject(11, obraRequest.getConsagrada());
            cstmt.setObject(12, obraRequest.getControl());
            cstmt.setObject(13, obraRequest.getPreAutorizacion());
            cstmt.setObject(14, obraRequest.getActivo());
            cstmt.setObject(15, obraRequest.getDuracion());
            

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(16, Types.INTEGER);
            cstmt.registerOutParameter(17, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();


            // 6. Set value of dateValue property using first OUT param
            obraResponse = new ObraResultDto();
            obraResponse.setResponseBD(new HeaderDto());
            obraResponse.getResponseBD().setCodErr(cstmt.getInt(16));
            obraResponse.getResponseBD().setCodMsg(cstmt.getString(17));

            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(cstmt.getInt(16));
            obraResponse.getResponseService().setCodMsg(cstmt.getString(17));
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            obraResponse = new ObraResultDto();
            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(1);
            obraResponse.getResponseService().setCodMsg(e.getMessage());
            return obraResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return obraResponse;
    }

    /*---------------------------------------------------------sacm_actualiza_obra_consola Service----------------------------------------------------------------------*/

    public static ObraResultDto ActualizaObra(ObraDto obraRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        byte[] byteData = { (byte) 0x1a, (byte) 0x2b, (byte) 0x3c };

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.ACTUALIZA_OBRA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, obraRequest.getId_obra());

            byteData = (obraRequest.get_Imagen() == null ? null : Base64.getDecoder().decode(obraRequest.get_Imagen()));
            cstmt.setObject(2, byteData, java.sql
                                             .Types
                                             .BLOB);

            //cstmt.setObject(2, obraRequest.get_Imagen());
            cstmt.setObject(3, obraRequest.getObra_numero());
            cstmt.setObject(4, obraRequest.getObra_titulo());
            cstmt.setObject(5, obraRequest.getObra_descripcion());

            byteData = (obraRequest.getWav() == null ? null : Base64.getDecoder().decode(obraRequest.getWav()));
            cstmt.setObject(6, byteData, java.sql
                                             .Types
                                             .BLOB);
            //cstmt.setObject(6, obraRequest.getWav());

            byteData = (obraRequest.getMp3() == null ? null : Base64.getDecoder().decode(obraRequest.getMp3()));
            cstmt.setObject(7, byteData, java.sql
                                             .Types
                                             .BLOB);
            // cstmt.setObject(7, obraRequest.getMp3());

            byteData = (obraRequest.getAiff() == null ? null : Base64.getDecoder().decode(obraRequest.getAiff()));
            cstmt.setObject(8, byteData, java.sql
                                             .Types
                                             .BLOB);
            // cstmt.setObject(8, obraRequest.getAiff());


            cstmt.setObject(9, obraRequest.getLyric());
            cstmt.setObject(10, obraRequest.getConsagrada());
            cstmt.setObject(11, obraRequest.getControl());
            cstmt.setObject(12, obraRequest.getPreAutorizacion());
            cstmt.setObject(13, obraRequest.getActivo());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(14, Types.INTEGER);
            cstmt.registerOutParameter(15, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();


            // 6. Set value of dateValue property using first OUT param
            obraResponse = new ObraResultDto();
            obraResponse.setResponseBD(new HeaderDto());
            obraResponse.getResponseBD().setCodErr(cstmt.getInt(14));
            obraResponse.getResponseBD().setCodMsg(cstmt.getString(15));

            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(cstmt.getInt(14));
            obraResponse.getResponseService().setCodMsg(cstmt.getString(15));
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            obraResponse = new ObraResultDto();
            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(1);
            obraResponse.getResponseService().setCodMsg(e.getMessage());
            return obraResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return obraResponse;
    }

    /*-------------------------------------------------------------- sacm_consulta_tag_consola --------------------------------------------------------------------------*/
    public static TagConsolaResultDto ConsultaTagObra(ObraDto tagsRequest) {
        List<TagConsolaDto> tagListResult = new ArrayList<TagConsolaDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.CONSULTA_TAGS_OBRA(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, tagsRequest.getId_obra());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, -10);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(3) == 0) {
                rs = (ResultSet) cstmt.getObject(2);
                List<TagConsolaDto> tagList = new ArrayList<TagConsolaDto>();
                List<TagN2ConsolaDto> tagN2List = new ArrayList<TagN2ConsolaDto>();
                while (rs.next()) {
                    TagConsolaDto tag = new TagConsolaDto();
                    tagN2List = new ArrayList<TagN2ConsolaDto>();
                    tag.setTagsList(tagN2List);
                    TagN2ConsolaDto tagN2 = new TagN2ConsolaDto();
                    //Asignamiento de valores al objeto Tag
                    tag.setIdTag(rs.getInt(3));
                    tag.setTagName(rs.getString(1));
                    //Asignamiento de valores al objeto Tag nivel 1
                    tagN2.setIdTag(rs.getInt(4));
                    tagN2.setTagName(rs.getString(2));

                    //Se agrega el elemento Tag de nivel 1 en el objeto Tag nivel 2
                    
                    tag.getTagsList().add(tagN2);
                    tagList.add(tag);
                }
                organizaList(tagListResult, tagList);
                rs.close();
            }
            // 6. Set value of dateValue property using first OUT param
            tagsResponse = new TagConsolaResultDto();
            tagsResponse.setResponseBD(new HeaderDto());
            tagsResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            tagsResponse.getResponseBD().setCodMsg(cstmt.getString(4));
            tagsResponse.setResponseService(new HeaderDto());
            tagsResponse.getResponseService().setCodErr(cstmt.getInt(3));
            tagsResponse.getResponseService().setCodMsg(cstmt.getString(4));
            tagsResponse.setTagsList(tagListResult);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            tagsResponse = new TagConsolaResultDto();
            tagsResponse.setResponseService(new HeaderDto());
            tagsResponse.getResponseService().setCodErr(1);
            tagsResponse.getResponseService().setCodMsg(e.getMessage());
            return tagsResponse;
        }
        _logger.info("Finish getEstados");
        // 9. Return the result
        return tagsResponse;
    }


    /*-------------------------------------------------------------- sacm_actualiza_usuario_consola --------------------------------------------------------------------------*/
    public static UsuarioResultDto ActualizaUsuario(UsuarioDto usuarioRequest) {
      
        CallableStatement cstmt = null;
      
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.ACTUALIZA_USUARIOS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, usuarioRequest.getId_usuario());
            cstmt.setObject(2, usuarioRequest.getEmail());
            cstmt.setObject(3, usuarioRequest.getCompania());
            cstmt.setObject(4, usuarioRequest.getPuesto());
            cstmt.setObject(5, usuarioRequest.getId_sexo());
            cstmt.setObject(6, usuarioRequest.getId_pais());
            cstmt.setObject(7, usuarioRequest.getId_estado());
            cstmt.setObject(8, usuarioRequest.getMunicipio());
            cstmt.setObject(9, usuarioRequest.getCodigo_postal());
            cstmt.setObject(10, usuarioRequest.getDireccion());
            cstmt.setObject(11, usuarioRequest.getTelefono());
            cstmt.setObject(12, usuarioRequest.getExtension());
            cstmt.setObject(13, usuarioRequest.getEstatus());

            // 4. Register the positions and types of the OUT parameters
           
            cstmt.registerOutParameter(14, Types.INTEGER);
            cstmt.registerOutParameter(15, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();
          
            // 6. Set value of dateValue property using first OUT param
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.setResponseBD(new HeaderDto());
            usuarioResponse.getResponseBD().setCodErr(cstmt.getInt(14));
            usuarioResponse.getResponseBD().setCodMsg(cstmt.getString(15));
            usuarioResponse.setResponseService(new HeaderDto());
            usuarioResponse.getResponseService().setCodErr(cstmt.getInt(14));
            usuarioResponse.getResponseService().setCodMsg(cstmt.getString(15));
         //   usuarioResponse.setTagsList(tagListResult);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.setResponseService(new HeaderDto());
            usuarioResponse.getResponseService().setCodErr(1);
            usuarioResponse.getResponseService().setCodMsg(e.getMessage());
            return usuarioResponse;
        }
        _logger.info("Finish getEstados");
        // 9. Return the result
       
        return usuarioResponse;
    }

    /*-------------------------------------------------------------- sacm_actualiza_usuario_consola --------------------------------------------------------------------------*/
    /*   public static UsuarioResultDto ConsultaUsuario(UsuarioDto usuarioRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.CONSULTA_USUARIOS(?,?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, usuarioRequest.getNombre());
            cstmt.setObject(2, usuarioRequest.getEmail());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(3, -10);           
            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();

           
            if (cstmt.getInt(4) == 0) {   
                rs = (ResultSet) cstmt.getObject(2);
                while (rs.next()) {
                    UsuarioDto usuario = new UsuarioDto();
                    
                    usuario.setNombre(rs.getString(1));
                    usuario.setApellido_paterno(rs.getString(2));
                    usuario.setEmail(rs.getString(3));
                    usuario.setCompania(rs.getString(4));
                    usuario.setPuesto(rs.getString(5));
                    usuario.setId_pais(new Integer(cstmt.getString(8)));
                    usuario.setEstatus(cstmt.getString(9));
                    usuarioResponse.setLoginUser(usuario);
                }
                
                rs.close();
            }
            cstmt.close();
            conn.close();
            conn = null;
            
            usuarioResponse = new UsuarioResultDto();           
            // 6. Set value of dateValue property using first OUT param
            usuarioResponse.setResponseBD(new HeaderDto());
            usuarioResponse.getResponseBD().setCodErr(cstmt.getInt(4));
            usuarioResponse.getResponseBD().setCodMsg(cstmt.getString(5));

            usuarioResponse.setResponseService(new HeaderDto());
            usuarioResponse.getResponseService().setCodErr(cstmt.getInt(4));
            usuarioResponse.getResponseService().setCodMsg(cstmt.getString(5));

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            usuarioResponse = new UsuarioResultDto();
            usuarioResponse.setResponseService(new HeaderDto());
            usuarioResponse.getResponseService().setCodErr(1);
            usuarioResponse.getResponseService().setCodMsg(e.getMessage());
            return usuarioResponse;
        }
        _logger.info("Finish Login");
        // 9. Return the result
        return usuarioResponse;
    }*/

    private static void organizaList(List<TagConsolaDto> tagListResult, List<TagConsolaDto> tagList) {
        Map<Integer, TagN2ConsolaDto> mapN1 = new TreeMap<Integer, TagN2ConsolaDto>();
        Map<Integer, TagConsolaDto> map = new HashMap<Integer, TagConsolaDto>();
        List<TagN2ConsolaDto> tagsListN1 = new ArrayList<TagN2ConsolaDto>();

        // Creación de MAp para eliminar elementos Tag repetidos
        for (TagConsolaDto str : tagList) {
            map.put(str.getIdTag(), str);
        }
        for (TagConsolaDto value : map.values()) {
            tagListResult.add(value);
        }

        //Organización y eliminación de elementos Tag nivel 1 repetidos
        for (TagConsolaDto strTLR : tagListResult) {
            mapN1 = new TreeMap<Integer, TagN2ConsolaDto>();
            tagsListN1 = new ArrayList<TagN2ConsolaDto>();
            for (TagConsolaDto strTL : tagList) {
                if (strTL.getIdTag() == strTLR.getIdTag()) {
                    TagN2ConsolaDto partN1 = new TagN2ConsolaDto();
                    partN1.setIdTag(strTL.getTagsList()
                                         .get(0)
                                         .getIdTag());
                    partN1.setTagName(strTL.getTagsList()
                                           .get(0)
                                           .getTagName());
                    mapN1.put(partN1.getIdTag(), partN1);
                }
            }
            for (TagN2ConsolaDto value : mapN1.values()) {
                tagsListN1.add(value);
            }
            //Organización y eliminación de elementos Tag nivel 2 repetidos
           
            strTLR.setTagsList(tagsListN1);

        }

    }
}
