package de.due.ldsa.db;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.due.ldsa.model.*;

public interface Database {

	/**
	 * Closes the connection the Database
	 *
	 * @throws IOException Thrown if the close request fails.
	 */
	void close() throws IOException;

	/**
	 * Call this function if the connection died for some reason, and you need to establish it again.
	 */
	void reconnect() throws DbException;

	/**
	 * This method is intended for testing purposes. You probably do not want to
	 * call this AT ALL, because it deletes ALL the contents of a table.
	 *
	 * @param tName The name of the table you want to delete
	 */
	void truncateTable(String tName) throws DbException;

	/**
	 * Saves a Social Network into the Database.
	 *
	 * @param sn The social network you want to save.
	 */
	void saveSocialNetwork(SocialNetwork sn) throws DbException;

	/**
	 * Fetches a social Network
	 *
	 * @param i The ID of the social Network you want to get.
	 * @return The specified social network.
	 */
	SocialNetwork getSocialNetwork(int i) throws DbException;

	/**
	 * Use this to assign an ID to a new social network you want to save.
	 *
	 * @return A free ID
	 * @throws DbException Thrown if something
	 */
	long getNextSocialNetworkID() throws DbException;

	/**
	 * Saves a profile feed into the database.
	 * @param pf The Profile Feed you need to save.
	 */
	void saveProfileFeed(ProfileFeed pf) throws DbException;

	/**
	 * Fetches a specific Profile Feed from the database.
	 * @param id The ID of the profile feed you want to fetch.
	 * @return The specified ProfileFeed
	 */
	ProfileFeed getProfileFeed(long id) throws DbException;

	/**
	 * Saves a Media-Object into the Database
	 * @param m The Media object you want to save
	 */
	void saveMedia(Media m) throws DbException;

	/**
	 * Fetches a Media Object from the Database.
	 * @param id The ID of the Media Object you want to get.
	 * @return The specified Media object.
	 */
	Media getMedia(long id) throws DbException;

	/**
	 * Saves a Location into the Database
	 * @param l The Location you want to save.
	 */
	void saveLocation(LocationImpl l) throws DbException;

	/**
	 * Fetches a Location from the Database
	 * @param id The ID of the Location you want to get.
	 * @return The specified Location
	 */
	LocationImpl getLocation(long id) throws DbException;

	/**
	 * Saves an Organisation Place into the Database.
	 * @param op The Organisation Place you want to save
	 */
	void saveCoopLocation(CoopLocation op) throws DbException;

	/**
	 * Fetches an Organisation Place into the Database
	 *
	 * @param id The ID of the Organisation Place you want to fetch.
	 * @return The specified organisation place
	 */
	CoopLocation getCoopPlace(long id) throws DbException;

	/**
	 * Writes a Company Profile to the database.
	 *
	 * @param cp
	 *            The Company Profile you want to persist.
	 */
	void saveCoopProfile(CoopProfile cp) throws DbException;

	/**
	 * Fetches a Company Profile from the Database
	 * @param id The ID of the company Profile you want to fetch
	 * @return The specified company Profile
	 */
	CoopProfile getCoopProfile(long id) throws DbException;

	/**
	 * Saves a human Profile into the Database
	 * @param hp The Human Profile you want to save
	 * @throws DbException Thrown if the ID of that Human Profile is already occupied by a company profile
	 */
	void saveHumanProfile(HumanProfile hp) throws DbException;

	/**
	 * Fetches a human Profile from the Database
	 * @param id The ID of the Human Profile you want to fetch
	 * @return The specified Human Profile
	 */
	HumanProfile getHumanProfile(long id) throws DbException;

	/**
	 * Saves an Event into the Database
	 * @param id The Event you want to save.
	 */
	void saveEvent(Event id) throws DbException;

	/**
	 * Fetches an Event from the Database
	 * @param id The ID of the Event you want to fetch.
	 * @return The specified Event
	 */
	Event getEvent(long id) throws DbException;

	/**
	 * Saves a Comment into the Database
	 * @param c The Comment you want to save.
	 */
	void saveComment(Comment c) throws DbException;

	/**
	 * Fetches a Comment from the Database
	 * @param id The ID of the Comment you want to get.
	 * @return The Comment specified by the ID.
	 */
	Comment getComment(long id) throws DbException;

	/**
	 * Tests if a profile ID belongs to a human or a company.
	 * @param id The ID of the Profile you want to check.
	 * @return True if the ID belongs to a human Profile, false if the ID belongs to a company.
	 * @throws DbException Thrown if it is neither human nor company, or it does not exist at all.
	 */
	boolean isHuman(long id) throws DbException;

