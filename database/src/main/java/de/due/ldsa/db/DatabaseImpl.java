package de.due.ldsa.db;

/*
    References:

    Cassandra basics:
    http://www.tutorialspoint.com/cassandra/

    Persistence with Cassandra:
    https://docs.datastax.com/en/developer/java-driver/2.1/java-driver/reference/crudOperations.html
 */

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Truncate;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import de.due.ldsa.db.accessors.*;
import de.due.ldsa.db.codecs.*;
import de.due.ldsa.model.*;
import org.apache.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Provides access to a Cassandra-based Database.
 * @author scrobart
 */
public class DatabaseImpl implements Database, Closeable {
	private static DatabaseImpl singleton;
	private static Session session;
	private Mapper<SocialNetwork> socialNetworkMapper;
	private Mapper<ProfileFeed> profileFeedMapper;
	private Mapper<Media> mediaMapper;
	private Mapper<LocationImpl> locationMapper;
	private Mapper<OrganisationPlace> organisationPlaceMapper;
	private Mapper<CoopProfile> coopProfileMapper;
	private Mapper<HumanProfile> humanProfileMapper;
	private Mapper<Event> eventMapper;
	private Mapper<Comment> commentMapper;
	private Mapper<Hashtag> hashtagMapper;

	private MappingManager manager;

	private HumanProfileAccessor humanProfileAccessor;
	private CommentAccessor commentAccessor;
	private LocationAccessor locationAccessor;
	private OrganisationPlaceAccessor organisationPlaceAccessor;
	private ProfileFeedAccessor profileFeedAccessor;
	private MediaAccessor mediaAccessor;
	private CoopProfileAccessor coopProfileAccessor;
	private EventAccessor eventAccessor;
	private HashtagAccessor hashtagAccessor;
	private ProfileAccessor profileAccessor;

	private Logger logger;

	/**
	 * Closes the connection the Database
	 *
	 */
	@Override
	public void close() {
		session.close();
	}

	/**
	 * Connects to Cassandra
	 */
	private DatabaseImpl() {
		reconnect();
	}

	/**
	 * Call this function if the connection died for some reason, and you need to establish it again.
	 */
	public void reconnect() {
		logger = Logger.getLogger("database");
		logger.info("(Re-)Connecting to Cassandra...");
		Cluster cluster = Cluster.builder().addContactPoint(DatabaseConfiguration.getIP()).build();
		session = cluster.connect("ldsa");

		CodecRegistry registry = cluster.getConfiguration().getCodecRegistry();
		registry.register(new OffsetDateTimeCodec());
		registry.register(new LongArrayListCodec());
		registry.register(new StringArrayListCodec());
		registry.register(new ByteArrayCodec());
		registry.register(new UrlCodec());
		registry.register(new LocalDateCodec());
		registry.register(new RelationshipStatusCodec());
		registry.register(new SexCodec());
		registry.register(new InterestKindCodec());
		registry.register(new InterestKindArrayListCodec());
		registry.register(new UrlArrayListCodec());

		logger.info("Generating Cassandra Mappers...");
		socialNetworkMapper = new MappingManager(session).mapper(SocialNetwork.class);
		profileFeedMapper = new MappingManager(session).mapper(ProfileFeed.class);
		mediaMapper = new MappingManager(session).mapper(Media.class);
		locationMapper = new MappingManager(session).mapper(LocationImpl.class);
		organisationPlaceMapper = new MappingManager(session).mapper(OrganisationPlace.class);
		coopProfileMapper = new MappingManager(session).mapper(CoopProfile.class);
		humanProfileMapper = new MappingManager(session).mapper(HumanProfile.class);
		eventMapper = new MappingManager(session).mapper(Event.class);
		commentMapper = new MappingManager(session).mapper(Comment.class);
		hashtagMapper = new MappingManager(session).mapper(Hashtag.class);

		manager = new MappingManager(session);

		logger.info("Generating Cassandra Accessors...");
		humanProfileAccessor = manager.createAccessor(HumanProfileAccessor.class);
		commentAccessor = manager.createAccessor(CommentAccessor.class);
		locationAccessor = manager.createAccessor(LocationAccessor.class);
		organisationPlaceAccessor = manager.createAccessor(OrganisationPlaceAccessor.class);
		profileFeedAccessor = manager.createAccessor(ProfileFeedAccessor.class);
		mediaAccessor = manager.createAccessor(MediaAccessor.class);
		coopProfileAccessor = manager.createAccessor(CoopProfileAccessor.class);
		eventAccessor = manager.createAccessor(EventAccessor.class);
		hashtagAccessor = manager.createAccessor(HashtagAccessor.class);
		profileAccessor = manager.createAccessor(ProfileAccessor.class);

		logger.trace("Database is all set and ready to go!");
	}

