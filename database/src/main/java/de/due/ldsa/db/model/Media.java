package de.due.ldsa.db.model;

/**
 *
 */
public class Media
{
    long size;
    String crawlingPath;
    //Der Typ "Filetyp" fehlt im Modell
    //Filetyp filetyp;
    byte[] bytes;

    public long getSize() {
        return size;
    }

    //public Filetyp getFiletyp() {
    //    return filetyp;
    //}

    public String getCrawlingPath() {
        return crawlingPath;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
