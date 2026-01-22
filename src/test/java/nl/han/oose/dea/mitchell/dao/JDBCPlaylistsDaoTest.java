package nl.han.oose.dea.mitchell.dao;

import nl.han.oose.dea.mitchell.datasource.dao.JDBCPlaylistsDao;
import nl.han.oose.dea.mitchell.datasource.datamappers.PlaylistMapper;
import nl.han.oose.dea.mitchell.datasource.exceptions.SQLQueryException;
import nl.han.oose.dea.mitchell.datasource.interfaces.IDatabaseConnector;
import nl.han.oose.dea.mitchell.datasource.interfaces.ITracksDao;
import nl.han.oose.dea.mitchell.domain.dto.playlists.ListOfPlaylists;
import nl.han.oose.dea.mitchell.domain.dto.playlists.Playlist;
import junit.framework.TestCase;
import nl.han.oose.dea.mitchell.domain.dto.tracks.ListOfTracks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class JDBCPlaylistsDaoTest extends TestCase {
    private JDBCPlaylistsDao sut;
    private PlaylistMapper mockPlaylistMapper;
    private IDatabaseConnector mockDatabaseConnector;
    private ITracksDao mockTracksDao;
    private ListOfTracks mockListOfTracks;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private int userid;

    public void setUp() {
        mockPlaylistMapper = mock(PlaylistMapper.class);
        mockDatabaseConnector = mock(IDatabaseConnector.class);
        mockTracksDao = mock(ITracksDao.class);
        mockListOfTracks = mock(ListOfTracks.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        sut = spy(new JDBCPlaylistsDao());
        sut.setDatabaseConnector(mockDatabaseConnector);
        sut.setPlaylistMapper(mockPlaylistMapper);
        sut.setTracksDao(mockTracksDao);
        userid = 1;
    }

    public void testGetAllPlaylistsSuccess() throws Exception {
        // Arrange
        ListOfPlaylists expected = new ListOfPlaylists();
        when(mockDatabaseConnector.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        doReturn(mockListOfTracks).when(mockTracksDao).getAllTracksInPlaylists();
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockPlaylistMapper.mapPlaylistsFromResultSet(mockResultSet, userid, mockListOfTracks)).thenReturn(expected);

        // Act
        ListOfPlaylists actual = sut.getAllPlaylists(userid);

        // Assert
        assertEquals(expected, actual);
        verify(mockDatabaseConnector, times(1)).disconnect();
    }

    public void testDeletePlaylistSuccess() throws Exception {
        // Arrange
        String playlistId = "1";
        ListOfPlaylists expected = new ListOfPlaylists();
        when(mockDatabaseConnector.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.execute()).thenReturn(true);
        doReturn(expected).when(sut).getAllPlaylists(userid);
        doNothing().when(mockDatabaseConnector).disconnect();

        // Act
        ListOfPlaylists actual = sut.deletePlaylist(userid, playlistId);

        // Assert
        assertEquals(expected, actual);
        verify(mockDatabaseConnector, times(1)).disconnect();
    }

    public void testAddPlaylistSuccess() throws Exception {
        // Arrange
        String playlistName = "New Playlist";
        ListOfPlaylists expected = new ListOfPlaylists();
        when(mockDatabaseConnector.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.execute()).thenReturn(true);
        doReturn(expected).when(sut).getAllPlaylists(userid);
        doNothing().when(mockDatabaseConnector).disconnect();

        // Act
        ListOfPlaylists actual = sut.addPlaylist(userid, playlistName);

        // Assert
        assertEquals(expected, actual);
        verify(mockDatabaseConnector, times(1)).disconnect();
    }

    public void testEditPlaylistSuccess() throws Exception {
        // Arrange
        String playlistId = "1";
        Playlist playlist = new Playlist();
        playlist.setName("updated-name");
        ListOfPlaylists expected = new ListOfPlaylists();
        when(mockDatabaseConnector.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.execute()).thenReturn(true);
        doReturn(expected).when(sut).getAllPlaylists(userid);
        doNothing().when(mockDatabaseConnector).disconnect();

        // Act
        ListOfPlaylists actual = sut.editPlaylist(userid, playlistId, playlist);

        // Assert
        assertEquals(expected, actual);
        verify(mockDatabaseConnector, times(1)).disconnect();
    }

    public void testPlaylistGetRequestThrowsSQLQueryException() throws Exception {
        // Arrange
        when(mockDatabaseConnector.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        doReturn(mockListOfTracks).when(mockTracksDao).getAllTracksInPlaylists();
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());

        // Act & Assert
        assertThrows(SQLQueryException.class, () -> sut.getAllPlaylists(userid));

        verify(mockDatabaseConnector, times(1)).disconnect();
    }
}