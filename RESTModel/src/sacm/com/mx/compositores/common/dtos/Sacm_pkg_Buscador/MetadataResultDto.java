package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class MetadataResultDto implements Serializable {
    @SuppressWarnings("compatibility:7588089952613487821")
    private static final long serialVersionUID = 1L;

    public MetadataResultDto() {
        super();
    }
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    //private MetadataDto metadata = new MetadataDto();
    private List<MetadataDto> metadataList = new ArrayList<MetadataDto>();

    public void setMetadataList(List<MetadataDto> metadataList) {
        this.metadataList = metadataList;
    }

    public List<MetadataDto> getMetadataList() {
        return metadataList;
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
}
