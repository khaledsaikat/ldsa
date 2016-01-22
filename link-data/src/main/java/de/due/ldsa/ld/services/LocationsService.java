package de.due.ldsa.ld.services;

import java.util.ArrayList;
import java.util.List;

import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.ld.Fetch;
import de.due.ldsa.ld.exceptions.UndefinedFetchMethodException;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.Location;

/**
 * @author Firas Sabbah
 *
 */
public class LocationsService {

	private static LocationsService locationsService;

	private Database databaseService = DatabaseImpl.getInstance();
	private StreamsProviderService streamsProviderService = StreamsProviderService.getInstance();

	public static LocationsService getInstance() {
		if (locationsService == null) {
			locationsService = new LocationsService();
		}
		return locationsService;
	}

	private LocationsService() {

	}

	/**
	 * Get a list of Locations.
	 * 
	 * @param fetchMode
	 * @return
	 * @throws UndefinedFetchMethodException
	 */
	public List<Location> getLocations(Fetch fetchMode) throws UndefinedFetchMethodException {
		List<Location> locations = null;

		if (fetchMode == Fetch.OFFLINE) {
			locations = databaseService.getAllLocations();
		} else if (fetchMode == Fetch.ONLINE) {
			locations = streamsProviderService.getLastUpdatedLocationsStream();
		} else {
			throw new UndefinedFetchMethodException();
		}
		return locations;
	}

}