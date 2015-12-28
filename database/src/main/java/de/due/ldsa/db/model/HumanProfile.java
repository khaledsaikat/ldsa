package de.due.ldsa.db.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 *
 */
@Table(keyspace = "ldsa", name = "humanProfiles")
public class HumanProfile extends Profile
{
    /*This needs to be put right here, because Datastax' Cassandra mapper does not support inheritance.
      If you need access to these fields use the getters and setters from the upper classes.*/
    @Column(name = "snId")
    int socialNetworkId;
    @Column(name = "contentTimestamp")
    OffsetDateTime contentTimestamp;
    @Column(name = "crawlingTimestamp")
    OffsetDateTime crawlingTimestamp;

    @Column(name = "sex")
    Sex sex;
    @Column(name = "birthday")
    LocalDate birthday;
    Relationship relationship;

    public int getAge()
    {
        Date temp = new Date();
        LocalDate today = temp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (int)ChronoUnit.YEARS.between(today,birthday);
    }

    public void setRelationship(Relationship relationship)
    {
        this.relationship = relationship;
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
