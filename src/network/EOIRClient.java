package network;

import builder.GPMBuilder;
import model.TREXpacket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static serializer.packetreverseparser.serialize;

public class EOIRClient {

    public static void main(String[] args) throws Exception
    {
        DatagramSocket ds = new DatagramSocket();
        while(true)
        {
            TREXpacket p = GPMBuilder.buildReference((byte)16);
            byte[] data = serialize(p);
            DatagramPacket dp =
                    new DatagramPacket(
                            data,
                            data.length,
                            InetAddress.getLocalHost(),
                            5000
                    );
            ds.send(dp);
            System.out.println(
                    "GPM Reference sent"
            );

            Thread.sleep(
                    1000
            );
        }
    }
}