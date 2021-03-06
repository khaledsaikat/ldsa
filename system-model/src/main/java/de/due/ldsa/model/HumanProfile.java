package de.due.ldsa.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;
import com.google.gson.annotations.SerializedName;

import de.due.ldsa.ModelUtils;
import de.due.ldsa.exception.ModelException;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Romina (scrobart)
 *
 * If you need to serialize this, please use a serializer that honors transient
 * fields.
 */
@Table(keyspace = "ldsa", name = "humanProfiles")
public class HumanProfile extends Profile implements Serializable, SocialNetworkInterest {

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
	@SerializedName("username") 
	@Column(name = "username")
	String username;
	@Column(name = "profileURL")
	URL profileURL;
	@SerializedName("full_name")
	@Column(name = "fullname")
	String fullname;
	@SerializedName("bio") 
	@Column(name = "bio")
	String bio;
	@Column(name = "interestIds")
	ArrayList<Long> interestIds;
	@Column(name = "userEmail")
	String userEmail;
	@SerializedName("website") 
	@Column(name = "userWebsite")
	String userWebsite;
	@Column(name = "profilePhotoMediaId")
	long profilePhotoMediaId;
	@Column(name = "lastUpdateProfileFeedId")
	long lastUpdateProfileFeedId;
	@Column(name = "hometownLocationId")
	long hometownLocationId;
	@Column(name = "followedIds")
	ArrayList<Long> followsIds;
	@Column(name = "followedByIds")
	ArrayList<Long> followedByIds;
	@Column(name = "friendIds")
	ArrayList<Long> friendIds;
	@Column(name = "profileFeedIds")
	ArrayList<Long> profileFeedIds;
	@Column(name = "attendingEventIds")
	ArrayList<Long> attendingEventIds;
	@Column(name = "linkedOtherProfileIds")
	ArrayList<Long> linkedOtherSocialNetworkProfileIds;
	@Column(name = "allComments")
	ArrayList<Long> allCommentsId;
	@Column(name = "sex")
	Sex sex;
	@Column(name = "birthday")
	LocalDate birthday;
	@Column(name = "relationshipStatus")
	RelationshipStatus relationshipStatus;
	@Column(name = "relationshipPersons")
	ArrayList<Long> relationshipPersons;

	@Column(name = "interestKinds")
	ArrayList<InterestKind> interestKinds;

	@Transient
	transient Relationship rs;

	@Transient
	List<Comment> comments;

	@Transient
	List<ProfileFeed> profileFeeds;

	@Transient
	List<HumanProfile> linkedOtherSocialNetworkProfiles;

	public List<ProfileFeed> getProfileFeeds() {
		return profileFeeds;
	}

	public void setProfileFeeds(List<ProfileFeed> profileFeeds) {
		this.profileFeeds = profileFeeds;
	}

	@Transient
	private List<HumanProfile> relationshipPersonsProfiles;

