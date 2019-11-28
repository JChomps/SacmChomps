package sacm.com.mx.compositores.domain.restservices;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import sacm.com.mx.compositores.common.dtos.TagsResultDto;
import sacm.com.mx.compositores.infraestructure.repositories.SacmTags;

@Path("/tag")
public class TagsService {
    public TagsService() {
        super();
    }

    @POST
    @Produces("application/json")
    @Path("gettagsbyidtag")
    public TagsResultDto getTagsByIdTag(TagsResultDto tagsRequest) {
        return SacmTags.getTagsByIdTag(tagsRequest);
    }
}
