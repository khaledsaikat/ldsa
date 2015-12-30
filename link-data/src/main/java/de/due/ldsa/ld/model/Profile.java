package de.due.ldsa.ld.model;

import java.util.List;

public class Profile extends SocialNetworkContent {

	private long userId;
	private String username;
	private String url;
	private String fullname;
	private String bio;
	private String email;
	private String website;
	private Media photo;
	private ProfileFeed lastUpdate;
	private Location hometown;
	private List<Profile> follows;
	private List<Profile> followedBy;
	private List<Profile> friends;
	private List<ProfileFeed> feeds;
	private List<Comment> comments;
	private List<Event> attendingEvents;
	private List<Profile> linkedOtherSocialNetworkProfiles;

	public Profile(ContentMeta metaInfos) {
		super(metaInfos);
	}

	public Profile(ContentMeta metaInfos, long userId, String username, String url, String fullname, String bio,
			String email, String website, Media photo, ProfileFeed lastUpdate, Location hometown, List<Profile> follows,
			List<Profile> followedBy, List<Profile> friends, List<ProfileFeed> feeds, List<Comment> comments,
			List<Event> attendingEvents, List<Profile> linkedOtherSocialNetworkProfiles) {
		super(metaInfos);
		this.userId = userId;
		this.username = username;
		this.url = url;
		this.fullname = fullname;
		this.bio = bio;
		this.email = email;
		this.website = website;
		this.photo = photo;
		this.lastUpdate = lastUpdate;
		this.hometown = hometown;
		this.follows = follows;
		this.followedBy = followedBy;
		this.friends = friends;
		this.feeds = feeds;
		this.comments = comments;
		this.attendingEvents = attendingEvents;
		this.linkedOtherSocialNetworkProfiles = linkedOtherSocialNetworkProfiles;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Media getPhoto() {
		return photo;
	}

	public void setPhoto(Media photo) {
		this.photo = photo;
	}

	public ProfileFeed getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(ProfileFeed lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Location getHometown() {
		return hometown;
	}

	public void setHometown(Location hometown) {
		this.hometown = hometown;
	}

	public List<Profile> getFollows() {
		return follows;
	}

	public void setFollows(List<Profile> follows) {
		this.follows = follows;
	}

	public List<Profile> getFollowedBy() {
		return followedBy;
	}

	public void setFollowedBy(List<Profile> followedBy) {
		this.followedBy = followedBy;
	}

	public List<Profile> getFriends() {
		return friends;
	}

	public void setFriends(List<Profile> friends) {
		this.friends = friends;
	}

	public List<ProfileFeed> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<ProfileFeed> feeds) {
		this.feeds = feeds;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Event> getAttendingEvents() {
		return attendingEvents;
	}

	public void setAttendingEvents(List<Event> attendingEvents) {
		this.attendingEvents = attendingEvents;
	}

	public List<Profile> getLinkedOtherSocialNetworkProfiles() {
		return linkedOtherSocialNetworkProfiles;
	}

	public void setLinkedOtherSocialNetworkProfiles(List<Profile> linkedOtherSocialNetworkProfiles) {
		this.linkedOtherSocialNetworkProfiles = linkedOtherSocialNetworkProfiles;
	}

}
