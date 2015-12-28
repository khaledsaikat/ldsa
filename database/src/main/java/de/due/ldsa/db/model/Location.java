package de.due.ldsa.db.model;

import com.datastax.driver.mapping.annotations.Column;
import de.due.ldsa.db.DbException;

import java.time.OffsetDateTime;

/**
 *
 */
public class Location implements SocialNetworkContent
{
    /*This needs to be put right here, because Datastax' Cassandra mapper does not support inheritance.
      If you need access to these fields use the getters and setters from the upper classes.*/
    @Column(name = "snId")
    int socialNetworkId;
    @Column(name = "contentTimestamp")
    OffsetDateTime contentTimestamp;
    @Column(name = "crawlingTimestamp")
    OffsetDateTime crawlingTimestamp;

    public String name;
    public int timesUsed;
    public String city;
    public String country;
    public Position position;
    public Location isIn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimesUsed() {
        return timesUsed;
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

    public OffsetDateTime getContentTimestamp() throws DbException {
        throw new DbException("not yet implemented.");
    }

    public OffsetDateTime getCrawlingTimestamp() throws DbException {
        throw new DbException("not yet implemented.");
    }

    public SocialNetwork getSourceNetwork() throws DbException {
        throw new DbException("not yet implemented.");
    }

    public void setContentMeta(OffsetDateTime content, OffsetDateTime crawling, SocialNetwork sn) throws DbException {
        throw new DbException("not yet implemented.");
    }
}
