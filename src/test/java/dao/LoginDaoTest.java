package dao;

import datasource.dao.LoginDao;
import datasource.datamappers.CredentialsMapper;
import datasource.exceptions.SQLQueryException;
import domain.dto.login.Credentials;
import junit.framework.TestCase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class LoginDaoTest extends TestCase {
    private LoginDao sut;
    private CredentialsMapper mockCredentialsMapper;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    public void setUp() {
        mockCredentialsMapper = mock(CredentialsMapper.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        sut = spy(new LoginDao());
        sut.setCredentialsMapper(mockCredentialsMapper);
    }

    public void testGetCredentialsSuccess() throws Exception {
        // Arrange
        Credentials credentials = new Credentials("user", "password");
        Credentials expected = new Credentials("user", "hashed_password");
        doReturn(mockPreparedStatement).when(sut).prepareStatement(anyString());
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockCredentialsMapper.mapCredentialsFromResultSet(mockResultSet)).thenReturn(expected);

        // Act
        Credentials actual = sut.getCredentials(credentials);

        // Assert
        assertEquals(expected, actual);
        verify(sut, times(1)).disconnect();
    }

    public void testGetCredentialsThrowsSQLQueryException() throws Exception {
        // Arrange
        Credentials credentials = new Credentials("user", "password");
        doReturn(mockPreparedStatement).when(sut).prepareStatement(anyString());
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());

        // Act & Assert
        assertThrows(SQLQueryException.class, () -> sut.getCredentials(credentials));

        verify(sut, times(1)).disconnect();
        verify(mockCredentialsMapper, never()).mapCredentialsFromResultSet(any(ResultSet.class));
    }
}
