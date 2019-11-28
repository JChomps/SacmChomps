package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

import java.util.List;

public class UsuarioResultDto implements Serializable {
    @SuppressWarnings("compatibility:699063988906701885")
    private static final long serialVersionUID = 1L;

    public UsuarioResultDto() {
        super();
    }
    private HeaderDto headerRequest = new HeaderDto();
    private HeaderDto headerResponse = new HeaderDto();
    private UsuarioDto usuario = new UsuarioDto();

    public void setHeaderRequest(HeaderDto headerRequest) {
        this.headerRequest = headerRequest;
    }

    public HeaderDto getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderResponse(HeaderDto headerResponse) {
        this.headerResponse = headerResponse;
    }

    public HeaderDto getHeaderResponse() {
        return headerResponse;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }
}
