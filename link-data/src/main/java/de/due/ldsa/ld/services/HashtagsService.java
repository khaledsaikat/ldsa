package de.due.ldsa.ld.services;

import java.util.ArrayList;
import java.util.List;

import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.ld.Fetch;
import de.due.ldsa.ld.exceptions.UndefinedFetchMethodException;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.Hashtag;

/**
 * @author Firas Sabbah
 *
 */
public class HashtagsService {

	private static HashtagsService hashtagsService;

	private Database databaseService = DatabaseImpl.getInstance();
	private StreamsProviderService streamsProviderService = StreamsProviderService.getInstance();

	public static HashtagsService getInstance() {
		if (hashtagsService == null) {
			hashtagsService = new HashtagsService();
		}
		return hashtagsService;
	}

	private HashtagsService() {

	}

	/**
	 * Get a list of Hashtags.
	 * 
	 * @param fetchMode
	 * @return
	 * @throws UndefinedFetchMethodException
	 */
	public List<Hashtag> getHashtags(Fetch fetchMode) throws UndefinedFetchMethodException {
		List<Hashtag> hashtags = null;

		if (fetchMode == Fetch.OFFLINE) {
			hashtags = databaseService.getAllHashtags();
		} else if (fetchMode == Fetch.ONLINE) {
			hashtags = streamsProviderService.getLastUpdatedHashtagsStream();
		} else {
			throw new UndefinedFetchMethodException();
		}
		return hashtags;
	}

}