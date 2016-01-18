package de.due.ldsa.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;
import com.google.gson.Gson;

import de.due.ldsa.exception.DbException;

import java.io.Serializable;
import java.net.URL;
import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 *
 */
@Table(keyspace = "ldsa", name = "profileFeeds")
public class ProfileFeed extends SocialNetworkContentImpl implements Serializable {
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
	ArrayList<String> hashtags;
	@Column(name = "links")
	ArrayList<URL> links;
	@Column(name = "locationId")
	int locationId;
	@Column(name = "mediaId")
	int mediaId;
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

	public ArrayList<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(ArrayList<String> hashtags) {
		this.hashtags = hashtags;
	}

	public ArrayList<URL> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<URL> links) {
		this.links = links;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getMediaId() {
		return mediaId;
	}

	public void setMediaId(int mediaId) {
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

	@Transient
	Profile profile;
	@Transient
	ArrayList<Profile> liker;
	@Transient
	ArrayList<Profile> shares;
	@Transient
	Location location;
	@Transient
	Media media;
	@Transient
	ArrayList<Profile> taggedUser;
	@Transient
	ArrayList<Comment> comments;

	@Override
	public OffsetDateTime getContentTimestamp() throws DbException {
		return contentTimestamp;
	}

	@Override
	public OffsetDateTime getCrawlingTimestamp() throws DbException {
		return crawlingTimestamp;
	}

	@Override
	public SocialNetwork getSourceNetwork() throws DbException {
		throw new DbException("not yet implemented.");
	}

	@Override
	public void setContentMeta(OffsetDateTime content, OffsetDateTime crawling, SocialNetwork sn) throws DbException {
		this.contentTimestamp = content;
		this.crawlingTimestamp = crawling;
		this.socialNetworkId = sn.getId();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ProfileFeed))
			return false;

		ProfileFeed that = (ProfileFeed) o;

		if (socialNetworkId != that.socialNetworkId)
			return false;
		if (id != that.id)
			return false;
		if (profileId != that.profileId)
			return false;
		if (locationId != that.locationId)
			return false;
		if (mediaId != that.mediaId)
			return false;
		if (contentTimestamp != null ? !contentTimestamp.equals(that.contentTimestamp) : that.contentTimestamp != null)
			return false;
		if (crawlingTimestamp != null ? !crawlingTimestamp.equals(that.crawlingTimestamp)
				: that.crawlingTimestamp != null)
			return false;
		if (!rawStoryText.equals(that.rawStoryText))
			return false;
		if (likerIds != null ? !likerIds.equals(that.likerIds) : that.likerIds != null)
			return false;
		if (sharerIds != null ? !sharerIds.equals(that.sharerIds) : that.sharerIds != null)
			return false;
		if (hashtags != null ? !hashtags.equals(that.hashtags) : that.hashtags != null)
			return false;
		if (links != null ? !links.equals(that.links) : that.links != null)
			return false;
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
		result = 31 * result + rawStoryText.hashCode();
		result = 31 * result + (likerIds != null ? likerIds.hashCode() : 0);
		result = 31 * result + (sharerIds != null ? sharerIds.hashCode() : 0);
		result = 31 * result + (hashtags != null ? hashtags.hashCode() : 0);
		result = 31 * result + (links != null ? links.hashCode() : 0);
		result = 31 * result + locationId;
		result = 31 * result + mediaId;
		result = 31 * result + (taggedUserIds != null ? taggedUserIds.hashCode() : 0);
		result = 31 * result + (commentIds != null ? commentIds.hashCode() : 0);
		return result;
	}

	public String getJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
