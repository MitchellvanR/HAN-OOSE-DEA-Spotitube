package nl.han.oose.dea.mitchell.dao;

import nl.han.oose.dea.mitchell.datasource.dao.JDBCLoginDao;
import nl.han.oose.dea.mitchell.datasource.datamappers.CredentialsMapper;
import nl.han.oose.dea.mitchell.datasource.exceptions.SQLQueryException;
import nl.han.oose.dea.mitchell.datasource.interfaces.IDatabaseConnector;
import nl.han.oose.dea.mitchell.domain.dto.login.Credentials;
import junit.framework.TestCase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class JDBCLoginDaoTest extends TestCase {
    private JDBCLoginDao sut;
    private IDatabaseConnector mockDatabaseConnector;
    private CredentialsMapper mockCredentialsMapper;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    public void setUp() {
        mockDatabaseConnector = mock(IDatabaseConnector.class);
        mockCredentialsMapper = mock(CredentialsMapper.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        sut = new JDBCLoginDao();
        sut.setDatabaseConnector(mockDatabaseConnector);
        sut.setCredentialsMapper(mockCredentialsMapper);
    }

    public void testGetCredentialsSuccess() throws Exception {
        // Arrange
        Credentials credentials = new Credentials("user", "password");
        Credentials expected = new Credentials("user", "hashed_password");
        when(mockDatabaseConnector.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockCredentialsMapper.mapCredentialsFromResultSet(mockResultSet)).thenReturn(expected);

        // Act
        Credentials actual = sut.getCredentials(credentials);

        // Assert
        assertEquals(expected, actual);
        verify(mockDatabaseConnector, times(1)).disconnect();
    }

    public void testGetCredentialsThrowsSQLQueryException() throws Exception {
        // Arrange
        Credentials credentials = new Credentials("user", "password");
        when(mockDatabaseConnector.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());

        // Act & Assert
        assertThrows(SQLQueryException.class, () -> sut.getCredentials(credentials));

        verify(mockDatabaseConnector, times(1)).disconnect();
        verify(mockCredentialsMapper, never()).mapCredentialsFromResultSet(any(ResultSet.class));
    }
}
