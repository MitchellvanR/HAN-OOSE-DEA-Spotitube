package nl.han.oose.dea.mitchell.datasource.dao;

import nl.han.oose.dea.mitchell.datasource.datamappers.PlaylistMapper;
import nl.han.oose.dea.mitchell.datasource.exceptions.SQLQueryException;
import nl.han.oose.dea.mitchell.datasource.util.SQLString;
import nl.han.oose.dea.mitchell.domain.dto.playlists.ListOfPlaylists;
import nl.han.oose.dea.mitchell.domain.dto.playlists.Playlist;
import jakarta.inject.Inject;

import java.sql.ResultSet;
import java.sql.SQLException;

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
