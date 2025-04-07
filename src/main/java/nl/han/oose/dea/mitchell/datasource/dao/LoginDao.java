package nl.han.oose.dea.mitchell.datasource.dao;

import nl.han.oose.dea.mitchell.datasource.datamappers.CredentialsMapper;
import nl.han.oose.dea.mitchell.datasource.exceptions.SQLQueryException;
import nl.han.oose.dea.mitchell.datasource.util.SQLString;
import nl.han.oose.dea.mitchell.domain.dto.login.Credentials;
import jakarta.inject.Inject;
import nl.han.oose.dea.mitchell.domain.dto.login.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao extends Dao {
    private CredentialsMapper credentialsMapper;

    public Credentials getCredentials(Credentials credentials) {
        try (ResultSet resultSet = prepareStatement(String.format(SQLString.GET_CREDENTIALS.label, credentials.getUser(), credentials.getPassword())).executeQuery()) {
            return credentialsMapper.mapCredentialsFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    public void registerUserAsLoggedIn(User user) {
        try {
            prepareStatement(String.format(SQLString.REGISTER_USER.label, user.getToken(), user.getUser())).execute();
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    @Inject
    public void setCredentialsMapper(CredentialsMapper credentialsMapper) {
        this.credentialsMapper = credentialsMapper;
    }
}