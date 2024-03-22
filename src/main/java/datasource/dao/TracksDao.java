package datasource.dao;

import datasource.datamappers.TrackMapper;
import datasource.exceptions.SQLQueryException;
import datasource.util.SQLString;
import domain.dto.tracks.ListOfTracks;
import jakarta.inject.Inject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TracksDao extends Dao {
    private TrackMapper trackMapper;

    public ListOfTracks getTracksFromPlaylist(String id) {
        return trackGetRequest(String.format(SQLString.GET_TRACKS_FROM_PLAYLIST.label, id));
    }

    public ListOfTracks getAllTracks() {
        return trackGetRequest(SQLString.GET_ALL_TRACKS.label);
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
