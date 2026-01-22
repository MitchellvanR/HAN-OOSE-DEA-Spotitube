package nl.han.oose.dea.mitchell.service.users;

import nl.han.oose.dea.mitchell.datasource.interfaces.ILoginDao;
import nl.han.oose.dea.mitchell.domain.dto.login.Credentials;
import nl.han.oose.dea.mitchell.domain.dto.login.User;
import junit.framework.TestCase;
import nl.han.oose.dea.mitchell.service.exceptions.InvalidCredentialsException;
import nl.han.oose.dea.mitchell.service.users.LocalLoginService;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class LoginServiceTest extends TestCase {
    private LocalLoginService sut;
    private ILoginDao mockLoginDao;

    public void setUp() {
        mockLoginDao = mock(ILoginDao.class);
        sut = spy(new LocalLoginService());
        sut.setLoginDao(mockLoginDao);
    }

    public void testSuccessfulLogin() {
        // Arrange
        Credentials credentials = new Credentials("username", "password");
        User user = new User("1234-1234-1234", "username");
        doReturn("1234-1234-1234").when(sut).generateToken();
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