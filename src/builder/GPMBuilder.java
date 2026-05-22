package builder;

import model.TREXpacket;

public class GPMBuilder {

    private static short sequence = 0;

    public static TREXpacket buildReference(byte reference)
    {
        TREXpacket p = new TREXpacket();

        p.header = (byte) 0xE1;

        p.sequenceNumber = sequence;
        sequence++;

        p.deviceId = 0x13;

        p.deviceNo = 0;

        p.length = 4;

        p.commandType = 0x57;

        p.commandCode = 1;

        p.data = new byte[]{
                reference
        };

        p.checksum = 0; // checksum logic later

        p.stopBytes = (short) 0xFFFE;

        return p;
    }
}