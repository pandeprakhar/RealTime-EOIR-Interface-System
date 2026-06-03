package serializer;

import model.TREXpacket;
import service.ChecksumService;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class packetreverseparser {

    public static byte[] serialize(
            TREXpacket p
    )
    {
        ByteBuffer bb =
                ByteBuffer.allocate(300);

        bb.put(p.header);

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

        // get only written bytes
        byte[] packetData =
                Arrays.copyOf(
                        bb.array(),
                        bb.position()
                );

        p.checksum =
                ChecksumService.calculate(
                        packetData
                );

        bb.put(
                p.checksum
        );

        bb.putShort(
                p.stopBytes
        );

        return Arrays.copyOf(
                bb.array(),
                bb.position()
        );
    }
}