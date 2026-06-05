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
        if(commandCode == GPMCommandCodes.OFFSET)
        {
            return 8;
        }
        if(commandCode == GPMCommandCodes.BEARING)
        {
            return 11;
        }

        if(commandCode == GPMCommandCodes.LANDMARK)
        {
            return 12;
        }
        if(commandCode == GPMCommandCodes.PTU)
        {
            return 1;
        }
        if(commandCode ==
                GPMCommandCodes.POINT)
        {
            return 1;
        }
        return 0;
    }
}