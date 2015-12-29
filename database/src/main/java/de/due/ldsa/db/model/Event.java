package de.due.ldsa.db.model;

import com.datastax.driver.mapping.annotations.Column;
import de.due.ldsa.db.DbException;

import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 *
 */
public class Event implements SocialNetworkContent
{
    /*This needs to be put right here, because Datastax' Cassandra mapper does not support inheritance.
      If you need access to these fields use the getters and setters from the upper classes.*/
    @Column(name = "snId")
    int socialNetworkId;
    @Column(name = "contentTimestamp")
    OffsetDateTime contentTimestamp;
    @Column(name = "crawlingTimestamp")
    OffsetDateTime crawlingTimestamp;
    @Column(name = "id")
    long id;

    public String name;
    public ArrayList<Profile> hosts;
    public Location location;
    public ArrayList<Profile> invited;
    public ArrayList<Profile> attending;
    public String eventText;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