	public List<HumanProfile> getRelationshipPersonsProfiles() {
		return relationshipPersonsProfiles;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public OffsetDateTime getContentTimestamp() {
		return contentTimestamp;
	}

	@Override
	public OffsetDateTime getCrawlingTimestamp() {
		return crawlingTimestamp;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public URL getProfileURL() {
		return profileURL;
	}

	@Override
	public void setProfileURL(URL profileURL) {
		this.profileURL = profileURL;
	}

	@Override
	public String getFullname() {
		return fullname;
	}

	@Override
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Override
	public String getBio() {
		return bio;
	}

	@Override
	public void setBio(String bio) {
		this.bio = bio;
	}

	@Override
	public ArrayList<Long> getInterestIds() {
		return interestIds;
	}

	@Override
	public void setInterestIds(ArrayList<Long> interestIds) {
		this.interestIds = interestIds;
	}

	@Override
	public String getUserEmail() {
		return userEmail;
	}

	@Override
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String getUserWebsite() {
		return userWebsite;
	}

	@Override
	public void setUserWebsite(String userWebsite) {
		this.userWebsite = userWebsite;
	}

	@Override
	public long getProfilePhotoMediaId() {
		return profilePhotoMediaId;
	}

	@Override
	public void setProfilePhotoMediaId(long profilePhotoMediaId) {
		this.profilePhotoMediaId = profilePhotoMediaId;
	}

	@Override
	public long getLastUpdateProfileFeedId() {
		return lastUpdateProfileFeedId;
	}

	@Override
	public void setLastUpdateProfileFeedId(long lastUpdateProfileFeedId) {
		this.lastUpdateProfileFeedId = lastUpdateProfileFeedId;
	}

	@Override
	public long getHometownLocationId() {
		return hometownLocationId;
	}

	@Override
	public void setHometownLocationId(long hometownLocationId) {
		this.hometownLocationId = hometownLocationId;
	}

	@Override
	public ArrayList<Long> getFollowsIds() {
		return this.followsIds;
	}

	@Override
	public void setFollowsIds(ArrayList<Long> followingId) {
		this.followsIds = followingId;
	}

	@Override
	public ArrayList<Long> getFollowedByIds() {
		return followedByIds;
	}

	@Override
	public void setFollowedByIds(ArrayList<Long> followedByIds) {
		this.followedByIds = followedByIds;
	}

	@Override
	public ArrayList<Long> getFriendIds() {
		return friendIds;
	}

	@Override
	public void setFriendIds(ArrayList<Long> friendIds) {
		this.friendIds = friendIds;
	}

	@Override
	public ArrayList<Long> getProfileFeedIds() {
		return profileFeedIds;
	}

	@Override
	public void setProfileFeedIds(ArrayList<Long> profileFeedIds) {
		this.profileFeedIds = profileFeedIds;
	}

	@Override
	public ArrayList<Long> getAttendingEventIds() {
		return attendingEventIds;
	}

	@Override
	public void setAttendingEventIds(ArrayList<Long> attendingEventIds) {
		this.attendingEventIds = attendingEventIds;
	}

	@Override
	public ArrayList<Long> getLinkedOtherSocialNetworkProfileIds() {
		return linkedOtherSocialNetworkProfileIds;
	}

	@Override
	public void setLinkedOtherSocialNetworkProfileIds(ArrayList<Long> linkedOtherSocialNetworkProfileIds) {
		this.linkedOtherSocialNetworkProfileIds = linkedOtherSocialNetworkProfileIds;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

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

	public RelationshipStatus getRelationshipStatus() {
		return relationshipStatus;
	}

	public void setRelationshipStatus(RelationshipStatus relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}

	public ArrayList<Long> getRelationshipPersons() {
		return relationshipPersons;
	}

	public void setRelationshipPersons(ArrayList<Long> relationshipPersons) {
		this.relationshipPersons = relationshipPersons;
	}

	public ArrayList<InterestKind> getInterestKinds() {
		return interestKinds;
	}

	public void setInterestKinds(ArrayList<InterestKind> interestKinds) {
		this.interestKinds = interestKinds;
	}

	public ArrayList<Long> getAllCommentsId() {
		return allCommentsId;
	}

	public void setAllCommentsId(ArrayList<Long> allCommentsId) {
		this.allCommentsId = allCommentsId;
	}

	// ------------------------------------------------------------------------------------------------------------------
	// COMPLEX METHODS
	// ------------------------------------------------------------------------------------------------------------------
	public int getAge() {
		Date temp = new Date();
		LocalDate today = temp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return (int) ChronoUnit.YEARS.between(today, birthday);
	}

	@Override
	public void setContentMeta(OffsetDateTime content, OffsetDateTime crawling, SocialNetwork sn) throws ModelException {
		this.contentTimestamp = content;
		this.crawlingTimestamp = crawling;
		this.socialNetworkId = sn.getId();
	}

	public void setRelationship(Relationship relationship) {
		this.relationshipPersons = relationship.getPersonIds();
		this.relationshipStatus = relationship.getRelationshipStatus();
	}

	public void setRelationship(RelationshipStatus relationshipStatus, ArrayList<Long> persons) {
		this.relationshipPersons = persons;
		this.relationshipStatus = relationshipStatus;
	}

	public Relationship getRelationship() throws ModelException {
		return new Relationship(getRelationshipStatus(), getRelationshipPersons());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		HumanProfile that = (HumanProfile) o;

		if (socialNetworkId != that.socialNetworkId)
			return false;
		if (id != that.id)
			return false;
		if (profilePhotoMediaId != that.profilePhotoMediaId)
			return false;
		if (lastUpdateProfileFeedId != that.lastUpdateProfileFeedId)
			return false;
		if (hometownLocationId != that.hometownLocationId)
			return false;
		if (contentTimestamp != null ? !contentTimestamp.equals(that.contentTimestamp) : that.contentTimestamp != null)
			return false;
		if (crawlingTimestamp != null ? !crawlingTimestamp.equals(that.crawlingTimestamp)
				: that.crawlingTimestamp != null)
			return false;
		if (username != null ? !username.equals(that.username) : that.username != null)
			return false;
		if (profileURL != null ? !profileURL.equals(that.profileURL) : that.profileURL != null)
			return false;
		if (fullname != null ? !fullname.equals(that.fullname) : that.fullname != null)
			return false;
		if (bio != null ? !bio.equals(that.bio) : that.bio != null)
			return false;
		if (interestIds != null ? !interestIds.equals(that.interestIds) : that.interestIds != null)
			return false;
		if (userEmail != null ? !userEmail.equals(that.userEmail) : that.userEmail != null)
			return false;
		if (userWebsite != null ? !userWebsite.equals(that.userWebsite) : that.userWebsite != null)
			return false;
		if (followsIds != null ? !followsIds.equals(that.followsIds) : that.followsIds != null)
			return false;
		if (followedByIds != null ? !followedByIds.equals(that.followedByIds) : that.followedByIds != null)
			return false;
		if (friendIds != null ? !friendIds.equals(that.friendIds) : that.friendIds != null)
			return false;
		if (profileFeedIds != null ? !profileFeedIds.equals(that.profileFeedIds) : that.profileFeedIds != null)
			return false;
		if (attendingEventIds != null ? !attendingEventIds.equals(that.attendingEventIds)
				: that.attendingEventIds != null)
			return false;
		if (linkedOtherSocialNetworkProfileIds != null
				? !linkedOtherSocialNetworkProfileIds.equals(that.linkedOtherSocialNetworkProfileIds)
				: that.linkedOtherSocialNetworkProfileIds != null)
			return false;
		if (sex != that.sex)
			return false;
		if (!(birthday != null ? !birthday.equals(that.birthday) : that.birthday != null))
			if (relationshipStatus == that.relationshipStatus)
				if (!(relationshipPersons != null ? !relationshipPersons.equals(that.relationshipPersons) : that.relationshipPersons != null))
					if (!(interestKinds != null ? !interestKinds.equals(that.interestKinds) : that.interestKinds != null))
						return true;
		return false;

	}

	@Override
	public int hashCode() {
		int result = socialNetworkId;
		result = 31 * result + (contentTimestamp != null ? contentTimestamp.hashCode() : 0);
		result = 31 * result + (crawlingTimestamp != null ? crawlingTimestamp.hashCode() : 0);
		result = 31 * result + (int) (id ^ (id >>> 32));
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (profileURL != null ? profileURL.hashCode() : 0);
		result = 31 * result + (fullname != null ? fullname.hashCode() : 0);
		result = 31 * result + (bio != null ? bio.hashCode() : 0);
		result = 31 * result + (interestIds != null ? interestIds.hashCode() : 0);
		result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
		result = 31 * result + (userWebsite != null ? userWebsite.hashCode() : 0);
		result = 31 * result + (int) (profilePhotoMediaId ^ (profilePhotoMediaId >>> 32));
		result = 31 * result + (int) (lastUpdateProfileFeedId ^ (lastUpdateProfileFeedId >>> 32));
		result = 31 * result + (int) (hometownLocationId ^ (hometownLocationId >>> 32));
		result = 31 * result + (followsIds != null ? followsIds.hashCode() : 0);
		result = 31 * result + (followedByIds != null ? followedByIds.hashCode() : 0);
		result = 31 * result + (friendIds != null ? friendIds.hashCode() : 0);
		result = 31 * result + (profileFeedIds != null ? profileFeedIds.hashCode() : 0);
		result = 31 * result + (attendingEventIds != null ? attendingEventIds.hashCode() : 0);
		result = 31 * result
				+ (linkedOtherSocialNetworkProfileIds != null ? linkedOtherSocialNetworkProfileIds.hashCode() : 0);
		result = 31 * result + (sex != null ? sex.hashCode() : 0);
		result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
		result = 31 * result + (relationshipStatus != null ? relationshipStatus.hashCode() : 0);
		result = 31 * result + (relationshipPersons != null ? relationshipPersons.hashCode() : 0);
		result = 31 * result + (interestKinds != null ? interestKinds.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "HumanProfile{" + "socialNetworkId=" + socialNetworkId + ", contentTimestamp=" + contentTimestamp
				+ ", crawlingTimestamp=" + crawlingTimestamp + ", id=" + id + ", username='" + username + '\''
				+ ", profileURL=" + profileURL + ", fullname='" + fullname + '\'' + ", bio='" + bio + '\''
				+ ", interestIds=" + interestIds + ", userEmail='" + userEmail + '\'' + ", userWebsite='" + userWebsite
				+ '\'' + ", profilePhotoMediaId=" + profilePhotoMediaId + ", lastUpdateProfileFeedId="
				+ lastUpdateProfileFeedId + ", hometownLocationId=" + hometownLocationId + ", followingId=" + followsIds
				+ ", followedByIds=" + followedByIds + ", friendIds=" + friendIds + ", profileFeedIds=" + profileFeedIds
				+ ", attendingEventIds=" + attendingEventIds + ", linkedOtherSocialNetworkProfileIds="
				+ linkedOtherSocialNetworkProfileIds + ", sex=" + sex + ", birthday=" + birthday
				+ ", relationshipStatus=" + relationshipStatus + ", relationshipPersons=" + relationshipPersons + '}';
	}

	@Override
	public void addInterestKind(InterestKind ik) {
		if (interestKinds == null) {
			interestKinds = new ArrayList<>();
		}
		ModelUtils.addInterestKind(interestKinds, ik);
	}

	@Override
	public void removeInterestKind(InterestKind ik) {
		if (interestKinds == null) {
			interestKinds = new ArrayList<>();
		}
		ModelUtils.removeInterestKind(interestKinds, ik);
	}

	@Override
	public boolean isInterestKind(InterestKind ik) {
		if (interestKinds == null) {
			interestKinds = new ArrayList<>();
		}
		return interestKinds.contains(ik);
	}

	@Override
	public boolean checkValidInterestKinds() {
		return ModelUtils.checkValidInterestKinds(interestKinds);
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setRelationshipPersonsProfiles(List<HumanProfile> relationshipPersonsProfiles) {
		this.relationshipPersonsProfiles = relationshipPersonsProfiles;
	}

	public List<HumanProfile> getLinkedOtherSocialNetworkProfiles() {
		return linkedOtherSocialNetworkProfiles;
	}

	public void setLinkedOtherSocialNetworkProfiles(List<HumanProfile> linkedOtherSocialNetworkProfiles) {
		this.linkedOtherSocialNetworkProfiles = linkedOtherSocialNetworkProfiles;
	}

	@Transient
	List<HumanProfile> friends;

	public List<HumanProfile> getFriends() {
		return friends;
	}

	public void setFriends(List<HumanProfile> friends) {
		this.friends = friends;
	}

	@Transient
	List<HumanProfile> follows;
	
	@Transient
	List<HumanProfile> followedBy;

	public List<HumanProfile> getFollows() {
		return follows;
	}

	public void setFollows(List<HumanProfile> follows) {
		this.follows = follows;
	}

	public List<HumanProfile> getFollowedBy() {
		return followedBy;
	}

	public void setFollowedBy(List<HumanProfile> followedBy) {
		this.followedBy = followedBy;
	}
}
