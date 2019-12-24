package sacm.com.mx.compositores.infraestructure.repositories;

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

import oracle.adf.share.logging.ADFLogger;

import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.Tag;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TagN1;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos.ProyectoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos.ProyectoResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos.SubProyectoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos.SubProyectoResultDto;
import sacm.com.mx.compositores.infraestructure.utils.AppModule;

public class SacmProyecto {
    @SuppressWarnings("compatibility:-4232378700098733855")
    private static final long serialVersionUID = 1L;


    private static ADFLogger _logger = ADFLogger.createADFLogger(SacmActivacion.class);
    private static ProyectoResultDto proyectoResponse;
    private static ProyectoResultDto subProyectoResponse;
    private static ObraResultDto obraResponse;

    public SacmProyecto() {
        super();
    }

    /*---------------------------------------------------------sacm_elimina_proyecto Service----------------------------------------------------------------------*/

    public static ProyectoResultDto getEliminaProyecto(ProyectoDto proyectoRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PROYECTOS.PRC_ELIMINA_PROYECTO_PADRE(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, proyectoRequest.getId_usuario());
            cstmt.setObject(2, proyectoRequest.getId_proyecto());
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

        _logger.info("Finish Elimina Proyecto Padre");
        // 9. Return the result
        return proyectoResponse;
    }

