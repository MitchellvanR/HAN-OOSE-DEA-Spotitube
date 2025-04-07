package nl.han.oose.dea.mitchell.resource;

import jakarta.inject.Inject;
import nl.han.oose.dea.mitchell.resource.exceptions.UserNotLoggedInException;
import nl.han.oose.dea.mitchell.service.AuthenticationService;

public class Resource {
    private AuthenticationService authenticationService;
    protected int userid;

    protected void validateUserLogin(String token) {
        if (!authenticationService.validateToken(token)) throw new UserNotLoggedInException();
        userid = authenticationService.getUserIdFromToken(token);
    }

    @Inject
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
}