	/**
	 * Use this to figure out the ID of a new CoopProfile or HumanProfile.
	 *
	 * @return A free ID
	 * @throws DbException
	 */
	long getNextProfileId() throws DbException;

	/**
	 * Use this to figure out a new ID for a Media Object
	 * @return A free ID
	 * @throws DbException Thrown if something goes wrong while querying the database.
	 */
	long getNextMediaId() throws DbException;

	/**
	 * Use this to figure out a new ID for a Comment
	 * @return A free ID
	 * @throws DbException Thrown if something goes wrong while querying the database.
	 */
	long getNextCommentId() throws DbException;

	/**
	 * Use this to figure out an ID for a new Profile Feed
	 * @return A free ID
	 * @throws DbException Thrown if something goes wrong while querying the database.
	 */
	long getNextProfileFeedId() throws DbException;

	/**
	 * Use this to figure out an empty ID for either an regular Location or an
	 * CoopLocation. Location and Organisation place share the same Number
	 * Sequence.
	 *
	 * @return An ID
	 * @throws DbException
	 *             Thrown if querying the ID from the Database fails.
	 */
	long getNextLocationId() throws DbException;

	/**
	 * Automagicially determines wheter a profile belongs to a human or a
	 * corporate entity, and returns its profile.
	 *
	 * @param id
	 *            The ID of the profile you want to get.
	 * @return Either a CoopProfile or a HumanProfile
	 */
	Profile autoGetProfile(long id) throws DbException;

	/**
	 * Saves a HumanProfile or a CoopProfile into the Database
	 * @param p The Profile you want to save
	 * @throws DbException Thrown if it is neither a HumanProfile nor a CoopProfile, or something goes wrong while
	 * querying the database.
	 */
	void autoSaveProfile(Profile p) throws DbException;

	/**
	 * Tests if an ID belongs to an Organisation Place
	 *
	 * @param id The ID of a Location you want to test.
	 * @return True if it is an Organisation Place, false if it is a Location
	 */
	boolean isCoopPlace(long id) throws DbException;

	/**
	 * @return An ArrayList containing all the human profiles
	 * @throws DbException
	 * @implNote Lists will get troublesome in terms of memory, if the database grows larger.
	 */
	List<HumanProfile> getAllHumanProfiles() throws DbException;

	/**
	 * @implNote Lists might become troublesome if the database grows very large.
	 * @return An ArrayList containing all the comments.
	 * @throws DbException
	 */
	List<Comment> getAllComments() throws DbException;

	/**
	 * Gets all the Locations from the Database.
	 *
	 * @return An ArrayList containing all the Locations and OrganisationPlaces in the Database
	 * @throws DbException Thrown, if something goes wrong when querying the Database
	 */
	List<Location> getAllLocations() throws DbException;

	/**
	 * Gets all the Profile Feeds from the database.
	 * @return An ArrayList containing all the profile feeds;
	 * @throws DbException Thrown, if something goes wrong when querying the database.
	 */
	List<ProfileFeed> getAllProfileFeeds() throws DbException;

	/**
	 * Fetches all Media from the Database
	 * @implNote Lists might become troublesome when the Database grows larger.
	 * @return An ArrayList containing all Media in the Database
	 * @throws DbException Thrown if something with the Database goes wrong.
	 */
	List<Media> getAllMedia() throws DbException;

	/**
	 * Gets all Profiles from a specified Social Network
	 * @param snId The ID of the social network you need the profiles from.
	 * @return A List of all the profiles in that social network.
	 */
	List<Profile> getAllProfilesFromSocialNetwork(int snId) throws DbException;

	/**
	 * Gets all Profile feeds from a specified Social Network
	 * @param snId The ID of the Social Network you want to query.
	 * @return A List of all the Profile Feeds from the specified social network.
	 */
	List<ProfileFeed> getAllProfileFeedsFromSocialNetwork(int snId) throws DbException;

	/**
	 * Gets all the Media from a specified Social Network
	 * @param snId The ID of the Social Network you want to query.
	 * @return A List of all Media from the specified social Network
	 */
	List<Media> getAllMediaFromSocialNetwork(int snId) throws DbException;

	/**
	 * Gets all SocialNetworkContent from a social network specified by the ID.
	 *
	 * @param snId The ID of the social network you want to query.
	 * @return An Iterable iterating over all the content.
	 * @implNote For more information about the Accessors and Results check this: https://docs.datastax.com/en/developer/java-driver/2.1/common/drivers/reference/accessorAnnotatedInterfaces.html
	 */
	List<SocialNetworkContent> getAllContentFromSocialNetwork(int snId) throws DbException;

