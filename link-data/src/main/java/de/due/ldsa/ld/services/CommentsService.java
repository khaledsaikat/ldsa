package de.due.ldsa.ld.services;

import java.util.ArrayList;
import java.util.List;

import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.ld.Fetch;
import de.due.ldsa.ld.exceptions.UndefinedFetchMethodException;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.Comment;

/**
 * @author Firas Sabbah
 *
 */
public class CommentsService {

	private static CommentsService commentsService;

	private Database databaseService = DatabaseImpl.getInstance();
	private StreamsProviderService streamsProviderService = StreamsProviderService.getInstance();

	public static CommentsService getInstance() {
		if (commentsService == null) {
			commentsService = new CommentsService();
		}
		return commentsService;
	}

	private CommentsService() {

	}

	/**
	 * Get a list of Comments.
	 * 
	 * @param fetchMode
	 * @return
	 * @throws UndefinedFetchMethodException
	 */
	public List<Comment> getComments(Fetch fetchMode) throws UndefinedFetchMethodException {
		List<Comment> comments = null;

		if (fetchMode == Fetch.OFFLINE) {
			comments = databaseService.getAllComments();
		} else if (fetchMode == Fetch.ONLINE) {
			comments = streamsProviderService.getLastUpdatedCommentsStream();
		} else {
			throw new UndefinedFetchMethodException();
		}
		return comments;
	}

}