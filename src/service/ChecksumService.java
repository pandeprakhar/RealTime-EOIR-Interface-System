package service;

import model.TREXpacket;

public class ChecksumService {

    public static byte calculate(byte[] data)
    {
        byte checksum=0;

        for(byte b:data)
        {
            checksum += b;
        }

        return checksum;
    }
    public static boolean verify(
            TREXpacket p,
            byte calculatedChecksum
    )
    {
        return p.checksum ==
                calculatedChecksum;
    }

}