package de.due.ldsa.ld.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.due.ldsa.db.Database;
import de.due.ldsa.db.DbException;
import de.due.ldsa.ld.StoreInDatabaseAction;
import de.due.ldsa.model.Comment;
import de.due.ldsa.model.CoopProfile;
import de.due.ldsa.model.Event;
import de.due.ldsa.model.Hashtag;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.Location;
import de.due.ldsa.model.LocationImpl;
import de.due.ldsa.model.Media;
import de.due.ldsa.model.OrganisationPlace;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.ProfileFeed;
import de.due.ldsa.model.SocialNetwork;
import de.due.ldsa.model.SocialNetworkContent;

/**Testcases for {@link StoreInDatabaseAction}.
 * Has one test with single objects and one test with a list.
 * 
 * @author Jan Kowollik
 *
 */
public class StoreInDatabaseActionTest {

	private class MockDatabase implements Database {

		private Comment storedComment;
		private CoopProfile storedCoopProfile;
		private Hashtag storedHashtag;
		private HumanProfile storedHumanProfile;
		private LocationImpl storedLocationImpl;
		private Media storedMedia;
		private OrganisationPlace storedOrganisationPlace;
		private ProfileFeed storedProfileFeed;
		private SocialNetwork storedSocialNetwork;

		@Override
		public void truncateTable(String tName) {
			fail("truncateTable of Database not supposed to be called");
		}

		@Override
		public void saveSocialNetwork(SocialNetwork sn) throws DbException {
			storedSocialNetwork = sn;
		}

		@Override
		public SocialNetwork getSocialNetwork(int id) {
			fail("getSocialNetwork of Database not supposed to be called");
			return storedSocialNetwork;
		}

		@Override
		public void saveProfileFeed(ProfileFeed pf) throws DbException {
			storedProfileFeed = pf;
		}

		@Override
		public ProfileFeed getProfileFeed(long id) {
			fail("getProfileFeed of Database not supposed to be called");
			return storedProfileFeed;
		}

		@Override
		public void saveMedia(Media m) throws DbException {
			storedMedia = m;
		}

		@Override
		public Media getMedia(long id) {
			fail("getMedia of Database not supposed to be called");
			return storedMedia;
		}

		@Override
		public void saveLocation(LocationImpl l) throws DbException {
			storedLocationImpl = l;
		}

		@Override
		public LocationImpl getLocation(long id) {
			fail("getLocation of Database not supposed to be called");
			return storedLocationImpl;
		}

		@Override
		public void saveOrganisationPlace(OrganisationPlace op) throws DbException {
			storedOrganisationPlace = op;
		}

		@Override
		public OrganisationPlace getOrganisationPlace(long id) {
			fail("getOrganisationPlace of Database not supposed to be called");
			return storedOrganisationPlace;
		}

		@Override
		public void saveCoopProfile(CoopProfile cp) throws DbException {
			storedCoopProfile = cp;
		}

		@Override
		public CoopProfile getCoopProfile(long id) {
			fail("getCoopProfile of Database not supposed to be called");
			return storedCoopProfile;
		}

		@Override
		public void saveHumanProfile(HumanProfile hp) throws DbException {
			storedHumanProfile = hp;
		}

		@Override
		public HumanProfile getHumanProfile(long id) {
			fail("getHumanProfile of Database not supposed to be called");
			return storedHumanProfile;
		}

		@Override
		public void saveEvent(Event id) throws DbException {
			fail("saveEvent of Database not supposed to be called");
		}

		@Override
		public Event getEvent(long id) {
			fail("getEvent of Database not supposed to be called");
			return null;
		}

		@Override
		public void saveComment(Comment c) throws DbException {
			storedComment = c;
		}

		@Override
		public Comment getComment(long id) {
			fail("getComment of Database not supposed to be called");
			return storedComment;
		}

		@Override
		public boolean isHuman(long id) throws DbException {
			fail("isHuman of Database not supposed to be called");
			return false;
		}

		@Override
		public long getNextProfileId() throws DbException {
			fail("getNextProfileId of Database not supposed to be called");
			return 0;
		}

		@Override
		public long getNextMediaId() throws DbException {
			fail("getNextMediaId of Database not supposed to be called");
			return 0;
		}

		@Override
		public long getNextCommentId() throws DbException {
			fail("getNextCommentId of Database not supposed to be called");
			return 0;
		}

		@Override
		public long getNextInterestId() throws DbException {
			fail("getNextInterestId of Database not supposed to be called");
			return 0;
		}

