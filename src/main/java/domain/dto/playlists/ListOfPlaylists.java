package domain.dto.playlists;

import domain.dto.tracks.Track;

import java.util.ArrayList;

public class ListOfPlaylists {
    private ArrayList<Playlist> playlists;
    private int length;

    public ListOfPlaylists() {
        length = calculateTotalLength();
    }

    public ListOfPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
        length = calculateTotalLength();
    }

    private int calculateTotalLength() {
        if (playlists == null || playlists.isEmpty()) return 0;
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
        length = calculateTotalLength();
    }

    public int getLength() {
        return length;
    }
}
