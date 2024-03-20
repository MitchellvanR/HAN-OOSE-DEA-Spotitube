package service;

import datasource.dao.PlaylistsDao;
import domain.dto.playlists.ListOfPlaylists;
import jakarta.inject.Inject;

public class PlaylistsService {
    private PlaylistsDao playlistsDao;

    public ListOfPlaylists getAllPlaylists(String token) {
        return playlistsDao.getAllPlaylists(token);
    }

    public ListOfPlaylists deletePlaylist(String token, String id) {
        return playlistsDao.deletePlaylist(token, id);
    }

    @Inject
    public void setPlaylistsDao(PlaylistsDao playlistsDao) {
        this.playlistsDao = playlistsDao;
    }
}
