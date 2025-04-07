package nl.han.oose.dea.mitchell.service;

import nl.han.oose.dea.mitchell.datasource.dao.PlaylistsDao;
import nl.han.oose.dea.mitchell.domain.dto.playlists.ListOfPlaylists;
import nl.han.oose.dea.mitchell.domain.dto.playlists.Playlist;
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
        return playlistsDao.addPlaylist(token, token, playlist.getName());
    }

    public ListOfPlaylists editPlaylist(String token, String id, Playlist playlist) {
        return playlistsDao.editPlaylist(token, id, playlist);
    }

    @Inject
    public void setPlaylistsDao(PlaylistsDao playlistsDao) {
        this.playlistsDao = playlistsDao;
    }
}
