package de.due.ldsa.db.model;

import de.due.ldsa.db.DbException;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * Author: Romina (scrobart)
 *
 */

//We turned Location into an interface and had LocationImpl and OrganisationPlace implement it, to get around the
//limitation (the lack of inheritance) of the Cassandra driver.
public interface Location extends SocialNetworkContent, Serializable {
    //------------------------------------------------------------------------------------------------------------------
    //Getters and setters
    //------------------------------------------------------------------------------------------------------------------
    long getId();

    void setId(long id);

    String getCity();

    void setCity(String city);

    String getCountry();

    void setCountry(String country);

    double getPositionLatidue();

    void setPositionLatidue(double positionLatidue);

    double getPositionLongitude();

    void setPositionLongitude(double positionLongitude);

    String getName();

    void setName(String name);

    int getSocialNetworkId();

    void setSocialNetworkId(int socialNetworkId);

    void setContentTimestamp(OffsetDateTime contentTimestamp);

    void setCrawlingTimestamp(OffsetDateTime crawlingTimestamp);

    long getIsInId();

    void setIsInId(long isInId);

    //------------------------------------------------------------------------------------------------------------------
    //Complex methods
    //------------------------------------------------------------------------------------------------------------------
    int getTimesUsed() throws DbException;

    OffsetDateTime getContentTimestamp() throws DbException;

    OffsetDateTime getCrawlingTimestamp() throws DbException;

    SocialNetwork getSourceNetwork() throws DbException;

    void setContentMeta(OffsetDateTime content, OffsetDateTime crawling, SocialNetwork sn) throws DbException;

    Position getPosition();

    void setPosition(Position p);

}
