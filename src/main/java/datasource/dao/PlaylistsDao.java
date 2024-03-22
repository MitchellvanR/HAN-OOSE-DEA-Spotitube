package datasource.dao;

import datasource.datamappers.PlaylistMapper;
import datasource.exceptions.InvalidValueException;
import datasource.exceptions.SQLQueryException;
import datasource.util.SQLString;
import domain.dto.playlists.ListOfPlaylists;
import domain.dto.playlists.Playlist;
import domain.dto.tracks.ListOfTracks;
import domain.dto.tracks.Track;
import jakarta.inject.Inject;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistsDao extends Dao {
    private PlaylistMapper playlistMapper;

    public ListOfPlaylists getAllPlaylists(String token) {
        return playlistGetRequest(SQLString.GET_ALL_PLAYLISTS.label, token);
    }

    public ListOfPlaylists deletePlaylist(String token, String id) {
        try {
            prepareStatement(String.format(SQLString.DELETE_PLAYLIST.label, id, token)).execute();
            return getAllPlaylists(token);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    public ListOfPlaylists addPlaylist(String token, String owner, String playlistName) {
        try {
            prepareStatement(String.format(SQLString.ADD_PLAYLIST.label, playlistName, owner)).execute();
            return getAllPlaylists(token);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    public ListOfPlaylists editPlaylist(String token, String id, Playlist playlist) {
        try {
            prepareStatement(String.format(SQLString.UPDATE_PLAYLIST.label, playlist.getName(), id, token)).execute();
            return getAllPlaylists(token);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    private ListOfPlaylists playlistGetRequest(String sqlString, String token) {
        try (ResultSet resultSet = prepareStatement(sqlString).executeQuery()) {
            return playlistMapper.mapPlaylistsFromResultSet(resultSet, token);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    @Inject
    public void setPlaylistMapper(PlaylistMapper playlistMapper) {
        this.playlistMapper = playlistMapper;
    }
}
