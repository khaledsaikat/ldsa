package de.due.ldsa.ld.example;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.model.Profile;
import de.due.ldsa.ld.Parser;

/**A Parser for an Instagram Api response from /media/media-id/likes. 
 * Needs to be used in combination with a {@link LinkMediaCommentsAction}
 * in order to have the comments link the correct media id as the JSON
 * response from Instagram does not contain that id.
 * 
 * @author Maik Wosnitzka
 *
 */
public class InstagramMediaMediaIdLikesParser implements Parser<ArrayList<Profile>>{

	public static final InstagramMediaMediaIdLikesParser INSTANCE = new
			InstagramMediaMediaIdLikesParser();
	
	@Override
	public ArrayList<Profile> parse(JSONObject json) throws JSONException {
		JSONArray jsonArray = json.getJSONArray("data");
		ArrayList<Profile> likerList = new ArrayList<>(jsonArray.length());
		for (int i = 0; i < jsonArray.length(); i++) {
			likerList.add(InstagramUserParser.INSTANCE.parse(
					jsonArray.getJSONObject(i)));
		}
		
		// TODO Add likes to corresponding profiles
		// TODO Add source profile to all likes
		
		return likerList;
	}

}
