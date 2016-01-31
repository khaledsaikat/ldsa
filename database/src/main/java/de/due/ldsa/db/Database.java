package de.due.ldsa.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import de.due.ldsa.db.accessors.*;
import de.due.ldsa.model.*;

public interface Database {
	void truncateTable(String tName);

	void saveSocialNetwork(SocialNetwork sn) throws DbException;

	SocialNetwork getSocialNetwork(int id);

	void saveProfileFeed(ProfileFeed pf) throws DbException;

	ProfileFeed getProfileFeed(long id);

	void saveMedia(Media m) throws DbException;

	Media getMedia(long id);

	void saveLocation(LocationImpl l) throws DbException;

	LocationImpl getLocation(long id);

	void saveOrganisationPlace(OrganisationPlace op) throws DbException;

	OrganisationPlace getOrganisationPlace(long id);

	void saveCoopProfile(CoopProfile cp) throws DbException;

	CoopProfile getCoopProfile(long id);

	void saveHumanProfile(HumanProfile hp) throws DbException;

	HumanProfile getHumanProfile(long id);

	void saveEvent(Event id) throws DbException;

	Event getEvent(long id);

	void saveComment(Comment c) throws DbException;

	Comment getComment(long id);

	boolean isHuman(long id) throws DbException;

	long getNextProfileId() throws DbException;

	long getNextMediaId() throws DbException;

	long getNextCommentId() throws DbException;

	long getNextInterestId() throws DbException;

	long getNextProfileFeedId() throws DbException;

	long getNextLocationId() throws DbException;

	Profile autoGetProfile(long id) throws DbException;

	void autoSaveProfile(Profile p) throws DbException;

	List<HumanProfile> getAllHumanProfiles() throws DbException;

	List<Comment> getAllComments() throws DbException;

	List<Hashtag> getAllHashtags() throws DbException;

	List<Location> getAllLocations() throws DbException;

	List<ProfileFeed> getAllProfileFeeds() throws DbException;

	List<Media> getAllMedia() throws DbException;

	void saveHashtag(Hashtag hashtag)throws DbException;

	boolean isOrganisationPlace(long id);

	long getNextEventId();

	List<Profile> getAllProfilesFromSocialNetwork(int snId);
	List<ProfileFeed> getAllProfileFeedsFromSocialNetwork(int snId);
	List<Media> getAllMediaFromSocialNetwork(int snId);
	List<SocialNetworkContent> getAllContentFromSocialNetwork(int snId) throws DbException;

	void reconnect();

	List<SocialNetworkContent> getHashtagUsedAtList(Hashtag hashtag) throws DbException;
	List<SocialNetworkContent> getHashtagUsedAtList(String hashtag) throws DbException;

	long getNextSocialNetworkInterestId() throws DbException;

	ArrayList<Long> getProfileProfileFeeds(Profile p);

	ArrayList<Long> getProfileAllComments(Profile p);

	Location autoGetLocation(long l) throws DbException;

	long locationTimesUsed(Location l);

	int coopProfileCountInteraction(CoopProfile cp, Profile p) throws DbException;

	double coopProfileCountAverageInteractionPerFeed(CoopProfile cp, Profile p) throws DbException;

	double coopProfileGetAverageInteractionPerFeed(CoopProfile cp) throws DbException;

	double coopProfileGetAverageOfActionsPerDay(CoopProfile cp) throws DbException;
}
