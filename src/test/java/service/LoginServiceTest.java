package service;

import datasource.dao.LoginDao;
import domain.dto.Credentials;
import domain.dto.User;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import service.exceptions.InvalidCredentialsException;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServiceTest extends TestCase {
    private LoginService sut;
    private LoginDao mockLoginDao;

    @Before
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

    public void testLoginFailure() {
        // Arrange
        Credentials credentials = new Credentials("username", "wrong_password");
        when(mockLoginDao.getCredentials(credentials)).thenReturn(new Credentials());

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> { sut.login(credentials); });
    }

}