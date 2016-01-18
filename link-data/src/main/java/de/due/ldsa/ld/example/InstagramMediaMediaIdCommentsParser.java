package de.due.ldsa.ld.example;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.db.model.Comment;
import de.due.ldsa.ld.Parser;

/**A Parser for an Instagram Api response from /media/media-id/comments. 
 * Needs to be used in combination with a {@link LinkMediaCommentsAction}
 * in order to have the comments link the correct media id as the JSON
 * response from Instagram does not contain that id.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramMediaMediaIdCommentsParser implements Parser<ArrayList<Comment>>{

	@Override
	public ArrayList<Comment> parse(JSONObject json) throws JSONException {
		JSONArray jsonArray = json.getJSONArray("data");
		ArrayList<Comment> commentList = new ArrayList<>(jsonArray.length());
		for (int i = 0; i < jsonArray.length(); i++) {
			commentList.add(InstagramCommentParser.INSTANCE.parse(
					jsonArray.getJSONObject(i)));
		}
		
		// TODO Add comments to corresponding profiles
		// TODO Add source profile to all comments
		
		return commentList;
	}

}
