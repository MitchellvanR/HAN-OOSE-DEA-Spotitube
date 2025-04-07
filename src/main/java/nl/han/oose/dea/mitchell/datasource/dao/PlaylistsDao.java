package nl.han.oose.dea.mitchell.datasource.dao;

import nl.han.oose.dea.mitchell.datasource.datamappers.PlaylistMapper;
import nl.han.oose.dea.mitchell.datasource.exceptions.SQLQueryException;
import nl.han.oose.dea.mitchell.datasource.util.SQLString;
import nl.han.oose.dea.mitchell.domain.dto.playlists.ListOfPlaylists;
import nl.han.oose.dea.mitchell.domain.dto.playlists.Playlist;
import jakarta.inject.Inject;
import nl.han.oose.dea.mitchell.domain.dto.tracks.ListOfTracks;
import nl.han.oose.dea.mitchell.domain.dto.tracks.Track;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistsDao extends Dao {
    private TracksDao tracksDao;
    private PlaylistMapper playlistMapper;

    public ListOfPlaylists getAllPlaylists(int userid) {
        ListOfTracks tracks = tracksDao.getAllTracksInPlaylists();
        return playlistGetRequest(SQLString.GET_ALL_PLAYLISTS.label, userid, tracks);
    }

    public ListOfPlaylists deletePlaylist(int userid, String id) {
        try {
            prepareStatement(String.format(SQLString.DELETE_PLAYLIST.label, id, userid)).execute();
            return getAllPlaylists(userid);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    public boolean playlistHasTracks(String id) {
        try (ResultSet resultSet = prepareStatement(String.format(SQLString.CHECK_IF_PLAYLIST_HAS_TRACKS.label, id)).executeQuery()) {
            return resultSet.next();
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    public void emptyPlaylist(String id) {
        try {
            prepareStatement(String.format(SQLString.EMPTY_PLAYLIST.label, id)).execute();
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    public ListOfPlaylists addPlaylist(int userid, String playlistName) {
        try {
            prepareStatement(String.format(SQLString.ADD_PLAYLIST.label, playlistName, userid)).execute();
            return getAllPlaylists(userid);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    public ListOfPlaylists editPlaylist(int userid, String id, Playlist playlist) {
        try {
            prepareStatement(String.format(SQLString.UPDATE_PLAYLIST.label, playlist.getName(), id, userid)).execute();
            return getAllPlaylists(userid);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    private ListOfPlaylists playlistGetRequest(String sqlString, int userid, ListOfTracks tracks) {
        try (ResultSet resultSet = prepareStatement(sqlString).executeQuery()) {
            return playlistMapper.mapPlaylistsFromResultSet(resultSet, userid, tracks);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    @Inject
    public void setTracksDao(TracksDao tracksDao) { this.tracksDao = tracksDao; }

    @Inject
    public void setPlaylistMapper(PlaylistMapper playlistMapper) {
        this.playlistMapper = playlistMapper;
    }
}
