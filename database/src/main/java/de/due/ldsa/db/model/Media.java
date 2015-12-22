package de.due.ldsa.db.model;

/**
 *
 */
public class Media
{
    long size;
    String crawlingPath;
    String filename;
    byte[] bytes;

    public long getSize() {
        return size;
    }

    public String getFilename() {
        return filename;
    }

    public String getCrawlingPath() {
        return crawlingPath;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
