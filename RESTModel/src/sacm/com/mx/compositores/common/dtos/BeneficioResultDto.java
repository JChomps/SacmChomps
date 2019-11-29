package sacm.com.mx.compositores.common.dtos;


import java.util.ArrayList;
import java.util.List;

public class BeneficioResultDto {
    @SuppressWarnings("compatibility:-8073432289962294725")
    private static final long serialVersionUID = 1L;
    
    public BeneficioResultDto() {
        super();
    }
    
    private HeaderDto headerRequest;
    private HeaderDto headerResponse = new HeaderDto();
    private BeneficioDto beneficio;
    private List<BeneficioDto> beneficioList= new ArrayList<BeneficioDto> ();

    public HeaderDto getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderRequest(HeaderDto headerRequest) {
        this.headerRequest = headerRequest;
    }

    public HeaderDto getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderDto headerResponse) {
        this.headerResponse = headerResponse;
    }

    public BeneficioDto getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(BeneficioDto beneficio) {
        this.beneficio = beneficio;
    }

    public List<BeneficioDto> getBeneficioList() {
        return beneficioList;
    }

    public void setBeneficioList(List<BeneficioDto> beneficioList) {
        this.beneficioList = beneficioList;
    }
}
