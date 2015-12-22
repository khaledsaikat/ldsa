package de.due.ldsa.db.model;

import de.due.ldsa.db.DbException;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 *
 */
public interface SocialNetworkContent {



    OffsetDateTime getContentTimestamp();

    OffsetDateTime getCrawlingTimestamp();

    SocialNetwork getSourceNetwork();

    void setContentMeta(OffsetDateTime contentTs, OffsetDateTime crawlingTs, SocialNetwork sn)
            throws DbException;

    void changeTimezones(ZoneOffset zo)
                    throws DbException;
}