	/**
	 * Provides an instance of this singleton. Allows reading and writing to a Cassandra-based Database
	 * @return An Instance of this singleton.
	 */
	public static Database getInstance() {
		if (singleton == null) {
			singleton = new DatabaseImpl();
		}
		return singleton;
	}

	/**
	 * This method is intended for testing purposes. You probably do not want to
	 * call this AT ALL, because it deletes ALL the contents of a table.
	 * 
	 * @param tName
	 *            The name of the table you want to delete
	 */
	public void truncateTable(String tName) {
		Truncate t = QueryBuilder.truncate("ldsa", tName);
		session.execute(t);
	}

	/**
	 * Saves a Social Network into the Database.
	 * @param sn The social network you want to save.
	 */
	public void saveSocialNetwork(SocialNetwork sn) {
		socialNetworkMapper.save(sn);
	}

	/**
	 * Fetches a social Network
	 * @param i The ID of the social Network you want to get.
	 * @return The specified social network.
	 */
	public SocialNetwork getSocialNetwork(int i) {
		return socialNetworkMapper.get(i);
	}

	/**
	 * Use this to assign an ID to a new social network you want to save.
	 *
	 * @return A free ID
	 * @throws DbException Thrown if something
	 */
	public long getNextSocialNetworkID() {
		ResultSet rs1 = session.execute("SELECT MAX(id) FROM socialNetworks");
		return rs1.one().getLong(0) + 1;
	}

	/**
	 * Saves a profile feed into the database.
	 *
	 * @param pf The Profile Feed you need to save.
	 */
	public void saveProfileFeed(ProfileFeed pf) {
		profileFeedMapper.save(pf);
	}

	/**
	 * Fetches a specific Profile Feed from the database.
	 * @param id The ID of the profile feed you want to fetch.
	 * @return The specified ProfileFeed
	 */
	public ProfileFeed getProfileFeed(long id) {
		return profileFeedMapper.get(id);
	}

	/**
	 * Saves a Media-Object into the Database
	 * @param m The Media object you want to save
	 */
	public void saveMedia(Media m) {
		mediaMapper.save(m);
	}

	/**
	 * Fetches a Media Object from the Database.
	 * @param id The ID of the Media Object you want to get.
	 * @return The specified Media object.
	 */
	public Media getMedia(long id) {
		return mediaMapper.get(id);
	}

	/**
	 * Saves a Location into the Database
	 * @param l The Location you want to save.
	 */
	public void saveLocation(LocationImpl l) {
		locationMapper.save(l);
	}

	/**
	 * Fetches a Location from the Database
	 * @param id The ID of the Location you want to get.
	 * @return The specified Location
	 */
	public LocationImpl getLocation(long id) {
		return locationMapper.get(id);
	}

	/**
	 * Saves an Organisation Place into the Database.
	 * @param op The Organisation Place you want to save
	 */
	public void saveOrganisationPlace(OrganisationPlace op) {
		organisationPlaceMapper.save(op);
	}

	/**
	 * Fetches an Organisation Place into the Database
	 * @param id The ID of the Organisation Place you want to fetch.
	 * @return The specified organisation place
	 */
	public OrganisationPlace getOrganisationPlace(long id) {
		return organisationPlaceMapper.get(id);
	}

