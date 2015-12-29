package de.due.ldsa.db.model;

import com.datastax.driver.mapping.annotations.Column;
import de.due.ldsa.db.DbException;

import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 *
 */
public class Comment implements SocialNetworkContent
{

    /*This needs to be put right here, because Datastax' Cassandra mapper does not support inheritance.
      If you need access to these fields use the getters and setters from the upper classes.*/
    @Column(name = "snId")
    int socialNetworkId;
    @Column(name = "contentTimestamp")
    OffsetDateTime contentTimestamp;
    @Column(name = "crawlingTimestamp")
    OffsetDateTime crawlingTimestamp;
    @Column(name = "id")
    long id;

    private String text;
    private Profile commenter;
    private Media media;
    private ArrayList<Hashtag> hashtags;
    private ArrayList<Profile> liker;
    private ArrayList<Comment> comments;

    public Media getMedia() {
        return media;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Profile> getLiker() {
        return liker;
    }

    public ArrayList<Comment> getComments() {
        return comments;
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

    public OffsetDateTime getContentTimestamp() throws DbException {
        throw new DbException("not yet implemented.");
    }

    public OffsetDateTime getCrawlingTimestamp() throws DbException {
        throw new DbException("not yet implemented.");
    }

    public SocialNetwork getSourceNetwork() throws DbException {
        throw new DbException("not yet implemented.");
    }

    public void setContentMeta(OffsetDateTime content, OffsetDateTime crawling, SocialNetwork sn) throws DbException {
        throw new DbException("not yet implemented.");
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
