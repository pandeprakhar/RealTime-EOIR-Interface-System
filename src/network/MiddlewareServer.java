package network;

import model.TrackData;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import service.TrackManager;

import static parser.packetparser.parse;
import static serializer.packetreverseparser.serialize;

public class MiddlewareServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(5000);
        DatagramSocket ds2 = new DatagramSocket(7000);
        TrackManager tm = new TrackManager();
        while (true) {
            DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
            ds.receive(dp);//recieving from the client
            byte[] data = dp.getData();
            TrackData td = parse(data);
            tm.updateTrack(td);
//            tm.printAll();
//            td.range+=10;
            byte[] forwarddata = serialize(td);
            InetAddress ia = InetAddress.getLocalHost();
            DatagramPacket forwardpacket = new DatagramPacket(forwarddata, forwarddata.length, ia, 6000);
            ds.send(forwardpacket);

            DatagramPacket commandpacket = new DatagramPacket(new byte[1024], 1024);
            ds2.receive(commandpacket);
            byte[] data2 = commandpacket.getData();
            DatagramPacket eoirCommandPacket =
                    new DatagramPacket(
                            data2,
                            commandpacket.getLength(),
                            InetAddress.getLocalHost(),
                            8000
                    );

            ds2.send(
                    eoirCommandPacket
            );

        }
    }
}