	/**
	 * Writes a Company Profile to the database.
	 *
	 * @param cp
	 *            The Company Profile you want to persist.
	 */
	public void saveCoopProfile(CoopProfile cp) throws DbException {
		if (getHumanProfile(cp.getId()) != null) {
			// This needs to be done because Human IDs and Company IDs share the
			// same number sequence.
			throw new DbException(String.format("The ID specified in that company profile is already used by a human ID:%s", Long.toString(cp.getId())));
		}
		coopProfileMapper.save(cp);
	}

	/**
	 * Fetches a Company Profile from the Database
	 * @param id The ID of the company Profile you want to fetch
	 * @return The specified company Profile
	 */
	public CoopProfile getCoopProfile(long id) {
		return coopProfileMapper.get(id);
	}

	/**
	 * Saves a human Profile into the Database
	 * @param hp The Human Profile you want to save
	 * @throws DbException Thrown if the ID of that Human Profile is already occupied by a company profile
	 */
	public void saveHumanProfile(HumanProfile hp) throws DbException {
		if (getCoopProfile(hp.getId()) != null) {
			// This needs to be done because Human IDs and Company IDs share the
			// same number sequence.
			throw new DbException("The ID specified in that company profile is already used by a company ID: "
					+ Long.toString(hp.getId()));
		}
		humanProfileMapper.save(hp);
	}

	/**
	 * Fetches a human Profile from the Database
	 * @param id The ID of the Human Profile you want to fetch
	 * @return The specified Human Profile
	 */
	public HumanProfile getHumanProfile(long id) {
		return humanProfileMapper.get(id);
	}

	/**
	 * Saves an Event into the Database
	 * @param id The Event you want to save.
	 */
	public void saveEvent(Event id) {
		eventMapper.save(id);
	}

	/**
	 * Fetches an Event from the Database
	 * @param id The ID of the Event you want to fetch.
	 * @return The specified Event
	 */
	public Event getEvent(long id) {
		return eventMapper.get(id);
	}

	/**
	 * Saves a Comment into the Database
	 * @param c The Comment you want to save.
	 */
	public void saveComment(Comment c) {
		commentMapper.save(c);
	}

	/**
	 * Fetches a Comment from the Database
	 * @param id The ID of the Comment you want to get.
	 * @return The specified Comment.
	 */
	public Comment getComment(long id) {
		return commentMapper.get(id);
	}

	/**
	 * Tests if a profile ID belongs to a human or a company.
	 * @param id The ID of the Profile you want to check.
	 * @return True if the ID belongs to a human Profile, false if the ID belongs to a company.
	 * @throws DbException Thrown if it is neither human nor company, or it does not exist at all.
	 */
	public boolean isHuman(long id) throws DbException {
		HumanProfile hp = getHumanProfile(id);
		if (hp != null)
			return true;

		CoopProfile cp = getCoopProfile(id);
		if (cp != null)
			return false;

		throw new DbException("The specified ID is neither a human nor a company profile:" + Long.toString(id));
	}

	/**
	 * Use this to figure out the ID of a new CoopProfile or HumanProfile.
	 *
	 * @return A free ID
	 * @throws DbException
	 */
	public long getNextProfileId() {
		//HumanProfile, CoopProfile, OrganisationPlace, Location & Event now share the same Number Sequence, to satisfy
		//SocialNetworkInterest.
		return getNextSocialNetworkInterestId();
	}

	/**
	 * Use this to figure out a new ID for a Media Object
	 *
	 * @return A free ID
	 * @throws DbException Thrown if something goes wrong while querying the database.
	 */
	public long getNextMediaId() {
		ResultSet rs1 = session.execute("SELECT MAX(id) FROM media");
		return rs1.one().getLong(0) + 1;
	}

	/**
	 * Use this to figure out a new ID for a Comment
	 *
	 * @return A free ID
	 * @throws DbException Thrown if something goes wrong while querying the database.
	 */
	public long getNextCommentId() {
		ResultSet rs1 = session.execute("SELECT MAX(id) FROM comments");
		return rs1.one().getLong(0) + 1;
	}

