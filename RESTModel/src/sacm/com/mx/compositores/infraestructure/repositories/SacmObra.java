package sacm.com.mx.compositores.infraestructure.repositories;

import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.TreeMap;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.CompObraDto;
import sacm.com.mx.compositores.common.dtos.CompObraResultDto;
import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.ObraDto;
import sacm.com.mx.compositores.common.dtos.ObraResultDto;
import sacm.com.mx.compositores.common.dtos.PalabraDto;
import sacm.com.mx.compositores.common.dtos.PalabraIdObra;
import sacm.com.mx.compositores.common.dtos.ParticipanteDto;
import sacm.com.mx.compositores.common.dtos.TrackInfoDto;
import sacm.com.mx.compositores.common.dtos.VersionDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;


public class SacmObra implements Serializable {
    @SuppressWarnings("compatibility:3396088568485404005")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmEstado.class);
    private static ObraResultDto obraResponse;

    public SacmObra() {
        super();
    }

    public static ObraResultDto getVersionesByIdObra(ObraDto obraRequest) {
        List<ObraDto> obraListResult = new ArrayList<ObraDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PRC_CONSULTA_VERSIONES(?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, obraRequest.getId_obra());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);

            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(2) == 0) {
                // print the results
                rs = (ResultSet) cstmt.getObject(4);
                List<ObraDto> obraList = new ArrayList<ObraDto>();
                Map<Integer, ObraDto> map = new TreeMap<Integer, ObraDto>();
                byte[] bdata;
                while (rs.next()) {
                    ObraDto obra = new ObraDto();
                    VersionDto version = new VersionDto();
                    obra.setId_obra(rs.getInt(1));
                    obra.setObra_numero(rs.getInt(2));
                    obra.setObra_id_album(rs.getInt(3));
                    obra.setObra_titulo(rs.getString(4));
                    obra.setObra_descripcion(rs.getString(5));
                    version.setVersion_id(rs.getInt(6));
                    version.setVersion_titulo(rs.getString(7));
                    version.setVersion_descripcion(rs.getString(8));
                    version.setVersion_duracion(rs.getString(9));
                    bdata =
                        (rs.getObject(10) == null ? null : rs.getBlob(10).getBytes(1, (int) rs.getBlob(10).length()));
                    version.setVersion_wav(rs.getObject(10) == null ? null : new String(bdata));
                    bdata =
                        (rs.getObject(11) == null ? null : rs.getBlob(11).getBytes(1, (int) rs.getBlob(11).length()));
                    version.setVersion_mp3(rs.getObject(11) == null ? null : new String(bdata));
                    bdata =
                        (rs.getObject(12) == null ? null : rs.getBlob(12).getBytes(1, (int) rs.getBlob(12).length()));
                    version.setVersion_aiff(rs.getObject(12) == null ? null : new String(bdata));
                    version.setVersion_lyric(rs.getObject(13) == null ? null : rs.getString(13));
                    version.setVersion_type(rs.getString(14));

                    obra.getVersiones().add(version);

                    obraList.add(obra);
                }

                for (ObraDto str : obraList) {
                    map.put(str.getId_obra(), str);
                }

                for (ObraDto value : map.values()) {
                    obraListResult.add(value);
                }

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
            obraResponse.setObras(obraListResult);

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
        _logger.info("Finish getVersiones");
        // 9. Return the result
        return obraResponse;
    }

    public static ObraResultDto sacmConsultaObra(PalabraDto palabra) {
        List<ObraDto> obraList = new ArrayList<ObraDto>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;


        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PRC_CONSULTA_OBRA_PALABRA(?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, palabra.getPalabra());

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
                    bdata = (rs.getObject(4) == null ? null : rs.getBlob(4).getBytes(1, (int) rs.getBlob(4).length()));
                    obra.set_Imagen(rs.getObject(4) == null ? null : new String(bdata));
                    obra.setObra_titulo(rs.getString(5));
                    obra.setObra_descripcion(rs.getString(6));
                    obra.setVersion_duracion(rs.getString(7));
                    bdata = (rs.getObject(8) == null ? null : rs.getBlob(8).getBytes(1, (int) rs.getBlob(8).length()));
                    obra.setVersion_wav(rs.getObject(8) == null ? null : new String(bdata));
                    bdata = (rs.getObject(9) == null ? null : rs.getBlob(9).getBytes(1, (int) rs.getBlob(9).length()));
                    obra.setVersion_mp3(rs.getObject(9) == null ? null : new String(bdata));
                    bdata =
                        (rs.getObject(10) == null ? null : rs.getBlob(10).getBytes(1, (int) rs.getBlob(10).length()));
                    obra.setVersion_aiff(rs.getObject(10) == null ? null : new String(bdata));
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
        _logger.info("Finish getVersiones");
        // 9. Return the result
        return obraResponse;
    }

    public static ObraResultDto ConsultaObraByAudio(ObraDto obraRequest) {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PRC_CONSULTA_AUDIO(?,?,?,?)}");

            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, obraRequest.getId_obra());

            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, -10);

            // 5. Execute the statement
            cstmt.executeUpdate();

            // print the results
            rs = (ResultSet) cstmt.getObject(4);
            List<ObraDto> obraList = new ArrayList<ObraDto>();
            byte[] bdata;
            if (rs != null) {
                while (rs.next()) {
                    ObraDto obra = new ObraDto();
                    obra.setId_obra(rs.getInt(1));
                    obra.setObra_numero(rs.getInt(2));
                    obra.setObra_id_album(rs.getInt(3));
                    bdata = (rs.getObject(4) == null ? null : rs.getBlob(4).getBytes(1, (int) rs.getBlob(4).length()));
                    obra.set_Imagen(rs.getObject(4) == null ? null : new String(bdata));
                    obra.setObra_titulo(rs.getString(5));
                    obra.setObra_descripcion(rs.getString(6));
                    obra.setVersion_duracion(rs.getString(7));
                    bdata = (rs.getObject(8) == null ? null : rs.getBlob(8).getBytes(1, (int) rs.getBlob(8).length()));
                    obra.setVersion_wav(rs.getObject(8) == null ? null : new String(bdata));
                    bdata = (rs.getObject(9) == null ? null : rs.getBlob(9).getBytes(1, (int) rs.getBlob(9).length()));
                    obra.setVersion_mp3(rs.getObject(9) == null ? null : new String(bdata));
                    bdata =
                        (rs.getObject(10) == null ? null : rs.getBlob(10).getBytes(1, (int) rs.getBlob(10).length()));
                    obra.setVersion_aiff(rs.getObject(10) == null ? null : new String(bdata));

                    // obra.setVersion_lyric(rs.getObject(11) == null ? null : rs.getString(11));

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
        _logger.info("Finish getVersiones");
        // 9. Return the result
        return obraResponse;
    }

    public static ObraResultDto compartirObra(CompObraDto obraRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        ObraDto obra = new ObraDto();
        ObraResultDto obraResponse = new ObraResultDto();
        try {
            conn = AppModule.getDbConexionJDBC();

            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PRC_COMPARTIR_OBRA(?,?,?,?,?,?,?)}");

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
                obra.setId_obra(cstmt.getInt(4));
                obra.setObra_titulo(cstmt.getString(5));
                obraList.add(obra);
            }

            obraResponse.setObras(obraList);

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
        _logger.info("Finish getVersiones");
        // 9. Return the result
        return obraResponse;
    }

}

