package de.due.ldsa.db.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;
import de.due.ldsa.db.DbException;

import java.net.URL;
import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 *
 */
public abstract class Profile implements SocialNetworkContent
{

    long userID;
    String username;
    URL profileURL;
    String fullname;
    String bio;
    ArrayList<SocialNetworkInterest> interests;
    String userEmail;
    String userWebsite;
    Media profilePhoto;
    ProfileFeed lastUpdate;
    Location hometown;
    ArrayList<Profile> follows;
    ArrayList<Profile> followedBy;
    ArrayList<Profile> friends;
    ArrayList<ProfileFeed> profileFeeds;
    ArrayList<Event> attendingEvents;
    ArrayList<Profile> linkedOtherSocialNetworkProfile;

    public boolean link(SocialNetworkContent snc)
        throws DbException
    {
        throw new DbException("not yet implemented");
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
}
