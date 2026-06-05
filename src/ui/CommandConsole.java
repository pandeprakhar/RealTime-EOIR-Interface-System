package ui;

import builder.GPMBuilder;
import model.TREXpacket;
import network.HostSystem;

import java.util.Scanner;

public class CommandConsole {

    public static void main(String[] args)
            throws Exception {

        Scanner sc =
                new Scanner(System.in);

        while(true)
        {
            System.out.println(
                    "\n===== TREX GPM Command Console ====="
            );

            System.out.println(
                    "1. Reference"
            );

            System.out.println(
                    "2. Azimuth Elevation"
            );

            System.out.println(
                    "3. Offset"
            );

            System.out.println(
                    "4. Bearing"
            );

            System.out.println(
                    "5. Add Landmark"
            );

            System.out.println(
                    "6. PTU"
            );

            System.out.println(
                    "7. Point"
            );

            System.out.println(
                    "0. Exit"
            );

            System.out.print(
                    "Enter Choice: "
            );

            int choice =
                    sc.nextInt();

            if(choice == 0)
            {
                System.out.println(
                        "Exiting..."
                );
                break;
            }

            TREXpacket p;

            if(choice == 1)
            {
                p =
                        GPMBuilder.buildReference(
                                (byte)16
                        );
            }
            else if(choice == 2)
            {
                p =
                        GPMBuilder.buildAzimuthElevation(
                                9000000,
                                1250000
                        );
            }
            else if(choice == 3)
            {
                p =
                        GPMBuilder.buildOffset(
                                1000,
                                500
                        );
            }
            else if(choice == 4)
            {
                p =
                        GPMBuilder.buildBearing(
                                (byte)255,
                                9000000,
                                1200000,
                                (short)1000
                        );
            }
            else if(choice == 5)
            {
                p =
                        GPMBuilder.buildLandmark(
                                28500000,
                                (byte)0,
                                77000000,
                                (byte)0,
                                (short)500
                        );
            }
            else if(choice == 6)
            {
                p =
                        GPMBuilder.buildPTU(
                                (byte)0
                        );
            }
            else if(choice == 7)
            {
                p =
                        GPMBuilder.buildPoint(
                                (byte)5
                        );
            }
            else
            {
                System.out.println(
                        "Invalid Choice"
                );
                continue;
            }

            HostSystem.sendPacket(
                    p
            );
        }

        sc.close();
    }
}