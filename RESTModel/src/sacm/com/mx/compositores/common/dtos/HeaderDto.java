package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

public class HeaderDto implements Serializable {
    @SuppressWarnings("compatibility:-8057475845259089021")
    private static final long serialVersionUID = 1L;

    public HeaderDto() {
        super();
    }
    private String operacion;
    private String sistemaOrigen;
    private Integer errorCode;
    private String errorMsg;

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setSistemaOrigen(String sistemaOrigen) {
        this.sistemaOrigen = sistemaOrigen;
    }

    public String getSistemaOrigen() {
        return sistemaOrigen;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