	/**
	 * Use this to figure out an ID for a new Profile Feed
	 * @return A free ID
	 * @throws DbException Thrown if something goes wrong while querying the database.
	 */
	public long getNextProfileFeedId() {
		ResultSet rs1 = session.execute("SELECT MAX(id) FROM profileFeeds");
		return rs1.one().getLong(0) + 1;
	}

	/**
	 * Use this to figure out an empty ID for either an regular Location or an
	 * OrganisationPlace. Location and Organisation place share the same Number
	 * Sequence.
	 *
	 * @return An ID
	 * @throws DbException
	 *             Thrown if querying the ID from the Database fails.
	 */
	public long getNextLocationId() {
		//HumanProfile, CoopProfile, OrganisationPlace, Location & Event now share the same Number Sequence, to satisfy
		//SocialNetworkInterest.
		return getNextSocialNetworkInterestId();
	}

	/**
	 * Automagicially determines wheter a profile belongs to a human or a
	 * corporate entity, and returns its profile.
	 *
	 * @param id
	 *            The ID of the profile you want to get.
	 * @return Either a CoopProfile or a HumanProfile
	 */
	public Profile autoGetProfile(long id) {
		if (isHuman(id)) {
			return getHumanProfile(id);
		} else {
			return getCoopProfile(id);
		}
	}

	/**
	 * Saves a HumanProfile or a CoopProfile into the Database
	 * @param p The Profile you want to save
	 * @throws DbException Thrown if it is neither a HumanProfile nor a CoopProfile, or something goes wrong while
	 * querying the database.
	 */
	public void autoSaveProfile(Profile p) throws DbException {
		if (p instanceof HumanProfile) {
			saveHumanProfile((HumanProfile) p);
		} else if (p instanceof CoopProfile) {
			saveCoopProfile((CoopProfile) p);
		} else {
			throw new DbException("You supplied neither a HumanProfile nor a CoopProfile.");
		}
	}

	/**
	 * Tests if an ID belongs to an Organisation Place
	 *
	 * @param id The ID of a Location you want to test.
	 * @return True if it is an Organisation Place, false if it is a Location
	 */
	public boolean isOrganisationPlace(long id) throws DbException {
		OrganisationPlace op = getOrganisationPlace(id);
		if (op != null)
			return true;

		Location l = getLocation(id);
		if (l != null)
			return false;

		throw new DbException(
				"The specified ID is neither a Location nor an OrganisationPlace:" + Long.toString(id));
	}

	/**
	 * @return An ArrayList containing all the human profiles
	 * @throws DbException
	 * @deprecated Please use the Iterable instead! Lists will get troublesome in terms of memory, if the database grows larger.
	 */
	@Override
	public List<HumanProfile> getAllHumanProfiles() {
		Result<HumanProfile> humanprofiles = humanProfileAccessor.getAll();

		return humanprofiles.all();
	}

	/**
	 * @deprecated Lists might become troublesome if the database grows very large. Please use the Iterable instead.
	 * @return An ArrayList containing all the comments.
	 * @throws DbException
	 */
	@Override
	public List<Comment> getAllComments() {

		Result<Comment> comments = commentAccessor.getAll();

		return comments.all();
	}

	/**
	 * Gets all the Locations from the Database.
	 *
	 * @return An ArrayList containing all the Locations and OrganisationPlaces in the Database
	 * @throws DbException Thrown, if something goes wrong when querying the Database
	 */
	@Override
	public List<Location> getAllLocations() {
		Result<LocationImpl> locations = locationAccessor.getAll();
		Result<OrganisationPlace> organisationPlaces = organisationPlaceAccessor.getAll();

		List<Location> result = new ArrayList<>();
		result.addAll(locations.all());
		result.addAll(organisationPlaces.all());
		return result;
	}

	/**
	 * Gets all the Profile Feeds from the database.
	 * @return An ArrayList containing all the profile feeds;
	 * @throws DbException Thrown, if something goes wrong when querying the database.
	 */
	@Override
	public List<ProfileFeed> getAllProfileFeeds() {
		Result<ProfileFeed> profileFeeds = profileFeedAccessor.getAll();

		return profileFeeds.all();
	}

