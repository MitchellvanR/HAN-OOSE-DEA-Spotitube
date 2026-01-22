package nl.han.oose.dea.mitchell.datasource.dao;

import nl.han.oose.dea.mitchell.datasource.datamappers.TrackMapper;
import nl.han.oose.dea.mitchell.datasource.exceptions.SQLQueryException;
import nl.han.oose.dea.mitchell.datasource.interfaces.IDatabaseConnector;
import nl.han.oose.dea.mitchell.datasource.interfaces.ITracksDao;
import nl.han.oose.dea.mitchell.datasource.util.SQLString;
import nl.han.oose.dea.mitchell.domain.dto.tracks.ListOfTracks;
import nl.han.oose.dea.mitchell.domain.dto.tracks.Track;
import jakarta.inject.Inject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTracksDao implements ITracksDao {
    private TrackMapper trackMapper;
    private IDatabaseConnector databaseConnector;

    @Override
    public ListOfTracks getTracksFromPlaylist(String id) {
        return trackGetRequest(String.format(SQLString.GET_TRACKS_FROM_PLAYLIST.label, id));
    }

    @Override
    public ListOfTracks getAllAvailableTracks(int playlistId) {
        return trackGetRequest(String.format(SQLString.GET_ALL_AVAILABLE_TRACKS_FROM_PLAYLIST.label, playlistId));
    }

    @Override
    public ListOfTracks getAllTracksInPlaylists() { return trackGetRequest(SQLString.GET_ALL_TRACKS_IN_PLAYLISTS.label); }

    @Override
    public ListOfTracks addTrackToPlaylist(String id, Track track) {
        try {
            this.databaseConnector.prepareStatement(String.format(SQLString.ADD_TRACK_TO_PLAYLIST.label, Integer.parseInt(id), track.getId())).execute();
            return getTracksFromPlaylist(id);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            this.databaseConnector.disconnect();
        }
    }

    @Override
    public ListOfTracks deleteTrackFromPlaylist(String playlistId, String trackId) {
        try {
            this.databaseConnector.prepareStatement(String.format(SQLString.DELETE_TRACK_FROM_PLAYLIST.label, playlistId, trackId)).execute();
            return getTracksFromPlaylist(playlistId);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            this.databaseConnector.disconnect();
        }
    }

    private ListOfTracks trackGetRequest(String sqlString) {
        try (ResultSet resultSet = this.databaseConnector.prepareStatement(sqlString).executeQuery()) {
            return trackMapper.mapTracksFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            this.databaseConnector.disconnect();
        }
    }

    @Inject
    public void setTrackMapper(TrackMapper trackMapper) {
        this.trackMapper = trackMapper;
    }

    @Inject
    public void setDatabaseConnector(IDatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }
}
