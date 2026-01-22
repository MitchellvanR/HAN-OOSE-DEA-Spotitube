package nl.han.oose.dea.mitchell.datasource.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IDatabaseConnector {
    Connection getConnection();
    PreparedStatement prepareStatement(String sqlString) throws SQLException;
    void disconnect();
}
