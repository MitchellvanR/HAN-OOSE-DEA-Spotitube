package datasource.dao;

import datasource.datamappers.CredentialsMapper;
import datasource.exceptions.SQLQueryException;
import datasource.util.SQLString;
import domain.dto.login.Credentials;
import jakarta.inject.Inject;

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

    @Inject
    public void setCredentialsMapper(CredentialsMapper credentialsMapper) {
        this.credentialsMapper = credentialsMapper;
    }
}