package nl.han.oose.dea.mitchell.service.interfaces;

import nl.han.oose.dea.mitchell.domain.dto.login.Credentials;
import nl.han.oose.dea.mitchell.domain.dto.login.User;

public interface ILoginService {
    User login(Credentials credentials);

}
