package nl.han.oose.dea.mitchell.dao;

import nl.han.oose.dea.mitchell.datasource.dao.JDBCTracksDao;
import nl.han.oose.dea.mitchell.datasource.datamappers.TrackMapper;
import nl.han.oose.dea.mitchell.datasource.exceptions.SQLQueryException;
import nl.han.oose.dea.mitchell.datasource.interfaces.IDatabaseConnector;
import nl.han.oose.dea.mitchell.domain.dto.tracks.ListOfTracks;
import nl.han.oose.dea.mitchell.domain.dto.tracks.Track;
import junit.framework.TestCase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class JDBCTracksDaoTest extends TestCase {
    private JDBCTracksDao sut;
    private IDatabaseConnector mockDatabaseConnector;
    private TrackMapper mockTrackMapper;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    public void setUp() {
        mockDatabaseConnector = mock(IDatabaseConnector.class);
        mockTrackMapper = mock(TrackMapper.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        sut = spy(new JDBCTracksDao());
        sut.setDatabaseConnector(mockDatabaseConnector);
        sut.setTrackMapper(mockTrackMapper);
    }

    public void testGetTracksFromPlaylistSuccess() throws Exception {
        // Arrange
        String playlistId = "1";
        ListOfTracks expected = new ListOfTracks();
        when(mockDatabaseConnector.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockTrackMapper.mapTracksFromResultSet(mockResultSet)).thenReturn(expected);
        doNothing().when(mockDatabaseConnector).disconnect();

        // Act
        ListOfTracks actual = sut.getTracksFromPlaylist(playlistId);

        // Assert
        assertEquals(expected, actual);
        verify(mockDatabaseConnector, times(1)).disconnect();
    }

    public void testGetAllAvailableTracksSuccess() throws Exception {
        // Arrange
        ListOfTracks expected = new ListOfTracks();
        when(mockDatabaseConnector.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockTrackMapper.mapTracksFromResultSet(mockResultSet)).thenReturn(expected);
        doNothing().when(mockDatabaseConnector).disconnect();

        // Act
        ListOfTracks actual = sut.getAllAvailableTracks(1);

        // Assert
        assertEquals(expected, actual);
        verify(mockDatabaseConnector, times(1)).disconnect();
    }

    public void testAddTrackToPlaylistSuccess() throws Exception {
        // Arrange
        String playlistId = "1";
        Track track = new Track();
        track.setId(1);
        ListOfTracks expected = new ListOfTracks();
        when(mockDatabaseConnector.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.execute()).thenReturn(true);
        doReturn(expected).when(sut).getTracksFromPlaylist(playlistId);
        doNothing().when(mockDatabaseConnector).disconnect();

        // Act
        ListOfTracks actual = sut.addTrackToPlaylist(playlistId, track);

        // Assert
        assertEquals(expected, actual);
        verify(mockDatabaseConnector, times(1)).disconnect();
    }

    public void testDeleteTrackFromPlaylistSuccess() throws Exception {
        // Arrange
        String playlistId = "1";
        String trackId = "1";
        ListOfTracks expected = new ListOfTracks();
        when(mockDatabaseConnector.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.execute()).thenReturn(true);
        doReturn(expected).when(sut).getTracksFromPlaylist(playlistId);
        doNothing().when(mockDatabaseConnector).disconnect();

        // Act
        ListOfTracks actual = sut.deleteTrackFromPlaylist(playlistId, trackId);

        // Assert
        assertEquals(expected, actual);
        verify(mockDatabaseConnector, times(1)).disconnect();
    }

    public void testTrackGetRequestThrowsSQLQueryException() throws Exception {
        // Arrange
        when(mockDatabaseConnector.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
        doNothing().when(mockDatabaseConnector).disconnect();

        // Act & Assert
        assertThrows(SQLQueryException.class, () -> sut.getAllAvailableTracks(1));

        verify(mockDatabaseConnector, times(1)).disconnect();
    }
}
