package ui;

import builder.GPMBuilder;
import model.TREXpacket;
import network.EOIRClient;

import java.util.Scanner;

public class CommandConsole {

    public static void main(String[] args)
            throws Exception {

        Scanner sc =
                new Scanner(System.in);

        while(true)
        {
            System.out.println(
                    "1. Reference"
            );

            System.out.println(
                    "2. Azimuth Elevation"
            );

            int choice =
                    sc.nextInt();

            TREXpacket p;

            if(choice == 1)
            {
                p =
                        GPMBuilder
                                .buildReference(
                                        (byte)16
                                );
            }
            else
            {
                p =
                        GPMBuilder
                                .buildAzimuthElevation(
                                        9000000,
                                        1250000
                                );
            }

            EOIRClient.sendPacket(
                    p
            );
        }
    }
}