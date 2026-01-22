package nl.han.oose.dea.mitchell.resource.util;

import jakarta.inject.Inject;
import nl.han.oose.dea.mitchell.resource.exceptions.UserNotLoggedInException;
import nl.han.oose.dea.mitchell.service.interfaces.IAuthenticationService;

public class RESTAuthenticator {
    private IAuthenticationService authenticationService;
    protected int userid;

    public void validateUserLogin(String token) {
        if (!authenticationService.validateToken(token)) throw new UserNotLoggedInException();
        userid = authenticationService.getUserIdFromToken(token);
    }

    public int getUserid() {
        return userid;
    }

    @Inject
    public void setAuthenticationService(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
}
