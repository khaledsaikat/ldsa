package de.due.ldsa.db.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import com.sun.xml.internal.ws.api.model.SEIModel;
import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.db.DbException;
import de.due.ldsa.db.LinkedWithOtherObjects;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 * Author: Romina (scrobart)
 *
 * If you need to serialize this, please use a serializer that honors transient fields.
 */
@Table(keyspace = "ldsa", name = "comments")
public class Comment implements SocialNetworkContent, LinkedWithOtherObjects, Serializable
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
     * This method is supposed to be used by the Cassandra mapper. The returned value is not reliable.
     * Use getCommenter() instead.
     *
     * @return The Commenter ID how it was read from the Database
     */
    public long getCommenterId() {
        return commenterId;
    }

    /**
     * This method is supposed to be used by the Cassandra mapper. The set value will be overwritten when actually
     * saving this object. Use setCommenter() instead.
     *
     * @param commenterId The Commenter ID to set.
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

    //------------------------------------------------------------------------------------------------------------------
    // Complex methods
    //------------------------------------------------------------------------------------------------------------------

    @Transient
    private transient Media mediaData;
    public Media getMedia()
            throws DbException
    {
        if (mediaData == null)
        {
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
    private transient ArrayList<Profile> likerData;
    public ArrayList<Profile> getLiker()
            throws DbException
    {
        Database db = DatabaseImpl.getInstance();
        if (likerData == null)
        {
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
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (socialNetworkId != comment.socialNetworkId) return false;
        if (id != comment.id) return false;
        if (commenterId != comment.commenterId) return false;
        if (mediaId != comment.mediaId) return false;
        if (contentTimestamp != null ? !contentTimestamp.equals(comment.contentTimestamp) : comment.contentTimestamp != null)
            return false;
        if (crawlingTimestamp != null ? !crawlingTimestamp.equals(comment.crawlingTimestamp) : comment.crawlingTimestamp != null)
            return false;
        if (text != null ? !text.equals(comment.text) : comment.text != null) return false;
        if (hashtagNames != null ? !hashtagNames.equals(comment.hashtagNames) : comment.hashtagNames != null)
            return false;
        if (likerIds != null ? !likerIds.equals(comment.likerIds) : comment.likerIds != null) return false;
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
    private transient ArrayList<Comment> commentData;
    public ArrayList<Comment> getComments()
            throws DbException {
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
        if (commenterData != null) {
            commenterId = commenterData.getId();
        }
        if (hashtagsData != null) {
            hashtagNames = new ArrayList<String>();
            for (Hashtag h : hashtagsData) {
                hashtagNames.add(h.getTitle());
            }
        }
    }

    @Transient
    private transient Profile commenterData;

    public Profile getCommenter() throws DbException {
        if (commenterData == null) {
            Database ourDb = DatabaseImpl.getInstance();
            commenterData = ourDb.autoGetProfile(commenterId);
        }
        return commenterData;
    }

    public void setCommenter(Profile p) {
        commenterId = p.getId();
        commenterData = p;
    }

    @Transient
    private transient ArrayList<Hashtag> hashtagsData;

    public ArrayList<Hashtag> getHashtags() {
        if (hashtagsData == null) {
            hashtagsData = new ArrayList<Hashtag>();
            for (String s : hashtagNames) {
                hashtagsData.add(new Hashtag(s));
            }
        }
        return hashtagsData;
    }
}
