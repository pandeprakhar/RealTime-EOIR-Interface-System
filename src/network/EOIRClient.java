package network;

import model.TREXpacket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static serializer.packetreverseparser.serialize;

public class EOIRClient {

    public static void sendPacket(
            TREXpacket p
    ) throws Exception
    {
        DatagramSocket ds =
                new DatagramSocket(8000);
        System.out.println(
                "Listening on port: "
                        + ds.getLocalPort()
        );
        byte[] data =
                serialize(p);

        DatagramPacket dp =
                new DatagramPacket(
                        data,
                        data.length,
                        InetAddress.getLocalHost(),
                        5000
                );

        ds.send(dp);

        System.out.println(
                "Packet Sent"
        );

        DatagramPacket response =
                new DatagramPacket(
                        new byte[1024],
                        1024
                );

        ds.receive(
                response
        );

        System.out.println(
                "Response : "
                        +
                        new String(
                                response.getData(),
                                0,
                                response.getLength()
                        )
        );
        ds.close();
    }
}