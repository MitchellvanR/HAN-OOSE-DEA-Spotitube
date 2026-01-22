package nl.han.oose.dea.mitchell.datasource.interfaces;

import nl.han.oose.dea.mitchell.domain.dto.login.Credentials;
import nl.han.oose.dea.mitchell.domain.dto.login.User;

public interface ILoginDao {
    Credentials getCredentials(Credentials credentials);
    void registerUserAsLoggedIn(User user);

}
