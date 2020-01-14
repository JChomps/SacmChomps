package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class TagsResultDto implements Serializable {
    @SuppressWarnings("compatibility:-3447506902476224828")
    private static final long serialVersionUID = 1L;

    public TagsResultDto() {
        super();
    }

  
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    
    private List<Tag> tagsList =null;// new ArrayList<Tag>();
    private List<TagsDto> tagList = null;
  
   

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


   /* public List<Tag> getTagsList() {
        return tagsList;
    }*/

    public void setTagsList(List<Tag> tagsList) {
        this.tagsList = tagsList;
    }


    public List<TagsDto> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagsDto> tagList) {
        this.tagList = tagList;
    }
}
