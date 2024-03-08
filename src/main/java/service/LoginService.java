package service;

import datasource.dao.LoginDao;
import domain.dto.Credentials;
import domain.dto.User;
import jakarta.inject.Inject;
import service.exceptions.InvalidCredentialsException;

public class LoginService {
    private LoginDao loginDao;

    public User login(Credentials credentials) {
        Credentials localCredentials = loginDao.getCredentials(credentials);
        if (localCredentials.getUser().equals(credentials.getUser()) && localCredentials.getPassword().equals(credentials.getPassword())) {
            return new User(credentials.getUser(), this.generateToken());
        } else {
            throw new InvalidCredentialsException();
        }
    }

    private String generateToken() {
        return "1234-1234-1234";
    }

    @Inject
    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

}
