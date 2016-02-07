package de.due.ldsa.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * Author: Romina (scrobart)
 *
 */
public class ContentMeta implements Serializable
{
    private OffsetDateTime contentTimestamp;
    private OffsetDateTime crawlingTimestamp;
    private int sourceNetworkId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentMeta that = (ContentMeta) o;

        if (sourceNetworkId != that.sourceNetworkId) return false;
        if (contentTimestamp != null ? !contentTimestamp.equals(that.contentTimestamp) : that.contentTimestamp != null)
            return false;
        if (!(crawlingTimestamp != null ? !crawlingTimestamp.equals(that.crawlingTimestamp) : that.crawlingTimestamp != null))
            return true;
        else return false;

    }

    @Override
    public int hashCode() {
        int result = contentTimestamp != null ? contentTimestamp.hashCode() : 0;
        result = 31 * result + (crawlingTimestamp != null ? crawlingTimestamp.hashCode() : 0);
        result = 31 * result + sourceNetworkId;
        return result;
    }

    public OffsetDateTime getContentTimestamp() {
        return contentTimestamp;
    }

    public void setContentTimestamp(OffsetDateTime contentTimestamp) {
        this.contentTimestamp = contentTimestamp;
    }

    public OffsetDateTime getCrawlingTimestamp() {
        return crawlingTimestamp;
    }

    public void setCrawlingTimestamp(OffsetDateTime crawlingTimestamp) {
        this.crawlingTimestamp = crawlingTimestamp;
    }

    public int getSourceNetworkId() {
        return sourceNetworkId;
    }

    public void setSourceNetworkId(int sourceNetworkId) {
        this.sourceNetworkId = sourceNetworkId;
    }
}
