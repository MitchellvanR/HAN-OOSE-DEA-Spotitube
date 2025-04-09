package nl.han.oose.dea.mitchell.resource;

import nl.han.oose.dea.mitchell.domain.dto.tracks.ListOfTracks;
import jakarta.ws.rs.core.Response;
import junit.framework.TestCase;
import nl.han.oose.dea.mitchell.service.AuthenticationService;
import nl.han.oose.dea.mitchell.service.TracksService;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

public class TracksResourceTest extends TestCase {
    private TracksResource sut;
    private TracksService mockTracksService;
    private AuthenticationService mockAuthenticationService;
    private String token;

    public void setUp() {
        mockTracksService = mock(TracksService.class);
        mockAuthenticationService = mock(AuthenticationService.class);
        sut = new TracksResource();
        sut.setTracksService(mockTracksService);
        sut.setAuthenticationService(mockAuthenticationService);
        token = "1234-1234-1234";
    }

    public void testGetAllTracks() {
        // Arrange
        ListOfTracks expected = new ListOfTracks();
        doReturn(true).when(mockAuthenticationService).validateToken(token);
        doReturn(1).when(mockAuthenticationService).getUserIdFromToken(token);
        when(mockTracksService.getAllAvailableTracks(anyInt())).thenReturn(expected);

        // Act
        Response response = sut.getAllAvailableTracks(1, token);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(expected, response.getEntity());
    }
}
