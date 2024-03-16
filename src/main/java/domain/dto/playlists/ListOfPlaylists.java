package domain.dto.playlists;

import domain.dto.tracks.Track;

import java.util.ArrayList;

public class ListOfPlaylists {
    private ArrayList<Playlist> playlists;
    private int length;

    public ListOfPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
        length = calculateTotalLength();
    }

    public ListOfPlaylists() {}

    private int calculateTotalLength() {
        int totalLength = 0;
        for (Playlist playlist : playlists) {
            for (Track track : playlist.getTracks()) {
                totalLength += track.getDuration();
            }
        }
        return totalLength;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }
}
