package de.due.ldsa.ld.example;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.model.Comment;
import de.due.ldsa.ld.Parser;

/**Parses a single comment from an Instagram /media/media-id/comment query.
 * Currently used by {@link InstagramMediaMediaIdCommentsParser}
 * and not to be used directly.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramCommentParser implements Parser<Comment> {
	
	public static final InstagramCommentParser INSTANCE = 
			new InstagramCommentParser();

	@Override
	public Comment parse(JSONObject json) throws JSONException {
		Comment comment = new Comment();
		comment.setText(json.getString("text"));
		comment.setId(json.getLong("id"));
		
		return comment;
	}

}
