package parser;

import Constants.GPMCommandCodes;
import model.TREXpacket;
import service.PayloadLengthResolver;

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
        int payloadLength =
                PayloadLengthResolver
                        .getLength(
                                p.commandCode
                        );

        p.data =
                new byte[payloadLength];

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