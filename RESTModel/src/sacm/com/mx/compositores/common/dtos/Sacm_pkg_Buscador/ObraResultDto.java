package sacm.com.mx.compositores.common.dtos.Sacm_pkg_Buscador;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import sacm.com.mx.compositores.common.dtos.HeaderDto;

public class ObraResultDto implements Serializable {
    @SuppressWarnings("compatibility:-1701967989758779376")
    private static final long serialVersionUID = 1L;

    public ObraResultDto() {
        super();
    }
    private HeaderDto ResponseBD;
    private HeaderDto ResponseService;
    private List<ObraDto> obras=null;// new ArrayList<ObraDto>();
    private List<ObraDto> inboxList= null;//new ArrayList<ObraDto>();
    private List<ObraDto> sharedList= null;//new ArrayList<ObraDto>();
  
   // private List<ObraDto> Versiones = new ArrayList<ObraDto>();

    public void setResponseBD(HeaderDto ResponseBD) {
        this.ResponseBD = ResponseBD;
    }

    public HeaderDto getResponseBD() {
        return ResponseBD;
    }

    public void setResponseService(HeaderDto ResponseService) {
        this.ResponseService = ResponseService;
    }

    public HeaderDto getResponseService() {
        return ResponseService;
    }


    public List<ObraDto> getObras() {
        return obras;
    }

    public void setObras(List<ObraDto> obras) {
        this.obras = obras;
    }

    public List<ObraDto> getInboxList() {
        return inboxList;
    }

    public void setInboxList(List<ObraDto> inboxList) {
        this.inboxList = inboxList;
}

    public List<ObraDto> getSharedList() {
        return sharedList;
    }

    public void setSharedList(List<ObraDto> sharedList) {
        this.sharedList = sharedList;
    }
}
