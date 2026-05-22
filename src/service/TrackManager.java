//package service;
//import model.TREXpacket;
//import java.util.*;
//
//public class TrackManager {
//    private Map<Integer, TREXpacket> tracks= new HashMap<>();
//    public void updateTrack(TREXpacket td){
//        tracks.put(td.trackId,td);
//    }
//    public TREXpacket getTrack(int trackId){
//        return tracks.get(trackId);
//    }
//    public void printAll() {
//
//        for (TREXpacket td : tracks.values()) {
//            System.out.println(
//                    "Track ID: " +
//                            td.trackId
//            );
//
//            System.out.println(
//                    "Range: " +
//                            td.range
//            );
//
//            System.out.println(
//                    "Azimuth: " +
//                            td.azimuth
//            );
//
//            System.out.println(
//                    "--------------"
//            );
//        }
//    }
//}
