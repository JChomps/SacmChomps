package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class EstadoResultDto implements Serializable {
    @SuppressWarnings("compatibility:-8073432289962294725")
    private static final long serialVersionUID = 1L;

    public EstadoResultDto() {
        super();
    }
    private EstadoDto estado = new EstadoDto();
    private List<EstadoDto> estadoList = new ArrayList<EstadoDto>();
    private HeaderDto headerRequest;
    private HeaderDto headerResponse = new HeaderDto();

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

    public void setEstado(EstadoDto estado) {
        this.estado = estado;
    }

    public EstadoDto getEstado() {
        return estado;
    }

    public void setEstadoList(List<EstadoDto> estadoList) {
        this.estadoList = estadoList;
    }

    public List<EstadoDto> getEstadoList() {
        return estadoList;
    }
}
