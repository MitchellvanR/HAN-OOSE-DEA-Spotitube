package nl.han.oose.dea.mitchell.datasource.datamappers;

import nl.han.oose.dea.mitchell.domain.dto.playlists.ListOfPlaylists;
import nl.han.oose.dea.mitchell.domain.dto.playlists.Playlist;
import nl.han.oose.dea.mitchell.domain.dto.tracks.ListOfTracks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistMapper {
    public ListOfPlaylists mapPlaylistsFromResultSet(ResultSet resultSet, int userid, ListOfTracks tracks) throws SQLException {
        ListOfPlaylists listOfPlaylists = new ListOfPlaylists();
        ArrayList<Playlist> playlists = new ArrayList<>();
        while (resultSet.next()) {
            playlists.add(new Playlist(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                evaluatePlaylistOwnership(resultSet.getInt("owner"), userid),
                new ArrayList<>()
            ));
        }
        listOfPlaylists.setPlaylists(playlists);
        listOfPlaylists.calculateTotalLength(tracks);
        return listOfPlaylists;
    }

    private boolean evaluatePlaylistOwnership(int owner, int userid) {
        return owner == userid;
    }
}
