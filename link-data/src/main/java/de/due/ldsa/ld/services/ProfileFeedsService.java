package de.due.ldsa.ld.services;

import java.util.ArrayList;
import java.util.List;

import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.ld.Fetch;
import de.due.ldsa.ld.exceptions.UndefinedFetchMethodException;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.ProfileFeed;

/**
 * @author Firas Sabbah
 *
 */
public class ProfileFeedsService {

	private static ProfileFeedsService profileFeedsService;

	private Database databaseService = DatabaseImpl.getInstance();
	private StreamsProviderService streamsProviderService = StreamsProviderService.getInstance();

	public static ProfileFeedsService getInstance() {
		if (profileFeedsService == null) {
			profileFeedsService = new ProfileFeedsService();
		}
		return profileFeedsService;
	}

	private ProfileFeedsService() {

	}

	/**
	 * Get a list of ProfileFeeds.
	 * 
	 * @param fetchMode
	 * @return
	 * @throws UndefinedFetchMethodException
	 */
	public List<ProfileFeed> getProfileFeeds(Fetch fetchMode) throws UndefinedFetchMethodException {
		List<ProfileFeed> profileFeeds = null;

		if (fetchMode == Fetch.OFFLINE) {
			profileFeeds = databaseService.getAllProfileFeeds();
		} else if (fetchMode == Fetch.ONLINE) {
			profileFeeds = streamsProviderService.getLastUpdatedProfileFeedsStream();
		} else {
			throw new UndefinedFetchMethodException();
		}
		return profileFeeds;
	}

}