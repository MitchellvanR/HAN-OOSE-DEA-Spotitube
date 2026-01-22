package nl.han.oose.dea.mitchell.service;

import jakarta.inject.Inject;
import nl.han.oose.dea.mitchell.datasource.interfaces.IAuthenticationDao;

public class AuthenticationService {
    private IAuthenticationDao authenticationDao;

    public boolean validateToken(String token) {
        return authenticationDao.validateToken(token);
    }

    public int getUserIdFromToken(String token) {
        int userid = authenticationDao.getUserIdFromToken(token);
        return userid;
    }

    @Inject
    public void setAuthenticationDao(IAuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
    }
}