    /*---------------------------------------------------------sacm_elimina_proyecto_hijo Service----------------------------------------------------------------------*/
    public static ProyectoResultDto getEliminaSubProyecto(ProyectoDto proyectoRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PROYECTOS.PRC_ELIMINA_PROYECTO_HIJO(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, proyectoRequest.getId_usuario());
            cstmt.setObject(2, proyectoRequest.getId_proyecto());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();


            // 6. Set value of dateValue property using first OUT param
            subProyectoResponse = new ProyectoResultDto();
            subProyectoResponse.setResponseBD(new HeaderDto());
            subProyectoResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            subProyectoResponse.getResponseBD().setCodMsg(cstmt.getString(4));

            subProyectoResponse.setResponseService(new HeaderDto());
            subProyectoResponse.getResponseService().setCodErr(cstmt.getInt(3));
            subProyectoResponse.getResponseService().setCodMsg(cstmt.getString(4));
            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());
            subProyectoResponse = new ProyectoResultDto();
            subProyectoResponse.setResponseService(new HeaderDto());
            subProyectoResponse.getResponseService().setCodErr(1);
            subProyectoResponse.getResponseService().setCodMsg(e.getMessage());
            return subProyectoResponse;
        }

        _logger.info("Finish Elimina Proyecto Padre");
        // 9. Return the result
        return subProyectoResponse;
    }

    /*---------------------------------------------------------sacm_crear_proyecto Service----------------------------------------------------------------------*/
    public static ProyectoResultDto getCreaProyecto(ProyectoDto projectRequest) {
        ProyectoResultDto proyecto = new ProyectoResultDto();
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PROYECTOS.PRC_CREA_PROYECTO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, projectRequest.getNombre());
            cstmt.setObject(2, projectRequest.getId_usuario());
            cstmt.setObject(3, projectRequest.getDescripcion());
            cstmt.setObject(4, projectRequest.getCliente());
            cstmt.setObject(5, projectRequest.getAtributo_01());
            cstmt.setObject(6, projectRequest.getAtributo_02());
            cstmt.setObject(7, projectRequest.getAtributo_03());
            cstmt.setObject(8, projectRequest.getAtributo_04());
            cstmt.setObject(9, projectRequest.getAtributo_05());
            cstmt.setObject(10, projectRequest.getAtributo_06());
            cstmt.setObject(11, projectRequest.getAtributo_07());
            cstmt.setObject(12, projectRequest.getAtributo_08());
            cstmt.setObject(13, projectRequest.getAtributo_09());
            cstmt.setObject(14, projectRequest.getAtributo_10());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(15, Types.INTEGER);
            cstmt.registerOutParameter(16, Types.INTEGER);
            cstmt.registerOutParameter(17, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();

            if (cstmt.getInt(16) == 0) {

                proyecto.setId_proyecto(cstmt.getInt(15));
            }


            // 6. Set value of dateValue property using first OUT param

            proyecto.setResponseBD(new HeaderDto());
            proyecto.getResponseBD().setCodErr(cstmt.getInt(16));
            proyecto.getResponseBD().setCodMsg(cstmt.getString(17));

            proyecto.setResponseService(new HeaderDto());
            proyecto.getResponseService().setCodErr(cstmt.getInt(16));
            proyecto.getResponseService().setCodMsg(cstmt.getString(17));

            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());

            proyecto.setResponseService(new HeaderDto());
            proyecto.getResponseService().setCodErr(1);
            proyecto.getResponseService().setCodMsg(e.getMessage());
            return proyecto;
        }

        _logger.info("Finish Elimina Proyecto Padre");
        // 9. Return the result
        return proyecto;
    }

    /*---------------------------------------------------------sacm_crear_subproyecto Service----------------------------------------------------------------------*/
    public static ProyectoResultDto getCreaSubProyecto(ProyectoDto projectRequest) {
        ProyectoResultDto proyecto = new ProyectoResultDto();
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt =
                conn.prepareCall("{call SACM_PKG_PROYECTOS.PRC_CREA_SUBPROYECTO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, projectRequest.getId_proyecto());
            cstmt.setObject(2, projectRequest.getNombre());
            cstmt.setObject(3, projectRequest.getId_usuario());
            cstmt.setObject(4, projectRequest.getDescripcion());
            cstmt.setObject(5, projectRequest.getCliente());
            cstmt.setObject(6, projectRequest.getAtributo_01());
            cstmt.setObject(7, projectRequest.getAtributo_02());
            cstmt.setObject(8, projectRequest.getAtributo_03());
            cstmt.setObject(9, projectRequest.getAtributo_04());
            cstmt.setObject(10, projectRequest.getAtributo_05());
            cstmt.setObject(11, projectRequest.getAtributo_06());
            cstmt.setObject(12, projectRequest.getAtributo_07());
            cstmt.setObject(13, projectRequest.getAtributo_08());
            cstmt.setObject(14, projectRequest.getAtributo_09());
            cstmt.setObject(15, projectRequest.getAtributo_10());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(16, Types.INTEGER);
            cstmt.registerOutParameter(17, Types.INTEGER);
            cstmt.registerOutParameter(18, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();

            if (cstmt.getInt(17) == 0) {

                proyecto.setId_proyecto(cstmt.getInt(16));
            }


            // 6. Set value of dateValue property using first OUT param

            proyecto.setResponseBD(new HeaderDto());
            proyecto.getResponseBD().setCodErr(cstmt.getInt(17));
            proyecto.getResponseBD().setCodMsg(cstmt.getString(18));

            proyecto.setResponseService(new HeaderDto());
            proyecto.getResponseService().setCodErr(cstmt.getInt(17));
            proyecto.getResponseService().setCodMsg(cstmt.getString(18));

            // 9. Close the JDBC CallableStatement
            cstmt.close();
            conn.close();
            conn = null;

        } catch (Exception e) {
            // a failure occurred log message;
            _logger.severe(e.getMessage());

            proyecto.setResponseService(new HeaderDto());
            proyecto.getResponseService().setCodErr(1);
            proyecto.getResponseService().setCodMsg(e.getMessage());
            return proyecto;
        }

        _logger.info("Finish Elimina Proyecto Padre");
        // 9. Return the result
        return proyecto;
    }

    /*---------------------------------------------------------sacm_consulta_proyecto_subp Service----------------------------------------------------------------------*/
    public static ProyectoResultDto getConsultaProyecto(ProyectoDto projectRequest) {
        List<ProyectoDto> ProjectListResult = new ArrayList<ProyectoDto>();
        List<ProyectoDto> SubProjectListResult = new ArrayList<ProyectoDto>();
        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PROYECTOS.PRC_CONSULTA_PROYECTO_SUBP(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, projectRequest.getId_usuario());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, -10);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();

            if (cstmt.getInt(3) == 0) {
                // read the results
                rs = (ResultSet) cstmt.getObject(2);
                List<ProyectoDto> obraList = new ArrayList<ProyectoDto>();
                while (rs.next()) {
                    ProyectoDto proyecto = new ProyectoDto();
                    ProyectoDto subProyecto = new ProyectoDto();
                    //Verificar el parametro "nivel" del cursor para saber si es un proyecto o un subproyecto
                    if (rs.getInt(4) == 1) {
                        proyecto.setId_proyecto(rs.getInt(1));
                        proyecto.setNombre(rs.getString(3));
                        ProjectListResult.add(proyecto);
                    } else if (rs.getInt(4) == 2) {
                        subProyecto.setId_proyecto(rs.getInt(1));
                        subProyecto.setId_subproyecto(rs.getInt(2));
                        subProyecto.setNombre(rs.getString(3));
                        SubProjectListResult.add(subProyecto);
                    }
                }
                //Organizamos proyectos y subproyectos
                OrdenaProyectod(ProjectListResult, SubProjectListResult);
                rs.close();
            }


            // 6. Set value of dateValue property using first OUT param
            proyectoResponse = new ProyectoResultDto();
            proyectoResponse.setResponseBD(new HeaderDto());
            proyectoResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            proyectoResponse.getResponseBD().setCodMsg(cstmt.getString(4));

            proyectoResponse.setResponseService(new HeaderDto());
            proyectoResponse.getResponseService().setCodErr(cstmt.getInt(3));
            proyectoResponse.getResponseService().setCodMsg(cstmt.getString(4));
            proyectoResponse.setProjectList(ProjectListResult);

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

        _logger.info("Finish Elimina Proyecto Padre");
        // 9. Return the result
        return proyectoResponse;
    }

    /*---------------------------------------------------------sacm_mueve_proyecto_subp Service----------------------------------------------------------------------*/

    public static ProyectoResultDto MueveProyecto(ProyectoDto projectRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PROYECTOS.PRC_MUEVE_PROYECTO(?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, projectRequest.getId_usuario());
            cstmt.setObject(2, projectRequest.getId_proyecto());
            cstmt.setObject(3, projectRequest.getTipo());
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

        _logger.info("Finish Elimina Proyecto Padre");
        // 9. Return the result
        return proyectoResponse;
    }

    /*---------------------------------------------------------sacm_agrega_obra_proyecto  Service----------------------------------------------------------------------*/

    public static ProyectoResultDto AgregaObra(ProyectoDto projectRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PROYECTOS.PRC_AGREGA_OBRA_PROYECTO(?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, projectRequest.getId_usuario());
            cstmt.setObject(2, projectRequest.getId_proyecto());
            cstmt.setObject(3, projectRequest.getId_obra());
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

        _logger.info("Finish Elimina Proyecto Padre");
        // 9. Return the result
        return proyectoResponse;
    }

    /*---------------------------------------------------------sacm_consulta_inbox Service----------------------------------------------------------------------*/
    public static ObraResultDto ConsultaInbox(ProyectoDto projectRequest) {
        List<ObraDto> inboxListResult = new ArrayList<ObraDto>();
        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PROYECTOS.PRC_CONSULTA_INBOX(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, projectRequest.getId_usuario());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, -10);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();

            if (cstmt.getInt(3) == 0) {
                // read the results
                rs = (ResultSet) cstmt.getObject(2);
                while (rs.next()) {
                    ObraDto obra = new ObraDto();
                    obra.setObra_descripcion(rs.getString(1));
                    obra.setId_obra(rs.getInt(2));
                    obra.setObra_titulo(rs.getString(3));
                    inboxListResult.add(obra);
                }

                rs.close();
            }


            // 6. Set value of dateValue property using first OUT param
            obraResponse = new ObraResultDto();
            obraResponse.setResponseBD(new HeaderDto());
            obraResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            obraResponse.getResponseBD().setCodMsg(cstmt.getString(4));

            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(cstmt.getInt(3));
            obraResponse.getResponseService().setCodMsg(cstmt.getString(4));
            if (inboxListResult.size() > 0) {
                obraResponse.setInboxList(inboxListResult);
            }

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

        _logger.info("Finish Consulta Inbox");
        // 9. Return the result
        return obraResponse;
    }

    /*---------------------------------------------------------sacm_consulta_shared Service----------------------------------------------------------------------*/
    public static ObraResultDto ConsultaShared(ProyectoDto projectRequest) {
        List<ObraDto> inboxListResult = new ArrayList<ObraDto>();
        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PROYECTOS.PRC_CONSULTA_SHARED(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, projectRequest.getId_usuario());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, -10);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();

            if (cstmt.getInt(3) == 0) {
                // read the results
                rs = (ResultSet) cstmt.getObject(2);
                while (rs.next()) {
                    ObraDto obra = new ObraDto();
                    obra.setObra_descripcion(rs.getString(1));
                    obra.setId_obra(rs.getInt(2));
                    obra.setObra_titulo(rs.getString(3));
                    inboxListResult.add(obra);
                }

                rs.close();
            }


            // 6. Set value of dateValue property using first OUT param
            obraResponse = new ObraResultDto();
            obraResponse.setResponseBD(new HeaderDto());
            obraResponse.getResponseBD().setCodErr(cstmt.getInt(3));
            obraResponse.getResponseBD().setCodMsg(cstmt.getString(4));

            obraResponse.setResponseService(new HeaderDto());
            obraResponse.getResponseService().setCodErr(cstmt.getInt(3));
            obraResponse.getResponseService().setCodMsg(cstmt.getString(4));
            if (inboxListResult.size() > 0) {
                obraResponse.setSharedList(inboxListResult);
            }

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

        _logger.info("Finish Consulta Inbox");
        // 9. Return the result
        return obraResponse;
    }

    /*---------------------------------------------------------sacm_consulta_proyecto Service----------------------------------------------------------------------*/
    public static ProyectoResultDto ConsultaProyecto(ProyectoDto projectRequest) {
        List<ProyectoDto> proyectoListResult = new ArrayList<ProyectoDto>();
        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PROYECTOS.PRC_CONSULTA_PROYECTO(?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, projectRequest.getId_usuario());
            cstmt.setObject(2, projectRequest.getTipo());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(3, -10);
            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.registerOutParameter(5, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();

            if (cstmt.getInt(4) == 0) {
                // read the results
                rs = (ResultSet) cstmt.getObject(3);
                while (rs.next()) {
                    ProyectoDto proyecto = new ProyectoDto();
                    proyecto.setId_proyecto(rs.getInt(1));
                    proyecto.setNombre(rs.getString(2));
                    if (rs.getInt(3) != 0) {
                        proyecto.setId_subproyecto(rs.getInt(3));
                    }
                    proyecto.setId_obra(rs.getInt(4));
                    proyecto.setObra_numero(rs.getInt(5));
                    proyecto.setObra_titulo(rs.getString(6));
                    proyectoListResult.add(proyecto);
                }

                rs.close();
            }


            // 6. Set value of dateValue property using first OUT param
            proyectoResponse = new ProyectoResultDto();
            proyectoResponse.setResponseBD(new HeaderDto());
            proyectoResponse.getResponseBD().setCodErr(cstmt.getInt(4));
            proyectoResponse.getResponseBD().setCodMsg(cstmt.getString(5));

            proyectoResponse.setResponseService(new HeaderDto());
            proyectoResponse.getResponseService().setCodErr(cstmt.getInt(4));
            proyectoResponse.getResponseService().setCodMsg(cstmt.getString(5));
            if (proyectoListResult.size() > 0) {
                proyectoResponse.setProjectList(proyectoListResult);
            }

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

        _logger.info("Finish Consulta Proyecto");
        // 9. Return the result
        return proyectoResponse;
    }

    /*---------------------------------------------------------Método de ordenamiento de proyectos y subproyectos ----------------------------------------------------------------------*/

    private static void OrdenaProyectod(List<ProyectoDto> ProjectListResult,
                                        List<ProyectoDto> SubProjectListResult) {
        List<ProyectoDto> SubProjectList = new ArrayList<ProyectoDto>();

        for (ProyectoDto strProyecto : ProjectListResult) {
            SubProjectList = new ArrayList<ProyectoDto>();
            for (ProyectoDto strSubProyecto : SubProjectListResult) {
                if (strProyecto.getId_proyecto() == strSubProyecto.getId_subproyecto()) {
                    ProyectoDto subP = new ProyectoDto();
                    subP.setId_proyecto(strSubProyecto.getId_proyecto());
                    subP.setNombre(strSubProyecto.getNombre());
                    SubProjectList.add(subP);
                }
            }
            //Se agrega la lista de subproyectos al proyecto correspondiente solo si existen subproyectos en ella
            if (SubProjectList.size() > 0)
                strProyecto.setSubProjectList(SubProjectList);
        }


    }


}
