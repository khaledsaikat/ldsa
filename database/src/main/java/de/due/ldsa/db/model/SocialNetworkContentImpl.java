package de.due.ldsa.db.model;

import de.due.ldsa.db.DbException;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Author: Romina (scrobart)
 *
 */
public abstract class SocialNetworkContentImpl implements SocialNetworkContent, Serializable
{
    @Override
    public abstract OffsetDateTime getContentTimestamp() throws DbException;

    @Override
    public abstract OffsetDateTime getCrawlingTimestamp() throws DbException;

    @Override
    public abstract SocialNetwork getSourceNetwork() throws DbException;

    @Override
    public abstract void setContentMeta(OffsetDateTime content, OffsetDateTime crawling, SocialNetwork sn)
            throws DbException;

    @Override
    public abstract void setId(long id);

    @Override
    public abstract long getId();
}