		@Override
		public long getNextProfileFeedId() throws DbException {
			fail("getNextProfileFeedId of Database not supposed to be called");
			return 0;
		}

		@Override
		public long getNextLocationId() throws DbException {
			fail("getNextLocationId of Database not supposed to be called");
			return 0;
		}

		@Override
		public Profile autoGetProfile(long id) throws DbException {
			fail("autoGetProfile of Database not supposed to be called");
			return null;
		}

		@Override
		public void autoSaveProfile(Profile p) throws DbException {
			fail("Profile of Database not supposed to be called");
		}

		@Override
		public List<HumanProfile> getAllHumanProfiles() throws DbException {
			fail("getAllHumanProfiles of Database not supposed to be called");
			return null;
		}

		@Override
		public List<Comment> getAllComments() throws DbException {
			fail("getAllComments of Database not supposed to be called");
			return null;
		}

		@Override
		public List<Hashtag> getAllHashtags() throws DbException {
			fail("getAllHashtags of Database not supposed to be called");
			return null;
		}

		@Override
		public List<Location> getAllLocations() throws DbException {
			fail("getAllLocations of Database not supposed to be called");
			return null;
		}

		@Override
		public List<ProfileFeed> getAllProfileFeeds() throws DbException {
			fail("getAllProfileFeeds of Database not supposed to be called");
			return null;
		}

		@Override
		public List<Media> getAllMedia() throws DbException {
			fail("getAllMedia of Database not supposed to be called");
			return null;
		}

		@Override
		public void saveHashtag(Hashtag hashtag) throws DbException {
			storedHashtag = hashtag;
		}

		@Override
		public boolean isOrganisationPlace(long id) {
			fail("isOrganisationPlace of Database not supposed to be called");
			return false;
		}

		@Override
		public long getNextEventId() {
			fail("getNextEventId of Database not supposed to be called");
			return 0;
		}

		@Override
		public List<Profile> getAllProfilesFromSocialNetwork(int snId) {
			fail("getAllProfilesFromSocialNetwork of Database not supposed to be called");
			return null;
		}

		@Override
		public List<ProfileFeed> getAllProfileFeedsFromSocialNetwork(int snId) {
			fail("getAllProfileFeedsFromSocialNetwork of Database not supposed to be called");
			return null;
		}

		@Override
		public List<Media> getAllMediaFromSocialNetwork(int snId) {
			fail("getAllMediaFromSocialNetwork of Database not supposed to be called");
			return null;
		}

		@Override
		public List<SocialNetworkContent> getAllContentFromSocialNetwork(int snId) throws DbException {
			fail("getAllContentFromSocialNetwork of Database not supposed to be called");
			return null;
		}

		@Override
		public void reconnect() {
			fail("reconnect of Database not supposed to be called");
		}

		@Override
		public List<SocialNetworkContent> getHashtagUsedAtList(Hashtag hashtag) throws DbException {
			fail("getHashtagUsedAtList of Database not supposed to be called");
			return null;
		}

		@Override
		public List<SocialNetworkContent> getHashtagUsedAtList(String hashtag) throws DbException {
			fail("getHashtagUsedAtList of Database not supposed to be called");
			return null;
		}

		@Override
		public long getNextSocialNetworkInterestId() throws DbException {
			fail("getNextSocialNetworkInterestId of Database not supposed to be called");
			return 0;
		}

		@Override
		public ArrayList<Long> getProfileProfileFeeds(Profile p) {
			fail("getProfileProfileFeeds of Database not supposed to be called");
			return null;
		}

		@Override
		public ArrayList<Long> getProfileAllComments(Profile p) {
			fail("getProfileAllComments of Database not supposed to be called");
			return null;
		}

		@Override
		public Location autoGetLocation(long l) throws DbException {
			fail("autoGetLocation of Database not supposed to be called");
			return null;
		}

		@Override
		public long locationTimesUsed(Location l) {
			fail("locationTimesUsed of Database not supposed to be called");
			return 0;
		}

		@Override
		public int coopProfileCountInteraction(CoopProfile cp, Profile p) throws DbException {
			fail("coopProfileCountInteraction of Database not supposed to be called");
			return 0;
		}

		@Override
		public double coopProfileCountAverageInteractionPerFeed(CoopProfile cp, Profile p) throws DbException {
			fail("coopProfileCountAverageInteractionPerFeed of Database not supposed to be called");
			return 0;
		}

		@Override
		public double coopProfileGetAverageInteractionPerFeed(CoopProfile cp) throws DbException {
			fail("coopProfileGetAverageInteractionPerFeed of Database not supposed to be called");
			return 0;
		}

