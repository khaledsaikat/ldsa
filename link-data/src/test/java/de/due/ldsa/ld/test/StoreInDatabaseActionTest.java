package de.due.ldsa.ld.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.due.ldsa.model.*;
import org.junit.Test;

import de.due.ldsa.db.Database;
import de.due.ldsa.db.DbException;
import de.due.ldsa.ld.StoreInDatabaseAction;
import de.due.ldsa.model.CoopLocation;

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
		private CoopLocation storedOrganisationPlace;
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
		public void saveCoopLocation(CoopLocation op) throws DbException {
			storedOrganisationPlace = op;
		}

		@Override
		public CoopLocation getCoopPlace(long id) {
			fail("getCoopPlace of Database not supposed to be called");
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
		public boolean isCoopPlace(long id) {
			fail("isCoopPlace of Database not supposed to be called");
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
		public ArrayList<Long> getProfileRelationshipPersons(HumanProfile humanProfile) throws DbException {
			fail("getProfileRelationshipPersons of Database not supposed to be called");
			return null;
		}

		@Override
		public ArrayList<Long> getProfileLinkedOtherSocialNetworkProfileIds(HumanProfile humanProfile)
				throws DbException {
			fail("getProfileLinkedOtherSocialNetworkProfileIds of Database not supposed to be called");
			return null;
		}

		@Override
		public ArrayList<Long> getProfileFriendsIds(HumanProfile humanProfile) throws DbException {
			fail("getProfileFriendsIds of Database not supposed to be called");
			return null;
		}

		@Override
		public ArrayList<Long> getProfileFollowsIds(HumanProfile humanProfile) throws DbException {
			fail("getProfileFollowsIds of Database not supposed to be called");
			return null;
		}

		@Override
		public ArrayList<Long> getProfileFollowedByIds(HumanProfile humanProfile) throws DbException {
			fail("getProfileFollowedByIds of Database not supposed to be called");
			return null;
		}

		/*
			Romina: We have added some methods to our Database interface.
		    We added them here because we did not want to mess up the build.
		 */
		@Override
		public void close() throws IOException {
			fail("close of Database not supposed to be called");
		}

		@Override
		public long getNextSocialNetworkID() throws DbException {
			fail("getNextSocialNetworkID of Database not supposed to be called");
			return 0;
		}

		@Override
		public long getHashtagTimesUsed(Hashtag hashtag) throws DbException {
			fail("getHashtagTimesUsed of Database not supposed to be called");
			return 0;
		}

		@Override
		public CoopProfile coopLocationGetCoopProfile(CoopLocation op) throws DbException {
			fail("coopLocationGetCoopProfile of Database not supposed to be called");
			return null;
		}

		@Override
		public void SaveSet(Iterable<SocialNetworkContent> set) throws DbException {
			fail("SaveSet of Database not supposed to be called");
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

		public CoopLocation getStoredOrganisationPlace() {
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
		
		CoopLocation organisationPlace = new CoopLocation();
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
		contentList.add(new CoopLocation());
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
