package nl.han.oose.dea.mitchell.resource.endpoints;

import nl.han.oose.dea.mitchell.domain.dto.tracks.ListOfTracks;
import jakarta.ws.rs.core.Response;
import junit.framework.TestCase;
import nl.han.oose.dea.mitchell.resource.endpoints.TracksResource;
import nl.han.oose.dea.mitchell.resource.util.RESTAuthenticator;
import nl.han.oose.dea.mitchell.service.users.LocalAuthenticationService;
import nl.han.oose.dea.mitchell.service.business.TracksService;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

public class TracksResourceTest extends TestCase {
    private RESTAuthenticator mockAuthenticator;
    private TracksResource sut;
    private TracksService mockTracksService;
    private String token;

    public void setUp() {
        mockTracksService = mock(TracksService.class);
        mockAuthenticator = mock(RESTAuthenticator.class);
        sut = new TracksResource();
        sut.setTracksService(mockTracksService);
        sut.setAuthenticator(mockAuthenticator);
        token = "1234-1234-1234";
    }

    public void testGetAllTracks() {
        // Arrange
        ListOfTracks expected = new ListOfTracks();
        doNothing().when(mockAuthenticator).validateUserLogin(token);
        doReturn(1).when(mockAuthenticator).getUserid();
        when(mockTracksService.getAllAvailableTracks(anyInt())).thenReturn(expected);

        // Act
        Response response = sut.getAllAvailableTracks(1, token);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(expected, response.getEntity());
    }
}
