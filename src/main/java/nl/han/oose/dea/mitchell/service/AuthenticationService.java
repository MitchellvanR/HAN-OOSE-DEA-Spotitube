package nl.han.oose.dea.mitchell.service;

import jakarta.inject.Inject;
import nl.han.oose.dea.mitchell.datasource.dao.AuthenticationDao;

public class AuthenticationService {
    private AuthenticationDao authenticationDao;

    public boolean validateToken(String token) {
        return authenticationDao.validateToken(token);
    }

    public int getUserIdFromToken(String token) {
        int userid = authenticationDao.getUserIdFromToken(token);
        return userid;
    }

    @Inject
    public void setAuthenticationDao(AuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
    }
}
