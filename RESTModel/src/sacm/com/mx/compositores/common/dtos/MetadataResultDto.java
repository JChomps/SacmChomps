package sacm.com.mx.compositores.common.dtos;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class MetadataResultDto implements Serializable {
    @SuppressWarnings("compatibility:7588089952613487821")
    private static final long serialVersionUID = 1L;

    public MetadataResultDto() {
        super();
    }
    private HeaderDto headerRequest;
    private HeaderDto headerResponse = new HeaderDto();
    private MetadataDto metadata = new MetadataDto();
    private List<MetadataDto> metadataList = new ArrayList<MetadataDto>();

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

    public void setMetadata(MetadataDto metadata) {
        this.metadata = metadata;
    }

    public MetadataDto getMetadata() {
        return metadata;
    }

    public void setMetadataList(List<MetadataDto> metadataList) {
        this.metadataList = metadataList;
    }

    public List<MetadataDto> getMetadataList() {
        return metadataList;
    }
}
