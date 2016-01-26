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
	private Mapper<SocialNetworkInterestImpl> interestMapper;

	@Override
	public void close() throws IOException {
		session.close();
	}

	private DatabaseImpl() {
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

	public void saveInterest(SocialNetworkInterestImpl socialNetworkInterest) {
		if (interestMapper == null) {
			interestMapper = new MappingManager(session).mapper(SocialNetworkInterestImpl.class);
		}

		interestMapper.save(socialNetworkInterest);
	}

	public SocialNetworkInterestImpl getInterest(long id) {
		if (interestMapper == null) {
			interestMapper = new MappingManager(session).mapper(SocialNetworkInterestImpl.class);
		}

		return interestMapper.get(id);
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
		// We have not found a way to do this in a mapper-way, therefore we
		// absolutely need to use a hand-crafted command
		// here. The alternative way would be keeping a seperate Table which
		// keeps tracks of the highest IDs in each
		// table, but we were unsure if this wouldn't be too slow.

		ResultSet rs1 = session.execute("SELECT MAX(id) FROM coopProfiles");
		long amount1 = rs1.one().getLong(0);

		ResultSet rs2 = session.execute("SELECT MAX(id) FROM humanProfiles");
		long amount2 = rs2.one().getLong(0);

		return Math.max(amount1, amount2) + 1;
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
		ResultSet rs1 = session.execute("SELECT MAX(id) FROM locations");
		long amount1 = rs1.one().getLong(0);

		ResultSet rs2 = session.execute("SELECT MAX(id) FROM organisationPlaces");
		long amount2 = rs2.one().getLong(0);

		return Math.max(amount1, amount2) + 1;
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


	public Iterable<HumanProfile> getAllHumanProfilesAsIterable() {
		MappingManager manager = new MappingManager(session);
		HumanProfileAccessor humanProfileAccessor = manager.createAccessor(HumanProfileAccessor.class);
		Result<HumanProfile> humanprofiles = humanProfileAccessor.getAll();

		return () -> humanprofiles.iterator();
	}

	/**
	 * @return An ArrayList containing all the human profiles
	 * @throws DbException
	 * @deprecated Please use the Iterable instead! Lists will get troublesome in terms of memory, if the database grows larger.
	 */
	@Deprecated
	@Override
	public List<HumanProfile> getAllHumanProfiles() throws DbException {
		// Please change your methods, so we can use Iterables! Lists _WILL_ get troublesome if the database grows
		// larger.
		ArrayList<HumanProfile> result = new ArrayList<>();
		for (HumanProfile hp : getAllHumanProfilesAsIterable()) {
			result.add(hp);
		}
		return result;
	}

	public Iterable<Comment> getAllCommentsAsIterable() {
		MappingManager manager = new MappingManager(session);
		CommentAccessor commentAccessor = manager.createAccessor(CommentAccessor.class);
		Result<Comment> comments = commentAccessor.getAll();

		return () -> comments.iterator();
	}

	/**
	 * @deprecated Lists might become troublesome if the database grows very large. Please use the Iterable instead.
	 * @return An ArrayList containing all the comments.
	 * @throws DbException
	 */
	@Override
	@Deprecated
	public List<Comment> getAllComments() throws DbException {
		// Please change your methods, so we can use Iterables! Lists _WILL_ get troublesome if the database grows
		// larger.
		ArrayList<Comment> result = new ArrayList<Comment>();
		for (Comment hp : getAllCommentsAsIterable()) {
			result.add(hp);
		}
		return result;
	}

	public Iterable<Location> getAllLocationsAsIterable() {
		MappingManager manager = new MappingManager(session);
		LocationAccessor locationAccessor = manager.createAccessor(LocationAccessor.class);
		Result<LocationImpl> locations = locationAccessor.getAll();

		OrganisationPlaceAccessor organisationPlaceAccessor = manager.createAccessor(OrganisationPlaceAccessor.class);
		Result<OrganisationPlace> organisationPlaces = organisationPlaceAccessor.getAll();

		Iterator<? extends Location> result;
		result = new IteratorMerger<>(new Iterator[]{locations.iterator(), organisationPlaces.iterator()});

		return () -> (Iterator<Location>) result;
	}

	/**
	 * Gets all the Locations from the Database.
	 *
	 * @return An ArrayList containing all the Locations and OrganisationPlaces in the Database
	 * @throws DbException Thrown, if something goes wrong when querying the Database
	 * @deprecated Please use getAllLocationsAsIterable instead.
	 */
	@Override
	@Deprecated
	public List<Location> getAllLocations() throws DbException {
		// Please change your methods, so we can use Iterables! Lists _WILL_ get troublesome if the database grows
		// larger.
		ArrayList<Location> result = new ArrayList<>();
		for (Location l : getAllLocationsAsIterable()) {
			result.add(l);
		}
		return result;
	}

	public Iterable<ProfileFeed> getAllProfileFeedsAsIterable() throws DbException {
		MappingManager mappingManager = new MappingManager(session);
		ProfileFeedAccessor profileFeedAccessor = mappingManager.createAccessor(ProfileFeedAccessor.class);
		Result<ProfileFeed> profileFeeds = profileFeedAccessor.getAll();

		return () -> profileFeeds.iterator();
	}

	/**
	 * Gets all the Profile Feeds from the database.
	 * @return An ArrayList containing all the profile feeds;
	 * @throws DbException Thrown, if something goes wrong when querying the database.
	 */
	@Override
	@Deprecated
	public List<ProfileFeed> getAllProfileFeeds() throws DbException {
		// Please change your methods, so we can use Iterables! Lists _WILL_ get troublesome if the database grows
		// larger.
		ArrayList<ProfileFeed> result = new ArrayList<ProfileFeed>();
		for (ProfileFeed pf : getAllProfileFeedsAsIterable()) {
			result.add(pf);
		}
		return result;
	}

	public Iterable<Media> getAllMediaAsIterable() throws DbException {
		MappingManager mappingManager = new MappingManager(session);
		MediaAccessor mediaAccessor = mappingManager.createAccessor(MediaAccessor.class);
		Result<Media> mediaResult = mediaAccessor.getAll();

		return () -> mediaResult.iterator();
	}

	/**
	 * Fetches all Media from the Database
	 * @deprecated Lists might become troublesome when the Database grows larger. Please use getAllMediaAsIterable()
	 * instead.
	 * @return An ArrayList containing all Media in the Database
	 * @throws DbException Thrown if something with the Database goes wrong.
	 */
	@Override
	@Deprecated
	public List<Media> getAllMedia() throws DbException {
		// Please change your methods, so we can use Iterables! Lists _WILL_ get troublesome if the database grows
		// larger.
		ArrayList<Media> result = new ArrayList<Media>();
		for (Media m : getAllMediaAsIterable()) {
			result.add(m);
		}
		return result;
	}

	@Override
	public Iterable<Profile> getAllProfilesFromSocialNetwork(int snId) {
		MappingManager mappingManager = new MappingManager(session);
		HumanProfileAccessor humanProfileAccessor = mappingManager.createAccessor(HumanProfileAccessor.class);
		CoopProfileAccessor coopProfileAccessor = mappingManager.createAccessor(CoopProfileAccessor.class);
		Result<HumanProfile> humanProfiles = humanProfileAccessor.getAllFromSocialNetwork(snId);
		Result<CoopProfile> coopProfiles = coopProfileAccessor.getAllFromSocialNetwork(snId);

		Iterator<? extends Profile> result;
		result = new IteratorMerger<>(new Iterator[]{humanProfiles.iterator(), coopProfiles.iterator()});

		return () -> (Iterator<Profile>) result;
	}

	@Override
	public Iterable<ProfileFeed> getAllProfileFeedsFromSocialNetwork(int snId) {
		MappingManager mappingManager = new MappingManager(session);
		ProfileFeedAccessor profileFeedAccessor = mappingManager.createAccessor(ProfileFeedAccessor.class);
		Result<ProfileFeed> profileFeeds = profileFeedAccessor.getAllFromSocialNetwork(snId);

		return new Iterable<ProfileFeed>() {
			@Override
			public Iterator<ProfileFeed> iterator() {
				return profileFeeds.iterator();
			}
		};
	}

	@Override
	public Iterable<Media> getAllMediaFromSocialNetwork(int snId) {
		MappingManager mappingManager = new MappingManager(session);
		MediaAccessor mediaAccessor = mappingManager.createAccessor(MediaAccessor.class);
		Result<Media> mediaResult = mediaAccessor.getAllFromSocialNetwork(snId);

		return new Iterable<Media>() {
			@Override
			public Iterator<Media> iterator() {
				return mediaResult.iterator();
			}
		};
	}

	/**
	 * Gets all SocialNetworkContent from a social network specified by the ID.
	 *
	 * @param snId The ID of the social network you want to query.
	 * @return An Iterable iterating over all the content.
	 * @implNote For more information about the Accessors and Results check this: https://docs.datastax.com/en/developer/java-driver/2.1/common/drivers/reference/accessorAnnotatedInterfaces.html
	 */
	@Override
	public Iterable<? extends SocialNetworkContent> getAllContentFromSocialNetwork(int snId) {
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

		Iterator<? extends SocialNetworkContent> result;
		result = new IteratorMerger<SocialNetworkContent>(new Iterator[]{
				comments.iterator(), medias.iterator(), profileFeeds.iterator(), humanProfiles.iterator(),
				coopProfiles.iterator(), organisationPlaces.iterator(), locations.iterator(), events.iterator()});


		return new Iterable<SocialNetworkContent>() {
			@Override
			public Iterator<SocialNetworkContent> iterator() {
				return (Iterator<SocialNetworkContent>) result;
			}
		};
	}

	public long getNextEventId() {
		ResultSet rs1 = session.execute("SELECT MAX(id) FROM events");
		return rs1.one().getLong(0) + 1;
	}

	@Override
	public void saveHashtag(Hashtag hashtag) throws DbException {
		// TODO Auto-generated method stub
	}

	@Override
	@Deprecated
	public List<Hashtag> getAllHashtags() throws DbException {
		// TODO: change the Hashtags in such a way that this can work.
		// TODO Auto-generated method stub
		// Please change your methods, so we can use Iterators! Lists _WILL_ get troublesome if the database grows
		// larger.
		return null;
	}
}