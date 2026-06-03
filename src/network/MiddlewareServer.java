package network;

import logger.Packetlogger;
import model.TREXpacket;
import service.PacketValidator;

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
            if(
                    !PacketValidator
                            .validate(p)
            )
            {
                Packetlogger.log(
                        "ERROR",
                        "Invalid Packet"
                );

                continue;
            }

            Packetlogger.log(
                    "RX",
                    "TREX packet received from EOIR"
            );
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
            Packetlogger.log(
                    "RX",
                    "Packet received from EOIR"
            );

            DatagramPacket cmd =
                    new DatagramPacket(
                            new byte[1024],
                            1024
                    );

            internalSocket.receive(
                    cmd
            );
            Packetlogger.log(
                    "RX",
                    "ACK received from Internal System"
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
            Packetlogger.log(
                    "TX",
                    "ACK forwarded to EOIR"
            );
        }
    }
}