package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Proyectos;

import java.util.List;

public class ProyectoDto {
    public ProyectoDto() {
        super();
    }
    
    private Integer id_proyecto;
    private String nombre;
    private Integer id_usuario;
    private String descripcion;
    private String cliente;
    private String atributo_01;
    private String atributo_02;
    private String atributo_03;
    private String atributo_04;
    private String atributo_05;
    private String atributo_06;
    private String atributo_07;
    private String atributo_08;
    private String atributo_09;
    private String atributo_10;
    private List<SubProyectoDto> SubProjectList =null;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getAtributo_01() {
        return atributo_01;
    }

    public void setAtributo_01(String atributo_01) {
        this.atributo_01 = atributo_01;
    }

    public String getAtributo_02() {
        return atributo_02;
    }

    public void setAtributo_02(String atributo_02) {
        this.atributo_02 = atributo_02;
    }

    public String getAtributo_03() {
        return atributo_03;
    }

    public void setAtributo_03(String atributo_03) {
        this.atributo_03 = atributo_03;
    }

    public String getAtributo_04() {
        return atributo_04;
    }

    public void setAtributo_04(String atributo_04) {
        this.atributo_04 = atributo_04;
    }

    public String getAtributo_05() {
        return atributo_05;
    }

    public void setAtributo_05(String atributo_05) {
        this.atributo_05 = atributo_05;
    }

    public String getAtributo_06() {
        return atributo_06;
    }

    public void setAtributo_06(String atributo_06) {
        this.atributo_06 = atributo_06;
    }

    public String getAtributo_07() {
        return atributo_07;
    }

    public void setAtributo_07(String atributo_07) {
        this.atributo_07 = atributo_07;
    }

    public String getAtributo_08() {
        return atributo_08;
    }

    public void setAtributo_08(String atributo_08) {
        this.atributo_08 = atributo_08;
    }

    public String getAtributo_09() {
        return atributo_09;
    }

    public void setAtributo_09(String atributo_09) {
        this.atributo_09 = atributo_09;
    }

    public String getAtributo_10() {
        return atributo_10;
    }

    public void setAtributo_10(String atributo_10) {
        this.atributo_10 = atributo_10;
    }

    public Integer getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(Integer id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public List<SubProyectoDto> getSubProjectList() {
        return SubProjectList;
    }

    public void setSubProjectList(List<SubProyectoDto> SubProjectList) {
        this.SubProjectList = SubProjectList;
    }
}
