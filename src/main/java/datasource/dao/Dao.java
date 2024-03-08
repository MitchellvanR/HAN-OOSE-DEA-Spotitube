package datasource.dao;

import datasource.util.DatabaseProperties;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dao {
    private DatabaseProperties databaseProperties;

    protected PreparedStatement prepareStatement(String sqlString) throws SQLException {
        return databaseProperties.getConnection().prepareStatement(sqlString);
    }

    protected void disconnect() {
        databaseProperties.disconnect();
    }

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }
}
