package de.due.ldsa.db.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import de.due.ldsa.db.DbException;

import java.net.URL;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 *
 */
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
    Long hometownLocationId;
    @Column(name = "followedIds")
    ArrayList<Long> followingId;
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
    public Long getHometownLocationId() {
        return hometownLocationId;
    }

    @Override
    public void setHometownLocationId(Long hometownLocationId) {
        this.hometownLocationId = hometownLocationId;
    }

    @Override
    public ArrayList<Long> getFollowingId() {
        return followingId;
    }

    @Override
    public void setFollowingId(ArrayList<Long> followingId) {
        this.followingId = followingId;
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
        throw new DbException("not yet implemented.");
    }
}
