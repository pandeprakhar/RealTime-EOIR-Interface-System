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
    public static TREXpacket buildOffset(
            int azOffset,
            int elOffset
    )
    {
        TREXpacket p = new TREXpacket();

        p.header = (byte)0xE1;
        p.sequenceNumber = sequence++;
        p.deviceId = 0x13;
        p.deviceNo = 0;
        p.length = 8;
        p.commandType = 0x57;
        p.commandCode =GPMCommandCodes.OFFSET;;

        ByteBuffer payload =
                ByteBuffer.allocate(8);

        payload.putInt(azOffset);
        payload.putInt(elOffset);

        p.data = payload.array();

        p.stopBytes = (short)0xFFFE;

        return p;
    }
    public static TREXpacket buildBearing(
            byte rff,
            int azimuth,
            int elevation,
            short distance
    )
    {
        TREXpacket p = new TREXpacket();

        p.header = (byte)0xE1;
        p.sequenceNumber = sequence++;
        p.deviceId = 0x13;
        p.deviceNo = 0;
        p.length = 11;
        p.commandType = 0x57;
        p.commandCode = GPMCommandCodes.BEARING;

        ByteBuffer payload =
                ByteBuffer.allocate(11);

        payload.put(rff);
        payload.putInt(azimuth);
        payload.putInt(elevation);
        payload.putShort(distance);

        p.data = payload.array();

        p.stopBytes = (short)0xFFFE;

        return p;
    }
    public static TREXpacket buildLandmark(
            int latitude,
            byte latRef,
            int longitude,
            byte lonRef,
            short altitude
    )
    {
        TREXpacket p = new TREXpacket();

        p.header = (byte)0xE1;
        p.sequenceNumber = sequence++;
        p.deviceId = 0x13;
        p.deviceNo = 0;
        p.length = 12;
        p.commandType = 0x57;
        p.commandCode = GPMCommandCodes.LANDMARK;

        ByteBuffer payload =
                ByteBuffer.allocate(12);

        payload.putInt(latitude);
        payload.put(latRef);
        payload.putInt(longitude);
        payload.put(lonRef);
        payload.putShort(altitude);

        p.data = payload.array();

        p.stopBytes = (short)0xFFFE;

        return p;
    }
    public static TREXpacket buildPTU(
            byte action
    )
    {
        TREXpacket p = new TREXpacket();

        p.header = (byte)0xE1;
        p.sequenceNumber = sequence++;
        p.deviceId = 0x13;
        p.deviceNo = 0;
        p.length = 1;
        p.commandType = 0x57;
        p.commandCode = GPMCommandCodes.PTU;

        p.data = new byte[]{
                action
        };

        p.stopBytes = (short)0xFFFE;

        return p;
    }
    public static TREXpacket buildPoint(
            byte point
    )
    {
        TREXpacket p = new TREXpacket();

        p.header = (byte)0xE1;
        p.sequenceNumber = sequence++;
        p.deviceId = 0x13;
        p.deviceNo = 0;
        p.length = 1;
        p.commandType = 0x57;
        p.commandCode = GPMCommandCodes.POINT;

        p.data = new byte[]{
                point
        };

        p.stopBytes = (short)0xFFFE;

        return p;
    }
}