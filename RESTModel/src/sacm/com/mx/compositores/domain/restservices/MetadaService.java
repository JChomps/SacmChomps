package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.MetadataDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.MetadataResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmMetadata;

@Path("/metadata")
public class MetadaService {
    public MetadaService() {
        super();
    }
    /*-----------------------------------------------------sacm_metadata Service-------------------------------------------------------------------*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("sacm_metadata")
    public MetadataResultDto getMetadata2(MetadataDto metadataRequest) {
        return SacmMetadata.getMetadata(metadataRequest);
    }
}
