package de.due.ldsa.db.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import de.due.ldsa.db.DbException;

import java.time.OffsetDateTime;

/**
 *
 */
@Table(keyspace = "ldsa", name = "media")
public class Media extends SocialNetworkContentImpl
{
    @PartitionKey
    long id;
    @Column(name = "crawlingPath")
    String crawlingPath;
    @Column(name = "filename")
    String filename;
    @Column(name = "bytes")
    byte[] bytes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSize() {
        return bytes.length;
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

    public void setCrawlingPath(String crawlingPath) {
        this.crawlingPath = crawlingPath;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    /*This needs to be put right here, because Datastax' Cassandra mapper does not support inheritance.
          If you need access to these fields use the getters and setters from the upper classes.*/
    @Column(name = "snId")
    int socialNetworkId;
    @Column(name = "contentTimestamp")
    OffsetDateTime contentTimestamp;
    @Column(name = "crawlingTimestamp")
    OffsetDateTime crawlingTimestamp;

    @Override
    public OffsetDateTime getContentTimestamp() throws DbException {
        return contentTimestamp;
    }

    @Override
    public OffsetDateTime getCrawlingTimestamp() throws DbException {
        return crawlingTimestamp;
    }

    @Override
    public SocialNetwork getSourceNetwork() throws DbException {
        throw new DbException("not yet implemented.");
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

    public void setContentTimestamp(OffsetDateTime contentTimestamp) {
        this.contentTimestamp = contentTimestamp;
    }

    public void setCrawlingTimestamp(OffsetDateTime crawlingTimestamp) {
        this.crawlingTimestamp = crawlingTimestamp;
    }
}
