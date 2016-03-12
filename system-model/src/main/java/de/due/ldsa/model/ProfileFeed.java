package de.due.ldsa.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import de.due.ldsa.exception.ModelException;

import java.net.URL;
import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 * Author: Romina Romina Barth (scrobart), Janine Neffgen (sijaneff), Marc Henning (sdmahenn)
 *
 * If you need to serialize this, make sure your serializer honors transient
 * fields.
 */
@Table(keyspace = "ldsa", name = "profileFeeds")
public class ProfileFeed extends SocialNetworkContentImpl {
	/*
	 * This needs to be put right here, because Datastax' Cassandra mapper does
	 * not support inheritance. If you need access to these fields use the
	 * getters and setters from the upper classes.
	 */
	@Column(name = "snId")
	int socialNetworkId;
	@Column(name = "contentTimestamp")
	OffsetDateTime contentTimestamp;
	@Column(name = "crawlingTimestamp")
	OffsetDateTime crawlingTimestamp;

	@PartitionKey
	long id;
	@Column(name = "profileId")
	long profileId;
	@Column(name = "rawStoryText")
	String rawStoryText;
	@Column(name = "likerIds")
	ArrayList<Long> likerIds;
	@Column(name = "sharerIds")
	ArrayList<Long> sharerIds;
	@Column(name = "hashtags")
	ArrayList<String> hashtagNames;
	@Column(name = "links")
	ArrayList<URL> links;
	@Column(name = "locationId")
	long locationId;
	@Column(name = "mediaId")
	long mediaId;
	@Column(name = "taggedUserIds")
	ArrayList<Long> taggedUserIds;
	@Column(name = "commentIds")
	ArrayList<Long> commentIds;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	public String getRawStoryText() {
		return rawStoryText;
	}

	public void setRawStoryText(String rawStoryText) {
		this.rawStoryText = rawStoryText;
	}

	public ArrayList<Long> getLikerIds() {
		return likerIds;
	}

	public void setLikerIds(ArrayList<Long> likerIds) {
		this.likerIds = likerIds;
	}

	public ArrayList<Long> getSharerIds() {
		return sharerIds;
	}

	public void setSharerIds(ArrayList<Long> sharerIds) {
		this.sharerIds = sharerIds;
	}

	public ArrayList<String> getHashtagNames() {
		return hashtagNames;
	}

	public void setHashtagNames(ArrayList<String> hashtagNames) {
		this.hashtagNames = hashtagNames;
	}

	public ArrayList<URL> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<URL> links) {
		this.links = links;
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public long getMediaId() {
		return mediaId;
	}

	public void setMediaId(long mediaId) {
		this.mediaId = mediaId;
	}

	public ArrayList<Long> getTaggedUserIds() {
		return taggedUserIds;
	}

	public void setTaggedUserIds(ArrayList<Long> taggedUserIds) {
		this.taggedUserIds = taggedUserIds;
	}

	public ArrayList<Long> getCommentIds() {
		return commentIds;
	}

	public void setCommentIds(ArrayList<Long> commentIds) {
		this.commentIds = commentIds;
	}

	@Override
	public OffsetDateTime getContentTimestamp() throws ModelException {
		return contentTimestamp;
	}

	@Override
	public OffsetDateTime getCrawlingTimestamp() throws ModelException {
		return crawlingTimestamp;
	}


	@Override
	public void setContentMeta(OffsetDateTime content, OffsetDateTime crawling, SocialNetwork sn) throws ModelException {
		this.contentTimestamp = content;
		this.crawlingTimestamp = crawling;
		this.socialNetworkId = sn.getId();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ProfileFeed that = (ProfileFeed) o;

		if (socialNetworkId != that.socialNetworkId) return false;
		if (id != that.id) return false;
		if (profileId != that.profileId) return false;
		if (locationId != that.locationId) return false;
		if (mediaId != that.mediaId) return false;
		if (contentTimestamp != null ? !contentTimestamp.equals(that.contentTimestamp) : that.contentTimestamp != null)
			return false;
		if (crawlingTimestamp != null ? !crawlingTimestamp.equals(that.crawlingTimestamp) : that.crawlingTimestamp != null)
			return false;
		if (rawStoryText != null ? !rawStoryText.equals(that.rawStoryText) : that.rawStoryText != null) return false;
		if (likerIds != null ? !likerIds.equals(that.likerIds) : that.likerIds != null) return false;
		if (sharerIds != null ? !sharerIds.equals(that.sharerIds) : that.sharerIds != null) return false;
		if (hashtagNames != null ? !hashtagNames.equals(that.hashtagNames) : that.hashtagNames != null) return false;
		if (links != null ? !links.equals(that.links) : that.links != null) return false;
		if (taggedUserIds != null ? !taggedUserIds.equals(that.taggedUserIds) : that.taggedUserIds != null)
			return false;
		return !(commentIds != null ? !commentIds.equals(that.commentIds) : that.commentIds != null);

	}

	@Override
	public int hashCode() {
		int result = socialNetworkId;
		result = 31 * result + (contentTimestamp != null ? contentTimestamp.hashCode() : 0);
		result = 31 * result + (crawlingTimestamp != null ? crawlingTimestamp.hashCode() : 0);
		result = 31 * result + (int) (id ^ (id >>> 32));
		result = 31 * result + (int) (profileId ^ (profileId >>> 32));
		result = 31 * result + (rawStoryText != null ? rawStoryText.hashCode() : 0);
		result = 31 * result + (likerIds != null ? likerIds.hashCode() : 0);
		result = 31 * result + (sharerIds != null ? sharerIds.hashCode() : 0);
		result = 31 * result + (hashtagNames != null ? hashtagNames.hashCode() : 0);
		result = 31 * result + (links != null ? links.hashCode() : 0);
		result = 31 * result + (int) (locationId ^ (locationId >>> 32));
		result = 31 * result + (int) (mediaId ^ (mediaId >>> 32));
		result = 31 * result + (taggedUserIds != null ? taggedUserIds.hashCode() : 0);
		result = 31 * result + (commentIds != null ? commentIds.hashCode() : 0);
		return result;
	}
}