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

    private boolean evaluatePlaylistOwnership(String owner, String token) {
        return owner.equals(token);
    }
}
