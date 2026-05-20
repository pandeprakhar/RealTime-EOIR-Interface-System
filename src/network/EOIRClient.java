package network;

import model.TrackData;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

import static parser.packetparser.parse;
import static serializer.packetreverseparser.serialize;

public class EOIRClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(8000);
        double range = 0.5;
        while (true) {
            TrackData td = new TrackData();
            td.trackId = 101;
            td.range =range;
            td.azimuth = 0.5;
            td.elevation = 15.2;
            td.speed = 82.4;
            td.heading = 240;
            td.confidence = 92;
            td.status = 1;

            byte[] data = serialize(td);
            InetAddress ia = InetAddress.getLocalHost();
            DatagramPacket dp = new DatagramPacket(data, data.length, ia, 5000);
            ds.send(dp);
            System.out.println("Packet sent");
            byte[] responsebuffer = new byte[1024];
            DatagramPacket rp1 = new DatagramPacket(responsebuffer, responsebuffer.length);//recieving response from the server
            ds.receive(rp1);
            String cmd =
                    new String(
                            rp1.getData(),
                            0,
                            rp1.getLength()
                    );
            System.out.println(
                    "Received Command: "
                            + cmd
            );
            range+=1;
            Thread.sleep(1000);
        }
    }
}