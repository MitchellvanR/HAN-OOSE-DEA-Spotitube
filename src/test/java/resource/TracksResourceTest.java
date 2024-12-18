package resource;

import domain.dto.tracks.ListOfTracks;
import jakarta.ws.rs.core.Response;
import junit.framework.TestCase;
import service.TracksService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TracksResourceTest extends TestCase {
    private TracksResource sut;
    private TracksService mockTracksService;
    private String token;

    public void setUp() {
        mockTracksService = mock(TracksService.class);
        sut = new TracksResource();
        sut.setTracksService(mockTracksService);
        token = "1234-1234-1234";
    }

    public void testGetAllTracks() {
        // Arrange
        ListOfTracks expected = new ListOfTracks();
        when(mockTracksService.getAllTracks()).thenReturn(expected);

        // Act
        Response response = sut.getAllTracks(token);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(expected, response.getEntity());
    }
}
