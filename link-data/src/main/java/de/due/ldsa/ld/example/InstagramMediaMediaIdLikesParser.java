package de.due.ldsa.ld.example;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.model.Profile;
import de.due.ldsa.ld.Parser;

/**A Parser for an Instagram Api response from /media/media-id/likes. 
 * Needs to be used in combination with a {@link InstagramObjectParser}
 * to parse the liker profiles.
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
			Profile liker = InstagramObjectParser.parseProfile(jsonArray.getJSONObject(i));
			likerList.add(liker);
		}
		
		return likerList;
	}

}
