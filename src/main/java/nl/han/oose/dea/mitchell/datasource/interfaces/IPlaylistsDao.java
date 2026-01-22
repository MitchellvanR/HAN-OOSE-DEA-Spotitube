package nl.han.oose.dea.mitchell.datasource.interfaces;

import nl.han.oose.dea.mitchell.domain.dto.playlists.ListOfPlaylists;
import nl.han.oose.dea.mitchell.domain.dto.playlists.Playlist;
import nl.han.oose.dea.mitchell.domain.dto.tracks.ListOfTracks;

public interface IPlaylistsDao {
    ListOfPlaylists getAllPlaylists(int userid);
    ListOfPlaylists deletePlaylist(int userid, String id);
    boolean playlistHasTracks(String id);
    void emptyPlaylist(String id);
    ListOfPlaylists addPlaylist(int userid, String playlistName);
    ListOfPlaylists editPlaylist(int userid, String id, Playlist playlist);
}