	/**
	 * Fetches all Media from the Database
	 * @deprecated Lists might become troublesome when the Database grows larger. Please use getAllMediaAsIterable()
	 * instead.
	 * @return An ArrayList containing all Media in the Database
	 * @throws DbException Thrown if something with the Database goes wrong.
	 */
	@Override
	public List<Media> getAllMedia() {
		Result<Media> mediaResult = mediaAccessor.getAll();

		return mediaResult.all();
	}

	/**
	 * Gets all Profiles from a specified Social Network
	 * @param snId The ID of the social network you need the profiles from.
	 * @return A List of all the profiles in that social network.
	 */
	public List<Profile> getAllProfilesFromSocialNetwork(int snId) {
		Result<HumanProfile> humanProfiles = humanProfileAccessor.getAllFromSocialNetwork(snId);
		Result<CoopProfile> coopProfiles = coopProfileAccessor.getAllFromSocialNetwork(snId);

		ArrayList<Profile> result = new ArrayList<>();
		result.addAll(humanProfiles.all());
		result.addAll(coopProfiles.all());
		return result;
	}

	/**
	 * Gets all Profile feeds from a specified Social Network
	 * @param snId The ID of the Social Network you want to query.
	 * @return A List of all the Profile Feeds from the specified social network.
	 */
	public List<ProfileFeed> getAllProfileFeedsFromSocialNetwork(int snId) {
		Result<ProfileFeed> profileFeeds = profileFeedAccessor.getAllFromSocialNetwork(snId);

		return profileFeeds.all();
	}

	/**
	 * Gets all the Media from a specified Social Network
	 * @param snId The ID of the Social Network you want to query.
	 * @return A List of all Media from the specified social Network
	 */
	public List<Media> getAllMediaFromSocialNetwork(int snId) {
		Result<Media> mediaResult = mediaAccessor.getAllFromSocialNetwork(snId);

		return mediaResult.all();
	}

	/**
	 * Gets all SocialNetworkContent from a social network specified by the ID.
	 *
	 * @param snId The ID of the social network you want to query.
	 * @return An Iterable iterating over all the content.
	 * @implNote For more information about the Accessors and Results check this: https://docs.datastax.com/en/developer/java-driver/2.1/common/drivers/reference/accessorAnnotatedInterfaces.html
	 */
	public List<SocialNetworkContent> getAllContentFromSocialNetwork(int snId)
	{
		Result<Comment> comments = commentAccessor.getAllFromSocialNetwork(snId);
		Result<Media> medias = mediaAccessor.getAllFromSocialNetwork(snId);
		Result<ProfileFeed> profileFeeds = profileFeedAccessor.getAllFromSocialNetwork(snId);
		Result<HumanProfile> humanProfiles = humanProfileAccessor.getAllFromSocialNetwork(snId);
		Result<CoopProfile> coopProfiles = coopProfileAccessor.getAllFromSocialNetwork(snId);
		Result<OrganisationPlace> organisationPlaces = organisationPlaceAccessor.getAllFromSocialNetwork(snId);
		Result<LocationImpl> locations = locationAccessor.getAllFromSocialNetwork(snId);
		Result<Event> events = eventAccessor.getAllFromSocialNetwork(snId);

		ArrayList<SocialNetworkContent> result = new ArrayList<>();
		result.addAll(comments.all());
		result.addAll(medias.all());
		result.addAll(profileFeeds.all());
		result.addAll(humanProfiles.all());
		result.addAll(coopProfiles.all());
		result.addAll(organisationPlaces.all());
		result.addAll(locations.all());
		result.addAll(events.all());

		return result;
	}

	/**
	 * Use this to figure out the ID for a new Event
	 * @return A free ID
	 */
	public long getNextEventId() {
		//HumanProfile, CoopProfile, OrganisationPlace, Location & Event now share the same Number Sequence, to satisfy
		//SocialNetworkInterest.
		return getNextSocialNetworkInterestId();
	}

