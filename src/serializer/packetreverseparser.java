package serializer;
import model.TrackData;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
public class packetreverseparser {
    public static byte[] serialize(TrackData trackdata) throws IOException {
        ByteBuffer bb= ByteBuffer.allocate(60);
        bb.putInt(trackdata.trackId);
        bb.putDouble(trackdata.range);
        bb.putDouble(trackdata.azimuth);
        bb.putDouble(trackdata.elevation);
        bb.putDouble(trackdata.speed);
        bb.putDouble(trackdata.heading);
        bb.putInt(trackdata.confidence);
        bb.put(trackdata.status);

        return bb.array();



    }

}
