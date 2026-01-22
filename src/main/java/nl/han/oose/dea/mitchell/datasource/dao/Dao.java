package nl.han.oose.dea.mitchell.datasource.dao;

import nl.han.oose.dea.mitchell.datasource.connectors.JDBCDatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dao {

    public PreparedStatement prepareStatement(String sqlString) throws SQLException {
        return JDBCDatabaseConnector.getInstance().getConnection().prepareStatement(sqlString);
    }

    public void disconnect() {
        JDBCDatabaseConnector.getInstance().disconnect();
    }
}