	/**
	 * Saves an hashtag into the database. If it is already known, this does nothing at all.
	 *
	 * @param hashtag The hashtag you want to save
	 * @throws DbException Thrown if something goes wrong while querying the database.
	 */
	@Override
	public void saveHashtag(Hashtag hashtag) {
		hashtagMapper.save(hashtag);
	}

	/**
	 * Returns a List of all the Hashtag objects in the database.
	 * @return A List of hashtags.
	 * @throws DbException Thrown, if something goes wrong while querying the database
	 */
	@Override
	public List<Hashtag> getAllHashtags() {
		return hashtagAccessor.getHashtags().all();
	}

	/**
	 * Gets a list of all ProfileFeeds and comments in which a specific hashtag is mentioned.
	 * @param hashtag The hashtag you want to analyze.
	 * @return An ArrayList containing all the ProfileFeeds and Comments which mention the hashtag.
	 * @throws DbException Thrown if something goes wrong while querying the database.
	 */
	public List<SocialNetworkContent> getHashtagUsedAtList(Hashtag hashtag)
	{
		List<SocialNetworkContent> result = getHashtagUsedAtList(hashtag.getTitle());
		hashtag.setUsedAtList(result);
		return result;
	}

	/**
	 * Gets a list of all Comments and ProfileFeeds in which a specific Hashtag was mentioned.
	 * @param hashtag The Hashtag you need to know more about. Should start with "#"
	 * @return An ArrayList containing all Comments and ProfileFeeds.
	 * @throws DbException Thrown if something goes wrong while querying the database
	 */
	@Override
	public List<SocialNetworkContent> getHashtagUsedAtList(String hashtag)
	{
		Result<Comment> commentResult = hashtagAccessor.getCommentsUsedIn(hashtag);
		Result<ProfileFeed> profileFeedResult = hashtagAccessor.getProfileFeedsUsedIn(hashtag);

		ArrayList<SocialNetworkContent> result = new ArrayList<>();
		result.addAll(commentResult.all());
		result.addAll(profileFeedResult.all());
		return result;
	}

	/**
	 * Figures out how many times a hashtag was mentioned in all comments or profileFeeds.
	 * @param hashtag The Hashtag you need to know more about
	 * @return Amount of comments and profiles added together.
	 * @throws DbException Thrown if something goes wrong while querying the database
	 */
	@Override
	public long getHashtagTimesUsed(Hashtag hashtag)
	{
		return getHashtagUsedAtList(hashtag).size();
	}

	/**
	 * Use this to figure out an empty ID for either an HumanProfile, an CoopProfile, an OrganisationPlace, an Location
	 * or an Event.
	 * @return An ID as Long
	 * @throws DbException Thrown, if something goes wrong while querying the database.
     */
	public long getNextSocialNetworkInterestId()
	{
		// We have not found a way to do this in a mapper-way, therefore we
		// absolutely need to use a hand-crafted command
		// here. The alternative way would be keeping a seperate Table which
		// keeps tracks of the highest IDs in each
		// table, but we were unsure if this wouldn't be too slow.

		ResultSet rs1 = session.execute("SELECT MAX(id) FROM coopProfiles");
		long amount1 = rs1.one().getLong(0);

		ResultSet rs2 = session.execute("SELECT MAX(id) FROM humanProfiles");
		long amount2 = rs2.one().getLong(0);

		ResultSet rs3 = session.execute("SELECT MAX(id) FROM locations");
		long amount3 = rs3.one().getLong(0);

		ResultSet rs4 = session.execute("SELECT MAX(id) FROM organisationPlaces");
		long amount4 = rs4.one().getLong(0);

		ResultSet rs5 = session.execute("SELECT MAX(id) FROM events");
		long amount5 = rs5.one().getLong(0);

		return Math.max(amount1, Math.max(amount2,Math.max(amount3,Math.max(amount4,amount5)))) + 1;
	}

