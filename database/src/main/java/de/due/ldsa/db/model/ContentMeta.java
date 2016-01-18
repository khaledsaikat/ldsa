package de.due.ldsa.db.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * Author: Romina (scrobart)
 *
 */
public class ContentMeta implements Serializable
{
    OffsetDateTime contentTimestamp;
    OffsetDateTime crawlingTimestamp;
    SocialNetwork sourceNetwork;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentMeta that = (ContentMeta) o;

        if (contentTimestamp != null ? !contentTimestamp.equals(that.contentTimestamp) : that.contentTimestamp != null)
            return false;
        if (crawlingTimestamp != null ? !crawlingTimestamp.equals(that.crawlingTimestamp) : that.crawlingTimestamp != null)
            return false;
        return !(sourceNetwork != null ? !sourceNetwork.equals(that.sourceNetwork) : that.sourceNetwork != null);

    }

    @Override
    public int hashCode() {
        int result = contentTimestamp != null ? contentTimestamp.hashCode() : 0;
        result = 31 * result + (crawlingTimestamp != null ? crawlingTimestamp.hashCode() : 0);
        result = 31 * result + (sourceNetwork != null ? sourceNetwork.hashCode() : 0);
        return result;
    }
}
