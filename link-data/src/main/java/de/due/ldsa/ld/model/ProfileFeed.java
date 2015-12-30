package de.due.ldsa.ld.model;

import java.util.List;

public class ProfileFeed extends SocialNetworkContent {
	private Profile profile;
	private String rowStoryText;
	private List<Profile> liker;
	private List<Profile> shares;
	private List<Profile> comments;
	private List<Hashtag> hashtags;
	private List<String> links;
	private Location location;
	private Media media;
	private List<Profile> taggedUsers;

	public ProfileFeed(ContentMeta metaInfos) {
		super(metaInfos);
	}

	public ProfileFeed(ContentMeta metaInfos, Profile profile, String rowStoryText, List<Profile> liker,
			List<Profile> shares, List<Profile> comments, List<Hashtag> hashtags, List<String> links, Location location,
			Media media, List<Profile> taggedUsers) {
		super(metaInfos);
		this.profile = profile;
		this.rowStoryText = rowStoryText;
		this.liker = liker;
		this.shares = shares;
		this.comments = comments;
		this.hashtags = hashtags;
		this.links = links;
		this.location = location;
		this.media = media;
		this.taggedUsers = taggedUsers;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getRowStoryText() {
		return rowStoryText;
	}

	public void setRowStoryText(String rowStoryText) {
		this.rowStoryText = rowStoryText;
	}

	public List<Profile> getLiker() {
		return liker;
	}

	public void setLiker(List<Profile> liker) {
		this.liker = liker;
	}

	public List<Profile> getShares() {
		return shares;
	}

	public void setShares(List<Profile> shares) {
		this.shares = shares;
	}

	public List<Profile> getComments() {
		return comments;
	}

	public void setComments(List<Profile> comments) {
		this.comments = comments;
	}

	public List<Hashtag> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}

	public List<String> getLinks() {
		return links;
	}

	public void setLinks(List<String> links) {
		this.links = links;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public List<Profile> getTaggedUsers() {
		return taggedUsers;
	}

	public void setTaggedUsers(List<Profile> taggedUsers) {
		this.taggedUsers = taggedUsers;
	}

}