	/**
	 * Gets a list of Profile Feeds related to a Profile
	 *
	 * @param p The Profile you want to analyze.
	 * @return An ArrayList of Profile Feed IDs related to that profile.
	 */
	public ArrayList<Long> getProfileProfileFeeds(Profile p) {
		Result<ProfileFeed> feedResult = profileAccessor.getProfileFeeds(p.getId());

		ArrayList<Long> result = new ArrayList<>();
		for (ProfileFeed pf : feedResult.all()) {
			result.add(pf.getId());
		}

		return result;
	}

	/**
	 * Gets a list of all Comments made by a profile.
	 *
	 * @param p The Profile you want to analyze
	 * @return An ArrayList containing all the Comment IDs
	 */
	public ArrayList<Long> getProfileAllComments(Profile p) {
		Result<Comment> commentResult = profileAccessor.getProfileComments(p.getId());

		ArrayList<Long> result = new ArrayList<>();
		for (Comment c : commentResult.all()) {
			result.add(c.getId());
		}

		return result;
	}

	/**
	 * Count how often an Location is used for events.
	 *
	 * @param l The Location you want to analyze.
	 * @return How often the Location was used for an event.
	 */
	public long locationTimesUsed(Location l) {
		Result<Event> eventResult = locationAccessor.getEvents(l.getId());

		return eventResult.all().size();
	}

	/**
	 * Determines whether an Location ID correspondends to an Location or an OrganisationPlace and gets the object.
	 *
	 * @param l The ID of an Location or an OrganisationPlace
	 * @return Location or an OrganisationPlace
	 */
	public Location autoGetLocation(long l) throws DbException {
		Location loc = getLocation(l);
		if (loc != null) return loc;

		OrganisationPlace op = getOrganisationPlace(l);
		if (op != null) return op;

		throw new DbException("The supplied ID is neither an Location nor an OrganisationPlace");
	}

	//@Firas: What are these methods for?
	@Override
	public ArrayList<Long> getProfileRelationshipPersons(HumanProfile humanProfile) {
		return humanProfile.getRelationshipPersons();
	}

	@Override
	public ArrayList<Long> getProfileLinkedOtherSocialNetworkProfileIds(HumanProfile humanProfile) {
		return humanProfile.getLinkedOtherSocialNetworkProfileIds();
	}

	@Override
	public ArrayList<Long> getProfileFriendsIds(HumanProfile humanProfile) {
		return humanProfile.getProfileFeedIds();
	}

	@Override
	public ArrayList<Long> getProfileFollowsIds(HumanProfile humanProfile) {
		return humanProfile.getFollowsIds();
	}

	@Override
	public ArrayList<Long> getProfileFollowedByIds(HumanProfile humanProfile) {
		return humanProfile.getFollowedByIds();
	}

	/**
	 * Returns the Organisation Place of a CoopProfile
	 *
	 * @param op The Organisation Place form
	 * @return The CoopProfile associated to that OrganisationPlace
	 * @throws DbException
	 */
	public CoopProfile organisationPlaceGetCoopProfile(OrganisationPlace op) {
		return getCoopProfile(op.organisationProfileId);
	}

	/**
	 * Saves more than one Content element at a time.
	 *
	 * @param set Any Iterable object that iterates over content.
	 * @throws DbException Thrown if an unknown type of content is encountered or if something goes wrong while querying
	 *                     the Database.
	 */
	@Override
	public void SaveSet(Iterable<SocialNetworkContent> set) throws DbException {
		for (SocialNetworkContent snc : set)
			if (snc instanceof Comment) saveComment((Comment) snc);
			else if (snc instanceof Media) saveMedia((Media) snc);
			else if (snc instanceof ProfileFeed) saveProfileFeed((ProfileFeed) snc);
			else if (snc instanceof HumanProfile) saveHumanProfile((HumanProfile) snc);
			else if (snc instanceof CoopProfile) saveCoopProfile((CoopProfile) snc);
			else if (snc instanceof LocationImpl) saveLocation((LocationImpl) snc);
			else if (snc instanceof OrganisationPlace) saveOrganisationPlace((OrganisationPlace) snc);
			else if (snc instanceof Event) saveEvent((Event) snc);
			else throw new DbException("Detected unknown SocialNetworkContent type: " + snc.getClass().getName());
	}
}