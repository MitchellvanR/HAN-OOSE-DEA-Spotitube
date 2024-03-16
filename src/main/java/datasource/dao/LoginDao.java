package datasource.dao;

import datasource.exceptions.SQLQueryException;
import domain.dto.login.Credentials;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao extends Dao {

    public Credentials getCredentials(Credentials credentials) {
        Credentials localCredentials = new Credentials();
        try (ResultSet resultSet = prepareStatement("SELECT * FROM users WHERE username='" + credentials.getUser() + "' AND password='" + credentials.getPassword() + "'").executeQuery()) {
            if (resultSet.next()) {
                localCredentials.setUser(resultSet.getString("username"));
                localCredentials.setPassword(resultSet.getString("password"));
            }
            return localCredentials;
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }
}