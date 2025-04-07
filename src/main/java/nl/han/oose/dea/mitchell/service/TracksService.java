package nl.han.oose.dea.mitchell.service;

import nl.han.oose.dea.mitchell.datasource.dao.TracksDao;
import nl.han.oose.dea.mitchell.domain.dto.tracks.ListOfTracks;
import nl.han.oose.dea.mitchell.domain.dto.tracks.Track;
import jakarta.inject.Inject;

public class TracksService {

    private TracksDao tracksDao;

    public ListOfTracks getTracksFromPlaylist(String id) { return tracksDao.getTracksFromPlaylist(id); }

    public ListOfTracks getAllTracks() {
        return tracksDao.getAllTracks();
    }

    public ListOfTracks addTrackToPlaylist(String id, Track track) {
        return tracksDao.addTrackToPlaylist(id, track);
    }

    public ListOfTracks deleteTrackFromPlaylist(String playlistId, String trackId) {
        return tracksDao.deleteTrackFromPlaylist(playlistId, trackId);
    }

    @Inject
    public void setTracksDao(TracksDao tracksDao) {
        this.tracksDao = tracksDao;
    }
}
