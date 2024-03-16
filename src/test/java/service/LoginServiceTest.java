package service;

import datasource.dao.LoginDao;
import domain.dto.login.Credentials;
import domain.dto.login.User;
import junit.framework.TestCase;
import service.exceptions.InvalidCredentialsException;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServiceTest extends TestCase {
    private LoginService sut;
    private LoginDao mockLoginDao;

    public void setUp() {
        mockLoginDao = mock(LoginDao.class);
        sut = new LoginService();
        sut.setLoginDao(mockLoginDao);
    }

    public void testSuccessfulLogin() {
        // Arrange
        Credentials credentials = new Credentials("username", "password");
        User user = new User("username", "1234-1234-1234");
        when(mockLoginDao.getCredentials(credentials)).thenReturn(credentials);

        // Act
        User result = sut.login(credentials);

        // Assert
        assertEquals(user.getUser(), result.getUser());
        assertEquals(user.getToken(), result.getToken());
    }

    public void testInvalidUsernameLogin() {
        // Arrange
        Credentials credentials = new Credentials("invalid_username", "password");
        when(mockLoginDao.getCredentials(credentials)).thenReturn(new Credentials());

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> { sut.login(credentials); });
    }

    public void testInvalidPasswordLogin() {
        // Arrange
        Credentials credentials = new Credentials("invalid_username", "password");
        when(mockLoginDao.getCredentials(credentials)).thenReturn(new Credentials());

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> { sut.login(credentials); });
    }
}