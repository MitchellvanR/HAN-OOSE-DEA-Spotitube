package datasource.util;

public enum SQLString {
    GET_TRACKS_FROM_PLAYLIST("SELECT * FROM track JOIN track_in_playlist ON track.id = track_in_playlist.trackId WHERE track_in_playlist.playlistId = '%s'"),
    GET_ALL_TRACKS("SELECT * FROM track");

    public final String label;

    private SQLString(String sqlString) {
        this.label = sqlString;
    }
}
