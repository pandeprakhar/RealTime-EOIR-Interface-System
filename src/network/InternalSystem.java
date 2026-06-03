package network;

import Constants.GPMCommandCodes;
import logger.Packetlogger;
import model.TREXpacket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

import static parser.packetparser.parse;

public class InternalSystem {

    public static void main(String[] args)
            throws Exception {

        DatagramSocket ds =
                new DatagramSocket(6000);

        while(true)
        {
            DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
            ds.receive(dp);
            TREXpacket p =
                    parse(dp.getData());
            if(p.commandCode == GPMCommandCodes.REFERENCE)
            {
                System.out.println(
                        "Reference = "
                                + p.data[0]
                );
            }
            if(p.commandCode == GPMCommandCodes.AZIMUTH_ELEVATION)
            {
                ByteBuffer bb =
                        ByteBuffer.wrap(
                                p.data
                        );

                int azimuth =
                        bb.getInt();

                int elevation =
                        bb.getInt();

                System.out.println(
                        "Azimuth Degrees = "
                                + azimuth / 100000.0
                );

                System.out.println(
                        "Elevation Degrees = "
                                + elevation / 100000.0
                );
            }
            Packetlogger.log(
                    "RX",
                    "Packet received from Middleware"
            );
            System.out.println("Packet received from middleware");
            Packetlogger.log(
                    "TX",
                    "ACK sent"
            );
            String cmd = "ACK";
            byte[] b =
                    cmd.getBytes();

            DatagramPacket response = new DatagramPacket(b, b.length, InetAddress.getLocalHost(), 7000);
            ds.send(response);
        }
    }
}