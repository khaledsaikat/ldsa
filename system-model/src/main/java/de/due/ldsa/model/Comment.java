package de.due.ldsa.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;
import com.google.gson.annotations.SerializedName;

import de.due.ldsa.exception.DbException;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 * Author: Romina (scrobart)
 *
 * If you need to serialize this, please use a serializer that honors transient
 * fields.
 */
@Table(keyspace = "ldsa", name = "comments")
public class Comment extends SocialNetworkContentImpl implements Serializable {
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
	@SerializedName("id")
	@PartitionKey
	long id;
	@SerializedName("text")
	@Column(name = "text")
	private String text;
	@Column(name = "commenter")
	long commenterId;
	@Column(name = "mediaId")
	long mediaId;
	@Column(name = "hashtagNames")
	ArrayList<String> hashtagNames;
	@Column(name = "likerIds")
	ArrayList<Long> likerIds;
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

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public OffsetDateTime getContentTimestamp() throws DbException {
		return this.contentTimestamp;
	}

	public OffsetDateTime getCrawlingTimestamp() throws DbException {
		return this.crawlingTimestamp;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * This method is supposed to be used by the Cassandra mapper. The returned
	 * value is not reliable. Use getCommenter() instead.
	 *
	 * @return The Commenter ID how it was read from the Database
	 */
	public long getCommenterId() {
		return commenterId;
	}

	/**
	 * This method is supposed to be used by the Cassandra mapper. The set value
	 * will be overwritten when actually saving this object. Use setCommenter()
	 * instead.
	 *
	 * @param commenterId
	 *            The Commenter ID to set.
	 */
	public void setCommenterId(long commenterId) {
		this.commenterId = commenterId;
	}

	public long getMediaId() {
		return mediaId;
	}

	public void setMediaId(long mediaId) {
		this.mediaId = mediaId;
	}

	public ArrayList<Long> getLikerIds() {
		return likerIds;
	}

	public void setLikerIds(ArrayList<Long> likerIds) {
		this.likerIds = likerIds;
	}

	public ArrayList<Long> getCommentIds() {
		return commentIds;
	}

	public void setCommentIds(ArrayList<Long> commentIds) {
		this.commentIds = commentIds;
	}

	public ArrayList<String> getHashtagNames() {
		return hashtagNames;
	}

	public void setHashtagNames(ArrayList<String> hashtagNames) {
		this.hashtagNames = hashtagNames;
	}

	public String getText() {
		return text;
	}

	// ------------------------------------------------------------------------------------------------------------------
	// Complex methods
	// ------------------------------------------------------------------------------------------------------------------

	public void setContentMeta(OffsetDateTime content, OffsetDateTime crawling, SocialNetwork sn) throws DbException {
		this.contentTimestamp = content;
		this.crawlingTimestamp = crawling;
		this.socialNetworkId = sn.getId();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Comment))
			return false;

		Comment comment = (Comment) o;

		if (socialNetworkId != comment.socialNetworkId)
			return false;
		if (id != comment.id)
			return false;
		if (commenterId != comment.commenterId)
			return false;
		if (mediaId != comment.mediaId)
			return false;
		if (contentTimestamp != null ? !contentTimestamp.equals(comment.contentTimestamp)
				: comment.contentTimestamp != null)
			return false;
		if (crawlingTimestamp != null ? !crawlingTimestamp.equals(comment.crawlingTimestamp)
				: comment.crawlingTimestamp != null)
			return false;
		if (text != null ? !text.equals(comment.text) : comment.text != null)
			return false;
		if (hashtagNames != null ? !hashtagNames.equals(comment.hashtagNames) : comment.hashtagNames != null)
			return false;
		if (likerIds != null ? !likerIds.equals(comment.likerIds) : comment.likerIds != null)
			return false;
		return !(commentIds != null ? !commentIds.equals(comment.commentIds) : comment.commentIds != null);

	}

	@Override
	public int hashCode() {
		int result = socialNetworkId;
		result = 31 * result + (contentTimestamp != null ? contentTimestamp.hashCode() : 0);
		result = 31 * result + (crawlingTimestamp != null ? crawlingTimestamp.hashCode() : 0);
		result = 31 * result + (int) (id ^ (id >>> 32));
		result = 31 * result + (text != null ? text.hashCode() : 0);
		result = 31 * result + (int) (commenterId ^ (commenterId >>> 32));
		result = 31 * result + (int) (mediaId ^ (mediaId >>> 32));
		result = 31 * result + (hashtagNames != null ? hashtagNames.hashCode() : 0);
		result = 31 * result + (likerIds != null ? likerIds.hashCode() : 0);
		result = 31 * result + (commentIds != null ? commentIds.hashCode() : 0);
		return result;
	}


}
