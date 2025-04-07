package nl.han.oose.dea.mitchell.datasource.dao;

import nl.han.oose.dea.mitchell.datasource.exceptions.SQLQueryException;
import nl.han.oose.dea.mitchell.datasource.util.SQLString;
import nl.han.oose.dea.mitchell.domain.dto.login.Credentials;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationDao extends Dao {
    public boolean validateToken(String token) {
        try (ResultSet resultSet = prepareStatement(String.format(SQLString.AUTHENTICATE_USER.label, token)).executeQuery()) {
            return resultSet.next();
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }
}
