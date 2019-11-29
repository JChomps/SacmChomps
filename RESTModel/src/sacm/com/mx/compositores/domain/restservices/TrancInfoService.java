package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import sacm.com.mx.compositores.common.dtos.TrackInfoDto;
import sacm.com.mx.compositores.common.dtos.TrackInfoResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmTrackInfo;

@Path("/trackInfo")
public class TrancInfoService {
    public TrancInfoService() {
        super();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_track_info")
    public TrackInfoResultDto getTrackInfoByIdObra(TrackInfoDto trackinfoRequest) {
        return SacmTrackInfo.getTrackInfoByIdObra(trackinfoRequest);
    }
}
