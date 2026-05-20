package service;
import model.TrackData;
import java.util.*;

public class TrackManager {
    private Map<Integer,TrackData> tracks= new HashMap<>();
    public void updateTrack(TrackData td){
        tracks.put(td.trackId,td);
    }
    public TrackData getTrack(int trackId){
        return tracks.get(trackId);
    }
    public void printAll() {

        for (TrackData td : tracks.values()) {
            System.out.println(
                    "Track ID: " +
                            td.trackId
            );

            System.out.println(
                    "Range: " +
                            td.range
            );

            System.out.println(
                    "Azimuth: " +
                            td.azimuth
            );

            System.out.println(
                    "--------------"
            );
        }
    }
}
