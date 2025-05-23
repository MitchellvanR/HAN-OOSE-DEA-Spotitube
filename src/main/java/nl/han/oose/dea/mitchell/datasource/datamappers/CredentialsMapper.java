package nl.han.oose.dea.mitchell.datasource.datamappers;

import nl.han.oose.dea.mitchell.domain.dto.login.Credentials;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CredentialsMapper {
    public Credentials mapCredentialsFromResultSet(ResultSet resultSet) throws SQLException {
        Credentials localCredentials = new Credentials();
        if (resultSet.next()) {
            localCredentials.setUser(resultSet.getString("username"));
            localCredentials.setPassword(resultSet.getString("password"));
        }
        return localCredentials;
    }
}
