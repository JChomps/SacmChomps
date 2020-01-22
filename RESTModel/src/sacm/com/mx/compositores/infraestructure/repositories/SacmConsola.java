package sacm.com.mx.compositores.infraestructure.repositories;

import java.io.ByteArrayInputStream;

import java.io.FileInputStream;

import java.io.InputStream;

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


import sacm.com.mx.compositores.common.dtos.CotizacionDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TagsDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.ValidaObraResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.CalificacionDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.CalificacionResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.LogueoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.LogueoResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.ParticipanteResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.TagConsolaDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.TagConsolaResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.TagN1ConsolaDto;
import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.AlbumDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.AlbumResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.CompObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.NombreParticipanteDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ParticipanteDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.Tag;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TagN1;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TagN2;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TagN2Dto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TagsResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.VersionDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.VersionResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Compras.RegistroResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.TagN2ConsolaDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Inicio_Sesion.UsuarioDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Inicio_Sesion.UsuarioResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil.SolicitudDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Perfil.SolicitudResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos.ProyectoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos.ProyectoResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Registro_Usuario.ActivacionDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmConsola {

    @SuppressWarnings("compatibility:3396088568485404005")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmEstado.class);
    private static ObraResultDto obraResponse;
    private static ParticipanteResultDto participanteResult;
    private static VersionResultDto versionResult;
    private static ValidaObraResultDto validaREsponse;
    private static TagConsolaResultDto tagsResponse;
    private static UsuarioResultDto usuarioResponse;
    private static AlbumResultDto albumResponse;
    private static LogueoResultDto logResponse;
    private static UsuarioResultDto UsuarioResponse;
    private static CalificacionResultDto calificacionResponse;
    private static SolicitudResultDto SolicitudResponse;
    private static TagsResultDto TagsResponse;
     private static ProyectoResultDto proyectoResponse;
    


    public SacmConsola() {
        super();
    }

    /*------------------------------------------------------------- sacm_consulta_obra_consola Service ------------------------------------------------------------------------------*/
    public static ObraResultDto getObra(ObraDto obraRequest) {
        List<ObraDto> obraList = new ArrayList<ObraDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        Short a = 0;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.CONSULTA_OBRA(?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            //= new Boolean(true);
            cstmt.setObject(1, obraRequest.getObra_numero());
            cstmt.setObject(2, obraRequest.getObra_titulo());
            cstmt.setObject(3, obraRequest.getCarga_audio());


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
                    obra.setObra_descripcion(rs.getObject(4) == null ? "" : rs.getString(4));

                    if (rs.getObject(5) != null) {
                        bdata =
                            (rs.getObject(5) == null ? null : rs.getBlob(5).getBytes(1, (int) rs.getBlob(5).length()));
                        obra.setVersion_wav(rs.getObject(5) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    } else {
                        obra.setVersion_wav("");
                    }


                    if (rs.getObject(6) != null) {
                        bdata =
                            (rs.getObject(6) == null ? null : rs.getBlob(6).getBytes(1, (int) rs.getBlob(6).length()));
                        obra.setVersion_mp3(rs.getObject(6) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    } else {
                        obra.setVersion_mp3("");
                    }


                    if (rs.getObject(7) != null) {
                        bdata =
                            (rs.getObject(7) == null ? null : rs.getBlob(7).getBytes(1, (int) rs.getBlob(7).length()));
                        obra.setVersion_aiff(rs.getObject(7) == null ? null :
                                             Base64.getEncoder().encodeToString(bdata));
                    } else {
                        obra.setVersion_aiff("");
                    }

                    obra.setVersion_lyric(rs.getObject(8) == null ? "" : rs.getString(8));

                    //   obra.setConsagrada(rs.getInt(9));
                    obra.setObra_consagrada(rs.getObject(9) == null ? "" : rs.getString(9));

                    obra.setControl(rs.getInt(10));

                    //  obra.setPreAutorizacion(rs.getInt(11));
                    obra.setObra_preAutorizado(rs.getObject(11) == null ? "" : rs.getString(11));

                    // obra.setActivo(rs.getInt(12));
                    obra.setObra_activo(rs.getObject(12) == null ? "" : rs.getString(12));

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
        // byte[] byteData = { (byte) 0x1a, (byte) 0x2b, (byte) 0x3c };

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.INSERTA_OBRA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, obraRequest.getId_obra());
            cstmt.setObject(2, obraRequest.getId_album());


            if (obraRequest.getPicture() != null) {
                String imagen = obraRequest.getPicture();
                byte[] byteData = Base64.getDecoder().decode(imagen.getBytes());
                InputStream targetStream = new ByteArrayInputStream(byteData);

                cstmt.setBlob(3, targetStream);
            } else {
                cstmt.setObject(3, null, java.sql
                                             .Types
                                             .BLOB);
            }


            //cstmt.setObject(2, obraRequest.get_Imagen());
            cstmt.setObject(4, obraRequest.getObra_numero());
            cstmt.setObject(5, obraRequest.getObra_titulo());
            cstmt.setObject(6, obraRequest.getObra_descripcion());

            if (obraRequest.getWav() != null) {
                String imagen = obraRequest.getWav();
                byte[] byteData = Base64.getDecoder().decode(imagen.getBytes());
                InputStream targetStream = new ByteArrayInputStream(byteData);

                cstmt.setBlob(7, targetStream);
            } else {
                cstmt.setObject(7, null, java.sql
                                             .Types
                                             .BLOB);
            }


            if (obraRequest.getMp3() != null) {
                String imagen = obraRequest.getMp3();
                byte[] byteData = Base64.getDecoder().decode(imagen.getBytes());
                InputStream targetStream = new ByteArrayInputStream(byteData);

                cstmt.setBlob(8, targetStream);
            } else {
                cstmt.setObject(8, null, java.sql
                                             .Types
                                             .BLOB);
            }


            if (obraRequest.getAiff() != null) {
                String imagen = obraRequest.getAiff();
                byte[] byteData = Base64.getDecoder().decode(imagen.getBytes());
                InputStream targetStream = new ByteArrayInputStream(byteData);

                cstmt.setBlob(9, targetStream);
            } else
                cstmt.setObject(9, null, java.sql
                                             .Types
                                             .BLOB);


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


        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.ACTUALIZA_OBRA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, obraRequest.getId_obra());

            if (obraRequest.getPicture() != null) {
                String imagen = obraRequest.getPicture();
                byte[] byteData = Base64.getDecoder().decode(imagen.getBytes());
                InputStream targetStream = new ByteArrayInputStream(byteData);

                cstmt.setBlob(2, targetStream);
            } else {
                cstmt.setObject(2, null, java.sql
                                             .Types
                                             .BLOB);
            }


            //cstmt.setObject(2, obraRequest.get_Imagen());
            cstmt.setObject(3, obraRequest.getObra_numero());
            cstmt.setObject(4, obraRequest.getObra_titulo());
            cstmt.setObject(5, obraRequest.getObra_descripcion());


            if (obraRequest.getWav() != null) {
                String imagen = obraRequest.getWav();
                byte[] byteData = Base64.getDecoder().decode(imagen.getBytes());
                InputStream targetStream = new ByteArrayInputStream(byteData);

                cstmt.setBlob(6, targetStream);
            } else {
                cstmt.setObject(6, null, java.sql
                                             .Types
                                             .BLOB);
            }


            if (obraRequest.getMp3() != null) {
                String imagen = obraRequest.getMp3();
                byte[] byteData = Base64.getDecoder().decode(imagen.getBytes());
                InputStream targetStream = new ByteArrayInputStream(byteData);

                cstmt.setBlob(7, targetStream);
            } else {
                cstmt.setObject(7, null, java.sql
                                             .Types
                                             .BLOB);
            }


            if (obraRequest.getAiff() != null) {
                String imagen = obraRequest.getAiff();
                byte[] byteData = Base64.getDecoder().decode(imagen.getBytes());
                InputStream targetStream = new ByteArrayInputStream(byteData);

                cstmt.setBlob(8, targetStream);
            } else
                cstmt.setObject(8, null, java.sql
                                             .Types
                                             .BLOB);


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
                List<TagN1ConsolaDto> tagN2List = new ArrayList<TagN1ConsolaDto>();
                while (rs.next()) {
                    TagConsolaDto tag = new TagConsolaDto();
                    tagN2List = new ArrayList<TagN1ConsolaDto>();
                    tag.setTagsList(tagN2List);
                    TagN1ConsolaDto tagN2 = new TagN1ConsolaDto();
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

    /*---------------------------------------------------------sacm_agrega_carrito Service----------------------------------------------------------------------*/

    public static AlbumResultDto ConsultaAlbum() {
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.LOV_ALBUMS(?,?,?)}");
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(1, -10);
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();
            List<AlbumDto> albumList = new ArrayList<AlbumDto>();
            if (cstmt.getInt(2) == 0) {

                // print the results
                rs = (ResultSet) cstmt.getObject(1);
                while (rs.next()) {
                    AlbumDto album = new AlbumDto();
                    album.setId_album(rs.getInt(1));
                    album.setNombre_album(rs.getString(2));

                    albumList.add(album);
                }
                rs.close();
            }
            // 6. Set value of dateValue property using first OUT param
            albumResponse = new AlbumResultDto();
            albumResponse.setResponseBD(new HeaderDto());
            albumResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            albumResponse.getResponseBD().setCodMsg(cstmt.getString(3));
            albumResponse.setResponseService(new HeaderDto());
            albumResponse.getResponseService().setCodErr(cstmt.getInt(2));
            albumResponse.getResponseService().setCodMsg(cstmt.getString(3));
            if (albumList.size() > 0)
                albumResponse.setAlbumes(albumList);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            albumResponse = new AlbumResultDto();
            albumResponse.setResponseService(new HeaderDto());
            albumResponse.getResponseService().setCodErr(1);
            albumResponse.getResponseService().setCodMsg(e.getMessage());
            return albumResponse;
        }

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return albumResponse;
    }

    /*-------------------------------------------------------------- sacm_consulta_usuario_consola --------------------------------------------------------------------------*/
    public static UsuarioResultDto ConsultaUsuario(UsuarioDto usuarioRequest) {
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
            usuarioResponse = new UsuarioResultDto();
            UsuarioDto usuario = new UsuarioDto();
            if (cstmt.getInt(4) == 0) {
                rs = (ResultSet) cstmt.getObject(3);
                while (rs.next()) {
                    usuario = new UsuarioDto();
                    usuario.setNombre(rs.getObject(1) == null ? "" : rs.getString(1));
                    usuario.setApellido_paterno(rs.getObject(2) == null ? "" : rs.getString(2));
                    usuario.setEmail(rs.getObject(3) == null ? "" : rs.getString(3));
                    usuario.setCompania(rs.getObject(4) == null ? "" : rs.getString(4));
                    usuario.setPuesto(rs.getObject(5) == null ? "" : rs.getString(5));
                    usuario.setSexo(rs.getObject(6) == null ? "" : rs.getString(6));
                    usuario.setPais(rs.getObject(7) == null ? "" : rs.getString(7));
                    usuario.setEstado(rs.getObject(8) == null ? "" : rs.getString(8));
                    usuario.setMunicipio(rs.getObject(9) == null ? "" : rs.getString(9));
                    usuario.setCodigo_postal(rs.getObject(10) == null ? "" : rs.getString(10));
                    usuario.setDireccion(rs.getObject(11) == null ? "" : rs.getString(11));
                    usuario.setTelefono(rs.getObject(12) == null ? "" : rs.getString(12));
                    usuario.setExtension(rs.getObject(13) == null ? "" : rs.getString(13));
                    usuario.setEstatus(rs.getObject(14) == null ? "" : rs.getString(14));
                    usuarioResponse.setLoginUser(usuario);
                }

                rs.close();
            }


            // usuarioResponse = new UsuarioResultDto();
            // 6. Set value of dateValue property using first OUT param
            usuarioResponse.setResponseBD(new HeaderDto());
            usuarioResponse.getResponseBD().setCodErr(cstmt.getInt(4));
            usuarioResponse.getResponseBD().setCodMsg(cstmt.getString(5));

            usuarioResponse.setResponseService(new HeaderDto());
            usuarioResponse.getResponseService().setCodErr(cstmt.getInt(4));
            usuarioResponse.getResponseService().setCodMsg(cstmt.getString(5));
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
        _logger.info("Finish Login");
        // 9. Return the result
        return usuarioResponse;
    }

    /*-------------------------------------------------------------- sacm_lov_tag_consola --------------------------------------------------------------------------*/
    public static TagConsolaResultDto ConsultaLovTag(TagConsolaDto tagsRequest) {
        List<TagConsolaDto> tagListResult = new ArrayList<TagConsolaDto>();
        List<TagConsolaDto> tagList = new ArrayList<TagConsolaDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.LOV_TAGS(?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, tagsRequest.getIdTag());
            cstmt.setObject(2, tagsRequest.getIdTagItem());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(3, -10);
            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(4) == 0) {
                rs = (ResultSet) cstmt.getObject(3);

                List<TagN1ConsolaDto> tagN1List = new ArrayList<TagN1ConsolaDto>();
                List<TagN2ConsolaDto> tagN2List = new ArrayList<TagN2ConsolaDto>();
                TagConsolaDto tag = new TagConsolaDto();
                TagN1ConsolaDto tagN1 = new TagN1ConsolaDto();
                tag.setTagsList(tagN1List);
                tagN1.setTagsList(tagN2List);
                while (rs.next()) {

                    if (rs.getInt(4) == 1) {

                        if (tagN2List.size() > 0) {
                            tagN1.setTagsList(tagN2List);
                            tagN2List = new ArrayList<TagN2ConsolaDto>();
                        }

                        tagN1 = new TagN1ConsolaDto();
                        tag.setTagName(rs.getString(2));
                        tagN1.setIdTag(rs.getInt(1));
                        tagN1.setTagName(rs.getString(3));
                        tag.getTagsList().add(tagN1);

                    }
                    if (rs.getInt(4) == 2) {
                        TagN2ConsolaDto tagN2 = new TagN2ConsolaDto();
                        tagN2.setIdTag(rs.getInt(1));
                        tagN2.setTagName(rs.getString(3));
                        tagN2List.add(tagN2);
                    }
                }

                if (tagN2List.size() > 0) {
                    tagN1.setTagsList(tagN2List);
                }

                tagList.add(tag);
                rs.close();
            }
            // 6. Set value of dateValue property using first OUT param
            tagsResponse = new TagConsolaResultDto();
            tagsResponse.setResponseBD(new HeaderDto());
            tagsResponse.getResponseBD().setCodErr(cstmt.getInt(4));
            tagsResponse.getResponseBD().setCodMsg(cstmt.getString(5));
            tagsResponse.setResponseService(new HeaderDto());
            tagsResponse.getResponseService().setCodErr(cstmt.getInt(4));
            tagsResponse.getResponseService().setCodMsg(cstmt.getString(5));
            // if (tagListResult.size() > 0)
            tagsResponse.setTagsList(tagList);
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

    /*-------------------------------------------------------------- sacm_inserta_tag_obra_consola --------------------------------------------------------------------------*/
    public static ParticipanteResultDto insertaTagObra(ObraDto obraRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.INSERTA_TAG_OBRA(?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, obraRequest.getId_obra());
            cstmt.setObject(2, obraRequest.getIdTagItem());
            cstmt.setObject(3, obraRequest.getActivo());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();

            // 6. Set value of dateValue property using first OUT param
            participanteResult = new ParticipanteResultDto();
            participanteResult.setResponseBD(new HeaderDto());
            participanteResult.getResponseBD().setCodErr(cstmt.getInt(4));
            participanteResult.getResponseBD().setCodMsg(cstmt.getString(5));
            participanteResult.setResponseService(new HeaderDto());
            participanteResult.getResponseService().setCodErr(cstmt.getInt(4));
            participanteResult.getResponseService().setCodMsg(cstmt.getString(5));

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
        _logger.info("Finish getEstados");
        // 9. Return the result
        return participanteResult;
    }

    /*---------------------------------------------------------sacm_agrega_carrito Service----------------------------------------------------------------------*/

    public static ParticipanteResultDto LovParticipantes() {
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.LOV_PARTICIPANTES(?,?,?)}");
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(1, -10);
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();
            List<NombreParticipanteDto> participanteList = new ArrayList<NombreParticipanteDto>();
            if (cstmt.getInt(2) == 0) {

                // print the results
                rs = (ResultSet) cstmt.getObject(1);
                while (rs.next()) {
                    NombreParticipanteDto participante = new NombreParticipanteDto();
                    participante.setId_participante(rs.getInt(1));
                    participante.setNombre(rs.getString(2));

                    participanteList.add(participante);
                }
                rs.close();
            }
            // 6. Set value of dateValue property using first OUT param
            participanteResult = new ParticipanteResultDto();
            participanteResult.setResponseBD(new HeaderDto());
            participanteResult.getResponseBD().setCodErr(cstmt.getInt(2));
            participanteResult.getResponseBD().setCodMsg(cstmt.getString(3));
            participanteResult.setResponseService(new HeaderDto());
            participanteResult.getResponseService().setCodErr(cstmt.getInt(2));
            participanteResult.getResponseService().setCodMsg(cstmt.getString(3));
            if (participanteList.size() > 0)
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

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return participanteResult;
    }


    /*---------------------------------------------------------sacm_lov_obras_consola Service----------------------------------------------------------------------*/

    public static ObraResultDto LovObras() {
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.LOV_OBRAS(?,?,?)}");
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(1, -10);
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();
            List<ObraDto> obraList = new ArrayList<ObraDto>();
            if (cstmt.getInt(2) == 0) {

                // print the results
                rs = (ResultSet) cstmt.getObject(1);
                while (rs.next()) {
                    ObraDto obra = new ObraDto();
                    obra.setId_obra(rs.getInt(1));
                    obra.setObra_titulo(rs.getString(2));

                    obraList.add(obra);
                }
                rs.close();
            }
            // 6. Set value of dateValue property using first OUT param
            obraResponse = new ObraResultDto();
            obraResponse.setResponseBD(new HeaderDto());
            obraResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            obraResponse.getResponseBD().setCodMsg(cstmt.getString(3));
            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(cstmt.getInt(2));
            obraResponse.getResponseService().setCodMsg(cstmt.getString(3));
            if (obraList.size() > 0)
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

        _logger.info("Finish Activacion Cuenta");
        // 9. Return the result
        return obraResponse;
    }


    private static void organizaList(List<TagConsolaDto> tagListResult, List<TagConsolaDto> tagList) {
        Map<Integer, TagN1ConsolaDto> mapN1 = new TreeMap<Integer, TagN1ConsolaDto>();
        Map<Integer, TagConsolaDto> map = new HashMap<Integer, TagConsolaDto>();
        List<TagN1ConsolaDto> tagsListN1 = new ArrayList<TagN1ConsolaDto>();

        // Creación de MAp para eliminar elementos Tag repetidos
        for (TagConsolaDto str : tagList) {
            map.put(str.getIdTag(), str);
        }
        for (TagConsolaDto value : map.values()) {
            tagListResult.add(value);
        }

        //Organización y eliminación de elementos Tag nivel 1 repetidos
        for (TagConsolaDto strTLR : tagListResult) {
            mapN1 = new TreeMap<Integer, TagN1ConsolaDto>();
            tagsListN1 = new ArrayList<TagN1ConsolaDto>();
            for (TagConsolaDto strTL : tagList) {
                if (strTL.getIdTag() == strTLR.getIdTag()) {
                    TagN1ConsolaDto partN1 = new TagN1ConsolaDto();
                    partN1.setIdTag(strTL.getTagsList()
                                         .get(0)
                                         .getIdTag());
                    partN1.setTagName(strTL.getTagsList()
                                           .get(0)
                                           .getTagName());
                    mapN1.put(partN1.getIdTag(), partN1);
                }
            }
            for (TagN1ConsolaDto value : mapN1.values()) {
                tagsListN1.add(value);
            }
            //Organización y eliminación de elementos Tag nivel 2 repetidos

            strTLR.setTagsList(tagsListN1);

        }

    }


    /*---------------------------------------------------------sacm_lov_inserta_participantes_consola Service----------------------------------------------------------------------*/

    public static ParticipanteResultDto LovInsertaParticipantes(NombreParticipanteDto participanteRequest) {

        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.INSERTA_PARTICIPANTE_OBRA(?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, participanteRequest.getId_obra());
            cstmt.setObject(2, participanteRequest.getId_participante());
            cstmt.setObject(3, participanteRequest.getActivo());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();

            // 6. Set value of dateValue property using first OUT param
            participanteResult = new ParticipanteResultDto();
            participanteResult.setResponseBD(new HeaderDto());
            participanteResult.getResponseBD().setCodErr(cstmt.getInt(4));
            participanteResult.getResponseBD().setCodMsg(cstmt.getString(5));
            participanteResult.setResponseService(new HeaderDto());
            participanteResult.getResponseService().setCodErr(cstmt.getInt(4));
            participanteResult.getResponseService().setCodMsg(cstmt.getString(5));

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
        _logger.info("Finish getEstados");
        // 9. Return the result
        return participanteResult;
    }

    /*-----------------------------------------------------sacm_consulta_logueos_mes Service-------------------------------------------------------------------*/
    public static LogueoResultDto ConsultaLogueo(LogueoDto LogRequest) {
        List<LogueoDto> logListResult = new ArrayList<LogueoDto>();
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.CONSULTA_LOGUEOS_MES(?,?,?,?)}");


            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, LogRequest.getOpcion());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, -10);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();
            logResponse = new LogueoResultDto();
            if (cstmt.getInt(3) == 0) {
                rs = (ResultSet) cstmt.getObject(2);
                while (rs.next()) {
                    LogueoDto usuario = new LogueoDto();
                    switch (LogRequest.getOpcion()) {
                    case 1:
                        logResponse.setTotal(rs.getInt(1));
                        break;
                    case 2:
                        usuario.setId_usuario(rs.getInt(1));
                        usuario.setNombre(rs.getString(2));
                        usuario.setEmail(rs.getString(3));
                        usuario.setId_pais(rs.getInt(4));
                        usuario.setPais(rs.getString(5));
                        usuario.setFecha(rs.getString(6));
                        logListResult.add(usuario);
                        break;

                    case 3:
                        usuario.setId_pais(rs.getInt(1));
                        usuario.setPais(rs.getString(2));
                        usuario.setConteo(rs.getInt(3));
                        logListResult.add(usuario);
                        break;
                    default:
                        break;
                    }
                }
                rs.close();
            }


            // 6. Set value of dateValue property using first OUT param
            logResponse.setResponseBD(new HeaderDto());
            logResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            logResponse.getResponseBD().setCodMsg(cstmt.getString(4));

            logResponse.setResponseService(new HeaderDto());
            logResponse.getResponseService().setCodErr(cstmt.getInt(3));
            logResponse.getResponseService().setCodMsg(cstmt.getString(4));
            if (logListResult.size() > 0)
                logResponse.setAccesos(logListResult);
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            logResponse = new LogueoResultDto();
            logResponse.setResponseService(new HeaderDto());
            logResponse.getResponseService().setCodErr(1);
            logResponse.getResponseService().setCodMsg(e.getMessage());
            return logResponse;
        }
        _logger.info("Finish Login");
        // 9. Return the result
        return logResponse;
    }

    /*-----------------------------------------------------sacm_califica_obra Service-------------------------------------------------------------------*/
    public static CalificacionResultDto CalificaObra(CalificacionDto calRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.CALIFICA_OBRA(?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, calRequest.getId_usuario());
            cstmt.setObject(2, calRequest.getId_obra());
            cstmt.setObject(3, calRequest.getCalificacion());


            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();

            // 6. Set value of dateValue property using first OUT param
            calificacionResponse = new CalificacionResultDto();
            calificacionResponse.setResponseBD(new HeaderDto());
            calificacionResponse.getResponseBD().setCodErr(cstmt.getInt(4));
            calificacionResponse.getResponseBD().setCodMsg(cstmt.getString(5));
            calificacionResponse.setResponseService(new HeaderDto());
            calificacionResponse.getResponseService().setCodErr(cstmt.getInt(4));
            calificacionResponse.getResponseService().setCodMsg(cstmt.getString(5));

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            calificacionResponse = new CalificacionResultDto();
            calificacionResponse.setResponseService(new HeaderDto());
            calificacionResponse.getResponseService().setCodErr(1);
            calificacionResponse.getResponseService().setCodMsg(e.getMessage());
            return calificacionResponse;
        }
        _logger.info("Finish getEstados");
        // 9. Return the result
        return calificacionResponse;
    }

    /*-----------------------------------------------------sacm_verifica_calificacion Service-------------------------------------------------------------------*/

    public static CalificacionResultDto VerificaCalificacion(CalificacionDto calRequest) {

        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.VERIFICA_CALIFICACION(?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, calRequest.getId_usuario());
            cstmt.setObject(2, calRequest.getId_obra());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.INTEGER);
            cstmt.registerOutParameter(6, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();

            // 6. Set value of dateValue property using first OUT param
            calificacionResponse = new CalificacionResultDto();
            calificacionResponse.setCalificado(cstmt.getString(3));
            calificacionResponse.setCalificacion(cstmt.getInt(4));
            calificacionResponse.setResponseBD(new HeaderDto());
            calificacionResponse.getResponseBD().setCodErr(cstmt.getInt(5));
            calificacionResponse.getResponseBD().setCodMsg(cstmt.getString(6));
            calificacionResponse.setResponseService(new HeaderDto());
            calificacionResponse.getResponseService().setCodErr(cstmt.getInt(5));
            calificacionResponse.getResponseService().setCodMsg(cstmt.getString(6));

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            calificacionResponse = new CalificacionResultDto();
            calificacionResponse.setResponseService(new HeaderDto());
            calificacionResponse.getResponseService().setCodErr(1);
            calificacionResponse.getResponseService().setCodMsg(e.getMessage());
            return calificacionResponse;
        }
        _logger.info("Finish getEstados");
        // 9. Return the result
        return calificacionResponse;
    }

    /*-----------------------------------------------------sacm_consulta_calificacion Service-------------------------------------------------------------------*/

    public static CalificacionResultDto ConsultaCalificacion(CalificacionDto calRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.CONSULTA_CALIFICACION(?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters

            cstmt.setObject(1, calRequest.getId_obra());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.VARCHAR);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();

            // 6. Set value of dateValue property using first OUT param
            calificacionResponse = new CalificacionResultDto();
            calificacionResponse.setCalificado(cstmt.getString(2));
            calificacionResponse.setPromedio(cstmt.getInt(3));
            calificacionResponse.setResponseBD(new HeaderDto());
            calificacionResponse.getResponseBD().setCodErr(cstmt.getInt(4));
            calificacionResponse.getResponseBD().setCodMsg(cstmt.getString(5));
            calificacionResponse.setResponseService(new HeaderDto());
            calificacionResponse.getResponseService().setCodErr(cstmt.getInt(4));
            calificacionResponse.getResponseService().setCodMsg(cstmt.getString(5));

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            calificacionResponse = new CalificacionResultDto();
            calificacionResponse.setResponseService(new HeaderDto());
            calificacionResponse.getResponseService().setCodErr(1);
            calificacionResponse.getResponseService().setCodMsg(e.getMessage());
            return calificacionResponse;
        }
        _logger.info("Finish getEstados");
        // 9. Return the result
        return calificacionResponse;
    }

    public static CalificacionResultDto InsertaVersiones(VersionDto versionRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.INSERTA_VERSION(?,?,?,?,?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters

            cstmt.setObject(1, versionRequest.getId_obra());
            cstmt.setObject(2, versionRequest.getVersion());
            cstmt.setObject(3, versionRequest.getVersion_descripcion());
            cstmt.setObject(4, versionRequest.getActivo());
            cstmt.setObject(5, versionRequest.getVersion_duracion());

            if (versionRequest.getVersion_wav() != null) {
                String imagen = versionRequest.getVersion_wav();
                byte[] byteData = Base64.getDecoder().decode(imagen.getBytes());
                InputStream targetStream = new ByteArrayInputStream(byteData);

                cstmt.setBlob(6, targetStream);
            } else {
                cstmt.setObject(6, null, java.sql
                                             .Types
                                             .BLOB);
            }
            // cstmt.setObject(6, versionRequest.getVersion_wav());

            if (versionRequest.getVersion_mp3() != null) {
                String imagen = versionRequest.getVersion_mp3();
                byte[] byteData = Base64.getDecoder().decode(imagen.getBytes());
                InputStream targetStream = new ByteArrayInputStream(byteData);

                cstmt.setBlob(7, targetStream);
            } else {
                cstmt.setObject(7, null, java.sql
                                             .Types
                                             .BLOB);
            }
            // cstmt.setObject(7, versionRequest.getVersion_mp3());

            if (versionRequest.getVersion_aiff() != null) {
                String imagen = versionRequest.getVersion_aiff();
                byte[] byteData = Base64.getDecoder().decode(imagen.getBytes());
                InputStream targetStream = new ByteArrayInputStream(byteData);

                cstmt.setBlob(8, targetStream);
            } else {
                cstmt.setObject(8, null, java.sql
                                             .Types
                                             .BLOB);
            }
            //cstmt.setObject(8, versionRequest.getVersion_aiff());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(9, Types.INTEGER);
            cstmt.registerOutParameter(10, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();

            // 6. Set value of dateValue property using first OUT param
            calificacionResponse = new CalificacionResultDto();
            calificacionResponse.setResponseBD(new HeaderDto());
            calificacionResponse.getResponseBD().setCodErr(cstmt.getInt(9));
            calificacionResponse.getResponseBD().setCodMsg(cstmt.getString(10));
            calificacionResponse.setResponseService(new HeaderDto());
            calificacionResponse.getResponseService().setCodErr(cstmt.getInt(9));
            calificacionResponse.getResponseService().setCodMsg(cstmt.getString(10));

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            calificacionResponse = new CalificacionResultDto();
            calificacionResponse.setResponseService(new HeaderDto());
            calificacionResponse.getResponseService().setCodErr(1);
            calificacionResponse.getResponseService().setCodMsg(e.getMessage());
            return calificacionResponse;
        }
        _logger.info("Finish getEstados");
        // 9. Return the result
        return calificacionResponse;
    }

    /*-----------------------------------------------------sacm_reporte_calificacion Service-------------------------------------------------------------------*/
    public static CalificacionResultDto ReporteCalificacion() {
        List<CalificacionDto> CalifList = new ArrayList<CalificacionDto>();
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.REPORTE_CALIFICACION(?,?,?)}");
            // 3. Set the bind values of the IN parameters


            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(1, -10);
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(2) == 0) {

                // print the results
                rs = (ResultSet) cstmt.getObject(1);
                while (rs.next()) {
                    CalificacionDto obra = new CalificacionDto();
                    obra.setId_obra(rs.getInt(1));
                    obra.setNumero_obra(rs.getInt(2));
                    obra.setTitulo(rs.getString(3));
                    obra.setDescripcion(rs.getString(4));
                    obra.setCalificacion(rs.getInt(5));

                    CalifList.add(obra);
                }
                rs.close();
            }

            // 6. Set value of dateValue property using first OUT param
            calificacionResponse = new CalificacionResultDto();
            calificacionResponse.setResponseBD(new HeaderDto());
            calificacionResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            calificacionResponse.getResponseBD().setCodMsg(cstmt.getString(3));
            calificacionResponse.setResponseService(new HeaderDto());
            calificacionResponse.getResponseService().setCodErr(cstmt.getInt(2));
            calificacionResponse.getResponseService().setCodMsg(cstmt.getString(3));
            calificacionResponse.setObras(CalifList);

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            calificacionResponse = new CalificacionResultDto();
            calificacionResponse.setResponseService(new HeaderDto());
            calificacionResponse.getResponseService().setCodErr(1);
            calificacionResponse.getResponseService().setCodMsg(e.getMessage());
            return calificacionResponse;
        }
        _logger.info("Finish getEstados");
        // 9. Return the result
        return calificacionResponse;
    }

    /*-----------------------------------------------------sacm_consulta_items_usuario Service-------------------------------------------------------------------*/
    public static UsuarioResultDto getItemsUsuarios() {
        List<UsuarioDto> usuariosList = new ArrayList<UsuarioDto>();
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.LOV_USUARIOS(?,?,?)}");
            // 3. Set the bind values of the IN parameters


            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(1, -10);
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);

            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(2) == 0) {

                // print the results
                rs = (ResultSet) cstmt.getObject(1);
                while (rs.next()) {
                    UsuarioDto usuario = new UsuarioDto();
                    usuario.setId_usuario(rs.getInt(1));
                    usuario.setNombre(rs.getString(2));
                    usuario.setEmail(rs.getString(3));

                    usuariosList.add(usuario);
                }
                rs.close();
            }

            // 6. Set value of dateValue property using first OUT param
            UsuarioResponse = new UsuarioResultDto();
            UsuarioResponse.setResponseBD(new HeaderDto());
            UsuarioResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            UsuarioResponse.getResponseBD().setCodMsg(cstmt.getString(3));
            UsuarioResponse.setResponseService(new HeaderDto());
            UsuarioResponse.getResponseService().setCodErr(cstmt.getInt(2));
            UsuarioResponse.getResponseService().setCodMsg(cstmt.getString(3));
            UsuarioResponse.setUsuarios(usuariosList);

            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            UsuarioResponse = new UsuarioResultDto();
            UsuarioResponse.setResponseService(new HeaderDto());
            UsuarioResponse.getResponseService().setCodErr(1);
            UsuarioResponse.getResponseService().setCodMsg(e.getMessage());
            return UsuarioResponse;
        }
        _logger.info("Finish getEstados");
        // 9. Return the result
        return UsuarioResponse;
    }

    /*---------------------------------------------------------sacm_consulta_solicitud_consola Service----------------------------------------------------------------------*/

    public static SolicitudResultDto ReporteSolicitudes(UsuarioDto usuarioRequest) {
        List<SolicitudDto> solicitudListResult = new ArrayList<SolicitudDto>();
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_ADMIN.REPORTE_SOLICITUDES(?,?,?,?)}");
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
            SolicitudResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            SolicitudResponse.getResponseBD().setCodMsg(cstmt.getString(4));
            SolicitudResponse.setResponseService(new HeaderDto());
            SolicitudResponse.getResponseService().setCodErr(cstmt.getInt(3));
            SolicitudResponse.getResponseService().setCodMsg(cstmt.getString(4));
            if (solicitudListResult.size() > 0)
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

    /*-----------------------------------------------------sacm_consulta_tag Service-------------------------------------------------------------------*/
    public static TagsResultDto getTags(TagsDto tagRequest) {
        List<TagsDto> TagsList = new ArrayList<TagsDto>();
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_RUG.CONSULTA_TAG(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, tagRequest.getIdTag());
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
                    TagsDto tag = new TagsDto();

                    tag.setIdTag(rs.getInt(1));
                    tag.setTagName(rs.getString(2));
                    tag.setActivo(rs.getString(3));

                    TagsList.add(tag);
                }

                rs.close();
            }
            // 6. Set value of dateValue property using first OUT param
            TagsResponse = new TagsResultDto();
            TagsResponse.setResponseBD(new HeaderDto());
            TagsResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            TagsResponse.getResponseBD().setCodMsg(cstmt.getString(4));
            TagsResponse.setResponseService(new HeaderDto());
            TagsResponse.getResponseService().setCodErr(cstmt.getInt(3));
            TagsResponse.getResponseService().setCodMsg(cstmt.getString(4));
            if (TagsList.size() > 0)
                TagsResponse.setTagList(TagsList);


            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;
        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            TagsResponse = new TagsResultDto();
            TagsResponse.setResponseService(new HeaderDto());
            TagsResponse.getResponseService().setCodErr(1);
            TagsResponse.getResponseService().setCodMsg(e.getMessage());
            return TagsResponse;
        }
        _logger.info("Finish getConsultaAlbum");
        // 9. Return the result

        return TagsResponse;
    }

    /*-----------------------------------------------------sacm_consulta_tag_item Service-------------------------------------------------------------------*/

    public static TagsResultDto getTagItem(TagsDto tagRequest) {
        List<TagsDto> TagsList = new ArrayList<TagsDto>();
        List<TagN2Dto> Tagn2List = new ArrayList<TagN2Dto>();
        CallableStatement cstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_RUG.CONSULTA_TAG_ITEM(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, tagRequest.getIdTag());
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

                    TagsDto tag = new TagsDto();
                    TagN2Dto tagn2 = new TagN2Dto();

                    tag.setIdTag(rs.getInt(1));
                    tag.setTagName(rs.getString(2));
                    tag.setDescripcionTag(rs.getString(3));
                    tag.setActivo(rs.getString(4));

                    tagn2.setIdTag(rs.getInt(5));
                    tagn2.setTagName(rs.getString(6));
                    tagn2.setDescripcionTag(rs.getString(7));
                    tagn2.setActivoTag(rs.getString(8));

                    if (TagsList.size() > 0) {
                        if (rs.getInt(1) != TagsList.get(TagsList.size() - 1).getIdTag()) {
                            if (Tagn2List.size() > 0)
                                TagsList.get(TagsList.size() - 1).setTagList(Tagn2List);
                            TagsList.add(tag);
                            Tagn2List = new ArrayList<TagN2Dto>();

                        } else {
                            Tagn2List.add(tagn2);
                        }
                    } else {
                        TagsList.add(tag);
                        if(rs.getInt(5) >0)
                            Tagn2List.add(tagn2);
                    }

                }
                if (Tagn2List.size() > 0)
                    TagsList.get(TagsList.size() - 1).setTagList(Tagn2List);

                rs.close();
            }
            // 6. Set value of dateValue property using first OUT param
            TagsResponse = new TagsResultDto();
            TagsResponse.setResponseBD(new HeaderDto());
            TagsResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            TagsResponse.getResponseBD().setCodMsg(cstmt.getString(4));
            TagsResponse.setResponseService(new HeaderDto());
            TagsResponse.getResponseService().setCodErr(cstmt.getInt(3));
            TagsResponse.getResponseService().setCodMsg(cstmt.getString(4));
            if (TagsList.size() > 0)
                TagsResponse.setTagList(TagsList);


            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;
        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            TagsResponse = new TagsResultDto();
            TagsResponse.setResponseService(new HeaderDto());
            TagsResponse.getResponseService().setCodErr(1);
            TagsResponse.getResponseService().setCodMsg(e.getMessage());
            return TagsResponse;
        }
        _logger.info("Finish getConsultaAlbum");
        // 9. Return the result

        return TagsResponse;
    }

    /*-----------------------------------------------------sacm_actualiza_tag Service-------------------------------------------------------------------*/
    public static ValidaObraResultDto actualizaTag(TagsDto tagRequest) {
       
        CallableStatement cstmt = null;
        Connection conn = null;
        

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_RUG.ACTUALIZA_TAG(?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, tagRequest.getIdTag());
            cstmt.setObject(2, tagRequest.getTagName());
            cstmt.setObject(3, tagRequest.getActivo());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.VARCHAR);


            // 5. Execute the statement
            cstmt.executeUpdate();
           
            // 6. Set value of dateValue property using first OUT param
            validaREsponse = new ValidaObraResultDto();
            validaREsponse.setResponseBD(new HeaderDto());
            validaREsponse.getResponseBD().setCodErr(cstmt.getInt(4));
            validaREsponse.getResponseBD().setCodMsg(cstmt.getString(5));
            validaREsponse.setResponseService(new HeaderDto());
            validaREsponse.getResponseService().setCodErr(cstmt.getInt(4));
            validaREsponse.getResponseService().setCodMsg(cstmt.getString(5));


            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;
        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            validaREsponse = new ValidaObraResultDto();
            validaREsponse.setResponseService(new HeaderDto());
            validaREsponse.getResponseService().setCodErr(1);
            validaREsponse.getResponseService().setCodMsg(e.getMessage());
            return validaREsponse;
        }
        _logger.info("Finish getConsultaAlbum");
        // 9. Return the result

        return validaREsponse;
    }

    /*-----------------------------------------------------sacm_inserta_tag Service-------------------------------------------------------------------*/
    public static ValidaObraResultDto insertaTag(TagsDto tagRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_RUG.INSERTA_TAG(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, tagRequest.getDescripcionTag());
            cstmt.setObject(2, tagRequest.getActivo());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);


            // 5. Execute the statement
            cstmt.executeUpdate();
           
            // 6. Set value of dateValue property using first OUT param
            validaREsponse = new ValidaObraResultDto();
            validaREsponse.setResponseBD(new HeaderDto());
            validaREsponse.getResponseBD().setCodErr(cstmt.getInt(3));
            validaREsponse.getResponseBD().setCodMsg(cstmt.getString(4));
            validaREsponse.setResponseService(new HeaderDto());
            validaREsponse.getResponseService().setCodErr(cstmt.getInt(3));
            validaREsponse.getResponseService().setCodMsg(cstmt.getString(4));


            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;
        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            validaREsponse = new ValidaObraResultDto();
            validaREsponse.setResponseService(new HeaderDto());
            validaREsponse.getResponseService().setCodErr(1);
            validaREsponse.getResponseService().setCodMsg(e.getMessage());
            return validaREsponse;
        }
        _logger.info("Finish getConsultaAlbum");
        // 9. Return the result

        return validaREsponse;
    }

    /*-----------------------------------------------------sacm_inserta_tag_item Service-------------------------------------------------------------------*/
    public static ValidaObraResultDto insertaTagItem(TagsDto tagRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_RUG.INSERTA_TAG_ITEM(?,?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, tagRequest.getTagName());
            cstmt.setObject(2, tagRequest.getIdTagPadre());
            cstmt.setObject(3, tagRequest.getIdTag());
            cstmt.setObject(4, tagRequest.getDescripcionTag());
            cstmt.setObject(5, tagRequest.getActivo());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(6, Types.INTEGER);
            cstmt.registerOutParameter(7, Types.VARCHAR);


            // 5. Execute the statement
            cstmt.executeUpdate();
           
            // 6. Set value of dateValue property using first OUT param
            validaREsponse = new ValidaObraResultDto();
            validaREsponse.setResponseBD(new HeaderDto());
            validaREsponse.getResponseBD().setCodErr(cstmt.getInt(6));
            validaREsponse.getResponseBD().setCodMsg(cstmt.getString(7));
            validaREsponse.setResponseService(new HeaderDto());
            validaREsponse.getResponseService().setCodErr(cstmt.getInt(6));
            validaREsponse.getResponseService().setCodMsg(cstmt.getString(7));


            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;
        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            validaREsponse = new ValidaObraResultDto();
            validaREsponse.setResponseService(new HeaderDto());
            validaREsponse.getResponseService().setCodErr(1);
            validaREsponse.getResponseService().setCodMsg(e.getMessage());
            return validaREsponse;
        }
        _logger.info("Finish getConsultaAlbum");
        // 9. Return the result

        return validaREsponse;
    }

    /*-----------------------------------------------------sacm_actualiza_tag_item Service-------------------------------------------------------------------*/
    public static ValidaObraResultDto actualizaTagItem(TagsDto tagRequest) {
        
        CallableStatement cstmt = null;
        Connection conn = null;
        

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_RUG.ACTUALIZA_TAG_ITEM(?,?,?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, tagRequest.getIdTagItem());
            cstmt.setObject(2, tagRequest.getTagName());
            cstmt.setObject(3, tagRequest.getIdTagPadre());
            cstmt.setObject(4, tagRequest.getIdTag());
            cstmt.setObject(5, tagRequest.getDescripcionTag());
            cstmt.setObject(6, tagRequest.getActivo());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(7, Types.INTEGER);
            cstmt.registerOutParameter(8, Types.VARCHAR);


            // 5. Execute the statement
            cstmt.executeUpdate();
           
            // 6. Set value of dateValue property using first OUT param
            validaREsponse = new ValidaObraResultDto();
            validaREsponse.setResponseBD(new HeaderDto());
            validaREsponse.getResponseBD().setCodErr(cstmt.getInt(7));
            validaREsponse.getResponseBD().setCodMsg(cstmt.getString(8));
            validaREsponse.setResponseService(new HeaderDto());
            validaREsponse.getResponseService().setCodErr(cstmt.getInt(7));
            validaREsponse.getResponseService().setCodMsg(cstmt.getString(8));


            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;
        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            validaREsponse = new ValidaObraResultDto();
            validaREsponse.setResponseService(new HeaderDto());
            validaREsponse.getResponseService().setCodErr(1);
            validaREsponse.getResponseService().setCodMsg(e.getMessage());
            return validaREsponse;
        }
        _logger.info("Finish getConsultaAlbum");
        // 9. Return the result

        return validaREsponse;
    }

    /*-----------------------------------------------------sacm_lov_tags Service-------------------------------------------------------------------*/
    public static TagsResultDto getTags() {
        List<TagsDto> tagListResult = new ArrayList<TagsDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;        
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_RUG.LOV_TAGS(?,?,?)}");
            // 3. Set the bind values of the IN parameters
            
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(1, -10);
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(2) == 0) {
                rs = (ResultSet) cstmt.getObject(1);
               // List<Tag> tagList = new ArrayList<Tag>();               
                while (rs.next()) {
                    TagsDto tag = new TagsDto();
                   
                    //Asignamiento de valores al objeto Tag
                    tag.setIdTag(rs.getInt(1));
                    tag.setTagName(rs.getString(2));
                   
                    tagListResult.add(tag);
                }                
                
                rs.close();
            }
            // 6. Set value of dateValue property using first OUT param
            TagsResponse = new TagsResultDto();
            TagsResponse.setResponseBD(new HeaderDto());
            TagsResponse.getResponseBD().setCodErr(cstmt.getInt(2));
            TagsResponse.getResponseBD().setCodMsg(cstmt.getString(3));
            TagsResponse.setResponseService(new HeaderDto());
            TagsResponse.getResponseService().setCodErr(cstmt.getInt(2));
            TagsResponse.getResponseService().setCodMsg(cstmt.getString(3));
            TagsResponse.setTagList(tagListResult);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            TagsResponse = new TagsResultDto();
            TagsResponse.setResponseService(new HeaderDto());
            TagsResponse.getResponseService().setCodErr(1);
            TagsResponse.getResponseService().setCodMsg(e.getMessage());
            return TagsResponse;
        }
        _logger.info("Finish getEstados");
        // 9. Return the result
        return TagsResponse;
    }

    /*-----------------------------------------------------sacm_actualiza_proyecto Service-------------------------------------------------------------------*/
     public static ProyectoResultDto ActualizaProyecto(ProyectoDto proyectoRequest) {
        CallableStatement cstmt = null;       
        Connection conn = null;        
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_RUG.ACTUALIZA_PROYECTO(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, proyectoRequest.getId_proyecto());
            cstmt.setObject(2, proyectoRequest.getNombre());
            // 4. Register the positions and types of the OUT parameters            
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();
            
            
            // 6. Set value of dateValue property using first OUT param
            proyectoResponse = new ProyectoResultDto();
            
            proyectoResponse.setResponseBD(new HeaderDto());
            proyectoResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            proyectoResponse.getResponseBD().setCodMsg(cstmt.getString(4));
            
            proyectoResponse.setResponseService(new HeaderDto());
            proyectoResponse.getResponseService().setCodErr(cstmt.getInt(3));
            proyectoResponse.getResponseService().setCodMsg(cstmt.getString(4));
           
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            proyectoResponse = new ProyectoResultDto();
            proyectoResponse.setResponseService(new HeaderDto());
            proyectoResponse.getResponseService().setCodErr(1);
            proyectoResponse.getResponseService().setCodMsg(e.getMessage());
            return proyectoResponse;
        }
        _logger.info("Finish getEstados");
        // 9. Return the result
        return proyectoResponse;
    }

    /*-----------------------------------------------------sacm_elimina_obra_proyecto Service-------------------------------------------------------------------*/
    public static ProyectoResultDto EliminaObraProyecto(ProyectoDto proyectoRequest) {
        CallableStatement cstmt = null;       
        Connection conn = null;        
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_RUG.ELIMINA_PROYECTO_OBRA(?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, proyectoRequest.getId_usuario());
            cstmt.setObject(2, proyectoRequest.getId_proyecto());
            cstmt.setObject(3, proyectoRequest.getId_obra());
            // 4. Register the positions and types of the OUT parameters            
            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();
            
            
            // 6. Set value of dateValue property using first OUT param
            proyectoResponse = new ProyectoResultDto();
            
            proyectoResponse.setResponseBD(new HeaderDto());
            proyectoResponse.getResponseBD().setCodErr(cstmt.getInt(4));
            proyectoResponse.getResponseBD().setCodMsg(cstmt.getString(5));
            
            proyectoResponse.setResponseService(new HeaderDto());
            proyectoResponse.getResponseService().setCodErr(cstmt.getInt(4));
            proyectoResponse.getResponseService().setCodMsg(cstmt.getString(5));
           
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            proyectoResponse = new ProyectoResultDto();
            proyectoResponse.setResponseService(new HeaderDto());
            proyectoResponse.getResponseService().setCodErr(1);
            proyectoResponse.getResponseService().setCodMsg(e.getMessage());
            return proyectoResponse;
        }
        _logger.info("Finish getEstados");
        // 9. Return the result
        return proyectoResponse;
    }

    /*-----------------------------------------------------sacm_duplica_proyecto Service-------------------------------------------------------------------*/
    public static ProyectoResultDto DuplicaProyecto(ProyectoDto proyectoRequest) {
        CallableStatement cstmt = null;       
        Connection conn = null;        
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_RUG.DUPLICA_PROYECTO(?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, proyectoRequest.getId_proyecto());
            cstmt.setObject(2, proyectoRequest.getId_usuario());
            cstmt.setObject(3, proyectoRequest.getNombre());
            // 4. Register the positions and types of the OUT parameters            
            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();
            
            
            // 6. Set value of dateValue property using first OUT param
            proyectoResponse = new ProyectoResultDto();
            
            proyectoResponse.setResponseBD(new HeaderDto());
            proyectoResponse.getResponseBD().setCodErr(cstmt.getInt(4));
            proyectoResponse.getResponseBD().setCodMsg(cstmt.getString(5));
            
            proyectoResponse.setResponseService(new HeaderDto());
            proyectoResponse.getResponseService().setCodErr(cstmt.getInt(4));
            proyectoResponse.getResponseService().setCodMsg(cstmt.getString(5));
           
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            proyectoResponse = new ProyectoResultDto();
            proyectoResponse.setResponseService(new HeaderDto());
            proyectoResponse.getResponseService().setCodErr(1);
            proyectoResponse.getResponseService().setCodMsg(e.getMessage());
            return proyectoResponse;
        }
        _logger.info("Finish getEstados");
        // 9. Return the result
        return proyectoResponse;
    }

    /*-----------------------------------------------------sacm_compartir_proyecto Service-------------------------------------------------------------------*/
    public static ProyectoResultDto CompartorProyecto(CompObraDto proyectoRequest) {
        CallableStatement cstmt = null;       
        Connection conn = null;        
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_CONSOLA_RUG.PRC_COMPARTIR_PROYECTO(?,?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, proyectoRequest.getEmail_origen());
            cstmt.setObject(2, proyectoRequest.getEmail_destino());
            cstmt.setObject(3, proyectoRequest.getIdProyecto());
            // 4. Register the positions and types of the OUT parameters            
            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.VARCHAR);
            cstmt.registerOutParameter(6, Types.INTEGER);
            cstmt.registerOutParameter(7, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();
            
            
            // 6. Set value of dateValue property using first OUT param
            proyectoResponse = new ProyectoResultDto();
            proyectoResponse.setId_proyecto(cstmt.getInt(4));
            proyectoResponse.setNombre(cstmt.getString(5));
            proyectoResponse.setResponseBD(new HeaderDto());
            proyectoResponse.getResponseBD().setCodErr(cstmt.getInt(6));
            proyectoResponse.getResponseBD().setCodMsg(cstmt.getString(7));
            
            proyectoResponse.setResponseService(new HeaderDto());
            proyectoResponse.getResponseService().setCodErr(cstmt.getInt(6));
            proyectoResponse.getResponseService().setCodMsg(cstmt.getString(7));
           
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            proyectoResponse = new ProyectoResultDto();
            proyectoResponse.setResponseService(new HeaderDto());
            proyectoResponse.getResponseService().setCodErr(1);
            proyectoResponse.getResponseService().setCodMsg(e.getMessage());
            return proyectoResponse;
        }
        _logger.info("Finish getEstados");
        // 9. Return the result
        return proyectoResponse;
    }
}
