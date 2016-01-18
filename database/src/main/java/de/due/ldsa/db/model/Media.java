package de.due.ldsa.db.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.db.DbException;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Arrays;

/**
 * Author: Romina (scrobart)
 *
 */
@Table(keyspace = "ldsa", name = "media")
public class Media extends SocialNetworkContentImpl implements Serializable
{
    @PartitionKey
    long id;
    @Column(name = "crawlingPath")
    String crawlingPath;
    @Column(name = "filename")
    String filename;
    @Column(name = "bytes")
    byte[] bytes;
    /*This needs to be put right here, because Datastax' Cassandra mapper does not support inheritance.
          If you need access to these fields use the getters and setters from the upper classes.*/
    @Column(name = "snId")
    int socialNetworkId;
    @Column(name = "contentTimestamp")
    OffsetDateTime contentTimestamp;
    @Column(name = "crawlingTimestamp")
    OffsetDateTime crawlingTimestamp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSize() {
        return bytes.length;
    }

    public Filetyp getFiletype() {
        byte[] buf = new byte[64];
        for (int i = 0; i < Math.min(64, bytes.length); i++) {
            buf[i] = bytes[i];
        }

        if ((buf[0] == 0x42) && (buf[1] == 0x4d)) {
            return Filetyp.MicrosoftBMP;
        } else if ((buf[0] == 0x47) && (buf[1] == 0x49) && (buf[2] == 0x38)) {
            return Filetyp.GraphicsInterchangeFormat;
        } else if ((buf[0] == 0xff) && (buf[1] == 0xd8) && (buf[2] == 0xff)) {
            return Filetyp.JPEG;
        } else if ((buf[0] == 0x89) && (buf[1] == 0x50) && (buf[2] == 0x4e) && (buf[3] == 0x47)) {
            return Filetyp.PortableNetworkGraphic;
        } else if ((buf[31] == 0x77) && (buf[32] == 0x65) && (buf[33] == 0x62) && (buf[34] == 0x6d)) {
            return Filetyp.WebM;
        } else if ((buf[8] == 0x57) && (buf[9] == 0x41) && (buf[10] == 0x56) && (buf[11] == 0x45)) {
            return Filetyp.MicrosoftWAVE;
        } else if ((buf[0] == 0x00) && (buf[4] == 0x66) && (buf[5] == 0x74) && (buf[6] == 0x79)) {
            return Filetyp.ISO14496Part14;
        } else {
            return Filetyp.Unknown;
        }
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getCrawlingPath() {
        return crawlingPath;
    }

    public void setCrawlingPath(String crawlingPath) {
        this.crawlingPath = crawlingPath;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public OffsetDateTime getContentTimestamp() throws DbException {
        return contentTimestamp;
    }

    public void setContentTimestamp(OffsetDateTime contentTimestamp) {
        this.contentTimestamp = contentTimestamp;
    }

    @Override
    public OffsetDateTime getCrawlingTimestamp() throws DbException {
        return crawlingTimestamp;
    }

    public void setCrawlingTimestamp(OffsetDateTime crawlingTimestamp) {
        this.crawlingTimestamp = crawlingTimestamp;
    }

    @Override
    public SocialNetwork getSourceNetwork() throws DbException {
        return DatabaseImpl.getInstance().getSocialNetwork(socialNetworkId);
    }

    @Override
    public void setContentMeta(OffsetDateTime content, OffsetDateTime crawling, SocialNetwork sn) throws DbException {
        this.contentTimestamp = content;
        this.crawlingTimestamp = crawling;
        this.socialNetworkId = sn.getId();
    }

    public int getSocialNetworkId() {
        return socialNetworkId;
    }

    public void setSocialNetworkId(int socialNetworkId) {
        this.socialNetworkId = socialNetworkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Media media = (Media) o;

        if (id != media.id) return false;
        if (socialNetworkId != media.socialNetworkId) return false;
        if (crawlingPath != null ? !crawlingPath.equals(media.crawlingPath) : media.crawlingPath != null) return false;
        if (filename != null ? !filename.equals(media.filename) : media.filename != null) return false;
        if (!Arrays.equals(bytes, media.bytes)) return false;
        if (contentTimestamp != null ? !contentTimestamp.equals(media.contentTimestamp) : media.contentTimestamp != null)
            return false;
        return !(crawlingTimestamp != null ? !crawlingTimestamp.equals(media.crawlingTimestamp) : media.crawlingTimestamp != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (crawlingPath != null ? crawlingPath.hashCode() : 0);
        result = 31 * result + (filename != null ? filename.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(bytes);
        result = 31 * result + socialNetworkId;
        result = 31 * result + (contentTimestamp != null ? contentTimestamp.hashCode() : 0);
        result = 31 * result + (crawlingTimestamp != null ? crawlingTimestamp.hashCode() : 0);
        return result;
    }
}
