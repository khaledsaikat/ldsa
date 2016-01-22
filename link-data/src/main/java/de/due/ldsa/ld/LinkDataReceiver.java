package de.due.ldsa.ld;

import java.util.List;

import de.due.ldsa.model.Comment;
import de.due.ldsa.model.Hashtag;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.Location;
import de.due.ldsa.model.Media;
import de.due.ldsa.model.ProfileFeed;

/**
 * @author Firas Sabbah
 *
 */
public interface LinkDataReceiver {

	/**
	 * Setting the steam of HumanProfiles
	 * 
	 * @param humanProfilesJson
	 */
	public void setHumanProfiles(String humanProfilesJson);

	/**
	 * Setting the steam of Locations
	 * 
	 * @param locationsJson
	 */
	public void setLocations(String locationsJson);

	/**
	 * Setting the stream of ProfileFeeds
	 * 
	 * @param profileFeedsJson
	 */
	public void setProfileFeeds(String profileFeedsJson);

	/**
	 * Setting the Hashtags stream
	 * 
	 * @param hashtagsJson
	 */
	public void setHashtags(String hashtagsJson);

	/**
	 * Setting the Comments stream
	 * 
	 * @param commentsJson
	 */
	public void setComments(String commentsJson);

	/**
	 * Setting the Media Steam
	 * 
	 * @param mediaJson
	 */
	public void setMedia(String mediaJson);

	/**
	 * Switch the mode of database storage on and off
	 * 
	 * @param saveToDatabase
	 */
	public void setSaveToDatabase(boolean saveToDatabase);

	/**
	 * Get the available HumanProfiles according to the fetch mode
	 * 
	 * @param fetchMode
	 * @return
	 */
	public List<HumanProfile> getHumanProfiles(Fetch fetchMode);

	/**
	 * Get the available Comments according to the fetch mode
	 * 
	 * @param fetchMode
	 * @return
	 */
	public List<Comment> getComments(Fetch fetchMode);

	/**
	 * Get the available Media according to the fetch mode
	 * 
	 * @param fetchMode
	 * @return
	 */
	public List<Media> getMedia(Fetch fetchMode);

	/**
	 * Get the available ProfileFeeds according to the fetch mode
	 * 
	 * @param fetchMode
	 * @return
	 */
	public List<ProfileFeed> getProfileFeeds(Fetch fetchMode);

	/**
	 * Get the available Locations according to the fetch mode
	 * 
	 * @param fetchMode
	 * @return
	 */
	public List<Location> getLocations(Fetch fetchMode);

	/**
	 * Get the available Hashtags according to the fetch mode
	 * 
	 * @param fetchMode
	 * @return
	 */
	public List<Hashtag> getHashtags(Fetch fetchMode);

}
