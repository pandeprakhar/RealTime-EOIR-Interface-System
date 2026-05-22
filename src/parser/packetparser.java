package parser;

import model.TREXpacket;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class packetparser {

    public static TREXpacket parse(
            byte[] data
    )
    {
        ByteBuffer bb =
                ByteBuffer.wrap(data);

        TREXpacket p =
                new TREXpacket();

        p.header =
                bb.get();

        p.sequenceNumber =
                bb.getShort();

        p.deviceId =
                bb.get();

        p.deviceNo =
                bb.get();

        p.length =
                bb.get();

        p.commandType =
                bb.get();

        p.commandCode =
                bb.getShort();

        // data length = packet length - commandcode bytes etc.
        // temporarily assuming single byte payload

        p.data =
                new byte[1];

        bb.get(
                p.data
        );

        p.checksum =
                bb.get();

        p.stopBytes =
                bb.getShort();

        return p;
    }
}