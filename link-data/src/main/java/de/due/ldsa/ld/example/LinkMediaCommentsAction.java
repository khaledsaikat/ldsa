package de.due.ldsa.ld.example;

import java.util.ArrayList;

import de.due.ldsa.model.Comment;
import de.due.ldsa.ld.Action;

/**A {@link LinkMediaCommentsAction} adds a media id to
 * a list of comments. Api's like the Instagram Api do not
 * contain the id of the media in their JSON response, when
 * querying comments for a media object, so the media id
 * needs to be supplied by this mechanism.
 * 
 * @author Jan Kowollik
 * @version 1.0
 */
public class LinkMediaCommentsAction implements Action<ArrayList<Comment>>{

	private long mediaId;
	
	/**Creates a new instance of this class with the given media id.
	 * 
	 * @param mediaId the media id that comments should link to
	 */
	public LinkMediaCommentsAction(long mediaId) {
		this.mediaId = mediaId;
	}
	
	@Override
	public void onAction(ArrayList<Comment> t) {
		for (int i = 0; i < t.size(); i++) {
			t.get(i).setMediaId(mediaId);
		}
	}

}
