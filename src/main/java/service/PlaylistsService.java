package service;

import datasource.dao.PlaylistsDao;
import domain.dto.playlists.ListOfPlaylists;
import domain.dto.playlists.Playlist;
import domain.dto.tracks.ListOfTracks;
import jakarta.inject.Inject;

public class PlaylistsService {
    private PlaylistsDao playlistsDao;

    public ListOfPlaylists getAllPlaylists(String token) {
        return playlistsDao.getAllPlaylists(token);
    }

    public ListOfPlaylists deletePlaylist(String token, String id) {
        return playlistsDao.deletePlaylist(token, id);
    }

    public ListOfPlaylists addPlaylist(String token, Playlist playlist) {
        String owner = playlist.isOwner() ? token : "unknown";
        return playlistsDao.addPlaylist(token, owner, playlist.getName());
    }

    public ListOfPlaylists editPlaylist(String token, String id, Playlist playlist) {
        return playlistsDao.editPlaylist(token, id, playlist);
    }

    public ListOfTracks getTracksFromPlaylist(String token, String id) {
        return playlistsDao.getTracksFromPlaylist(token, id);
    }

    @Inject
    public void setPlaylistsDao(PlaylistsDao playlistsDao) {
        this.playlistsDao = playlistsDao;
    }
}
