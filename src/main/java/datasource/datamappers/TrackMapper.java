package datasource.datamappers;

import datasource.dao.TracksDao;
import datasource.exceptions.InvalidValueException;
import domain.dto.tracks.ListOfTracks;
import domain.dto.tracks.Track;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrackMapper {

    public ListOfTracks mapTracksFromResultSet(ResultSet resultSet) throws SQLException {
        ListOfTracks listOfTracks = new ListOfTracks();
        ArrayList<Track> tracks = new ArrayList<>();
        while (resultSet.next()) {
            tracks.add(new Track(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("performer"),
                    resultSet.getInt("duration"),
                    resultSet.getString("album"),
                    resultSet.getInt("playcount"),
                    convertDateToString(resultSet.getDate("publicationDate")),
                    resultSet.getString("trackDescription"),
                    convertStringToBoolean(resultSet.getString("offlineAvailable"))
            ));
        }
        listOfTracks.setTracks(tracks);
        return listOfTracks;
    }

    private boolean convertStringToBoolean(String booleanStringValue) {
        if (booleanStringValue.equals("false")) return false;
        if (booleanStringValue.equals("true")) return true;
        throw new InvalidValueException();
    }

    private String convertDateToString(Date date) {
        if (date != null) return date.toString();
        return null;
    }
}
