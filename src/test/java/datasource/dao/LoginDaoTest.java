package datasource.dao;

import datasource.util.DatabaseProperties;
import domain.dto.login.Credentials;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginDaoTest extends TestCase {
    private LoginDao sut;
    private DatabaseProperties mockDatabaseProperties;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    public void setUp() {
        sut = new LoginDao();
        mockDatabaseProperties = mock(DatabaseProperties.class);
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        sut.setDatabaseProperties(mockDatabaseProperties);
        when(mockDatabaseProperties.getConnection()).thenReturn(mockConnection);
    }

    public void testGetAllPlaylists() throws SQLException {
        // Arrange
        Credentials credentials = new Credentials("test", "passwd");
        when(sut.prepareStatement("SELECT * FROM users WHERE username='" + credentials.getUser() + "' AND password='" + credentials.getPassword() + "'")).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("username")).thenReturn("test");
        when(mockResultSet.getString("password")).thenReturn("passwd");

        // Act
        Credentials result = sut.getCredentials(credentials);

        // Assert
        assertEquals(credentials.getUser(), result.getUser());
        assertEquals(credentials.getPassword(), result.getPassword());
    }
}