		@Override
		public double coopProfileGetAverageOfActionsPerDay(CoopProfile cp) throws DbException {
			fail("coopProfileGetAverageOfActionsPerDay of Database not supposed to be called");
			return 0;
		}
		
		
		public Comment getStoredComment() {
			return storedComment;
		}

		public CoopProfile getStoredCoopProfile() {
			return storedCoopProfile;
		}
		
		public Hashtag getStoredHashtag() {
			return storedHashtag;
		}

		public HumanProfile getStoredHumanProfile() {
			return storedHumanProfile;
		}

		public LocationImpl getStoredLocationImpl() {
			return storedLocationImpl;
		}

		public Media getStoredMedia() {
			return storedMedia;
		}

		public OrganisationPlace getStoredOrganisationPlace() {
			return storedOrganisationPlace;
		}

		public ProfileFeed getStoredProfileFeed() {
			return storedProfileFeed;
		}

		public SocialNetwork getStoredSocialNetwork() {
			return storedSocialNetwork;
		}
		
	}
	
	@Test
	public void testStoreInDatabaseAction() {
		new StoreInDatabaseAction<>(new MockDatabase());
	}

	@Test
	public void testOnActionWithSingleObjects() {
		MockDatabase mockDatabase = new MockDatabase();
		StoreInDatabaseAction<SocialNetworkContent> storeInDatabaseAction = 
				new StoreInDatabaseAction<>(mockDatabase);
		
		Comment comment = new Comment();
		storeInDatabaseAction.onAction(comment);
		assertEquals(comment, mockDatabase.getStoredComment());
		
		CoopProfile coopProfile = new CoopProfile();
		storeInDatabaseAction.onAction(coopProfile);
		assertEquals(coopProfile, mockDatabase.getStoredCoopProfile());
		
		Hashtag hashtag = new Hashtag();
		new StoreInDatabaseAction<Hashtag>(mockDatabase).onAction(hashtag);
		assertEquals(hashtag, mockDatabase.getStoredHashtag());
		
		HumanProfile humanProfile = new HumanProfile();
		storeInDatabaseAction.onAction(humanProfile);
		assertEquals(humanProfile, mockDatabase.getStoredHumanProfile());
		
		LocationImpl locationImpl = new LocationImpl();
		storeInDatabaseAction.onAction(locationImpl);
		assertEquals(locationImpl, mockDatabase.getStoredLocationImpl());
		
		Media media = new Media();
		storeInDatabaseAction.onAction(media);
		assertEquals(media, mockDatabase.getStoredMedia());
		
		OrganisationPlace organisationPlace = new OrganisationPlace();
		storeInDatabaseAction.onAction(organisationPlace);
		assertEquals(organisationPlace, mockDatabase.getStoredOrganisationPlace());
		
		ProfileFeed profileFeed = new ProfileFeed();
		storeInDatabaseAction.onAction(profileFeed);
		assertEquals(profileFeed, mockDatabase.getStoredProfileFeed());
		
		SocialNetwork socialNetwork = new SocialNetwork();
		new StoreInDatabaseAction<SocialNetwork>(mockDatabase).onAction(socialNetwork);
		assertEquals(socialNetwork, mockDatabase.getStoredSocialNetwork());
		
	}
	
	@Test
	public void testOnActionWithList() {
		MockDatabase mockDatabase = new MockDatabase();
		StoreInDatabaseAction<List<SocialNetworkContent>> storeInDatabaseAction = 
				new StoreInDatabaseAction<>(mockDatabase);
		ArrayList<SocialNetworkContent> contentList = new ArrayList<>();
		
		contentList.add(new Comment());
		contentList.add(new CoopProfile());
		contentList.add(new HumanProfile());
		contentList.add(new LocationImpl());
		contentList.add(new Media());
		contentList.add(new OrganisationPlace());
		contentList.add(new ProfileFeed());
		
		storeInDatabaseAction.onAction(contentList);

		assertEquals(contentList.get(0), mockDatabase.getStoredComment());
		assertEquals(contentList.get(1), mockDatabase.getStoredCoopProfile());
		assertEquals(contentList.get(2), mockDatabase.getStoredHumanProfile());
		assertEquals(contentList.get(3), mockDatabase.getStoredLocationImpl());
		assertEquals(contentList.get(4), mockDatabase.getStoredMedia());
		assertEquals(contentList.get(5), mockDatabase.getStoredOrganisationPlace());
		assertEquals(contentList.get(6), mockDatabase.getStoredProfileFeed());
	}

}
