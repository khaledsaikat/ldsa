package de.due.ldsa.db.model;

import de.due.ldsa.db.DbException;

import java.net.URL;
import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 *
 */
public abstract class Profile implements SocialNetworkContent
{
    public abstract long getId();

    public abstract void setId(long ID);

    public abstract String getUsername();

    public abstract void setUsername(String username);

    public abstract URL getProfileURL();

    public abstract void setProfileURL(URL profileURL);

    public abstract String getFullname();

    public abstract void setFullname(String fullname);

    public abstract String getBio();

    public abstract void setBio(String bio);

    public abstract ArrayList<Long> getInterestIds();

    public abstract void setInterestIds(ArrayList<Long> interestIds);

    public abstract String getUserEmail();

    public abstract void setUserEmail(String userEmail);

    public abstract String getUserWebsite();

    public abstract void setUserWebsite(String userWebsite);

    public abstract long getProfilePhotoMediaId();

    public abstract void setProfilePhotoMediaId(long profilePhotoMediaId);

    public abstract long getLastUpdateProfileFeedId();

    public abstract void setLastUpdateProfileFeedId(long lastUpdateProfileFeedId);

    public abstract long getHometownLocationId();

    public abstract void setHometownLocationId(long hometownLocationId);

    public abstract ArrayList<Long> getFollowingId();

    public abstract void setFollowingId(ArrayList<Long> followingId);

    public abstract ArrayList<Long> getFollowedByIds();

    public abstract void setFollowedByIds(ArrayList<Long> followedByIds);

    public abstract ArrayList<Long> getFriendIds();

    public abstract void setFriendIds(ArrayList<Long> friendIds);

    public abstract ArrayList<Long> getProfileFeedIds();

    public abstract void setProfileFeedIds(ArrayList<Long> profileFeedIds);

    public abstract ArrayList<Long> getAttendingEventIds();

    public abstract void setAttendingEventIds(ArrayList<Long> attendingEventIds);

    public abstract ArrayList<Long> getLinkedOtherSocialNetworkProfileIds();

    public abstract void setLinkedOtherSocialNetworkProfileIds(ArrayList<Long> linkedOtherSocialNetworkProfileIds);

    //------------------------------------------------------------------------------------------------------------------
    // COMPLEX METHODS
    //------------------------------------------------------------------------------------------------------------------

    public boolean link(SocialNetworkContent socialNetworkContent)
            throws DbException
    {
        throw new DbException("not yet implemented");
    }


}
