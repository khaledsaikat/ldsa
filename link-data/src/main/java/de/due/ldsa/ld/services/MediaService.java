package de.due.ldsa.ld.services;

import java.util.ArrayList;
import java.util.List;

import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.ld.Fetch;
import de.due.ldsa.ld.exceptions.UndefinedFetchMethodException;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.Media;

/**
 * @author Firas Sabbah
 *
 */
public class MediaService {

	private static MediaService mediaService;

	private Database databaseService = DatabaseImpl.getInstance();
	private StreamsProviderService streamsProviderService = StreamsProviderService.getInstance();

	public static MediaService getInstance() {
		if (mediaService == null) {
			mediaService = new MediaService();
		}
		return mediaService;
	}

	private MediaService() {

	}

	/**
	 * Get a list of Media.
	 * 
	 * @param fetchMode
	 * @return
	 * @throws UndefinedFetchMethodException
	 */
	public List<Media> getMedia(Fetch fetchMode) throws UndefinedFetchMethodException {
		List<Media> media = null;

		if (fetchMode == Fetch.OFFLINE) {
			media = databaseService.getAllMedia();
		} else if (fetchMode == Fetch.ONLINE) {
			media = streamsProviderService.getLastUpdatedMediaSteam();
		} else {
			throw new UndefinedFetchMethodException();
		}
		return media;
	}

}