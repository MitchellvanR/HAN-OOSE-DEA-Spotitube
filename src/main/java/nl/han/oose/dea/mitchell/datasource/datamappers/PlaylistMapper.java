package nl.han.oose.dea.mitchell.datasource.datamappers;

import nl.han.oose.dea.mitchell.domain.dto.playlists.ListOfPlaylists;
import nl.han.oose.dea.mitchell.domain.dto.playlists.Playlist;
import nl.han.oose.dea.mitchell.domain.dto.tracks.ListOfTracks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistMapper {
    public ListOfPlaylists mapPlaylistsFromResultSet(ResultSet resultSet, String token, ListOfTracks tracks) throws SQLException {
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
        listOfPlaylists.calculateTotalLength(tracks);
        return listOfPlaylists;
    }

    private boolean evaluatePlaylistOwnership(String owner, String token) {
        return owner.equals(token);
    }
}
