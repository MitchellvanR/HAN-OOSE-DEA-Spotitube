package nl.han.oose.dea.mitchell.resource.endpoints;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.mitchell.domain.dto.login.Credentials;
import nl.han.oose.dea.mitchell.service.interfaces.ILoginService;

@Path("/login")
public class LoginResource {

    private ILoginService loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Credentials credentials) {
        return Response.ok().entity(loginService.login(credentials)).build();
    }
    @Inject
    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }
}
