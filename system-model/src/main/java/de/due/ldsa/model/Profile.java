package de.due.ldsa.model;

import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.exception.DbException;

import com.datastax.driver.mapping.annotations.Transient;
import com.google.gson.Gson;

import java.io.Serializable;
import java.net.URL;
import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 *
 */
public abstract class Profile implements SocialNetworkContent, LinkedWithOtherObjects, Serializable {
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

	public abstract ArrayList<Long> getFollowingIds();

	public abstract void setFollowingIds(ArrayList<Long> followingIds);

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

	// ------------------------------------------------------------------------------------------------------------------
	// COMPLEX METHODS
	// ------------------------------------------------------------------------------------------------------------------

	public boolean link(SocialNetworkContent socialNetworkContent) throws DbException {
		throw new DbException("not yet implemented");
	}

	@Transient
	private ArrayList<SocialNetworkInterest> interestData;

	/**
	 * Gets the Interests associated with this profile from the database. If
	 * they were already queried, they will be retrieved from the database.
	 *
	 * @return An ArrayList containing the Interests
	 * @throws DbException
	 *             Thrown if there are invalid values in the ArrayList
	 *             containing the IDs.
	 */
	public ArrayList<SocialNetworkInterest> getInterests() throws DbException {
		if (interestData == null) {
			interestData = new ArrayList<SocialNetworkInterest>();
			Database db = DatabaseImpl.getInstance();
			ArrayList<Long> ids = getInterestIds();
			if (ids != null) {
				for (Long l : ids) {
					interestData.add(db.getInterest(l));
				}
			}
		}
		return interestData;
	}

	@Transient
	private Media profilePhoto;

	/**
	 * Retrieves the current Profile Photo from the database, or from memory if
	 * it was set/read before.
	 *
	 * @return A Media Object representing the Profile Photo.
	 * @throws DbException
	 *             Thrown, if the ID is not set, or it does not exist.
	 */
	public Media getProfilePhoto() throws DbException {
		if (profilePhoto == null) {
			Database db = DatabaseImpl.getInstance();
			profilePhoto = db.getMedia(getProfilePhotoMediaId());
		}
		return profilePhoto;
	}

	public void setProfilePhoto(Media profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	@Transient
	public ProfileFeed lastUpdateData;

	/**
	 * Retrieves the last posted update from database. If it was already
	 * retrieved, or set, it will be returned from memory.
	 *
	 * @return The last posted Update as a ProfileFeed
	 * @throws DbException
	 *             Thrown if the ID is not set correctly.
	 */
	public ProfileFeed getLastUpdate() throws DbException {
		if (lastUpdateData == null) {
			lastUpdateData = DatabaseImpl.getInstance().getProfileFeed(getLastUpdateProfileFeedId());
		}
		return lastUpdateData;
	}

	public void setLastUpdate(ProfileFeed profileFeedData) {
		this.lastUpdateData = profileFeedData;
	}

	@Transient
	private Location homeTown;

	/**
	 * Gets the Home Town. If it is not yet in memory, it will be loaded from
	 * database.
	 *
	 * @return The Location associated with this Profile.
	 * @throws DbException
	 *             Thrown if the Hometown Location ID is not correctly set.
	 */
	public Location getHomeTown() throws DbException {
		if (homeTown == null) {
			homeTown = DatabaseImpl.getInstance().getLocation(getHometownLocationId());
		}
		return homeTown;
	}

	public void setHomeTown(Location homeTown) {
		this.homeTown = homeTown;
	}

	@Transient
	private ArrayList<Profile> follows;

	public ArrayList<Profile> getFollows() {
		if (follows == null) {
			follows = new ArrayList<Profile>();
			ArrayList<Long> followingIds = getFollowingIds();
			if (followingIds != null) {
				Database db = DatabaseImpl.getInstance();
				for (Long id : followingIds) {
					if (db.isHuman(id)) {
						follows.add(db.getHumanProfile(id));
					} else {
						follows.add(db.getCoopProfile(id));
					}
				}
			}
		}
		return follows;
	}

	@Override
	public void prepareSave() {
		if (interestData != null) {
			setInterestIds(new ArrayList<Long>());
			for (SocialNetworkInterest interest : interestData) {
				getInterestIds().add(interest.getId());
			}
		}
		if (profilePhoto != null) {
			setProfilePhotoMediaId(profilePhoto.getId());
		}
		if (lastUpdateData != null) {
			setLastUpdateProfileFeedId(lastUpdateData.getId());
		}
		if (homeTown != null) {
			setHometownLocationId(homeTown.getId());
		}
		if (follows != null) {
			setFollowingIds(new ArrayList<Long>());
			for (Profile p : follows) {
				getFollowingIds().add(p.getId());
			}
		}
	}

	public String getJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
