package network;

import Constants.GPMCommandCodes;
import logger.Packetlogger;
import model.TREXpacket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

import static parser.packetparser.parse;

public class EOIRSimulator {

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
            if(p.commandCode == GPMCommandCodes.OFFSET)
            {
                ByteBuffer bb =
                        ByteBuffer.wrap(
                                p.data
                        );

                int azOffset =
                        bb.getInt();

                int elOffset =
                        bb.getInt();

                System.out.println(
                        "Offset Received"
                );

                System.out.println(
                        "Azimuth Offset = "
                                + azOffset
                );

                System.out.println(
                        "Elevation Offset = "
                                + elOffset
                );
            }
            if(p.commandCode == GPMCommandCodes.BEARING)
            {
                ByteBuffer bb =
                        ByteBuffer.wrap(p.data);

                byte rff =
                        bb.get();

                int az =
                        bb.getInt();

                int el =
                        bb.getInt();

                short dist =
                        bb.getShort();

                System.out.println("Bearing Received");
                System.out.println("RFF = " + rff);
                System.out.println("Azimuth = " + az);
                System.out.println("Elevation = " + el);
                System.out.println("Distance = " + dist);
            }
            if(p.commandCode == GPMCommandCodes.LANDMARK)
            {
                ByteBuffer bb =
                        ByteBuffer.wrap(p.data);

                int lat =
                        bb.getInt();

                byte latRef =
                        bb.get();

                int lon =
                        bb.getInt();

                byte lonRef =
                        bb.get();

                short alt =
                        bb.getShort();

                System.out.println("Landmark Received");
                System.out.println("Latitude = " + lat);
                System.out.println("Latitude Ref = " + latRef);
                System.out.println("Longitude = " + lon);
                System.out.println("Longitude Ref = " + lonRef);
                System.out.println("Altitude = " + alt);
            }
            if(p.commandCode == GPMCommandCodes.PTU)
            {
                byte action =
                        p.data[0];

                System.out.println(
                        "PTU Command Received"
                );

                if(action == 0)
                {
                    System.out.println(
                            "Add Landmark"
                    );
                }
                else if(action == 1)
                {
                    System.out.println(
                            "Erase Landmark"
                    );
                }
            }
            if(p.commandCode ==
                    GPMCommandCodes.POINT)
            {
                byte point =
                        p.data[0];

                System.out.println(
                        "Point Command Received"
                );

                System.out.println(
                        "Point Number = "
                                + point
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