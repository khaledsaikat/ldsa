package de.due.ldsa.model;


import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import de.due.ldsa.exception.DbException;

/**
 *
 */
public interface SocialNetworkContent
{
    //We need to put these methods here, because Datastax' Mapping driver does not support inheritance.
    //If we would declare fields in an abstract class, they would neither be written nor read in the database.

    OffsetDateTime getContentTimestamp()
            throws DbException;

    OffsetDateTime getCrawlingTimestamp()
            throws DbException;

    SocialNetwork getSourceNetwork()
            throws DbException;

    void setContentMeta(OffsetDateTime content,OffsetDateTime crawling,SocialNetwork sn)
            throws DbException;

    void setId(long id);

    long getId();
}
