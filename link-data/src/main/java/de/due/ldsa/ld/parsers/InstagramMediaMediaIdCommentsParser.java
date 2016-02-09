package de.due.ldsa.ld.parsers;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.model.Comment;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.SocialNetworkContent;
import de.due.ldsa.ld.Parser;

/**A Parser for an Instagram Api response from /media/media-id/comments. 
 * Creates a List of comments and the sender profiles.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramMediaMediaIdCommentsParser implements Parser<ArrayList<SocialNetworkContent>>{

	public static final InstagramMediaMediaIdCommentsParser INSTANCE = new
			InstagramMediaMediaIdCommentsParser();
	
	@Override
	public ArrayList<SocialNetworkContent> parse(JSONObject json) throws JSONException {
		JSONArray jsonArray = json.getJSONArray("data");
		ArrayList<SocialNetworkContent> contentList = new ArrayList<>(jsonArray.length() * 2);
		for (int i = 0; i < jsonArray.length(); i++) {
			Comment comment = InstagramObjectParser.parseComment(
					jsonArray.getJSONObject(i));
			Profile sender = InstagramObjectParser.parseProfile(
					jsonArray.getJSONObject(i).getJSONObject("from"));
			
			ArrayList<Long> commentIdList = new ArrayList<>(1);
			commentIdList.add(sender.getId());
			sender.setAllCommentsId(commentIdList);
			comment.setCommenterId(sender.getId());	// There is no setCommenter() method
			
			contentList.add(comment);
			contentList.add(sender);
		}
		
		return contentList;
	}

}
