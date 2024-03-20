package datasource.dao;

import datasource.util.DatabaseProperties;
import domain.dto.playlists.ListOfPlaylists;
import domain.dto.playlists.Playlist;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlaylistsDaoTest extends TestCase {
    private PlaylistsDao sut;
    private DatabaseProperties mockDatabaseProperties;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    public void setUp() {
        sut = new PlaylistsDao();
        mockDatabaseProperties = mock(DatabaseProperties.class);
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        sut.setDatabaseProperties(mockDatabaseProperties);
        when(mockDatabaseProperties.getConnection()).thenReturn(mockConnection);
    }

    public void testGetAllPlaylists() throws SQLException {
        // Arrange
        when(sut.prepareStatement("SELECT * FROM playlist")).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Test Playlist");
        when(mockResultSet.getString("owner")).thenReturn("xxxx-xxxx-xxxx");

        // Act
        ListOfPlaylists result = sut.getAllPlaylists("xxxx-xxxx-xxxx");

        // Assert
        ArrayList<Playlist> playlists = result.getPlaylists();
        assertNotNull(playlists);
        assertEquals(1, playlists.size());

        Playlist playlist = playlists.get(0);
        assertEquals(1, playlist.getId());
        assertEquals("Test Playlist", playlist.getName());
        assertTrue(playlist.isOwner());
    }
}