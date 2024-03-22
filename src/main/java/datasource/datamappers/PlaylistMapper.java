package datasource.datamappers;

import domain.dto.playlists.ListOfPlaylists;
import domain.dto.playlists.Playlist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistMapper {
    public ListOfPlaylists mapPlaylistsFromResultSet(ResultSet resultSet, String token) throws SQLException {
        ListOfPlaylists listOfPlaylists = new ListOfPlaylists();
        ArrayList<Playlist> playlists = new ArrayList<>();
        while (resultSet.next()) {
            playlists.add(new Playlist(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                evaluatePlaylistOwnership(resultSet.getString("owner"), token),
                new ArrayList<>()
            ));
        }
        listOfPlaylists.setPlaylists(playlists);
        return listOfPlaylists;
    }

    private boolean evaluatePlaylistOwnership(String owner, String token) {
        return owner.equals(token);
    }
}
