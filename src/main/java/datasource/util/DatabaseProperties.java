package datasource.util;

import datasource.exceptions.PropertyLoadException;
import datasource.exceptions.SQLConnectionException;
import datasource.exceptions.SQLDisconnectException;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseProperties {
    private final Properties properties;
    private final Connection connection;

    public DatabaseProperties() {
        properties = new Properties();
        try {
            Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
                properties.load(input);
            } catch (IOException e) {
                throw new PropertyLoadException();
            }
            String connectionString = properties.getProperty("connectionString");
            connectionString = connectionString
                    .replace("${DB_SERVER}", dotenv.get("DB_SERVER"))
                    .replace("${DB_PORT}", dotenv.get("DB_PORT"))
                    .replace("${DB_DATABASE}", dotenv.get("DB_DATABASE"))
                    .replace("${DB_USER}", dotenv.get("DB_USER"))
                    .replace("${DB_PASSWORD}", dotenv.get("DB_PASSWORD"));

            properties.setProperty("connectionString", connectionString);

            connection = connect();
        } catch (Exception e) {
            throw new SQLConnectionException();
        }
    }

    private Connection connect() {
        try {
            return DriverManager.getConnection(properties.getProperty("connectionString"));
        } catch (SQLException e) {
            throw new SQLConnectionException();
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new SQLDisconnectException();
        }
    }

    public String connectionString() {
        return properties.getProperty("connectionString");
    }

    public String driver() {
        return properties.getProperty("driver");
    }

    public Connection getConnection() {
        return connection;
    }
}
