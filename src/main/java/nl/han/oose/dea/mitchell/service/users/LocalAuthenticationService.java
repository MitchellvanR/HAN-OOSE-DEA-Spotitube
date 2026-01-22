package nl.han.oose.dea.mitchell.service.users;

import jakarta.inject.Inject;
import nl.han.oose.dea.mitchell.datasource.interfaces.IAuthenticationDao;
import nl.han.oose.dea.mitchell.service.interfaces.IAuthenticationService;

public class LocalAuthenticationService implements IAuthenticationService {
    private IAuthenticationDao authenticationDao;

    @Override
    public boolean validateToken(String token) {
        return authenticationDao.validateToken(token);
    }

    @Override
    public int getUserIdFromToken(String token) {
        int userid = authenticationDao.getUserIdFromToken(token);
        return userid;
    }

    @Inject
    public void setAuthenticationDao(IAuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
    }
}
