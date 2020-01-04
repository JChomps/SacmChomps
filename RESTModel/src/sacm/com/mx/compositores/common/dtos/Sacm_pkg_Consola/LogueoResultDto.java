package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Consola;


import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class LogueoResultDto {
    
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private Integer total ;
    private List<LogueoDto> accesos = null;
    public LogueoResultDto() {
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<LogueoDto> getAccesos() {
        return accesos;
    }

    public void setAccesos(List<LogueoDto> accesos) {
        this.accesos = accesos;
    }
}
