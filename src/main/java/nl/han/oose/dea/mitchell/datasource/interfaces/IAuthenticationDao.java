package nl.han.oose.dea.mitchell.datasource.interfaces;

public interface IAuthenticationDao {
    boolean validateToken(String token);
    int getUserIdFromToken(String token);

}
