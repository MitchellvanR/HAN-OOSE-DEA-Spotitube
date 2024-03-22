package datasource.dao;

import datasource.util.DatabaseProperties;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dao {

    protected PreparedStatement prepareStatement(String sqlString) throws SQLException {

        return DatabaseProperties.getInstance().getConnection().prepareStatement(sqlString);
    }

    protected void disconnect() {
        DatabaseProperties.getInstance().disconnect();
    }
}
