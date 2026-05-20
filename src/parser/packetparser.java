package parser;
import model.TrackData;

import java.io.IOException;
import java.nio.ByteBuffer;

public class packetparser {
    public static TrackData parse(byte[] data) throws IOException {
        ByteBuffer bb = ByteBuffer.wrap(data);
        TrackData trackdata = new TrackData();
        trackdata.trackId = bb.getInt();

        trackdata.range = bb.getDouble();

        trackdata.azimuth = bb.getDouble();

        trackdata.elevation = bb.getDouble();

        trackdata.speed = bb.getDouble();

        trackdata.heading = bb.getDouble();

        trackdata.confidence = bb.getInt();

        trackdata.status = bb.get();
        return trackdata;
    }
}
