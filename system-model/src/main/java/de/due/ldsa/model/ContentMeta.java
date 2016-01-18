package de.due.ldsa.model;

import java.time.OffsetDateTime;

import com.google.gson.Gson;

/**
 *
 */
public class ContentMeta {
	OffsetDateTime contentTimestamp;
	OffsetDateTime crawlingTimestamp;
	SocialNetwork sourceNetwork;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ContentMeta that = (ContentMeta) o;

		if (contentTimestamp != null ? !contentTimestamp.equals(that.contentTimestamp) : that.contentTimestamp != null)
			return false;
		if (crawlingTimestamp != null ? !crawlingTimestamp.equals(that.crawlingTimestamp)
				: that.crawlingTimestamp != null)
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

	public String getJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
