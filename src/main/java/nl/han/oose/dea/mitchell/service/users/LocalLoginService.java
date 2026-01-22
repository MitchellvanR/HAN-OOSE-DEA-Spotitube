package nl.han.oose.dea.mitchell.service.users;

import nl.han.oose.dea.mitchell.datasource.interfaces.ILoginDao;
import nl.han.oose.dea.mitchell.domain.dto.login.Credentials;
import nl.han.oose.dea.mitchell.domain.dto.login.User;
import jakarta.inject.Inject;
import nl.han.oose.dea.mitchell.service.exceptions.InvalidCredentialsException;
import nl.han.oose.dea.mitchell.service.interfaces.ILoginService;

import java.util.Random;

public class LocalLoginService implements ILoginService {
    private ILoginDao loginDao;

    @Override
    public User login(Credentials credentials) {
        Credentials localCredentials = loginDao.getCredentials(credentials);
        if (localCredentials.getUser().equals(credentials.getUser()) && localCredentials.getPassword().equals(credentials.getPassword())) {
            User loggedInUser = new User(this.generateToken(), credentials.getUser());
            loginDao.registerUserAsLoggedIn(loggedInUser);
            return loggedInUser;
        } else {
            throw new InvalidCredentialsException();
        }
    }

    protected String generateToken() {
        Random random = new Random();
        return String.format("%d-%d-%d",
                random.nextInt(1000,10000),
                random.nextInt(1000,10000),
                random.nextInt(1000,10000)
        );
    }

    @Inject
    public void setLoginDao(ILoginDao loginDao) {
        this.loginDao = loginDao;
    }
}
