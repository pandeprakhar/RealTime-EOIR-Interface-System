package network;

import model.TREXpacket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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

            System.out.println("Packet received from middleware");
            String cmd = "ACK";
            byte[] b =
                    cmd.getBytes();

            DatagramPacket response = new DatagramPacket(b, b.length, InetAddress.getLocalHost(), 7000);
            ds.send(response);
        }
    }
}