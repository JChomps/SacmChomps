package sacm.com.mx.compositores.common.dtos;

import java.util.ArrayList;
import java.util.List;

public class AlbumResultDto {
    public AlbumResultDto() {
        super();
    }
    
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<AlbumDto> Albumes = new ArrayList<AlbumDto>();

    public HeaderDto getResponseBD() {
        return ResponseBD;
    }

    public void setResponseBD(HeaderDto ResponseBD) {
        this.ResponseBD = ResponseBD;
    }

    public HeaderDto getResponseService() {
        return ResponseService;
    }

    public void setResponseService(HeaderDto ResponseService) {
        this.ResponseService = ResponseService;
    }

    public List<AlbumDto> getAlbumes() {
        return Albumes;
    }

    public void setAlbumes(List<AlbumDto> Albumes) {
        this.Albumes = Albumes;
    }
}
