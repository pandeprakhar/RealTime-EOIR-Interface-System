package model;

public class TREXpacket {

    public byte header;

    public short sequenceNumber;

    public byte deviceId;

    public byte deviceNo;

    public byte length;

    public byte commandType;

    public short commandCode;

    public byte[] data;

    public byte checksum;

    public short stopBytes;
}
