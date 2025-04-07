package nl.han.oose.dea.mitchell.domain.dto.playlists;

import nl.han.oose.dea.mitchell.domain.dto.tracks.ListOfTracks;
import nl.han.oose.dea.mitchell.domain.dto.tracks.Track;

import java.util.ArrayList;

public class ListOfPlaylists {
    private ArrayList<Playlist> playlists;
    private int length;

    public ListOfPlaylists() {
        length = 0;
    }

    public ListOfPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
        length = 0;
    }

    public void calculateTotalLength(ListOfTracks tracks) {
        if (playlists == null || playlists.isEmpty()) length = 0;
        int totalLength = 0;
        for (Track track : tracks.getTracks()) {
            totalLength += track.getDuration();
        }
        length = totalLength;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }
}
