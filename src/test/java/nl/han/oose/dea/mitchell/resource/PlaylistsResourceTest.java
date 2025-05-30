package nl.han.oose.dea.mitchell.resource;

import nl.han.oose.dea.mitchell.domain.dto.playlists.ListOfPlaylists;
import nl.han.oose.dea.mitchell.domain.dto.playlists.Playlist;
import nl.han.oose.dea.mitchell.domain.dto.tracks.ListOfTracks;
import nl.han.oose.dea.mitchell.domain.dto.tracks.Track;
import jakarta.ws.rs.core.Response;
import junit.framework.TestCase;
import nl.han.oose.dea.mitchell.service.AuthenticationService;
import nl.han.oose.dea.mitchell.service.PlaylistsService;
import nl.han.oose.dea.mitchell.service.TracksService;

import static org.mockito.Mockito.*;

public class PlaylistsResourceTest extends TestCase {
    private PlaylistsResource sut;
    private PlaylistsService mockPlaylistsService;
    private AuthenticationService mockAuthenticationService;
    private TracksService mockTracksService;
    private String token;
    private int userid;

    public void setUp() {
        mockPlaylistsService = mock(PlaylistsService.class);
        mockAuthenticationService = mock(AuthenticationService.class);
        mockTracksService = mock(TracksService.class);
        token = "1234-1234-1234";
        userid = 1;
        sut = new PlaylistsResource();
        sut.setPlaylistsService(mockPlaylistsService);
        sut.setAuthenticationService(mockAuthenticationService);
        sut.setTracksService(mockTracksService);
    }

    public void testGetPlaylists() {
        // Arrange
        ListOfPlaylists expected = new ListOfPlaylists();
        doReturn(true).when(mockAuthenticationService).validateToken(token);
        doReturn(1).when(mockAuthenticationService).getUserIdFromToken(token);
        when(mockPlaylistsService.getAllPlaylists(userid)).thenReturn(expected);

        // Act
        Response response = sut.getPlaylists(token);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(expected, response.getEntity());
    }

    public void testDeletePlaylist() {
        // Arrange
        Playlist playlist = new Playlist();
        String playlistId = "1";
        ListOfPlaylists expected = new ListOfPlaylists();
        doReturn(true).when(mockAuthenticationService).validateToken(token);
        doReturn(1).when(mockAuthenticationService).getUserIdFromToken(token);
        when(mockPlaylistsService.deletePlaylist(userid, playlistId)).thenReturn(expected);

        // Act
        Response response = sut.deletePlaylist(token, playlistId);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(expected, response.getEntity());
    }

    public void testAddPlaylist() {
        // Arrange
        Playlist playlist = new Playlist();
        ListOfPlaylists expected = new ListOfPlaylists();
        doReturn(true).when(mockAuthenticationService).validateToken(token);
        doReturn(1).when(mockAuthenticationService).getUserIdFromToken(token);
        when(mockPlaylistsService.addPlaylist(userid, playlist)).thenReturn(expected);

        // Act
        Response response = sut.addPlaylist(token, playlist);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(expected, response.getEntity());
    }

    public void testEditPlaylist() {
        // Arrange
        String playlistId = "1";
        Playlist playlist = new Playlist();
        ListOfPlaylists expected = new ListOfPlaylists();
        doReturn(true).when(mockAuthenticationService).validateToken(token);
        doReturn(1).when(mockAuthenticationService).getUserIdFromToken(token);
        when(mockPlaylistsService.editPlaylist(userid, playlistId, playlist)).thenReturn(expected);

        // Act
        Response response = sut.editPlaylist(token, playlistId, playlist);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(expected, response.getEntity());
    }

    public void testGetTracksFromPlaylist() {
        // Arrange
        String playlistId = "1";
        ListOfTracks listOfTracks = new ListOfTracks();
        doReturn(true).when(mockAuthenticationService).validateToken(token);
        doReturn(1).when(mockAuthenticationService).getUserIdFromToken(token);
        when(mockTracksService.getTracksFromPlaylist(playlistId)).thenReturn(listOfTracks);

        // Act
        Response response = sut.getTracksFromPlaylist(token, playlistId);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(listOfTracks, response.getEntity());
    }

    public void testAddTrackToPlaylist() {
        // Arrange
        String playlistId = "1";
        Track track = new Track();
        ListOfTracks listOfTracks = new ListOfTracks();
        doReturn(true).when(mockAuthenticationService).validateToken(token);
        doReturn(1).when(mockAuthenticationService).getUserIdFromToken(token);
        when(mockTracksService.addTrackToPlaylist(playlistId, track)).thenReturn(listOfTracks);

        // Act
        Response response = sut.addTrackToPlaylist(token, playlistId, track);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(listOfTracks, response.getEntity());
    }

    public void testDeleteTrackFromPlaylist() {
        // Arrange
        String playlistId = "1";
        String trackId = "1";
        ListOfTracks listOfTracks = new ListOfTracks();
        doReturn(true).when(mockAuthenticationService).validateToken(token);
        doReturn(1).when(mockAuthenticationService).getUserIdFromToken(token);
        when(mockTracksService.deleteTrackFromPlaylist(playlistId, trackId)).thenReturn(listOfTracks);

        // Act
        Response response = sut.deleteTrackFromPlaylist(token, playlistId, trackId);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(listOfTracks, response.getEntity());
    }
}