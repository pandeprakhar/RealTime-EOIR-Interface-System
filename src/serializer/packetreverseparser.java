package serializer;

import model.TREXpacket;

import java.nio.ByteBuffer;

public class packetreverseparser {

    public static byte[] serialize(TREXpacket p)
    {
        ByteBuffer bb=
                ByteBuffer.allocate(300);

        bb.put(
                p.header
        );

        bb.putShort(
                p.sequenceNumber
        );

        bb.put(
                p.deviceId
        );

        bb.put(
                p.deviceNo
        );

        bb.put(
                p.length
        );

        bb.put(
                p.commandType
        );

        bb.putShort(
                p.commandCode
        );

        bb.put(
                p.data
        );

        bb.put(
                p.checksum
        );

        bb.putShort(
                p.stopBytes
        );

        return bb.array();
    }
}