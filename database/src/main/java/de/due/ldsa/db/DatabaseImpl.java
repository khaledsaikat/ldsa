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

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: Romina (scrobart)
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

	@Override
	public void close() throws IOException {
		session.close();
	}

	private DatabaseImpl() {
		reconnect();
	}

	/**
	 * Call this function, if the connection died for some reason, and you need to establish it again.
	 */
	public void reconnect() {
		// TODO: don't hardcode "127.0.0.1" - make this loadable from somewhere...
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
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

		socialNetworkMapper = null;
		profileFeedMapper = null;
		mediaMapper = null;
		locationMapper = null;
		organisationPlaceMapper = null;
		coopProfileMapper = null;
		humanProfileMapper = null;
		eventMapper = null;
		commentMapper = null;
		hashtagMapper = null;
	}


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

	public void saveSocialNetwork(SocialNetwork sn) {
		if (socialNetworkMapper == null) {
			socialNetworkMapper = new MappingManager(session).mapper(SocialNetwork.class);
		}

		socialNetworkMapper.save(sn);
	}

	public SocialNetwork getSocialNetwork(int i) {
		if (socialNetworkMapper == null) {
			socialNetworkMapper = new MappingManager(session).mapper(SocialNetwork.class);
		}

		return socialNetworkMapper.get(i);
	}

	public void saveProfileFeed(ProfileFeed pf) {
		if (profileFeedMapper == null) {
			profileFeedMapper = new MappingManager(session).mapper(ProfileFeed.class);
		}

		profileFeedMapper.save(pf);

	}

	public ProfileFeed getProfileFeed(long id) {
		if (profileFeedMapper == null) {
			profileFeedMapper = new MappingManager(session).mapper(ProfileFeed.class);
		}

		return profileFeedMapper.get(id);
	}

	public void saveMedia(Media m) {
		if (mediaMapper == null) {
			mediaMapper = new MappingManager(session).mapper(Media.class);
		}

		mediaMapper.save(m);
	}

	public Media getMedia(long id) {
		if (mediaMapper == null) {
			mediaMapper = new MappingManager(session).mapper(Media.class);
		}

		return mediaMapper.get(id);
	}

	public void saveLocation(LocationImpl l) {
		if (locationMapper == null) {
			locationMapper = new MappingManager(session).mapper(LocationImpl.class);
		}

		locationMapper.save(l);
	}

	public LocationImpl getLocation(long id) {
		if (locationMapper == null) {
			locationMapper = new MappingManager(session).mapper(LocationImpl.class);
		}

		return locationMapper.get(id);
	}

	public void saveOrganisationPlace(OrganisationPlace op) {
		if (organisationPlaceMapper == null) {
			organisationPlaceMapper = new MappingManager(session).mapper(OrganisationPlace.class);
		}

		organisationPlaceMapper.save(op);
	}

	public OrganisationPlace getOrganisationPlace(long id) {
		if (organisationPlaceMapper == null) {
			organisationPlaceMapper = new MappingManager(session).mapper(OrganisationPlace.class);
		}

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

		if (coopProfileMapper == null) {
			coopProfileMapper = new MappingManager(session).mapper(CoopProfile.class);
		}

		coopProfileMapper.save(cp);
	}

	public CoopProfile getCoopProfile(long id) {
		if (coopProfileMapper == null) {
			coopProfileMapper = new MappingManager(session).mapper(CoopProfile.class);
		}

		return coopProfileMapper.get(id);
	}

	public void saveHumanProfile(HumanProfile hp) throws DbException {
		if (getCoopProfile(hp.getId()) != null) {
			// This needs to be done because Human IDs and Company IDs share the
			// same number sequence.
			throw new DbException("The ID specified in that company profile is already used by a company ID: "
					+ Long.toString(hp.getId()));
		}

		if (humanProfileMapper == null) {
			humanProfileMapper = new MappingManager(session).mapper(HumanProfile.class);
		}

		humanProfileMapper.save(hp);
	}

	public HumanProfile getHumanProfile(long id) {
		if (humanProfileMapper == null) {
			humanProfileMapper = new MappingManager(session).mapper(HumanProfile.class);
		}

		return humanProfileMapper.get(id);
	}

	public void saveEvent(Event id) {
		if (eventMapper == null) {
			eventMapper = new MappingManager(session).mapper(Event.class);
		}

		eventMapper.save(id);
	}

	public Event getEvent(long id) {
		if (eventMapper == null) {
			eventMapper = new MappingManager(session).mapper(Event.class);
		}

		return eventMapper.get(id);
	}

	public void saveComment(Comment c) {
		if (commentMapper == null) {
			commentMapper = new MappingManager(session).mapper(Comment.class);
		}

		commentMapper.save(c);
	}

	public Comment getComment(long id) {
		if (commentMapper == null) {
			commentMapper = new MappingManager(session).mapper(Comment.class);
		}

		return commentMapper.get(id);
	}

	public boolean isHuman(long id) throws DbException {
		HumanProfile hp = getHumanProfile(id);
		if (hp != null)
			return true;

		CoopProfile cp = getCoopProfile(id);
		if (cp != null)
			return false;

		throw new DbException("The specified ID does not exist:" + Long.toString(id));
	}

	/**
	 * Use this to figure out the ID of a new CoopProfile or HumanProfile.
	 *
	 * @return A free ID
	 * @throws DbException
	 */
	public long getNextProfileId() throws DbException {
		//HumanProfile, CoopProfile, OrganisationPlace, Location & Event now share the same Number Sequence, to satisfy
		//SocialNetworkInterest.
		return getNextSocialNetworkInterestId();
	}

	public long getNextMediaId() throws DbException {
		ResultSet rs1 = session.execute("SELECT MAX(id) FROM media");
		return rs1.one().getLong(0) + 1;
	}

	public long getNextCommentId() throws DbException {
		ResultSet rs1 = session.execute("SELECT MAX(id) FROM comments");
		return rs1.one().getLong(0) + 1;
	}

	public long getNextInterestId() throws DbException {
		ResultSet rs1 = session.execute("SELECT MAX(id) FROM interests");
		return rs1.one().getLong(0) + 1;
	}

	public long getNextProfileFeedId() throws DbException {
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
	public long getNextLocationId() throws DbException {
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
	public Profile autoGetProfile(long id) throws DbException {
		if (isHuman(id)) {
			return getHumanProfile(id);
		} else {
			return getCoopProfile(id);
		}
	}

	public void autoSaveProfile(Profile p) throws DbException {
		if (p instanceof HumanProfile) {
			saveHumanProfile((HumanProfile) p);
		} else if (p instanceof CoopProfile) {
			saveCoopProfile((CoopProfile) p);
		} else {
			throw new DbException("You supplied neither a HumanProfile nor a CoopProfile.");
		}
	}

	public boolean isOrganisationPlace(long id) {
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
	public List<HumanProfile> getAllHumanProfiles() throws DbException {
		MappingManager manager = new MappingManager(session);
		HumanProfileAccessor humanProfileAccessor = manager.createAccessor(HumanProfileAccessor.class);
		Result<HumanProfile> humanprofiles = humanProfileAccessor.getAll();

		return humanprofiles.all();
	}

	/**
	 * @deprecated Lists might become troublesome if the database grows very large. Please use the Iterable instead.
	 * @return An ArrayList containing all the comments.
	 * @throws DbException
	 */
	@Override
	public List<Comment> getAllComments() throws DbException {
		MappingManager manager = new MappingManager(session);
		CommentAccessor commentAccessor = manager.createAccessor(CommentAccessor.class);
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
	public List<Location> getAllLocations() throws DbException {
		MappingManager manager = new MappingManager(session);
		LocationAccessor locationAccessor = manager.createAccessor(LocationAccessor.class);
		Result<LocationImpl> locations = locationAccessor.getAll();

		OrganisationPlaceAccessor organisationPlaceAccessor = manager.createAccessor(OrganisationPlaceAccessor.class);
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
	public List<ProfileFeed> getAllProfileFeeds() throws DbException {
		MappingManager mappingManager = new MappingManager(session);
		ProfileFeedAccessor profileFeedAccessor = mappingManager.createAccessor(ProfileFeedAccessor.class);
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
	public List<Media> getAllMedia() throws DbException {
		MappingManager mappingManager = new MappingManager(session);
		MediaAccessor mediaAccessor = mappingManager.createAccessor(MediaAccessor.class);
		Result<Media> mediaResult = mediaAccessor.getAll();

		return mediaResult.all();
	}

	public List<Profile> getAllProfilesFromSocialNetwork(int snId) {
		MappingManager mappingManager = new MappingManager(session);
		HumanProfileAccessor humanProfileAccessor = mappingManager.createAccessor(HumanProfileAccessor.class);
		CoopProfileAccessor coopProfileAccessor = mappingManager.createAccessor(CoopProfileAccessor.class);
		Result<HumanProfile> humanProfiles = humanProfileAccessor.getAllFromSocialNetwork(snId);
		Result<CoopProfile> coopProfiles = coopProfileAccessor.getAllFromSocialNetwork(snId);

		ArrayList<Profile> result = new ArrayList<Profile>();
		result.addAll(humanProfiles.all());
		result.addAll(coopProfiles.all());
		return result;
	}

	public List<ProfileFeed> getAllProfileFeedsFromSocialNetwork(int snId) {
		MappingManager mappingManager = new MappingManager(session);
		ProfileFeedAccessor profileFeedAccessor = mappingManager.createAccessor(ProfileFeedAccessor.class);
		Result<ProfileFeed> profileFeeds = profileFeedAccessor.getAllFromSocialNetwork(snId);

		return profileFeeds.all();
	}

	public List<Media> getAllMediaFromSocialNetwork(int snId) {
		MappingManager mappingManager = new MappingManager(session);
		MediaAccessor mediaAccessor = mappingManager.createAccessor(MediaAccessor.class);
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
	public List<SocialNetworkContent> getAllContentFromSocialNetwork(int snId) throws DbException
	{
		MappingManager mappingManager = new MappingManager(session);
		CommentAccessor commentAccessor = mappingManager.createAccessor(CommentAccessor.class);
		MediaAccessor mediaAccessor = mappingManager.createAccessor(MediaAccessor.class);
		ProfileFeedAccessor profileFeedAccessor = mappingManager.createAccessor(ProfileFeedAccessor.class);
		HumanProfileAccessor humanProfileAccessor = mappingManager.createAccessor(HumanProfileAccessor.class);
		CoopProfileAccessor coopProfileAccessor = mappingManager.createAccessor(CoopProfileAccessor.class);
		OrganisationPlaceAccessor organisationPlaceAccessor = mappingManager.createAccessor(OrganisationPlaceAccessor.class);
		LocationAccessor locationAccessor = mappingManager.createAccessor(LocationAccessor.class);
		EventAccessor eventAccessor = mappingManager.createAccessor(EventAccessor.class);

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
	public void saveHashtag(Hashtag hashtag) throws DbException {
		if (hashtagMapper == null) {
			hashtagMapper = new MappingManager(session).mapper(Hashtag.class);
		}
		hashtagMapper.save(hashtag);
	}

	@Override
	@Deprecated
	public List<Hashtag> getAllHashtags() throws DbException {
		//TODO: Implement getAllHashtags()
		return null;
	}

	/**
	 * Gets a list of all ProfileFeeds and comments in which a specific hashtag is mentioned.
	 * @param hashtag The hashtag you want to analyze.
	 * @return An ArrayList containing all the ProfileFeeds and Comments which mention the hashtag.
	 * @throws DbException Thrown if something goes wrong while querying the database.
	 */
	public List<SocialNetworkContent> getHashtagUsedAtList(Hashtag hashtag) throws DbException
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
	public List<SocialNetworkContent> getHashtagUsedAtList(String hashtag) throws DbException
	{
		MappingManager mappingManager = new MappingManager(session);
		HashtagAccessor hashtagAccessor = mappingManager.createAccessor(HashtagAccessor.class);
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
	public long getHashtagTimesUsed(Hashtag hashtag) throws DbException
	{
		return getHashtagUsedAtList(hashtag).size();
	}

	/**
	 * Use this to figure out an empty ID for either an HumanProfile, an CoopProfile, an OrganisationPlace, an Location
	 * or an Event.
	 * @return An ID as Long
	 * @throws DbException Thrown, if something goes wrong while querying the database.
     */
	public long getNextSocialNetworkInterestId() throws DbException
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
		MappingManager mappingManager = new MappingManager(session);
		ProfileAccessor profileAccessor = mappingManager.createAccessor(ProfileAccessor.class);
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
		MappingManager mappingManager = new MappingManager(session);
		ProfileAccessor profileAccessor = mappingManager.createAccessor(ProfileAccessor.class);
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
		MappingManager mappingManager = new MappingManager(session);
		LocationAccessor locationAccessor = mappingManager.createAccessor(LocationAccessor.class);
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

	/**
	 * Checks how often a Profile interacts with a Company Profile
	 *
	 * @param cp The company profile you want to analyze.
	 * @param p  The profile which you need the amount of interactions with.
	 * @return Amount of all the interactions
	 * @throws DbException Thrown if something goes wrong while querying the database.
	 * @implNote In the model, this is supposed to be in "CoopProfile", however, we moved it here so we can avoid a
	 * circular dependency.
	 */
	public int coopProfileCountInteraction(CoopProfile cp, Profile p) throws DbException {
		int result = 0;
		if (cp.getFollowedByIds() != null) {
			if (cp.getFollowedByIds().contains(p.getId())) {
				//If the person follows the company, it counts as interaction
				result++;
			}
		}
		if (cp.getProfileFeedIds() != null) {
			for (Long i : cp.getProfileFeedIds()) {
				ProfileFeed j = getProfileFeed(i);
				if (j.getSharerIds() != null) {
					//If our person shares it, it counts as interaction.
					if (j.getSharerIds().contains(p.getId()))
						result++;
				}
				if (j.getLikerIds() != null) {
					//If our person likes it, it counts as interaction.
					if (j.getLikerIds().contains(p.getId()))
						result++;
				}
				if (j.getCommentIds() != null) {
					for (Long k : j.getCommentIds()) {
						result += countInteractions(getComment(k), p);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Counts how often a specific profile appears in a comment tree. This is supposed to be used by coopProfileCountInteraction
	 *
	 * @param c The comment tree node you want to analyze
	 * @param p The profile you need the amount of interactions with.
	 * @return How often p appears somewhere in c.
	 */
	private int countInteractions(Comment c, Profile p) {
		int result = 0;
		if (c.getLikerIds() != null) {
			//If the person likes the comment, it counts as interaction.
			if (c.getLikerIds().contains(p.getId()))
				result++;
		}
		if (c.getCommentIds() != null) {
			for (Long i : c.getCommentIds()) {
				//Count the comments on this comment as well. (Yes, there are things like comment-trees - see reddit.)
				result += countInteractions(getComment(i), p);
			}
		}
		//If the person actually made this comment, it counts as interaction as well.
		if (c.getCommenterId() == p.getId())
			result++;

		return result;
	}

	public double coopProfileCountAverageInteractionPerFeed(CoopProfile cp, Profile p) throws DbException {
		throw new DbException("not yet implemented.");
	}

	public double coopProfileGetAverageInteractionPerFeed(CoopProfile cp) throws DbException {
		throw new DbException("not yet implemented.");
	}

	public double coopProfileGetAverageOfActionsPerDay(CoopProfile cp) throws DbException {
		throw new DbException("not yet implemented.");
	}

	@Override
	public ArrayList<Long> getProfileRelationshipPersons(HumanProfile humanProfile) throws DbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Long> getProfileLinkedOtherSocialNetworkProfileIds(HumanProfile humanProfile) throws DbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Long> getProfileFriendsIds(HumanProfile humanProfile) throws DbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Long> getProfileFollowsIds(HumanProfile humanProfile) throws DbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Long> getProfileFollowedByIds(HumanProfile humanProfile) throws DbException {
		// TODO Auto-generated method stub
		return null;
	}


	//TODO: Generate mappings upon connection instead of on request (as it is now) - to gain more performance.
}