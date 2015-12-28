package de.due.ldsa.db.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;
import de.due.ldsa.db.DbException;

import java.net.URL;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Table(keyspace = "ldsa", name = "profileFeeds")
public class ProfileFeed extends SocialNetworkContentImpl
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
    @Column(name = "profileId")
    long profileId;
    @Column(name = "rawStoryText")
    String rawStoryText;
    @Column(name = "likerIds")
    List<Long> likerIds;
    @Column(name = "sharerIds")
    List<Long> sharerIds;
    @Column(name = "hashtags")
    List<String> hashtags;
    @Column(name = "links")
    List<String> links;
    @Column(name = "locationId")
    int locationId;
    @Column(name = "mediaId")
    int mediaId;
    @Column(name = "taggedUserIds")
    List<Long> taggedUserIds;
    @Column(name = "commentIds")
    List<Long> commentIds;

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

    public List<Long> getLikerIds() {
        return likerIds;
    }

    public void setLikerIds(List<Long> likerIds) {
        this.likerIds = likerIds;
    }

    public List<Long> getSharerIds() {
        return sharerIds;
    }

    public void setSharerIds(List<Long> sharerIds) {
        this.sharerIds = sharerIds;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
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

    public List<Long> getTaggedUserIds() {
        return taggedUserIds;
    }

    public void setTaggedUserIds(List<Long> taggedUserIds) {
        this.taggedUserIds = taggedUserIds;
    }

    public List<Long> getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(List<Long> commentIds) {
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
    public void setContentMeta(OffsetDateTime content, OffsetDateTime crawling, SocialNetwork sn) throws DbException
    {
        this.contentTimestamp = content;
        this.crawlingTimestamp = crawling;
        this.socialNetworkId = sn.getId();
    }
}
