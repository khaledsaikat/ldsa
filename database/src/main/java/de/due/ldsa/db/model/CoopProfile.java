package de.due.ldsa.db.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import de.due.ldsa.db.DbException;

import java.net.URL;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 *
 */
@Table(keyspace = "ldsa", name = "coopProfiles")
public class CoopProfile extends Profile
{
    /*This needs to be put right here, because Datastax' Cassandra mapper does not support inheritance.
      If you need access to these fields use the getters and setters from the upper classes.*/
    @Column(name = "snId")
    int socialNetworkId;
    @Column(name = "contentTimestamp")
    OffsetDateTime contentTimestamp;
    @Column(name = "crawlingTimestamp")
    OffsetDateTime crawlingTimestamp;

    @PartitionKey
    long id;
    @Column(name = "username")
    String username;
    @Column(name = "profileURL")
    URL profileURL;
    @Column(name = "fullname")
    String fullname;
    @Column(name = "bio")
    String bio;
    @Column(name = "interestIds")
    ArrayList<Long> interestIds;
    @Column(name = "userEmail")
    String userEmail;
    @Column(name = "userWebsite")
    String userWebsite;
    @Column(name = "profilePhotoMediaId")
    long profilePhotoMediaId;
    @Column(name = "lastUpdateProfileFeedId")
    long lastUpdateProfileFeedId;
    @Column(name = "hometownLocationId")
    long hometownLocationId;
    @Column(name = "followedIds")
    ArrayList<Long> followingIds;
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

    @Column(name = "dateFounded")
    LocalDate dateFounded;

    public int getSocialNetworkId() {
        return socialNetworkId;
    }

    public void setSocialNetworkId(int socialNetworkId) {
        this.socialNetworkId = socialNetworkId;
    }

    @Override
    public OffsetDateTime getContentTimestamp() {
        return contentTimestamp;
    }

    public void setContentTimestamp(OffsetDateTime contentTimestamp) {
        this.contentTimestamp = contentTimestamp;
    }

    @Override
    public OffsetDateTime getCrawlingTimestamp() {
        return crawlingTimestamp;
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
    public ArrayList<Long> getFollowingIds() {
        return followingIds;
    }

    @Override
    public void setFollowingIds(ArrayList<Long> followingId) {
        this.followingIds = followingId;
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

    public LocalDate getDateFounded() {
        return dateFounded;
    }

    public void setDateFounded(LocalDate dateFounded) {
        this.dateFounded = dateFounded;
    }

    //------------------------------------------------------------------------------------------------------------------
    // COMPLEX METHODS
    //------------------------------------------------------------------------------------------------------------------
    public int countInteraction(Profile p)
        throws DbException
    {
        throw new DbException("not yet implemented.");
    }

    public double countAverageInteractionPerFeed(Profile p)
            throws DbException
    {
        throw new DbException("not yet implemented.");
    }

    public double getAverageInteractionPerFeed()
        throws DbException
    {
        throw new DbException("not yet implemented.");
    }

    public double getAverageOfActionsPerDay()
        throws DbException
    {
        throw new DbException("not yet implemented.");
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
        if (this == o) return true;
        if (!(o instanceof CoopProfile)) return false;

        CoopProfile that = (CoopProfile) o;

        if (socialNetworkId != that.socialNetworkId) return false;
        if (id != that.id) return false;
        if (profilePhotoMediaId != that.profilePhotoMediaId) return false;
        if (lastUpdateProfileFeedId != that.lastUpdateProfileFeedId) return false;
        if (hometownLocationId != that.hometownLocationId) return false;
        if (contentTimestamp != null ? !contentTimestamp.equals(that.contentTimestamp) : that.contentTimestamp != null)
            return false;
        if (crawlingTimestamp != null ? !crawlingTimestamp.equals(that.crawlingTimestamp) : that.crawlingTimestamp != null)
            return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (profileURL != null ? !profileURL.equals(that.profileURL) : that.profileURL != null) return false;
        if (fullname != null ? !fullname.equals(that.fullname) : that.fullname != null) return false;
        if (bio != null ? !bio.equals(that.bio) : that.bio != null) return false;
        if (interestIds != null ? !interestIds.equals(that.interestIds) : that.interestIds != null) return false;
        if (userEmail != null ? !userEmail.equals(that.userEmail) : that.userEmail != null) return false;
        if (userWebsite != null ? !userWebsite.equals(that.userWebsite) : that.userWebsite != null) return false;
        if (followingIds != null ? !followingIds.equals(that.followingIds) : that.followingIds != null) return false;
        if (followedByIds != null ? !followedByIds.equals(that.followedByIds) : that.followedByIds != null)
            return false;
        if (friendIds != null ? !friendIds.equals(that.friendIds) : that.friendIds != null) return false;
        if (profileFeedIds != null ? !profileFeedIds.equals(that.profileFeedIds) : that.profileFeedIds != null)
            return false;
        if (attendingEventIds != null ? !attendingEventIds.equals(that.attendingEventIds) : that.attendingEventIds != null)
            return false;
        if (linkedOtherSocialNetworkProfileIds != null ? !linkedOtherSocialNetworkProfileIds.equals(that.linkedOtherSocialNetworkProfileIds) : that.linkedOtherSocialNetworkProfileIds != null)
            return false;
        return !(dateFounded != null ? !dateFounded.equals(that.dateFounded) : that.dateFounded != null);

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
        result = 31 * result + (followingIds != null ? followingIds.hashCode() : 0);
        result = 31 * result + (followedByIds != null ? followedByIds.hashCode() : 0);
        result = 31 * result + (friendIds != null ? friendIds.hashCode() : 0);
        result = 31 * result + (profileFeedIds != null ? profileFeedIds.hashCode() : 0);
        result = 31 * result + (attendingEventIds != null ? attendingEventIds.hashCode() : 0);
        result = 31 * result + (linkedOtherSocialNetworkProfileIds != null ? linkedOtherSocialNetworkProfileIds.hashCode() : 0);
        result = 31 * result + (dateFounded != null ? dateFounded.hashCode() : 0);
        return result;
    }
}
