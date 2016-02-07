package de.due.ldsa.model;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

/**
 * Author: Romina (scrobart)
 *
 * If you need to serialize this, or any of it's inheritors, make sure your
 * serializer honors transient fields.
 */
public abstract class Profile extends SocialNetworkContentImpl implements Serializable {
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

	public abstract ArrayList<Long> getFollowsIds();

	public abstract void setFollowsIds(ArrayList<Long> followingIds);

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

	public abstract ArrayList<Long> getAllCommentsId();
	public abstract void setAllCommentsId(ArrayList<Long> allCommentsId);

	// ------------------------------------------------------------------------------------------------------------------
	// COMPLEX METHODS
	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * Tests whether a SocialNetworkContent is somehow related to this profile.
	 * Please do not forget to set all the IDs first.
	 * @param socialNetworkContent The Content you want to check.
	 * @return True if the content is related in any way, false if either it is not, or if the corresponding IDs are not
	 * loaded.
	 * @apiNote Please do not forget to call the set...Id methods to ensure this can work correctly.
     */
	public boolean link(SocialNetworkContent socialNetworkContent) {
		if (socialNetworkContent instanceof Comment)
		{
			if (getAllCommentsId() != null)
			{
				if (getAllCommentsId().contains(socialNetworkContent.getId()))
					return true;
			}
		}
		if (socialNetworkContent instanceof SocialNetworkInterest)
		{
			if (getInterestIds() != null)
			{
				if (getInterestIds().contains(socialNetworkContent.getId()))
					return true;
			}
		}
		if (socialNetworkContent instanceof  Media)
		{
			if (getProfilePhotoMediaId() == socialNetworkContent.getId())
				return true;
		}
		if (socialNetworkContent instanceof ProfileFeed)
		{
			if (getLastUpdateProfileFeedId() == socialNetworkContent.getId())
				return true;
			if (getProfileFeedIds() != null)
			{
				if (getProfileFeedIds().contains(socialNetworkContent.getId()))
					return true;
			}
		}
		if (socialNetworkContent instanceof Location)
		{
			if (getLastUpdateProfileFeedId() == socialNetworkContent.getId())
				return true;
		}
		if (socialNetworkContent instanceof Profile)
		{
			if (getFollowsIds() != null)
			{
				if (getFollowsIds().contains(socialNetworkContent.getId()))
						return true;
			}
			if (getFollowedByIds() != null)
			{
				if (getFollowedByIds().contains(socialNetworkContent.getId()))
					return true;
			}
			if (getFriendIds() != null)
			{
				if (getFriendIds().contains(socialNetworkContent.getId()))
					return true;
			}
			if (getLinkedOtherSocialNetworkProfileIds() != null)
			{
				if (getLinkedOtherSocialNetworkProfileIds().contains(socialNetworkContent.getId()))
					return true;
			}
		}
		if (socialNetworkContent instanceof Event)
		{
			if (getAttendingEventIds() != null)
			{
				if (getAttendingEventIds().contains(socialNetworkContent.getId()))
					return true;
			}
		}
		return false;
	}

	/**
	 * @param profilePhoto Please use setProfilePhotoMediaId instead.
	 */
	@Deprecated
	public void setProfilePhoto(Media profilePhoto) {
		setProfilePhotoMediaId(profilePhoto.getId());
	}
}
