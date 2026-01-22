package nl.han.oose.dea.mitchell.datasource.connectors;

import nl.han.oose.dea.mitchell.datasource.exceptions.PropertyLoadException;
import nl.han.oose.dea.mitchell.datasource.exceptions.SQLConnectionException;
import nl.han.oose.dea.mitchell.datasource.exceptions.SQLDisconnectException;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCDatabaseConnector {
    private static volatile JDBCDatabaseConnector instance;
    private final Properties properties;
    private Connection connection;

    private JDBCDatabaseConnector() {
        properties = new Properties();
        try {
            Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
                properties.load(input);
            } catch (IOException e) {
                throw new PropertyLoadException();
            }
            String connectionString = connectionString(dotenv);
            properties.setProperty("connectionString", connectionString);
        } catch (Exception e) {
            throw new SQLConnectionException();
        }
    }

    public static JDBCDatabaseConnector getInstance() {
        if (instance == null) {
            synchronized (JDBCDatabaseConnector.class) {
                if (instance == null) {
                    instance = new JDBCDatabaseConnector();
                }
            }
        }
        return instance;
    }

    private String connectionString(Dotenv dotenv) {
        return properties.getProperty("connectionString")
                .replace("${DB_SERVER}", dotenv.get("DB_SERVER"))
                .replace("${DB_PORT}", dotenv.get("DB_PORT"))
                .replace("${DB_DATABASE}", dotenv.get("DB_DATABASE"))
                .replace("${DB_USER}", dotenv.get("DB_USER"))
                .replace("${DB_PASSWORD}", dotenv.get("DB_PASSWORD"));
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(properties.getProperty("connectionString"));
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = createConnection();
            } catch (SQLException e) {
                throw new SQLConnectionException();
            }
        }
        return connection;
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                throw new SQLDisconnectException();
            }
        }
    }
}
