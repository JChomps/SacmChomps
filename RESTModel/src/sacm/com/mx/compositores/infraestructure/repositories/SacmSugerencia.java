package sacm.com.mx.compositores.infraestructure.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.TreeMap;

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.AlbumDto;
import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.MetadataDto;
import sacm.com.mx.compositores.common.dtos.NombreParticipanteDto;
import sacm.com.mx.compositores.common.dtos.ObraDto;
import sacm.com.mx.compositores.common.dtos.ObraResultDto;
import sacm.com.mx.compositores.common.dtos.PalabraDto;
import sacm.com.mx.compositores.common.dtos.ParticipanteDto;
import sacm.com.mx.compositores.common.dtos.SugerenciaDto;
import sacm.com.mx.compositores.common.dtos.SugerenciaResultDto;
import sacm.com.mx.compositores.common.dtos.Tag;
import sacm.com.mx.compositores.common.dtos.TagN1;
import sacm.com.mx.compositores.common.dtos.TagN2;
import sacm.com.mx.compositores.common.dtos.TagSugerencia;
import sacm.com.mx.compositores.common.dtos.TrackInfoDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmSugerencia {
    @SuppressWarnings("compatibility:3396088568485404005")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmEstado.class);
    private static SugerenciaResultDto sugerenciaResponse;

    public SacmSugerencia() {
        super();
    }

    /*---------------------------------------------------------------------- sacm_consulta_sugerencia ----------------------------------------------------------------------*/
    public static SugerenciaResultDto sacmgetSugerencia(PalabraDto palabra) {
        List<ObraDto> obraList = new ArrayList<ObraDto>();
        List<AlbumDto> albumList = new ArrayList<AlbumDto>();
        List<NombreParticipanteDto> participanteList = new ArrayList<NombreParticipanteDto>();
        List<TagSugerencia> tagList = new ArrayList<TagSugerencia>();

        Map<Integer, ObraDto> mapObra = new TreeMap<Integer, ObraDto>();
        Map<Integer, AlbumDto> mapAlbum = new HashMap<Integer, AlbumDto>();
        Map<Integer, NombreParticipanteDto> mapParticipante = new HashMap<Integer, NombreParticipanteDto>();
        Map<String, TagSugerencia> mapTag = new HashMap<String, TagSugerencia>();
        
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        //Ordenamos el arreglo de entrada de acuerdo a sus valores
        int valores[] = new int[7];
        for (int str : palabra.getArray_options()) {
            valores[str - 1] = 1;
        }
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_BUSCADOR.PRC_CONSULTA_SUGERENCIAS(?,?,?,?,?,?,?,?,?,?,?,?)}");
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
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(10, Types.INTEGER);
            cstmt.registerOutParameter(11, Types.VARCHAR);
            cstmt.registerOutParameter(12, -10);
            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(10) == 0) {
                // print the results
                rs = (ResultSet) cstmt.getObject(12);
                while (rs.next()) {
                    ObraDto obra = new ObraDto();
                    AlbumDto album = new AlbumDto();
                    NombreParticipanteDto participante = new NombreParticipanteDto();
                    TagSugerencia tag = new TagSugerencia();
                    //Creamos los objetos de acuerdo a su  categoría 
                    switch (rs.getString(1)) {
                    case "OBRA":
                        obra.setId_obra(rs.getInt(2));
                        obra.setObra_titulo(rs.getString(3));
                        obra.setObra_descripcion(rs.getString(4));
                        mapObra.put(obra.getId_obra() - 1, obra);
                        break;

                    case "ALBUM":
                        album.setId_album(rs.getInt(5));
                        album.setNombre_album(rs.getString(6));
                        album.setAlbum_descripcion(rs.getString(7));
                        mapAlbum.put(album.getId_album(), album);
                        break;

                    case "PARTICIPANTE":
                        participante.setId_participante(rs.getInt(8));
                        participante.setNombre(rs.getString(9));
                        participante.setSegundo_nombre(rs.getString(10));
                        participante.setApellido_paterno(rs.getString(11));
                        participante.setApellido_materno(rs.getString(12));
                        mapParticipante.put(participante.getId_participante(), participante);
                        break;

                    case "TAGS":
                        tag.setTag_path(rs.getString(19));
                        tag.setTag_path_name(rs.getString(20));
                        tag.setTag_path_id(rs.getString(21));
                        mapTag.put(tag.getTag_path_id(), tag);
                        break;
                    default:
                        break;
                    }
                }
                //Convertimos los maps a listas sin elementos repetidos 
                for (ObraDto value : mapObra.values()) {
                    obraList.add(value);
                }
                for (AlbumDto value : mapAlbum.values()) {
                    albumList.add(value);
                }
                for (NombreParticipanteDto value : mapParticipante.values()) {
                    participanteList.add(value);
                }
                for (TagSugerencia value : mapTag.values()) {
                    tagList.add(value);
                }               
                rs.close();
            }

            // 6. Set value of dateValue property using first OUT param
            sugerenciaResponse = new SugerenciaResultDto();
            sugerenciaResponse.setResponseBD(new HeaderDto());
            sugerenciaResponse.getResponseBD().setCodErr(cstmt.getInt(10));
            sugerenciaResponse.getResponseBD().setCodMsg(cstmt.getString(11));
            sugerenciaResponse.setResponseService(new HeaderDto());
            sugerenciaResponse.getResponseService().setCodErr(cstmt.getInt(10));
            sugerenciaResponse.getResponseService().setCodMsg(cstmt.getString(11));
            sugerenciaResponse.setObras(obraList);
            sugerenciaResponse.setAlbumes(albumList);
            sugerenciaResponse.setParticipantes(participanteList);
            sugerenciaResponse.setTags(tagList);
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;
        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            sugerenciaResponse = new SugerenciaResultDto();
            sugerenciaResponse.setResponseService(new HeaderDto());
            sugerenciaResponse.getResponseService().setCodErr(1);
            sugerenciaResponse.getResponseService().setCodMsg(e.getMessage());
            return sugerenciaResponse;
        }
        _logger.info("Finish getVersiones");
        // 9. Return the result
        sugerenciaResponse.setObras(obraList);
        sugerenciaResponse.setAlbumes(albumList);
        sugerenciaResponse.setParticipantes(participanteList);
        sugerenciaResponse.setTags(tagList);
        return sugerenciaResponse;
    }
}
