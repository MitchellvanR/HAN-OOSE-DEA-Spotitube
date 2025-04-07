package nl.han.oose.dea.mitchell.service;

import nl.han.oose.dea.mitchell.datasource.dao.PlaylistsDao;
import nl.han.oose.dea.mitchell.domain.dto.playlists.ListOfPlaylists;
import nl.han.oose.dea.mitchell.domain.dto.playlists.Playlist;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlaylistsServiceTest extends TestCase {
    private PlaylistsService sut;
    private PlaylistsDao mockPlaylistsDao;
    private String token;

    public void setUp() {
        mockPlaylistsDao = mock(PlaylistsDao.class);
        sut = new PlaylistsService();
        sut.setPlaylistsDao(mockPlaylistsDao);
        token = "1234-1234-1234";
    }

    public void testGetAllPlaylists() {
        // Arrange
        ListOfPlaylists expected = new ListOfPlaylists();
        when(mockPlaylistsDao.getAllPlaylists(token)).thenReturn(expected);

        // Act
        ListOfPlaylists actual = sut.getAllPlaylists(token);

        // Assert
        assertEquals(expected, actual);
    }

    public void testDeletePlaylist() {
        // Arrange
        String playlistId = "1";
        ListOfPlaylists expected = new ListOfPlaylists();
        when(mockPlaylistsDao.deletePlaylist(token, playlistId)).thenReturn(expected);

        // Act
        ListOfPlaylists actual = sut.deletePlaylist(token, playlistId);

        // Assert
        assertEquals(expected, actual);
    }

    public void testAddPlaylist() {
        // Arrange
        Playlist playlist = new Playlist();
        playlist.setOwner(true);
        playlist.setName("New Playlist");
        ListOfPlaylists expected = new ListOfPlaylists();
        when(mockPlaylistsDao.addPlaylist(token, token, "New Playlist")).thenReturn(expected);

        // Act
        ListOfPlaylists actual = sut.addPlaylist(token, playlist);

        // Assert
        assertEquals(expected, actual);
    }

    public void testAddPlaylistWhenNotOwner() {
        // Arrange
        Playlist playlist = new Playlist();
        playlist.setOwner(false);
        playlist.setName("Unowned Playlist");
        ListOfPlaylists expected = new ListOfPlaylists();
        when(mockPlaylistsDao.addPlaylist(token, "unknown", "Unowned Playlist")).thenReturn(expected);

        // Act
        ListOfPlaylists actual = sut.addPlaylist(token, playlist);

        // Arrange
        assertEquals(expected, actual);
    }

    public void testEditPlaylist() {
        // Arrange
        String playlistId = "1";
        Playlist playlist = new Playlist();
        ListOfPlaylists expected = new ListOfPlaylists();
        when(mockPlaylistsDao.editPlaylist(token, playlistId, playlist)).thenReturn(expected);

        // Act
        ListOfPlaylists result = sut.editPlaylist(token, playlistId, playlist);

        // Assert
        assertEquals(expected, result);
    }
}