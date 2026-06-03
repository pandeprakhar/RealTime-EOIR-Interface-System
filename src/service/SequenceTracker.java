package service;

public class SequenceTracker {

    private static short expectedSequence = 0;

    public static boolean validate(
            short receivedSequence
    )
    {
        if(receivedSequence
                != expectedSequence)
        {
            System.out.println(
                    "Sequence mismatch"
            );

            expectedSequence =
                    (short)(receivedSequence + 1);

            return false;
        }

        expectedSequence++;

        return true;
    }
}