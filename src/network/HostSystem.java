package network;

import model.TREXpacket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static serializer.packetreverseparser.serialize;

public class HostSystem {

    private static final DatagramSocket ds;

    static {
        try {
            ds = new DatagramSocket(8000);
            System.out.println("EOIR Client listening on port 8000");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendPacket(TREXpacket p) throws Exception {

        byte[] data = serialize(p);

        ds.send(
                new DatagramPacket(
                        data,
                        data.length,
                        InetAddress.getLocalHost(),
                        5000
                )
        );

        System.out.println("Packet Sent");

        DatagramPacket response =
                new DatagramPacket(
                        new byte[1024],
                        1024
                );

        ds.receive(response);

        System.out.println(
                "Response : " +
                        new String(
                                response.getData(),
                                0,
                                response.getLength()
                        )
        );
    }
}