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
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.CompObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.ObraResultDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.Tag;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TagN1;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.TagConsolaDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.TagN1ConsolaDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola.TagN2ConsolaDto;
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
    public static ProyectoResultDto ConsultaInbox(ProyectoDto projectRequest) {
        List<ProyectoDto> proyectoListResult = new ArrayList<ProyectoDto>();
        List<ProyectoDto> inboxListResult = new ArrayList<ProyectoDto>();
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


                List<ProyectoDto> subproyectos = new ArrayList<ProyectoDto>();
                List<ObraDto> obraList = new ArrayList<ObraDto>();                
                ProyectoDto proyecto = new ProyectoDto();
                ObraDto obra = new ObraDto();
                ProyectoDto obrasN3 = new ProyectoDto();
                ProyectoDto subproyecto = new ProyectoDto();
                //proyecto.setSubProjectList(subproyectos);

                while (rs.next()) {
                    //Se valida que los parametros de obra no vengan con valores nulos
                    if (rs.getInt(2) > 0) {
                        obra = new ObraDto();
                        obra.setId_obra(rs.getInt(14));
                        obra.setObra_numero(rs.getInt(15));
                        obra.setObra_titulo(rs.getString(16));
                        obra.setObra_descripcion(rs.getString(17));                      
                        obra.setCreacion(rs.getString(18));
                        obra.setModificacion(rs.getString(19));
                        obra.setId_usr_origen(rs.getInt(8));
                        obra.setNombre_origen(rs.getString(9));
                        obra.setEmail_origen(rs.getString(10));
                        obra.setId_usr_destino(rs.getInt(11));
                        obra.setNombre_destino(rs.getString(12));
                        obra.setEmail_destino(rs.getString(13));
                    }
                    //Se revisa si la entrada es un proyecto o un subproyecto
                 
                                            //proyectoListResult.getObrasList().add(obra);
                                        
                    if (rs.getInt(2) == 1) {
                        //Se agregan los subproyectos al último proyecto registrado (Siempre se recibe primero un proyecto)
                        if (subproyectos.size() > 0) {
                            proyecto.setSubProjectList(subproyectos);
                        }
                        //Se valida que el Array de proyectos no este vacíp
                        if (proyectoListResult.size() > 0) {
                            //Está validación es porque se puede recibir varias veces el mismo proyecto pero con diferente obra
                            if (proyectoListResult.get(proyectoListResult.size() - 1).getId_proyecto() !=
                                rs.getInt(1)) {
                                // Se crea un nuevo proyecto y se agrega la obra
                                proyecto = new ProyectoDto();
                                obraList = new ArrayList<ObraDto>();
                                proyecto.setId_proyecto(rs.getInt(1));
                                proyecto.setNombre(rs.getString(3));        
                                proyecto.setDescripcion(rs.getString(4));
                                proyecto.setCliente(rs.getString(5));
                                proyecto.setCreacion(rs.getString(6));
                                proyecto.setModificacion(rs.getString(7));
                                proyecto.setId_usr_origen(rs.getInt(8));
                                proyecto.setNombre_origen(rs.getString(9));
                                proyecto.setEmail_origen(rs.getString(10));
                                proyecto.setId_usr_destino(rs.getInt(11));
                                proyecto.setNombre_destino(rs.getString(12));
                                proyecto.setEmail_destino(rs.getString(13));
                                //Si el proyecto tiene una obra se agrega a la lista
                                if (rs.getInt(2) > 0) {
                                    proyecto.setObrasList(obraList);
                                    proyecto.getObrasList().add(obra);
                                    
                                }
                                proyectoListResult.add(proyecto);
                            } else {
                                //Si el proyecto es el mismo que el recibido anteriormente solo se agrega la obra que contiene
                                if (rs.getInt(2) > 0)
                                    proyecto.getObrasList().add(obra);
                            }
                        } else {
                            //Si es el priemr proyecto que se recibe se crea un proyecto nuevo
                            proyecto = new ProyectoDto();
                            obraList = new ArrayList<ObraDto>();

                            proyecto.setId_proyecto(rs.getInt(1));
                            proyecto.setNombre(rs.getString(3));        
                            proyecto.setDescripcion(rs.getString(4));
                            proyecto.setCliente(rs.getString(5));
                            proyecto.setCreacion(rs.getString(6));
                            proyecto.setModificacion(rs.getString(7));
                            proyecto.setId_usr_origen(rs.getInt(8));
                            proyecto.setNombre_origen(rs.getString(9));
                            proyecto.setEmail_origen(rs.getString(10));
                            proyecto.setId_usr_destino(rs.getInt(11));
                            proyecto.setNombre_destino(rs.getString(12));
                            proyecto.setEmail_destino(rs.getString(13));
                            //Si el proyecto incluye una obra se agrega a la lista
                            if (rs.getInt(2) > 0) {
                                proyecto.setObrasList(obraList);
                                proyecto.getObrasList().add(obra);
                               
                            }
                            proyectoListResult.add(proyecto);

                        }
                        subproyectos = new ArrayList<ProyectoDto>();

                 //Se revisa si la entrada es un subproyecto
                    } else if (rs.getInt(2) == 2) {
                        if (subproyectos.size() > 0) {
                            if (subproyectos.get(subproyectos.size() - 1).getId_subproyecto() != rs.getInt(1)) {
                                subproyecto = new ProyectoDto();
                                obraList = new ArrayList<ObraDto>();
                                subproyecto.setId_subproyecto(rs.getInt(1));
                                subproyecto.setNombre(rs.getString(3));     
                                subproyecto.setDescripcion(rs.getString(4));
                                subproyecto.setCliente(rs.getString(5));
                                subproyecto.setCreacion(rs.getString(6));
                                subproyecto.setModificacion(rs.getString(7));
                                subproyecto.setId_usr_origen(rs.getInt(8));
                                subproyecto.setNombre_origen(rs.getString(9));
                                subproyecto.setEmail_origen(rs.getString(10));
                                subproyecto.setId_usr_destino(rs.getInt(11));
                                subproyecto.setNombre_destino(rs.getString(12));
                                subproyecto.setEmail_destino(rs.getString(13));
                                
                                if (rs.getInt(2) > 0) {
                                    subproyecto.setObrasList(obraList);
                                    subproyecto.getObrasList().add(obra);
                                }
                                subproyectos.add(subproyecto);

                            } else {
                                if (rs.getInt(2) > 0) 
                                    subproyecto.getObrasList().add(obra);

                            }

                        } else {
                            subproyecto = new ProyectoDto();
                            obraList = new ArrayList<ObraDto>();
                            subproyecto.setId_subproyecto(rs.getInt(1));
                                subproyecto.setNombre(rs.getString(3));     
                                subproyecto.setDescripcion(rs.getString(4));
                                subproyecto.setCliente(rs.getString(5));
                                subproyecto.setCreacion(rs.getString(6));
                                subproyecto.setModificacion(rs.getString(7));
                                subproyecto.setId_usr_origen(rs.getInt(8));
                                subproyecto.setNombre_origen(rs.getString(9));
                                subproyecto.setEmail_origen(rs.getString(10));
                                subproyecto.setId_usr_destino(rs.getInt(11));
                                subproyecto.setNombre_destino(rs.getString(12));
                                subproyecto.setEmail_destino(rs.getString(13));
                            if (rs.getInt(2) > 0) {
                                subproyecto.setObrasList(obraList);
                                subproyecto.getObrasList().add(obra);
                            }
                            subproyectos.add(subproyecto);
                        }

                                                    
                    }else if (rs.getInt(2) == 3){
                        
                        
                        
                        
                        obrasN3.setId_obra(rs.getInt(14));
                        obrasN3.setObra_numero(rs.getInt(15));
                        obrasN3.setObra_titulo(rs.getString(16));
                        obrasN3.setDescripcion(rs.getString(17));
                        obrasN3.setCreacion(rs.getString(18));
                        obrasN3.setModificacion(rs.getString(19));
                        obrasN3.setId_usr_origen(rs.getInt(8));
                        obrasN3.setNombre_origen(rs.getString(9));
                        obrasN3.setEmail_origen(rs.getString(10));
                        obrasN3.setId_usr_destino(rs.getInt(11));
                        obrasN3.setNombre_destino(rs.getString(12));
                        obrasN3.setEmail_destino(rs.getString(13));
                        inboxListResult.add(obrasN3);
                        
                    }

                }
                if (subproyectos.size() > 0)
                    proyecto.setSubProjectList(subproyectos);

                //  proyectoListResult.add(proyecto);

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
            
            if (proyectoListResult.size() > 0) {
                            proyectoResponse.setProjectList(proyectoListResult);
                        }
                        
            if (inboxListResult.size() > 0) {
                            proyectoResponse.setObrasList(inboxListResult);
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

    /*---------------------------------------------------------sacm_consulta_shared Service----------------------------------------------------------------------*/
    public static ProyectoResultDto ConsultaShared(ProyectoDto projectRequest) {
        List<ProyectoDto> proyectoListResult = new ArrayList<ProyectoDto>();
        List<ProyectoDto> obrasListResult = new ArrayList<ProyectoDto>();
        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PROYECTOS.PRC_CONSULTA_SHARED(?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, projectRequest.getId_usuario());
            //cstmt.setObject(2, projectRequest.getTipo()); // SE VA***************
            //cstmt.setObject(3, projectRequest.getBusca()); // SE VA ***************
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(2, -10); // CURSOR
            cstmt.registerOutParameter(3, Types.INTEGER); //CODIGO DE ERROR
            cstmt.registerOutParameter(4, Types.VARCHAR); //MENSAJE DE ERROR
            // 5. Execute the statement
            cstmt.executeUpdate();
            if (cstmt.getInt(3) == 0) // valida que el código de error sea cero para continuar ***************
            {
                // read the results
                rs = (ResultSet) cstmt.getObject(2); // se lee el cursor ***************
                List<ProyectoDto> subproyectos = new ArrayList<ProyectoDto>();
                List<ObraDto> obraList = new ArrayList<ObraDto>();
                //List<ObraDto> obraListN3 = new ArrayList<ObraDto>();
                ProyectoDto proyecto = new ProyectoDto();
                ObraDto obra = new ObraDto();
                ProyectoDto subproyecto = new ProyectoDto();
                ProyectoDto obra_N3 = new ProyectoDto();
                //ObraDto obraN3 = new ObraDto();// posiblemente un objeto ***************
                //proyecto.setSubProjectList(subproyectos);
                while (rs.next()) {
                    //Se valida que los parametros de obra no vengan con valores nulos
                    if (rs.getInt(2) > 0) {
                        obra = new ObraDto();
                        obra.setId_obra(rs.getInt(14));
                        obra.setObra_numero(rs.getInt(15));
                        obra.setObra_titulo(rs.getString(16));
                        //INGRESADOR POR MI
                        obra.setObra_descripcion(rs.getString(17));
                        obra.setCreacion(rs.getString(18));
                        obra.setModificacion(rs.getString(19));
                        obra.setId_usr_origen(rs.getInt(8));
                        obra.setNombre_origen(rs.getString(9));
                        obra.setEmail_origen(rs.getString(10));
                        obra.setId_usr_destino(rs.getInt(11));
                        obra.setNombre_destino(rs.getString(12));
                        obra.setEmail_destino(rs.getString(13));
                    }
                    //Se revisa si la entrada es un proyecto o un subproyecto
                    if (rs.getInt(2) == 1) {
                        //Se agregan los subproyectos al último proyecto registrado (Siempre se recibe primero un proyecto)
                        if (subproyectos.size() > 0) {
                            proyecto.setSubProjectList(subproyectos);
                        }
                        //Se valida que el Array de proyectos no este vacíp
                        if (proyectoListResult.size() >
                            0) {
                            //Está validación es porque se puede recibir varias veces el mismo proyecto pero con diferente obra
                            if (proyectoListResult.get(proyectoListResult.size() - 1).getId_proyecto() !=
                                rs.getInt(1)) {
    // Se crea un nuevo proyecto y se agrega la obra
    proyecto = new ProyectoDto();
                                obraList = new ArrayList<ObraDto>();
                                proyecto.setId_proyecto(rs.getInt(1));
                                proyecto.setNombre(rs.getString(3));
                                //INGRESADOR POR MI
                                proyecto.setDescripcion(rs.getString(4));
                                proyecto.setCliente(rs.getString(5));
                                proyecto.setCreacion(rs.getString(6));
                                proyecto.setModificacion(rs.getString(7));
                                proyecto.setId_usr_origen(rs.getInt(8));
                                proyecto.setNombre_origen(rs.getString(9));
                                proyecto.setEmail_origen(rs.getString(10));
                                proyecto.setId_usr_destino(rs.getInt(11));
                                proyecto.setNombre_destino(rs.getString(12));
                                proyecto.setEmail_destino(rs.getString(13));
                                //Si el proyecto tiene una obra se agrega a la lista
                                if (rs.getInt(14) > 0) {
                                    proyecto.setObrasList(obraList);
                                    proyecto.getObrasList().add(obra);
                                }
                                proyectoListResult.add(proyecto);
                            } else {
                                //Si el proyecto es el mismo que el recibido anteriormente solo se agrega la obra que contiene
                                if (rs.getInt(14) > 0)
                                    proyecto.getObrasList().add(obra);
                            }
                        } else {
                            //Si es el priemr proyecto que se recibe se crea un proyecto nuevo
                            proyecto = new ProyectoDto();
                            obraList = new ArrayList<ObraDto>();

                            proyecto.setId_proyecto(rs.getInt(1));
                            proyecto.setNombre(rs.getString(3));
                            //INGRESADOR POR MI
                            proyecto.setDescripcion(rs.getString(4));
                            proyecto.setCliente(rs.getString(5));
                            proyecto.setCreacion(rs.getString(6));
                            proyecto.setModificacion(rs.getString(7));
                            proyecto.setId_usr_origen(rs.getInt(8));
                            proyecto.setNombre_origen(rs.getString(9));
                            proyecto.setEmail_origen(rs.getString(10));
                            proyecto.setId_usr_destino(rs.getInt(11));
                            proyecto.setNombre_destino(rs.getString(12));
                            proyecto.setEmail_destino(rs.getString(13));
                            //Si el proyecto incluye una obra se agrega a la lista
                            if (rs.getInt(14) > 0) {
                                proyecto.setObrasList(obraList);
                                proyecto.getObrasList().add(obra);

                            }
                            proyectoListResult.add(proyecto);

                        }
                        subproyectos = new ArrayList<ProyectoDto>();

                        //Se revisa si la entrada es un subproyecto
                    } else if (rs.getInt(2) == 2) {
                        if (subproyectos.size() > 0) {
                            if (subproyectos.get(subproyectos.size() - 1).getId_subproyecto() != rs.getInt(1)) {
                                subproyecto = new ProyectoDto();
                                obraList = new ArrayList<ObraDto>();
                                subproyecto.setId_subproyecto(rs.getInt(1));
                                subproyecto.setNombre(rs.getString(3));
                                //INGRESADOR POR MI
                                subproyecto.setDescripcion(rs.getString(4));
                                subproyecto.setCliente(rs.getString(5));
                                subproyecto.setCreacion(rs.getString(6));
                                subproyecto.setModificacion(rs.getString(7));
                                subproyecto.setId_usr_origen(rs.getInt(8));
                                subproyecto.setNombre_origen(rs.getString(9));
                                subproyecto.setEmail_origen(rs.getString(10));
                                subproyecto.setId_usr_destino(rs.getInt(11));
                                subproyecto.setNombre_destino(rs.getString(12));
                                subproyecto.setEmail_destino(rs.getString(13));

                                if (rs.getInt(14) > 0) {
                                    subproyecto.setObrasList(obraList);
                                    subproyecto.getObrasList().add(obra);
                                }
                                subproyectos.add(subproyecto);

                            } else {
                                if (rs.getInt(14) > 0)
                                    subproyecto.getObrasList().add(obra);

                            }

                        } else {
                            subproyecto = new ProyectoDto();
                            obraList = new ArrayList<ObraDto>();
                            subproyecto.setId_subproyecto(rs.getInt(1));
                            subproyecto.setNombre(rs.getString(3));
                            //INGRESADOR POR MI
                            subproyecto.setDescripcion(rs.getString(4));
                            subproyecto.setCliente(rs.getString(5));
                            subproyecto.setCreacion(rs.getString(6));
                            subproyecto.setModificacion(rs.getString(7));
                            subproyecto.setId_usr_origen(rs.getInt(8));
                            subproyecto.setNombre_origen(rs.getString(9));
                            subproyecto.setEmail_origen(rs.getString(10));
                            subproyecto.setId_usr_destino(rs.getInt(11));
                            subproyecto.setNombre_destino(rs.getString(12));
                            subproyecto.setEmail_destino(rs.getString(13));
                            if (rs.getInt(14) > 0) {
                                subproyecto.setObrasList(obraList);
                                subproyecto.getObrasList().add(obra);
                            }
                            subproyectos.add(subproyecto);
                        }
                    }
                    //AQUI VA LO DEL NIVEL 3
                    else if (rs.getInt(2) == 3) {
                        obra_N3 = new ProyectoDto();
                        //obraList = new ArrayList<ObraDto>();
                        obra_N3.setId_obra(rs.getInt(14));
                        obra_N3.setObra_numero(rs.getInt(15));
                        obra_N3.setObra_titulo(rs.getString(16));
                        //INGRESADOR POR MI
                        obra_N3.setDescripcion(rs.getString(17));
                        obra_N3.setCreacion(rs.getString(18));
                        obra_N3.setModificacion(rs.getString(19));
                        obra_N3.setId_usr_origen(rs.getInt(8));
                        obra_N3.setNombre_origen(rs.getString(9));
                        obra_N3.setEmail_origen(rs.getString(10));
                        obra_N3.setId_usr_destino(rs.getInt(11));
                        obra_N3.setNombre_destino(rs.getString(12));
                        obra_N3.setEmail_destino(rs.getString(13));

                        obrasListResult.add(obra_N3);
                    }
                }
                if (subproyectos.size() > 0)
                    proyecto.setSubProjectList(subproyectos);
                //  proyectoListResult.add(proyecto);
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
            if (proyectoListResult.size() > 0) {
                proyectoResponse.setProjectList(proyectoListResult);
            }
            
            if (obrasListResult.size() > 0) {
                proyectoResponse.setObrasList(obrasListResult);
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

    private static void OrdenaProyectod(List<ProyectoDto> ProjectListResult, List<ProyectoDto> SubProjectListResult) {
        List<ProyectoDto> SubProjectList = new ArrayList<ProyectoDto>();

        for (ProyectoDto strProyecto : ProjectListResult) {
            SubProjectList = new ArrayList<ProyectoDto>();
            for (ProyectoDto strSubProyecto : SubProjectListResult) {
                if (strProyecto.getId_proyecto().equals(strSubProyecto.getId_subproyecto())) {
                    ProyectoDto subP = new ProyectoDto();
                    subP.setId_proyecto(strProyecto.getId_proyecto());
                    subP.setNombre(strSubProyecto.getNombre());
                    subP.setId_subproyecto(strSubProyecto.getId_subproyecto());
                    SubProjectList.add(subP);
                }
            }
            //Se agrega la lista de subproyectos al proyecto correspondiente solo si existen subproyectos en ella
            if (SubProjectList.size() > 0)
                strProyecto.setSubProjectList(SubProjectList);
        }


    }


    /*-----------------------------------------------------sacm_consulta_proyecto_todo Service-------------------------------------------------------------------*/
 public static ProyectoResultDto ConsultaProyectoTodo(ProyectoDto projectRequest) 
 {
        List<ProyectoDto> proyectoListResult = new ArrayList<ProyectoDto>();
        ResultSet rs = null;
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PROYECTOS.PRC_CONSULTA_PROYECTO_TODO(?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, projectRequest.getId_usuario());
            cstmt.setObject(2, projectRequest.getTipo());
            cstmt.setObject(3, projectRequest.getBusca());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(4, -10);
            cstmt.registerOutParameter(5, Types.INTEGER);
            cstmt.registerOutParameter(6, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();

            if (cstmt.getInt(5) == 0) {
                // read the results
                rs = (ResultSet) cstmt.getObject(4);


                List<ProyectoDto> subproyectos = new ArrayList<ProyectoDto>();
                List<ObraDto> obraList = new ArrayList<ObraDto>();
                ProyectoDto proyecto = new ProyectoDto();
                ObraDto obra = new ObraDto();
                ProyectoDto subproyecto = new ProyectoDto();
                //proyecto.setSubProjectList(subproyectos);

                while (rs.next()) {
                    //Se valida que los parametros de obra no vengan con valores nulos
                    if (rs.getInt(8) > 0) {
                        obra = new ObraDto();
                        obra.setId_obra(rs.getInt(8));
                        obra.setObra_numero(rs.getInt(9));
                        obra.setObra_titulo(rs.getString(10));
                        obra.setObra_descripcion(rs.getString(11));
                        obra.setCreacion(rs.getString(12));
                        obra.setModificacion(rs.getString(13));

                    }
                    //Se revisa si la entrada es un proyecto o un subproyecto
                    if (rs.getInt(2) == 1) {
                        //Se agregan los subproyectos al último proyecto registrado (Siempre se recibe primero un proyecto)
                        if (subproyectos.size() > 0) {
                            proyecto.setSubProjectList(subproyectos);
                        }
                        //Se valida que el Array de proyectos no este vacíp
                        if (proyectoListResult.size() >
                            0) {
                            //Está validación es porque se puede recibir varias veces el mismo proyecto pero con diferente obra
                            if (proyectoListResult.get(proyectoListResult.size() - 1).getId_proyecto() !=
                                rs.getInt(1)) {
    // Se crea un nuevo proyecto y se agrega la obra
    proyecto = new ProyectoDto();
                                obraList = new ArrayList<ObraDto>();
                                proyecto.setId_proyecto(rs.getInt(1));
                                proyecto.setNombre(rs.getString(3));
                                proyecto.setDescripcion(rs.getString(4));
                                proyecto.setCliente(rs.getString(5));
                                proyecto.setCreacion(rs.getString(6));
                                proyecto.setModificacion(rs.getString(7));
                                //Si el proyecto tiene una obra se agrega a la lista
                                if (rs.getInt(8) > 0) {
                                    proyecto.setObrasList(obraList);
                                    proyecto.getObrasList().add(obra);

                                }
                                proyectoListResult.add(proyecto);
                            } else {
                                //Si el proyecto es el mismo que el recibido anteriormente solo se agrega la obra que contiene
                                if (rs.getInt(8) > 0)
                                    proyecto.getObrasList().add(obra);
                            }
                        } else {
                            //Si es el priemr proyecto que se recibe se crea un proyecto nuevo
                            proyecto = new ProyectoDto();
                            obraList = new ArrayList<ObraDto>();

                            proyecto.setId_proyecto(rs.getInt(1));
                            proyecto.setNombre(rs.getString(3));
                            proyecto.setDescripcion(rs.getString(4));
                            proyecto.setCliente(rs.getString(5));
                            proyecto.setCreacion(rs.getString(6));
                            proyecto.setModificacion(rs.getString(7));
                            //Si el proyecto incluye una obra se agrega a la lista
                            if (rs.getInt(8) > 0) {
                                proyecto.setObrasList(obraList);
                                proyecto.getObrasList().add(obra);

                            }
                            proyectoListResult.add(proyecto);

                        }
                        subproyectos = new ArrayList<ProyectoDto>();

                        //Se revisa si la entrada es un subproyecto
                    } else if (rs.getInt(2) == 2) {
                        if (subproyectos.size() > 0) {
                            if (subproyectos.get(subproyectos.size() - 1).getId_subproyecto() != rs.getInt(1)) {
                                subproyecto = new ProyectoDto();
                                obraList = new ArrayList<ObraDto>();
                                subproyecto.setId_subproyecto(rs.getInt(1));
                                subproyecto.setNombre(rs.getString(3));
                                subproyecto.setModificacion(rs.getString(7));
                                subproyecto.setDescripcion(rs.getString(4));
                                subproyecto.setCliente(rs.getString(5));
                                subproyecto.setCreacion(rs.getString(6));
                                

                                if (rs.getInt(8) > 0) {
                                    subproyecto.setObrasList(obraList);
                                    subproyecto.getObrasList().add(obra);
                                }
                                subproyectos.add(subproyecto);

                            } else {
                                if (rs.getInt(8) > 0)
                                    subproyecto.getObrasList().add(obra);

                            }

                        } else {
                            subproyecto = new ProyectoDto();
                            obraList = new ArrayList<ObraDto>();
                            subproyecto.setId_subproyecto(rs.getInt(1));
                            subproyecto.setNombre(rs.getString(3));
                            subproyecto.setModificacion(rs.getString(7));
                            subproyecto.setDescripcion(rs.getString(4));
                            subproyecto.setCliente(rs.getString(5));
                            subproyecto.setCreacion(rs.getString(6));
                            if (rs.getInt(8) > 0) {
                                subproyecto.setObrasList(obraList);
                                subproyecto.getObrasList().add(obra);
                            }
                            subproyectos.add(subproyecto);
                        }


                    }
                }


                if (subproyectos.size() > 0)
                    proyecto.setSubProjectList(subproyectos);

                //  proyectoListResult.add(proyecto);

                rs.close();
            }


            // 6. Set value of dateValue property using first OUT param
            proyectoResponse = new ProyectoResultDto();
            proyectoResponse.setResponseBD(new HeaderDto());
            proyectoResponse.getResponseBD().setCodErr(cstmt.getInt(5));
            proyectoResponse.getResponseBD().setCodMsg(cstmt.getString(6));

            proyectoResponse.setResponseService(new HeaderDto());
            proyectoResponse.getResponseService().setCodErr(cstmt.getInt(5));
            proyectoResponse.getResponseService().setCodMsg(cstmt.getString(6));
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
    /*-----------------------------------------------------sacm_inbox_copia_myprojects Service-------------------------------------------------------------------*/
     public static ProyectoResultDto getInboxProject(ProyectoDto projectRequest) {
       
        CallableStatement cstmt = null;
        Connection conn = null;

        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PROYECTOS.PRC_COPY_TO_MYPROJECTS(?,?,?,?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1, projectRequest.getId_usuario());
            cstmt.setObject(2, projectRequest.getId_obra());
            cstmt.setObject(3, projectRequest.getId_proyecto());
            cstmt.setObject(4, projectRequest.getNombre());
            cstmt.setObject(5, projectRequest.getDescripcion());
            cstmt.setObject(6, projectRequest.getCliente());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(7, Types.INTEGER);
            cstmt.registerOutParameter(8, Types.VARCHAR);
            cstmt.registerOutParameter(9, Types.INTEGER);
            // 5. Execute the statement
            cstmt.executeUpdate();

           

            // 6. Set value of dateValue property using first OUT param
            proyectoResponse = new ProyectoResultDto();
            proyectoResponse.setId_proyecto(cstmt.getInt(9));
            proyectoResponse.setResponseBD(new HeaderDto());
            proyectoResponse.getResponseBD().setCodErr(cstmt.getInt(7));
            proyectoResponse.getResponseBD().setCodMsg(cstmt.getString(8));

            proyectoResponse.setResponseService(new HeaderDto());
            proyectoResponse.getResponseService().setCodErr(cstmt.getInt(7));
            proyectoResponse.getResponseService().setCodMsg(cstmt.getString(8));
           

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
    /*-----------------------------------------------------sacm_eliminar_proyecto_shared Service-------------------------------------------------------------------*/
    public static ProyectoResultDto getEliminaProyectoShared(ProyectoDto ProyectoRequest) {
        CallableStatement cstmt = null;
        Connection conn = null;
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PROYECTOS.INACTIVA_PROYECTO_COMPARTIDO(?,?,?,?,?,?)}");
            // 3. Set the bind values of the IN parameters
            cstmt.setObject(1,ProyectoRequest.getId_proyecto());
            cstmt.setObject(2,ProyectoRequest.getId_usr_origen());
            cstmt.setObject(3,ProyectoRequest.getId_usr_destino());
            cstmt.setObject(4,ProyectoRequest.getClase());
            // 4. Register the positions and types of the OUT parameters
            cstmt.registerOutParameter(5, Types.INTEGER);
            cstmt.registerOutParameter(6, Types.VARCHAR);
            // 5. Execute the statement
            cstmt.executeUpdate();


            // 6. Set value of dateValue property using first OUT param
            proyectoResponse = new ProyectoResultDto();

            proyectoResponse.setResponseBD(new HeaderDto());
            proyectoResponse.getResponseBD().setCodErr(cstmt.getInt(5));
            proyectoResponse.getResponseBD().setCodMsg(cstmt.getString(6));

            proyectoResponse.setResponseService(new HeaderDto());
            proyectoResponse.getResponseService().setCodErr(cstmt.getInt(5));
            proyectoResponse.getResponseService().setCodMsg(cstmt.getString(6));

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

    /*-----------------------------------------------------sacm_duplica_proyecto Service-------------------------------------------------------------------*/
    public static ProyectoResultDto DuplicaProyecto(ProyectoDto proyectoRequest) {
        CallableStatement cstmt = null;       
        Connection conn = null;        
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PROYECTOS.DUPLICA_PROYECTO(?,?,?,?,?)}");
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
    /*-----------------------------------------------------sacm_elimina_obra_proyecto Service-------------------------------------------------------------------*/
    public static ProyectoResultDto EliminaObraProyecto(ProyectoDto proyectoRequest) {
        CallableStatement cstmt = null;       
        Connection conn = null;        
        try {
            conn = AppModule.getDbConexionJDBC();
            // 2. Define the PL/SQL block for the statement to invoke
            cstmt = conn.prepareCall("{call SACM_PKG_PROYECTOS.ELIMINA_PROYECTO_OBRA(?,?,?,?,?)}");
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
    /*-----------------------------------------------------sacm_compartir_proyecto Service-------------------------------------------------------------------*/
    public static ProyectoResultDto CompartorProyecto(CompObraDto proyectoRequest) {
            CallableStatement cstmt = null;       
            Connection conn = null;        
            try {
                conn = AppModule.getDbConexionJDBC();
                // 2. Define the PL/SQL block for the statement to invoke
                cstmt = conn.prepareCall("{call SACM_PKG_PROYECTOS.PRC_COMPARTIR_PROYECTO(?,?,?,?,?,?,?)}");
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
    /*-----------------------------------------------------sacm_actualiza_proyecto Service-------------------------------------------------------------------*/
    public static ProyectoResultDto ActualizaProyecto(ProyectoDto proyectoRequest) {
       CallableStatement cstmt = null;       
       Connection conn = null;        
       try {
           conn = AppModule.getDbConexionJDBC();
           // 2. Define the PL/SQL block for the statement to invoke
           cstmt = conn.prepareCall("{call SACM_PKG_PROYECTOS.ACTUALIZA_PROYECTO(?,?,?,?)}");
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
}