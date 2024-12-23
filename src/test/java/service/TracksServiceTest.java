package service;

import datasource.dao.TracksDao;
import domain.dto.tracks.ListOfTracks;
import domain.dto.tracks.Track;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TracksServiceTest extends TestCase {
    private TracksService sut;
    private TracksDao mockTracksDao;

    public void setUp() {
        mockTracksDao = mock(TracksDao.class);
        sut = new TracksService();
        sut.setTracksDao(mockTracksDao);
    }

    public void testGetTracksFromPlaylist() {
        // Arrange
        String playlistId = "1";
        ListOfTracks expected = new ListOfTracks();
        when(mockTracksDao.getTracksFromPlaylist(playlistId)).thenReturn(expected);

        // Act
        ListOfTracks actual = sut.getTracksFromPlaylist(playlistId);

        // Assert
        assertEquals(expected, actual);
    }

    public void testGetAllTracks() {
        // Arrange
        ListOfTracks expected = new ListOfTracks();
        when(mockTracksDao.getAllTracks()).thenReturn(expected);

        // Act
        ListOfTracks actual = sut.getAllTracks();

        // Assert
        assertEquals(expected, actual);
    }

    public void testAddTrackToPlaylist() {
        // Arrange
        String playlistId = "1";
        Track track = new Track();
        ListOfTracks expected = new ListOfTracks();
        when(mockTracksDao.addTrackToPlaylist(playlistId, track)).thenReturn(expected);

        // Act
        ListOfTracks actual = sut.addTrackToPlaylist(playlistId, track);

        // Assert
        assertEquals(expected, actual);
    }

    public void testDeleteTrackFromPlaylist() {
        // Arrange
        String playlistId = "1";
        String trackId = "1";
        ListOfTracks expected = new ListOfTracks();
        when(mockTracksDao.deleteTrackFromPlaylist(playlistId, trackId)).thenReturn(expected);

        // Act
        ListOfTracks actual = sut.deleteTrackFromPlaylist(playlistId, trackId);

        // Assert
        assertEquals(expected, actual);
    }
}
