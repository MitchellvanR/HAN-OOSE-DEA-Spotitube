package dao;

import nl.han.oose.dea.mitchell.datasource.dao.PlaylistsDao;
import nl.han.oose.dea.mitchell.datasource.datamappers.PlaylistMapper;
import nl.han.oose.dea.mitchell.datasource.exceptions.SQLQueryException;
import nl.han.oose.dea.mitchell.domain.dto.playlists.ListOfPlaylists;
import nl.han.oose.dea.mitchell.domain.dto.playlists.Playlist;
import junit.framework.TestCase;
import nl.han.oose.dea.mitchell.domain.dto.tracks.ListOfTracks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class PlaylistsDaoTest extends TestCase {
    private PlaylistsDao sut;
    private PlaylistMapper mockPlaylistMapper;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private int userid;

    public void setUp() {
        mockPlaylistMapper = mock(PlaylistMapper.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        sut = spy(new PlaylistsDao());
        sut.setPlaylistMapper(mockPlaylistMapper);
        userid = 1;
    }

    public void testGetAllPlaylistsSuccess() throws Exception {
        // Arrange
        ListOfPlaylists expected = new ListOfPlaylists();
        ListOfTracks tracks = new ListOfTracks();
        doReturn(mockPreparedStatement).when(sut).prepareStatement(anyString());
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockPlaylistMapper.mapPlaylistsFromResultSet(mockResultSet, userid, tracks)).thenReturn(expected);

        // Act
        ListOfPlaylists actual = sut.getAllPlaylists(userid);

        // Assert
        assertEquals(expected, actual);
        verify(sut, times(1)).disconnect();
    }

    public void testDeletePlaylistSuccess() throws Exception {
        // Arrange
        String playlistId = "1";
        ListOfPlaylists expected = new ListOfPlaylists();
        doReturn(mockPreparedStatement).when(sut).prepareStatement(anyString());
        when(mockPreparedStatement.execute()).thenReturn(true);
        doReturn(expected).when(sut).getAllPlaylists(userid);

        // Act
        ListOfPlaylists actual = sut.deletePlaylist(userid, playlistId);

        // Assert
        assertEquals(expected, actual);
        verify(sut, times(1)).disconnect();
    }

    public void testAddPlaylistSuccess() throws Exception {
        // Arrange
        String playlistName = "New Playlist";
        ListOfPlaylists expected = new ListOfPlaylists();
        doReturn(mockPreparedStatement).when(sut).prepareStatement(anyString());
        when(mockPreparedStatement.execute()).thenReturn(true);
        when(sut.getAllPlaylists(userid)).thenReturn(expected);

        // Act
        ListOfPlaylists actual = sut.addPlaylist(userid, playlistName);

        // Assert
        assertEquals(expected, actual);
        verify(sut, times(1)).disconnect();
    }

    public void testEditPlaylistSuccess() throws Exception {
        // Arrange
        String playlistId = "1";
        Playlist playlist = new Playlist();
        playlist.setName("updated-name");
        ListOfPlaylists expected = new ListOfPlaylists();
        doReturn(mockPreparedStatement).when(sut).prepareStatement(anyString());
        when(mockPreparedStatement.execute()).thenReturn(true);
        when(sut.getAllPlaylists(userid)).thenReturn(expected);

        // Act
        ListOfPlaylists actual = sut.editPlaylist(userid, playlistId, playlist);

        // Assert
        assertEquals(expected, actual);
        verify(sut, times(1)).disconnect();
    }

    public void testPlaylistGetRequestThrowsSQLQueryException() throws Exception {
        // Arrange
        doReturn(mockPreparedStatement).when(sut).prepareStatement(anyString());
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());

        // Act & Assert
        assertThrows(SQLQueryException.class, () -> sut.getAllPlaylists(userid));

        verify(sut, times(1)).disconnect();
    }
}