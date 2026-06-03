package builder;

import model.TREXpacket;
import Constants.GPMCommandCodes;

import java.nio.ByteBuffer;

public class GPMBuilder {

    private static short sequence = 0;

    // 5.1 Reference
    public static TREXpacket buildReference(byte reference)
    {
        TREXpacket p = new TREXpacket();

        p.header = (byte) 0xE1;

        p.sequenceNumber = sequence;
        sequence++;

        p.deviceId = 0x13;

        p.deviceNo = 0;

        p.length = 4;

        p.commandType = 0x57; // Write

        p.commandCode = (short) GPMCommandCodes.REFERENCE;

        p.data = new byte[]{
                reference
        };

        p.checksum = 0; // calculated during serialization

        p.stopBytes = (short) 0xFFFE;

        return p;
    }

    // 5.2 Bearing (Azimuth + Elevation)
    public static TREXpacket buildAzimuthElevation(
            long azimuth,
            long elevation
    )
    {
        TREXpacket p = new TREXpacket();

        p.header = (byte) 0xE1;

        p.sequenceNumber = sequence;
        sequence++;

        p.deviceId = 0x13;

        p.deviceNo = 0;

        p.length = 8; // 4 bytes AZ + 4 bytes EL

        p.commandType = 0x57; // Write

        p.commandCode = (short) GPMCommandCodes.AZIMUTH_ELEVATION;

        ByteBuffer payload =
                ByteBuffer.allocate(8);

        payload.putInt(
                (int) azimuth
        );

        payload.putInt(
                (int) elevation
        );

        p.data = payload.array();

        p.checksum = 0; // calculated during serialization

        p.stopBytes = (short) 0xFFFE;

        return p;
    }
}