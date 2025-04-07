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
    private int userid;

    public void setUp() {
        mockPlaylistsDao = mock(PlaylistsDao.class);
        sut = new PlaylistsService();
        sut.setPlaylistsDao(mockPlaylistsDao);
        userid = 1;
    }

    public void testGetAllPlaylists() {
        // Arrange
        ListOfPlaylists expected = new ListOfPlaylists();
        when(mockPlaylistsDao.getAllPlaylists(userid)).thenReturn(expected);

        // Act
        ListOfPlaylists actual = sut.getAllPlaylists(userid);

        // Assert
        assertEquals(expected, actual);
    }

    public void testDeletePlaylist() {
        // Arrange
        String playlistId = "1";
        ListOfPlaylists expected = new ListOfPlaylists();
        when(mockPlaylistsDao.deletePlaylist(userid, playlistId)).thenReturn(expected);

        // Act
        ListOfPlaylists actual = sut.deletePlaylist(userid, playlistId);

        // Assert
        assertEquals(expected, actual);
    }

    public void testAddPlaylist() {
        // Arrange
        Playlist playlist = new Playlist();
        playlist.setOwner(true);
        playlist.setName("New Playlist");
        ListOfPlaylists expected = new ListOfPlaylists();
        when(mockPlaylistsDao.addPlaylist(userid, "New Playlist")).thenReturn(expected);

        // Act
        ListOfPlaylists actual = sut.addPlaylist(userid, playlist);

        // Assert
        assertEquals(expected, actual);
    }

    public void testAddPlaylistWhenNotOwner() {
        // Arrange
        Playlist playlist = new Playlist();
        playlist.setOwner(false);
        playlist.setName("Unowned Playlist");
        ListOfPlaylists expected = new ListOfPlaylists();
        when(mockPlaylistsDao.addPlaylist(userid, "Unowned Playlist")).thenReturn(expected);

        // Act
        ListOfPlaylists actual = sut.addPlaylist(userid, playlist);

        // Arrange
        assertEquals(expected, actual);
    }

    public void testEditPlaylist() {
        // Arrange
        String playlistId = "1";
        Playlist playlist = new Playlist();
        ListOfPlaylists expected = new ListOfPlaylists();
        when(mockPlaylistsDao.editPlaylist(userid, playlistId, playlist)).thenReturn(expected);

        // Act
        ListOfPlaylists result = sut.editPlaylist(userid, playlistId, playlist);

        // Assert
        assertEquals(expected, result);
    }
}