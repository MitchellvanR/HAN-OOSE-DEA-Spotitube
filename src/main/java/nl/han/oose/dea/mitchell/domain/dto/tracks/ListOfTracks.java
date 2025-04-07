package nl.han.oose.dea.mitchell.domain.dto.tracks;

import java.util.List;

public class ListOfTracks {
    private List<Track> tracks;

    public ListOfTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public ListOfTracks() {}

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
