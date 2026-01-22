package nl.han.oose.dea.mitchell.datasource.interfaces;

import nl.han.oose.dea.mitchell.domain.dto.tracks.ListOfTracks;
import nl.han.oose.dea.mitchell.domain.dto.tracks.Track;

public interface ITracksDao {
    ListOfTracks getTracksFromPlaylist(String id);
    ListOfTracks getAllAvailableTracks(int playlistId);
    ListOfTracks getAllTracksInPlaylists();
    ListOfTracks addTrackToPlaylist(String id, Track track);
    ListOfTracks deleteTrackFromPlaylist(String playlistId, String trackId);
}
