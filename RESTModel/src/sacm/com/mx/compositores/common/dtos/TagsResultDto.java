package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TagsResultDto implements Serializable {
    @SuppressWarnings("compatibility:-3447506902476224828")
    private static final long serialVersionUID = 1L;

    public TagsResultDto() {
        super();
    }

  
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private Map<String, Tag> tagsMap = new HashMap<String, Tag>();
    private List<Tag> tagsList = new ArrayList<Tag>();
  
   

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

    public Map<String, Tag> getTagsMap() {
        return tagsMap;
    }

    public void setTagsMap(Map<String, Tag> tagsMap) {
        this.tagsMap = tagsMap;
    }


    public List<Tag> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<Tag> tagsList) {
        this.tagsList = tagsList;
    }
}
