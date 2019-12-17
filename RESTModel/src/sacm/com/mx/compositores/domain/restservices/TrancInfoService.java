package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TrackInfoDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TrackInfoResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmTrackInfo;

@Path("/trackInfo")
public class TrancInfoService {
    public TrancInfoService() {
        super();
    }


    /*-----------------------------------------------------sacm_track_info Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_track_info")
    public TrackInfoResultDto getTrackInfoByIdObra(TrackInfoDto trackinfoRequest) {
        return SacmTrackInfo.getTrackInfoByIdObra(trackinfoRequest);
    }
}
