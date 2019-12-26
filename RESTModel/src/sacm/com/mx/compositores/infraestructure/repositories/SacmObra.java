package sacm.com.mx.compositores.infraestructure.repositories;

import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import java.util.Map;
import java.util.TreeMap;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.AlbumDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.AlbumResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.CompObraDto;
import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.PalabraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.VersionDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.VersionResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;


public class SacmObra implements Serializable {
    @SuppressWarnings("compatibility:3396088568485404005")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmEstado.class);
    private static ObraResultDto obraResponse;
    private static ObraResultDto obraRes;

    public SacmObra() {
        super();
    }
    /*------------------------------------------------------------ sacm_versiones -------------------------------------------------------------------------*/

    public static ObraResultDto getVersionesByIdObra(ObraDto obraRequest) {
        List<ObraDto> obraListResult = new ArrayList<ObraDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_BUSCADOR.PRC_CONSULTA_VERSIONES(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, obraRequest.getId_obra());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);
            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(2) == 0) {
                // read the results
                rs = (ResultSet) cstmt.getObject(4);
                List<ObraDto> obraList = new ArrayList<ObraDto>();

                byte[] bdata;
                while (rs.next()) {
                    ObraDto obra = new ObraDto();
                    VersionDto version = new VersionDto();
                    obra.setId_obra(rs.getInt(1));
                    obra.setObra_numero(rs.getInt(2));
                    obra.setObra_id_album(rs.getInt(3));
                    obra.setObra_titulo(rs.getString(4));
                    obra.setObra_descripcion(rs.getString(5));
                    //Lectura de datos para objeto Version
                    version.setVersion_id(rs.getInt(6));
                    version.setVersion_titulo(rs.getString(7));
                    version.setVersion_descripcion(rs.getString(8));
                    version.setVersion_duracion(rs.getString(9));
                    //Conversión de BLOB a Base 64 en imágenes y audio
                    bdata =
                        (rs.getObject(10) == null ? null : rs.getBlob(10).getBytes(1, (int) rs.getBlob(10).length()));
                    version.setVersion_wav(rs.getObject(10) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    bdata =
                        (rs.getObject(11) == null ? null : rs.getBlob(11).getBytes(1, (int) rs.getBlob(11).length()));
                    version.setVersion_mp3(rs.getObject(11) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    bdata =
                        (rs.getObject(12) == null ? null : rs.getBlob(12).getBytes(1, (int) rs.getBlob(12).length()));
                    version.setVersion_aiff(rs.getObject(12) == null ? null :
                                            Base64.getEncoder().encodeToString(bdata));
                    version.setVersion_lyric(rs.getObject(13) == null ? null : rs.getString(13));
                    version.setVersion_type(rs.getString(14));
                    //Se agrega version al objeto Obra
                    obra.setVersiones(new ArrayList<VersionDto>());
                    obra.getVersiones().add(version);
                    obraList.add(obra);
                }
                //Organizamos las versiones correspondientes a cada obra
                OrdenamientoObra(obraListResult, obraList);
                rs.close();
            }
            // 6. Set value of dateValue property using first OUT param
            obraRes = new ObraResultDto();
            obraRes.setResponseBD(new HeaderDto());
            obraRes.getResponseBD().setCodErr(cstmt.getInt(2));
            obraRes.getResponseBD().setCodMsg(cstmt.getString(3));
            obraRes.setResponseService(new HeaderDto());
            obraRes.getResponseService().setCodErr(cstmt.getInt(2));
            obraRes.getResponseService().setCodMsg(cstmt.getString(3));
            obraRes.setObras(obraListResult);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            obraRes = new ObraResultDto();
            obraRes.setResponseService(new HeaderDto());
            obraRes.getResponseService().setCodErr(1);
            obraRes.getResponseService().setCodMsg(e.getMessage());
            return obraRes;
        }
        _logger.info("Finish getVersiones");
        // 9. Return the result
        return obraRes;
    }

    /*----------------------------------------------------------------- sacm_consulta_obra -------------------------------------------------------------------------*/

    public static ObraResultDto sacmConsultaObra(PalabraDto palabra) {
        List<ObraDto> obraList = new ArrayList<ObraDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        int valores[] = new int[7];
        for (int str : palabra.getArray_options()) {
            valores[str - 1] = 1;
        }
        String cadena =  palabra.getSearch().toString();
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_BUSCADOR.PRC_CONSULTA_OBRA_PALABRA(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, palabra.getPalabra());
            cstmt.setObject(2, palabra.getId_album());
            cstmt.setObject(3, valores[0]);
            cstmt.setObject(4, valores[1]);
            cstmt.setObject(5, valores[2]);
            cstmt.setObject(6, valores[3]);
            cstmt.setObject(7, valores[4]);
            cstmt.setObject(8, valores[5]);
            cstmt.setObject(9, valores[6]);
            cstmt.setObject(10, cadena.substring(1,cadena.length()-1));
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(11, Types.INTEGER);
            cstmt.registerOutParameter(12, Types.VARCHAR);
            cstmt.registerOutParameter(13, -10);
            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(11) == 0) {
                // print the results
                rs = (ResultSet) cstmt.getObject(13);
                byte[] bdata;
                while (rs.next()) {
                    ObraDto obra = new ObraDto();
                    obra.setId_obra(rs.getInt(1));
                    obra.setObra_numero(rs.getInt(2));
                    obra.setObra_id_album(rs.getInt(3));
                    //Conversión de BLOB a Base64
                    bdata = (rs.getObject(4) == null ? null : rs.getBlob(4).getBytes(1, (int) rs.getBlob(4).length()));
                    obra.set_Imagen(rs.getObject(4) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    obra.setObra_titulo(rs.getString(5));
                    obra.setObra_descripcion(rs.getString(6));
                    obra.setVersion_duracion(rs.getString(7));
                    bdata = (rs.getObject(8) == null ? null : rs.getBlob(8).getBytes(1, (int) rs.getBlob(8).length()));
                    obra.setVersion_wav(rs.getObject(8) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    bdata = (rs.getObject(9) == null ? null : rs.getBlob(9).getBytes(1, (int) rs.getBlob(9).length()));
                    obra.setVersion_mp3(rs.getObject(9) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    bdata =
                        (rs.getObject(10) == null ? null : rs.getBlob(10).getBytes(1, (int) rs.getBlob(10).length()));
                    obra.setVersion_aiff(rs.getObject(10) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    obra.setVersion_lyric(rs.getObject(11) == null ? null : rs.getString(11));
                    obraList.add(obra);
                }
                rs.close();
            }

            // 6. Set value of dateValue property using first OUT param
            obraResponse = new ObraResultDto();
            obraResponse.setResponseBD(new HeaderDto());
            obraResponse.getResponseBD().setCodErr(cstmt.getInt(11));
            obraResponse.getResponseBD().setCodMsg(cstmt.getString(12));
            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(cstmt.getInt(11));
            obraResponse.getResponseService().setCodMsg(cstmt.getString(12));
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

    /*-------------------------------------------------------------------------- sacm_consulta_album -----------------------------------------------------------------------*/
    public static AlbumResultDto sacmConsultaObraByAlbum(PalabraDto palabra) {
        AlbumResultDto AlbumRes = new AlbumResultDto();
        List<AlbumDto> obraList = new ArrayList<AlbumDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        //Ordenamiento de valores del arreglo de entrada
        int valores[] = new int[7];
        for (int str : palabra.getArray_options()) {
            valores[str - 1] = 1;
        }
        
        String search =palabra.getSearch().toString() ;
        search= search.substring(0,search.length()-1);
        search= search.substring(1);
        
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_BUSCADOR.PRC_CONSULTA_ALBUM(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, palabra.getPalabra());
            cstmt.setObject(2, palabra.getId_album());
            cstmt.setObject(3, valores[0]);
            cstmt.setObject(4, valores[1]);
            cstmt.setObject(5, valores[2]);
            cstmt.setObject(6, valores[3]);
            cstmt.setObject(7, valores[4]);
            cstmt.setObject(8, valores[5]);
            cstmt.setObject(9, valores[6]);
            cstmt.setObject(10, search);
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(11, Types.INTEGER);
            cstmt.registerOutParameter(12, Types.VARCHAR);
            cstmt.registerOutParameter(13, -10);
            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(11) == 0) {
                // print the results
                rs = (ResultSet) cstmt.getObject(13);
                byte[] bdata;
                while (rs.next()) {
                    AlbumDto obra = new AlbumDto();
                    obra.setId_album(rs.getInt(1));
                    obra.setNombre_album(rs.getString(2));
                    obra.setAlbum_descripcion(rs.getString(3));
                    bdata = (rs.getObject(4) == null ? null : rs.getBlob(4).getBytes(1, (int) rs.getBlob(4).length()));
                    obra.setImagen_album(rs.getObject(4) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    obraList.add(obra);
                }
                rs.close();
            }
            // 6. Set value of dateValue property using first OUT param
            AlbumRes = new AlbumResultDto();
            AlbumRes.setResponseBD(new HeaderDto());
            AlbumRes.getResponseBD().setCodErr(cstmt.getInt(11));
            AlbumRes.getResponseBD().setCodMsg(cstmt.getString(12));
            AlbumRes.setResponseService(new HeaderDto());
            AlbumRes.getResponseService().setCodErr(cstmt.getInt(11));
            AlbumRes.getResponseService().setCodMsg(cstmt.getString(12));
            AlbumRes.setAlbumes(obraList);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;
        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            AlbumRes = new AlbumResultDto();
            AlbumRes.setResponseService(new HeaderDto());
            AlbumRes.getResponseService().setCodErr(1);
            AlbumRes.getResponseService().setCodMsg(e.getMessage());
            return AlbumRes;
        }
        _logger.info("Finish getConsultaAlbum");
        // 9. Return the result
        AlbumRes.setAlbumes(obraList);
        return AlbumRes;
    }
    
    /*-----------------------------------------------------------sacm_audio_obra ------------------------------------------------------------------------*/
    public static ObraResultDto ConsultaObraByAudio(ObraDto obraRequest) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_BUSCADOR.PRC_CONSULTA_AUDIO(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, obraRequest.getId_obra());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);
            // 5. Execute the statement
            cstmt.executeUpdate();
            // Read the results
            rs = (ResultSet) cstmt.getObject(4);
            List<ObraDto> obraList = new ArrayList<ObraDto>();
            byte[] bdata;
            if (rs != null) {
                while (rs.next()) {
                    ObraDto obra = new ObraDto();
                    obra.setId_obra(rs.getInt(1));
                    obra.setObra_numero(rs.getInt(2));
                    obra.setObra_id_album(rs.getInt(3));
                    //Conversión de datos BLOB a BASE64
                    bdata = (rs.getObject(4) == null ? null : rs.getBlob(4).getBytes(1, (int) rs.getBlob(4).length()));
                    obra.set_Imagen(rs.getObject(4) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    obra.setObra_titulo(rs.getString(5));
                    obra.setObra_descripcion(rs.getString(6));
                    obra.setVersion_duracion(rs.getString(7));
                    bdata = (rs.getObject(8) == null ? null : rs.getBlob(8).getBytes(1, (int) rs.getBlob(8).length()));
                    obra.setVersion_wav(rs.getObject(8) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    bdata = (rs.getObject(9) == null ? null : rs.getBlob(9).getBytes(1, (int) rs.getBlob(9).length()));
                    obra.setVersion_mp3(rs.getObject(9) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    bdata =
                        (rs.getObject(10) == null ? null : rs.getBlob(10).getBytes(1, (int) rs.getBlob(10).length()));
                    obra.setVersion_aiff(rs.getObject(10) == null ? null : Base64.getEncoder().encodeToString(bdata));


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
        _logger.info("Finish getAudioObra");
        // 9. Return the result
        return obraResponse;
    }

    /*---------------------------------------------------------- sacm_compartir_obra -------------------------------------------------------------------------*/
    public static ObraResultDto compartirObra(CompObraDto obraRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        ObraDto obra = new ObraDto();
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_BUSCADOR.PRC_COMPARTIR_OBRA(?,?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, obraRequest.getIdUserOrigen());
            cstmt.setObject(2, obraRequest.getIdUserDestino());
            cstmt.setObject(3, obraRequest.getIdObra());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.VARCHAR);
            cstmt.registerOutParameter(6, Types.INTEGER);
            cstmt.registerOutParameter(7, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();
            List<ObraDto> obraList = new ArrayList<ObraDto>();
            if (cstmt.getInt(6) == 0) {
             //   obra.setId_obra(cstmt.getInt(4));
               // obra.setObra_titulo(cstmt.getString(5));
                obraList.add(obra);
            }
            obraResponse = new ObraResultDto();
            obraResponse.setResponseBD(new HeaderDto());
            obraResponse.getResponseBD().setCodErr(cstmt.getInt(6));
            obraResponse.getResponseBD().setCodMsg(cstmt.getString(7));
            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(cstmt.getInt(6));
            obraResponse.getResponseService().setCodMsg(cstmt.getString(7));
            obraResponse.setObras(obraList);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            _logger.severe(e.getMessage());
            obraResponse = new ObraResultDto();
            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(1);
            obraResponse.getResponseService().setCodMsg(e.getMessage());
            return obraResponse;
        }
        _logger.info("Finish CompartirObra");
        // 9. Return the result
        return obraResponse;
    }

    /*----------------------------------------------------------------- sacm_consulta_obra_album -------------------------------------------------------------------*/
    public static ObraResultDto sacmConsultaObraByAlbum(ObraDto obraRequest) {
        List<ObraDto> obraList = new ArrayList<ObraDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_BUSCADOR.PRC_CONSULTA_OBRAS_ALBUM(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, obraRequest.getId_album());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);
            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(2) == 0) {
                // print the results
                rs = (ResultSet) cstmt.getObject(4);
                byte[] bdata;
                while (rs.next()) {
                    ObraDto obra = new ObraDto();
                    obra.setId_obra(rs.getInt(1));
                    obra.setObra_numero(rs.getInt(2));
                    obra.setObra_id_album(rs.getInt(3));
                    //Converción de datos BLOB a Base64
                    bdata = (rs.getObject(4) == null ? null : rs.getBlob(4).getBytes(1, (int) rs.getBlob(4).length()));
                    obra.set_Imagen(rs.getObject(4) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    obra.setObra_titulo(rs.getString(5));
                    obra.setObra_descripcion(rs.getString(6));
                    obra.setVersion_duracion(rs.getString(7));
                    bdata = (rs.getObject(8) == null ? null : rs.getBlob(8).getBytes(1, (int) rs.getBlob(8).length()));
                    obra.setVersion_wav(rs.getObject(8) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    bdata = (rs.getObject(9) == null ? null : rs.getBlob(9).getBytes(1, (int) rs.getBlob(9).length()));
                    obra.setVersion_mp3(rs.getObject(9) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    bdata =
                        (rs.getObject(10) == null ? null : rs.getBlob(10).getBytes(1, (int) rs.getBlob(10).length()));
                    obra.setVersion_aiff(rs.getObject(10) == null ? null : Base64.getEncoder().encodeToString(bdata));
                    obra.setVersion_lyric(rs.getObject(11) == null ? null : rs.getString(11));

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
        _logger.info("Finish getConsultaObraAlbum");
        // 9. Return the result
        obraResponse.setObras(obraList);
        return obraResponse;
    }
    

    private static void OrdenamientoObra(List<ObraDto> obraListResult, List<ObraDto> obraList) {
        Map<Integer, ObraDto> map = new TreeMap<Integer, ObraDto>();
        //Conversión a Map para borrar obras duplicadas
        for (ObraDto str : obraList) {
            map.put(str.getId_obra(), str);
        }
        
        for (ObraDto value : map.values()) {
            obraListResult.add(value);
        }
        //Ordenamiento de versiones dentro de Obras correspondientes
        for (ObraDto strTIR : obraListResult) {
            for (ObraDto strTI : obraList) {
                if (strTI.getId_obra() == strTIR.getId_obra()) {
                    VersionDto ver = new VersionDto();
                    ver.setVersion_id(strTI.getVersiones()
                                           .get(0)
                                           .getVersion_id());
                    ver.setVersion_titulo(strTI.getVersiones()
                                               .get(0)
                                               .getVersion_titulo());
                    ver.setVersion_descripcion(strTI.getVersiones()
                                                    .get(0)
                                                    .getVersion_descripcion());
                    ver.setVersion_duracion(strTI.getVersiones()
                                                 .get(0)
                                                 .getVersion_duracion());
                    ver.setVersion_wav(strTI.getVersiones()
                                            .get(0)
                                            .getVersion_wav());
                    ver.setVersion_mp3(strTI.getVersiones()
                                            .get(0)
                                            .getVersion_mp3());
                    ver.setVersion_aiff(strTI.getVersiones()
                                             .get(0)
                                             .getVersion_aiff());
                    ver.setVersion_lyric(strTI.getVersiones()
                                              .get(0)
                                              .getVersion_lyric());
                    ver.setVersion_type(strTI.getVersiones()
                                             .get(0)
                                             .getVersion_type());
                    strTIR.getVersiones().add(ver);
                }
            }
            strTIR.getVersiones().remove(0);
        }
    }
}

