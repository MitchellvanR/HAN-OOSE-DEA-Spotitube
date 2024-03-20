package datasource.dao;

import datasource.exceptions.SQLQueryException;
import domain.dto.playlists.ListOfPlaylists;
import domain.dto.playlists.Playlist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistsDao extends Dao {

    public ListOfPlaylists getAllPlaylists(String token) {
        ListOfPlaylists listOfPlaylists = new ListOfPlaylists();
        ArrayList<Playlist> playlists = new ArrayList<>();
        try (ResultSet resultSet = prepareStatement("SELECT * FROM playlist").executeQuery()) {
            while (resultSet.next()) {
                playlists.add(
                        new Playlist(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                evaluatePlaylistOwnership(resultSet.getString("owner"), token),
                                new ArrayList<>()
                        )
                );
            }
            listOfPlaylists.setPlaylists(playlists);
            return listOfPlaylists;
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    public ListOfPlaylists deletePlaylist(String token, String id) {
        try {
            prepareStatement("DELETE FROM playlist WHERE id='" + id + "' AND owner='" + token + "'").execute();
            return getAllPlaylists(token);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    public ListOfPlaylists addPlaylist(String token, String owner, String playlistName) {
        try {
            prepareStatement("INSERT INTO playlist VALUES ('" + playlistName + "', '" + owner + "')").execute();
            return getAllPlaylists(token);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    public ListOfPlaylists editPlaylist(String token, String id, Playlist playlist) {
        try {
            prepareStatement("UPDATE playlist SET name = '" + playlist.getName() + "' WHERE id='" + id + "' AND owner='" + token + "'").execute();
            return getAllPlaylists(token);
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    private boolean evaluatePlaylistOwnership(String owner, String token) {
        return owner.equals(token);
    }
}
