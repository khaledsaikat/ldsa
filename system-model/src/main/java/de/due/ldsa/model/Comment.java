package de.due.ldsa.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;
import com.google.gson.Gson;

import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.exception.DbException;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 *
 */
@Table(keyspace = "ldsa", name = "comments")
public class Comment implements SocialNetworkContent, LinkedWithOtherObjects, Serializable {
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

	public long getCommenterId() {
		return commenterId;
	}

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

	// ------------------------------------------------------------------------------------------------------------------
	// Complex methods
	// ------------------------------------------------------------------------------------------------------------------

	@Transient
	Media mediaData;

	public Media getMedia() throws DbException {
		if (mediaData == null) {
			mediaData = DatabaseImpl.getInstance().getMedia(mediaId);
		}
		return mediaData;
	}

	public void setMedia(Media m) {
		mediaData = m;
		mediaId = m.getId();
	}

	public String getText() {
		return text;
	}

	@Transient
	ArrayList<Profile> likerData;

	public ArrayList<Profile> getLiker() throws DbException {
		Database db = DatabaseImpl.getInstance();
		if (likerData == null) {
			likerData = new ArrayList<Profile>();
			if (likerIds != null) {
				for (Long l : likerIds) {
					if (db.isHuman(l)) {
						likerData.add(db.getHumanProfile(l));
					} else {
						likerData.add(db.getCoopProfile(l));
					}
				}
			}
		}
		return likerData;
	}

	public SocialNetwork getSourceNetwork() throws DbException {
		return DatabaseImpl.getInstance().getSocialNetwork(socialNetworkId);
	}

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

	@Transient
	ArrayList<Comment> commentData;

	public ArrayList<Comment> getComments() throws DbException {
		Database db = DatabaseImpl.getInstance();
		if (commentData == null) {
			commentData = new ArrayList<Comment>();
			if (commentIds != null) {
				for (Long l : commentIds) {
					commentData.add(db.getComment(l));
				}
			}
		}
		return commentData;
	}

	@Override
	public void prepareSave() {
		if (likerData != null) {
			likerIds = new ArrayList<Long>();
			for (Profile p : likerData) {
				likerIds.add(p.getId());
			}
		}
		if (mediaData != null) {
			mediaId = mediaData.getId();
		}
		if (commentData != null) {
			commentIds = new ArrayList<Long>();
			for (Comment c : commentData) {
				commentIds.add(c.getId());
			}
		}
	}

	public String getJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}