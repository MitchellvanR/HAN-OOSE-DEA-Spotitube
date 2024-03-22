package service;

import datasource.dao.TracksDao;
import domain.dto.tracks.ListOfTracks;
import domain.dto.tracks.Track;
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

    @Inject
    public void setTracksDao(TracksDao tracksDao) {
        this.tracksDao = tracksDao;
    }
}
