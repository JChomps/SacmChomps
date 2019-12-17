package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TagsDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.TagsResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmTags;

@Path("/tag")
public class TagsService {
    public TagsService() {
        super();
    }

    /*-----------------------------------------------------sacm_cat_tags Service-------------------------------------------------------------------*/
    @POST
    @Produces("application/json")
    @Path("sacm_cat_tags")
    public TagsResultDto getTagsByIdTag(TagsDto tagsRequest) {
        return SacmTags.getTagsByIdTag(tagsRequest);
    }
}
