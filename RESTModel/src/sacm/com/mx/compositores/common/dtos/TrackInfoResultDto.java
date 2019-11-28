package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class TrackInfoResultDto implements Serializable {
    @SuppressWarnings("compatibility:5058617030550372763")
    private static final long serialVersionUID = 1L;

    public TrackInfoResultDto() {
        super();
    }
    private HeaderDto headerRequest;
    private HeaderDto headerResponse = new HeaderDto();
    private TrackInfoDto trackInfo = new TrackInfoDto();
    private List<TrackInfoDto> trackInfoList = new ArrayList<TrackInfoDto>();

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

    public void setTrackInfo(TrackInfoDto trackInfo) {
        this.trackInfo = trackInfo;
    }

    public TrackInfoDto getTrackInfo() {
        return trackInfo;
    }

    public void setTrackInfoList(List<TrackInfoDto> trackInfoList) {
        this.trackInfoList = trackInfoList;
    }

    public List<TrackInfoDto> getTrackInfoList() {
        return trackInfoList;
    }
}
