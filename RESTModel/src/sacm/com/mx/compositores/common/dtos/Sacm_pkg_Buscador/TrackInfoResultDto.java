package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class TrackInfoResultDto implements Serializable {
    @SuppressWarnings("compatibility:5058617030550372763")
    private static final long serialVersionUID = 1L;

    public TrackInfoResultDto() {
        super();
    }
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<TrackInfoDto> trackInfoList = new ArrayList<TrackInfoDto>();

   
   

    public void setTrackInfoList(List<TrackInfoDto> trackInfoList) {
        this.trackInfoList = trackInfoList;
    }

    public List<TrackInfoDto> getTrackInfoList() {
        return trackInfoList;
    }

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
}
