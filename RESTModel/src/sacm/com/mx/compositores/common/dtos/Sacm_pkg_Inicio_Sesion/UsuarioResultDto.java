package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Inicio_Sesion;

import java.io.Serializable;

import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class UsuarioResultDto implements Serializable {
    @SuppressWarnings("compatibility:699063988906701885")
    private static final long serialVersionUID = 1L;

    public UsuarioResultDto() {
        super();
    }
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private UsuarioDto LoginUser;
    private UsuarioDto UpdatePWD;
    private UsuarioDto SendEmail;
    private Integer id_usuario;
    private List<UsuarioDto> usuarios=null;
    
    

    public void setResponseBD(HeaderDto ResponseBD) {
        this.ResponseBD = ResponseBD;
    }

    public HeaderDto getResponseBD() {
        return ResponseBD;
    }

    public void setResponseService(HeaderDto ResponseService) {
        this.ResponseService = ResponseService;
    }

    public HeaderDto getResponseService() {
        return ResponseService;
    }

    public void setLoginUser(UsuarioDto LoginUser) {
        this.LoginUser = LoginUser;
    }

    public UsuarioDto getLoginUser() {
        return LoginUser;
    }

    public void setUpdatePWD(UsuarioDto UpdatePWD) {
        this.UpdatePWD = UpdatePWD;
    }

    public UsuarioDto getUpdatePWD() {
        return UpdatePWD;
    }

    public void setSendEmail(UsuarioDto SendEmail) {
        this.SendEmail = SendEmail;
    }

    public UsuarioDto getSendEmail() {
        return SendEmail;
    }

    public List<UsuarioDto> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioDto> usuarios) {
        this.usuarios = usuarios;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
