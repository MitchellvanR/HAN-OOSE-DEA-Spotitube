package datasource.dao;

import datasource.exceptions.InvalidValueException;
import datasource.exceptions.SQLQueryException;
import domain.dto.playlists.ListOfPlaylists;
import domain.dto.playlists.Playlist;
import domain.dto.tracks.ListOfTracks;
import domain.dto.tracks.Track;

import java.sql.Date;
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

    public ListOfTracks getTracksFromPlaylist(String token, String id) {
        ListOfTracks listOfTracks = new ListOfTracks();
        ArrayList<Track> tracks = new ArrayList<>();
        try (ResultSet resultSet = prepareStatement("SELECT * FROM track JOIN track_in_playlist ON track.id = track_in_playlist.trackId WHERE track_in_playlist.playlistId = '" + id + "'").executeQuery()) {
            while (resultSet.next()) {
                System.out.println("Resultset obtained. Mapping data now");
                tracks.add(new Track(
                        resultSet.getString("title"),
                        resultSet.getString("performer"),
                        resultSet.getInt("duration"),
                        resultSet.getString("album"),
                        resultSet.getInt("playcount"),
                        convertDateToString(resultSet.getDate("publicationDate")),
                        resultSet.getString("trackDescription"),
                        convertStringToBoolean(resultSet.getString("offlineAvailable"))
                ));
            }
            listOfTracks.setTracks(tracks);
            return listOfTracks;
        } catch (SQLException e) {
            throw new SQLQueryException();
        } finally {
            disconnect();
        }
    }

    private boolean evaluatePlaylistOwnership(String owner, String token) {
        return owner.equals(token);
    }

    private boolean convertStringToBoolean(String booleanStringValue) {
        if (booleanStringValue.equals("false")) return false;
        if (booleanStringValue.equals("true")) return true;
        throw new InvalidValueException();
    }

    private String convertDateToString(Date date) {
        if (date != null) return date.toString();
        return null;
    }
}
