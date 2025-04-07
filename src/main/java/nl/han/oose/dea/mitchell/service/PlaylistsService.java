package nl.han.oose.dea.mitchell.service;

import nl.han.oose.dea.mitchell.datasource.dao.PlaylistsDao;
import nl.han.oose.dea.mitchell.domain.dto.playlists.ListOfPlaylists;
import nl.han.oose.dea.mitchell.domain.dto.playlists.Playlist;
import jakarta.inject.Inject;

public class PlaylistsService {
    private PlaylistsDao playlistsDao;

    public ListOfPlaylists getAllPlaylists(int userid) {
        return playlistsDao.getAllPlaylists(userid);
    }

    public ListOfPlaylists deletePlaylist(int userid, String id) {
        return playlistsDao.deletePlaylist(userid, id);
    }

    public ListOfPlaylists addPlaylist(int userid, Playlist playlist) {
        return playlistsDao.addPlaylist(userid, playlist.getName());
    }

    public ListOfPlaylists editPlaylist(int userid, String id, Playlist playlist) {
        return playlistsDao.editPlaylist(userid, id, playlist);
    }

    @Inject
    public void setPlaylistsDao(PlaylistsDao playlistsDao) {
        this.playlistsDao = playlistsDao;
    }
}
