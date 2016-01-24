package de.due.ldsa.model;


import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import de.due.ldsa.exception.DbException;

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
    public abstract void setContentMeta(OffsetDateTime content, OffsetDateTime crawling, SocialNetwork sn)
            throws DbException;

    @Override
    public abstract void setId(long id);

    @Override
    public abstract long getId();

    @Override
    public abstract int getSocialNetworkId();

    @Override
    public ContentMeta getContentMeta() {
        ContentMeta result = new ContentMeta();
        result.setContentTimestamp(getContentTimestamp());
        result.setCrawlingTimestamp(getCrawlingTimestamp());
        result.setSourceNetworkId(getSocialNetworkId());
        return result;
    }

    @Override
    public void changeTimezones(ZoneOffset zo) {
        int snId = getSocialNetworkId();
        OffsetDateTime content = this.getContentTimestamp();
        OffsetDateTime crawling = this.getCrawlingTimestamp();
        // We are not sure if this needs withOffsetSameInstant or withOffsetSameLocal
        content = content.withOffsetSameInstant(zo);
        crawling = crawling.withOffsetSameInstant(zo);

        setContentMeta(content, crawling, new SocialNetwork(snId));
    }
}
