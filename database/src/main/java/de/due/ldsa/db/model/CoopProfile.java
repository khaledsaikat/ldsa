package de.due.ldsa.db.model;

import com.datastax.driver.mapping.annotations.Column;
import de.due.ldsa.db.DbException;

import java.time.LocalDate;
import java.time.OffsetDateTime;

/**
 *
 */
public class CoopProfile extends Profile
{
    /*This needs to be put right here, because Datastax' Cassandra mapper does not support inheritance.
      If you need access to these fields use the getters and setters from the upper classes.*/
    @Column(name = "snId")
    int socialNetworkId;
    @Column(name = "contentTimestamp")
    OffsetDateTime contentTimestamp;
    @Column(name = "crawlingTimestamp")
    OffsetDateTime crawlingTimestamp;

    LocalDate dateFounded;

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

    public int countInteraction(Profile p)
        throws DbException
    {
        throw new DbException("not yet implemented.");
    }

    public double countAverageInteractionPerFeed(Profile p)
            throws DbException
    {
        throw new DbException("not yet implemented.");
    }

    public double getAverageInteractionPerFeed()
        throws DbException
    {
        throw new DbException("not yet implemented.");
    }

    public double getAverageOfActionsPerDay()
        throws DbException
    {
        throw new DbException("not yet implemented.");
    }
}
