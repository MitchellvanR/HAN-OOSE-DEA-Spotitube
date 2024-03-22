package datasource.util;

public enum SQLString {
    GET_CREDENTIALS("SELECT * FROM users WHERE username='%s' AND password='%s'"),
    GET_ALL_PLAYLISTS("SELECT * FROM playlist"),
    DELETE_PLAYLIST("DELETE FROM playlist WHERE id='%s' AND owner='%s'"),
    ADD_PLAYLIST("INSERT INTO playlist VALUES ('%s', '%s')"),
    UPDATE_PLAYLIST("UPDATE playlist SET name = '%s' WHERE id='%s' AND owner='%s'"),
    GET_TRACKS_FROM_PLAYLIST("SELECT * FROM track JOIN track_in_playlist ON track.id = track_in_playlist.trackId WHERE track_in_playlist.playlistId = '%s'"),
    GET_ALL_TRACKS("SELECT * FROM track");

    public final String label;

    private SQLString(String sqlString) {
        this.label = sqlString;
    }
}
