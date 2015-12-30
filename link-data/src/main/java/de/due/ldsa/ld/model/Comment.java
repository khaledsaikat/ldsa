package de.due.ldsa.ld.model;

import java.util.List;

public class Comment extends SocialNetworkContent {
	private String text;
	private Profile commenter;
	private Media media;
	private List<Hashtag> hashtags;
	private List<Profile> liker;
	private List<Comment> comments;

	public Comment(ContentMeta metaInfos) {
		super(metaInfos);
	}

	public Comment(ContentMeta metaInfos, String text, Profile commenter, Media media, List<Hashtag> hashtags,
			List<Profile> liker, List<Comment> comments) {
		super(metaInfos);
		this.text = text;
		this.commenter = commenter;
		this.media = media;
		this.hashtags = hashtags;
		this.liker = liker;
		this.comments = comments;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Profile getCommenter() {
		return commenter;
	}

	public void setCommenter(Profile commenter) {
		this.commenter = commenter;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public List<Hashtag> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}

	public List<Profile> getLiker() {
		return liker;
	}

	public void setLiker(List<Profile> liker) {
		this.liker = liker;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
