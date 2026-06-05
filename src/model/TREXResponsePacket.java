package model;

public class TREXResponsePacket {

    public byte header;

    public short sequenceNumber;

    public byte deviceId;

    public byte deviceNo;

    public byte length;

    public byte commandType;

    public byte commandStatus;

    public short commandCode;

    public byte checksum;

    public short stopBytes;
}