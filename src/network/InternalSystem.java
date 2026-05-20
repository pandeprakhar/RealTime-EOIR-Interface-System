package network;
import model.TrackData;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import service.TrackManager;

import static parser.packetparser.parse;
import static serializer.packetreverseparser.serialize;
public class InternalSystem {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds= new DatagramSocket(6000);
        TrackManager tm = new TrackManager();
        while(true){
            DatagramPacket dp = new DatagramPacket(new byte[1024],1024);
            ds.receive(dp);
            byte[] bb=dp.getData();
            TrackData td=parse(bb);
            tm.updateTrack(td);
            tm.printAll();
            if(td.range < 20){
                String cmd = "LOCK_TARGET";
                byte[] b = cmd.getBytes();
                DatagramPacket dp1 = new DatagramPacket(b,b.length,InetAddress.getLocalHost(),7000);
                System.out.println(
                        "Sending LOCK_TARGET"
                );
                ds.send(dp1);


            }



        }

    }

}
