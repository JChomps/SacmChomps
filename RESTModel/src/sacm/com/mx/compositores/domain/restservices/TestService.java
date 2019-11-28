package sacm.com.mx.compositores.domain.restservices;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/test")
public class TestService {
    public TestService() {
        super();
    }

    @GET
    @Produces("application/json")
    @Path("test")
    public String test() {
        return "Servicio de prueba ok";
    }
}
