/**
 * 
 */
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
public class HumanProfilesService {

	private static HumanProfilesService humanProfilesService;

	private Database databaseService = DatabaseImpl.getInstance();
	private StreamsProviderService streamsProviderService = StreamsProviderService.getInstance();

	private ProfileFeedsService profileFeedsService = ProfileFeedsService.getInstance();

	public static HumanProfilesService getInstance() {
		if (humanProfilesService == null) {
			humanProfilesService = new HumanProfilesService();
		}
		return humanProfilesService;
	}

	private HumanProfilesService() {

	}

	/**
	 * Get a list of HumanProfiles.
	 * 
	 * @param fetchMode
	 * @return
	 * @throws UndefinedFetchMethodException
	 */
	public List<HumanProfile> getHumanProfiles(Fetch fetchMode) throws UndefinedFetchMethodException {
		List<HumanProfile> humanProfiles = null;

		if (fetchMode == Fetch.OFFLINE) {
			humanProfiles = databaseService.getAllHumanProfiles();
		} else if (fetchMode == Fetch.ONLINE) {
			humanProfiles = streamsProviderService.getLastUpdatedHumanProfilesSteam();
		} else {
			throw new UndefinedFetchMethodException();
		}
		return humanProfiles;
	}

}
