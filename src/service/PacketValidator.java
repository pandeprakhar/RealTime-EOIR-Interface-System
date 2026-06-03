package service;

import model.TREXpacket;

import static service.ChecksumService.calculate;

public class PacketValidator {

    public static boolean validate(
            TREXpacket p
    )
    {
        if(p.header != (byte)0xE1)
        {
            System.out.println(
                    "Invalid Header"
            );

            return false;
        }

        if(p.stopBytes != (short)0xFFFE)
        {
            System.out.println(
                    "Invalid Stop Bytes"
            );

            return false;
        }
        if(
                !SequenceTracker.validate(
                        p.sequenceNumber
                )
        )
        {
            return false;
        }

        return true;
    }

    //not enough info about which bytes participate in checksum
//    public static boolean validate(TREXpacket p)
//    {
//        if(!ChecksumService.verify(
//                p,
//                calculate(...)
//    ))
//        {
//            return false;
//        }
//
//        return true;
//    }
}