package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;
import sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador.Tag;

public class TagConsolaResultDto {
    
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    
    private List<TagConsolaDto> tagsList = new ArrayList<TagConsolaDto>();
    public TagConsolaResultDto() {
        super();
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

    public List<TagConsolaDto> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<TagConsolaDto> tagsList) {
        this.tagsList = tagsList;
    }
}
