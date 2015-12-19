package de.due.ldsa.db.model;

import de.due.ldsa.db.DbException;

import java.net.URL;
import java.util.ArrayList;

/**
 *
 */
public class Profile extends SocialNetworkContentImpl
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
    ArrayList<SocialNetworkContent> linkedOtherSocialNetworkContent;

    public boolean link(SocialNetworkContent snc)
        throws DbException
    {
        throw new DbException("not yet implemented");
    }
}
