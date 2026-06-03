package logger;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Packetlogger {

    public static void log(
            String direction,
            String message
    )
    {
        try
        {
            FileWriter fw =
                    new FileWriter(
                            "packetlog.txt",
                            true
                    );

            fw.write(
                    LocalDateTime.now()
                            + " | "
                            + direction
                            + " | "
                            + message
                            + "\n"
            );

            fw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}