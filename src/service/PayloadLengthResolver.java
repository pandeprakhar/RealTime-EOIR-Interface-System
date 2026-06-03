package service;

import Constants.GPMCommandCodes;

public class PayloadLengthResolver {

    public static int getLength(
    short commandCode
    )
    {
        if(commandCode ==
            GPMCommandCodes.REFERENCE)
        {
            return 1;
        }

        if(commandCode ==
            GPMCommandCodes.AZIMUTH_ELEVATION)
        {
            return 8;
        }

        return 0;
    }
}