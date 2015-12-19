package de.due.ldsa.db.model;

import de.due.ldsa.db.DbException;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 *
 */
public class SocialNetworkContentImpl implements SocialNetworkContent {
    public ContentMeta metaInfos;

    @Override
    public OffsetDateTime getContentTimestamp()
    {
        return metaInfos.contentTimestamp;
    }

    @Override
    public OffsetDateTime getCrawlingTimestamp()
    {
        return metaInfos.crawlingTimestamp;
    }

    @Override
    public SocialNetwork getSourceNetwork()
    {
        return metaInfos.sourceNetwork;
    }

    @Override
    public void setContentMeta(OffsetDateTime contentTs, OffsetDateTime crawlingTs, SocialNetwork sn)
            throws DbException
    {
        throw new DbException("not yet implemented");
    }

    @Override
    public void changeTimezones(ZoneOffset zo)
            throws DbException
    {
        throw new DbException("not yet implemented");
    }
}
