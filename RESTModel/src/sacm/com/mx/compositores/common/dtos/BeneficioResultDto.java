package sacm.com.mx.compositores.common.dtos;


import java.util.ArrayList;
import java.util.List;

public class BeneficioResultDto {
    @SuppressWarnings("compatibility:-8073432289962294725")
    private static final long serialVersionUID = 1L;
    
    public BeneficioResultDto() {
        super();
    }
    
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private BeneficioDto beneficio;
    private List<BeneficioDto> beneficioList= new ArrayList<BeneficioDto> ();

   

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
