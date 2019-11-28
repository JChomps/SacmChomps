package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class TagsResultDto implements Serializable {
    @SuppressWarnings("compatibility:-3447506902476224828")
    private static final long serialVersionUID = 1L;

    public TagsResultDto() {
        super();
    }

    private HeaderDto headerRequest;
    private HeaderDto headerResponse = new HeaderDto();
    private TagsDto tag = new TagsDto();
    private List<TagsDto> tagsList = new ArrayList<TagsDto>();

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

    public void setTag(TagsDto tag) {
        this.tag = tag;
    }

    public TagsDto getTag() {
        return tag;
    }

    public void setTagsList(List<TagsDto> tagsList) {
        this.tagsList = tagsList;
    }

    public List<TagsDto> getTagsList() {
        return tagsList;
    }
}
