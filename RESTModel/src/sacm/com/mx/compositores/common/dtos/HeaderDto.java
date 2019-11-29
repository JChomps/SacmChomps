package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

public class HeaderDto implements Serializable {
    @SuppressWarnings("compatibility:-8057475845259089021")
    private static final long serialVersionUID = 1L;

    public HeaderDto() {
        super();
    }
    private Integer CodErr;
    private String CodMsg;

    public void setCodErr(Integer CodErr) {
        this.CodErr = CodErr;
    }

    public Integer getCodErr() {
        return CodErr;
    }

    public void setCodMsg(String CodMsg) {
        this.CodMsg = CodMsg;
    }

    public String getCodMsg() {
        return CodMsg;
    }
}
