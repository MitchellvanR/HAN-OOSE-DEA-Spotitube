package nl.han.oose.dea.mitchell.service.interfaces;

public interface IAuthenticationService {
    boolean validateToken(String token);
    int getUserIdFromToken(String token);
}
