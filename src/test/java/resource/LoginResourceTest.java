package resource;

import domain.dto.Credentials;
import domain.dto.User;
import jakarta.ws.rs.core.Response;
import junit.framework.TestCase;
import org.junit.Before;
import service.LoginService;
import service.exceptions.InvalidCredentialsException;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class LoginResourceTest extends TestCase {
    private LoginResource sut;
    private LoginService mockLoginService;

    @Before
    public void setUp() {
        mockLoginService = mock(LoginService.class);
        sut = new LoginResource();
        sut.setLoginService(mockLoginService);
    }

    public void testSuccessfulLogin() {
        // Arrange
        Credentials credentials = new Credentials("username", "password");
        User user = new User("username", "1234-1234-1234");
        when(mockLoginService.login(credentials)).thenReturn(user);

        // Act
        Response response = sut.login(credentials);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(user, response.getEntity());
    }

    public void testInvalidUsernameLogin() {
        // Arrange
        Credentials credentials = new Credentials("invalid_username", "password");
        doThrow(InvalidCredentialsException.class).when(mockLoginService).login(credentials);

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> { sut.login(credentials); });
    }

    public void testInvalidPasswordLogin() {
        // Arrange
        Credentials credentials = new Credentials("username", "invalid_password");
        doThrow(InvalidCredentialsException.class).when(mockLoginService).login(credentials);

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> { sut.login(credentials); });
    }
}