	/**
	 * Use this to figure out the ID for a new Event
	 * @return A free ID
	 */
	long getNextEventId() throws DbException;

	/**
	 * Saves an hashtag into the database. If it is already known, this does nothing at all.
	 *
	 * @param hashtag The hashtag you want to save
	 * @throws DbException Thrown if something goes wrong while querying the database.
	 */
	void saveHashtag(Hashtag hashtag) throws DbException;

	/**
	 * Returns a List of all the Hashtag objects in the database.
	 * @return A List of hashtags.
	 * @throws DbException Thrown, if something goes wrong while querying the database
	 */
	List<Hashtag> getAllHashtags() throws DbException;

	/**
	 * Gets a list of all ProfileFeeds and comments in which a specific hashtag is mentioned.
	 * @param hashtag The hashtag you want to analyze.
	 * @return An ArrayList containing all the ProfileFeeds and Comments which mention the hashtag.
	 * @throws DbException Thrown if something goes wrong while querying the database.
	 */
	List<SocialNetworkContent> getHashtagUsedAtList(Hashtag hashtag) throws DbException;

	/**
	 * Gets a list of all Comments and ProfileFeeds in which a specific Hashtag was mentioned.
	 * @param hashtag The Hashtag you need to know more about. Should start with "#"
	 * @return An ArrayList containing all Comments and ProfileFeeds.
	 * @throws DbException Thrown if something goes wrong while querying the database
	 */
	List<SocialNetworkContent> getHashtagUsedAtList(String hashtag) throws DbException;

	/**
	 * Figures out how many times a hashtag was mentioned in all comments or profileFeeds.
	 *
	 * @param hashtag The Hashtag you need to know more about
	 * @return Amount of comments and profiles added together.
	 * @throws DbException Thrown if something goes wrong while querying the database
	 */
	long getHashtagTimesUsed(Hashtag hashtag) throws DbException;

	/**
	 * Use this to figure out an empty ID for either an HumanProfile, an CoopProfile, an CoopLocation, an Location
	 * or an Event.
	 * @return An ID as Long
	 * @throws DbException Thrown, if something goes wrong while querying the database.
	 */
	long getNextSocialNetworkInterestId() throws DbException;

	/**
	 * Gets a list of Profile Feeds related to a Profile
	 *
	 * @param p The Profile you want to analyze.
	 * @return An ArrayList of Profile Feed IDs related to that profile.
	 */
	ArrayList<Long> getProfileProfileFeeds(Profile p) throws DbException;

	/**
	 * Gets a list of all Comments made by a profile.
	 *
	 * @param p The Profile you want to analyze
	 * @return An ArrayList containing all the Comment IDs
	 */
	ArrayList<Long> getProfileAllComments(Profile p) throws DbException;

	/**
	 * Count how often an Location is used for events.
	 *
	 * @param l The Location you want to analyze.
	 * @return How often the Location was used for an event.
	 */
	long locationTimesUsed(Location l) throws DbException;

	/**
	 * Determines whether an Location ID correspondends to an Location or an CoopLocation and gets the object.
	 *
	 * @param l The ID of an Location or an CoopLocation
	 * @return Location or an CoopLocation
	 */
	Location autoGetLocation(long l) throws DbException;

	//@Firas: What are these methods for?
	ArrayList<Long> getProfileRelationshipPersons(HumanProfile humanProfile) throws DbException;

	ArrayList<Long> getProfileLinkedOtherSocialNetworkProfileIds(HumanProfile humanProfile) throws DbException;

	ArrayList<Long> getProfileFriendsIds(HumanProfile humanProfile) throws DbException;

	ArrayList<Long> getProfileFollowsIds(HumanProfile humanProfile) throws DbException;

	ArrayList<Long> getProfileFollowedByIds(HumanProfile humanProfile) throws DbException;

	/**
	 * Returns the Organisation Place of a CoopProfile
	 * @param op The Organisation Place form
	 * @return The CoopProfile associated to that CoopLocation
	 * @throws DbException
	 */
	CoopProfile coopLocationGetCoopProfile(CoopLocation op) throws DbException;

	/**
	 * Saves more than one Content element at a time.
	 *
	 * @param set Any Iterable object that iterates over content.
	 * @throws DbException Thrown if an unknown type of content is encountered or if something goes wrong while querying
	 *                     the Database.
	 */
	void SaveSet(Iterable<SocialNetworkContent> set) throws DbException;
}
