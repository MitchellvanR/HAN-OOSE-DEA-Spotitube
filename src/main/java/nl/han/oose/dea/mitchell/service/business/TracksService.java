package nl.han.oose.dea.mitchell.service.business;

import nl.han.oose.dea.mitchell.datasource.interfaces.ITracksDao;
import nl.han.oose.dea.mitchell.domain.dto.tracks.ListOfTracks;
import nl.han.oose.dea.mitchell.domain.dto.tracks.Track;
import jakarta.inject.Inject;

// Pass-through service that delegates tasks to dao classes.
public class TracksService {

    private ITracksDao tracksDao;

    public ListOfTracks getTracksFromPlaylist(String id) { return tracksDao.getTracksFromPlaylist(id); }

    public ListOfTracks getAllAvailableTracks(int playlistId) {
        return tracksDao.getAllAvailableTracks(playlistId);
    }

    public ListOfTracks addTrackToPlaylist(String id, Track track) {
        return tracksDao.addTrackToPlaylist(id, track);
    }

    public ListOfTracks deleteTrackFromPlaylist(String playlistId, String trackId) {
        return tracksDao.deleteTrackFromPlaylist(playlistId, trackId);
    }

    @Inject
    public void setTracksDao(ITracksDao tracksDao) {
        this.tracksDao = tracksDao;
    }
}
