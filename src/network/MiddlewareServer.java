package network;

import model.TREXpacket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static parser.packetparser.parse;
import static serializer.packetreverseparser.serialize;

public class MiddlewareServer {

    public static void main(String[] args)
            throws Exception {

        DatagramSocket eoirSocket =
                new DatagramSocket(5000);

        DatagramSocket internalSocket =
                new DatagramSocket(7000);

        while(true)
        {
            DatagramPacket dp =
                    new DatagramPacket(
                            new byte[1024],
                            1024
                    );

            eoirSocket.receive(dp);

            byte[] data =
                    dp.getData();

            TREXpacket p =
                    parse(data);

            System.out.println(
                    "TREX packet received"
            );

            byte[] forwardData =
                    serialize(p);

            DatagramPacket forward =
                    new DatagramPacket(
                            forwardData,
                            forwardData.length,
                            InetAddress.getLocalHost(),
                            6000
                    );

            eoirSocket.send(
                    forward
            );

            DatagramPacket cmd =
                    new DatagramPacket(
                            new byte[1024],
                            1024
                    );

            internalSocket.receive(
                    cmd
            );

            DatagramPacket returnPacket =
                    new DatagramPacket(
                            cmd.getData(),
                            cmd.getLength(),
                            InetAddress.getLocalHost(),
                            8000
                    );

            internalSocket.send(
                    returnPacket
            );
        }
    }
}