package nl.han.oose.dea.mitchell.datasource.dao;

import nl.han.oose.dea.mitchell.datasource.datamappers.TrackMapper;
import nl.han.oose.dea.mitchell.datasource.exceptions.SQLQueryException;
import nl.han.oose.dea.mitchell.datasource.interfaces.ITracksDao;
import nl.han.oose.dea.mitchell.datasource.util.SQLString;
import nl.han.oose.dea.mitchell.domain.dto.tracks.ListOfTracks;
import nl.han.oose.dea.mitchell.domain.dto.tracks.Track;
import jakarta.inject.Inject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTracksDao extends Dao implements ITracksDao {
    private TrackMapper trackMapper;

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
            prepareStatement(String.format(SQLString.ADD_TRACK_TO_PLAYLIST.label, Integer.parseInt(id), track.getId())).execute();
            return getTracksFromPlaylist(id);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    @Override
    public ListOfTracks deleteTrackFromPlaylist(String playlistId, String trackId) {
        try {
            prepareStatement(String.format(SQLString.DELETE_TRACK_FROM_PLAYLIST.label, playlistId, trackId)).execute();
            return getTracksFromPlaylist(playlistId);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    private ListOfTracks trackGetRequest(String sqlString) {
        try (ResultSet resultSet = prepareStatement(sqlString).executeQuery()) {
            return trackMapper.mapTracksFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    @Inject
    public void setTrackMapper(TrackMapper trackMapper) {
        this.trackMapper = trackMapper;
    }
}
