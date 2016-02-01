package de.due.ldsa.ld.example;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.model.Media;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.ProfileFeed;
import de.due.ldsa.model.SocialNetworkContent;
import de.due.ldsa.ld.Parser;

/**A Parser for an Instagram Api response from /media/search. 
 * Creates a List of a media objects, profiles and profilefeeds.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramMediaSearchParser implements Parser<ArrayList<SocialNetworkContent>>{

	public static final InstagramMediaSearchParser INSTANCE = new
			InstagramMediaSearchParser();
	
	@Override
	public ArrayList<SocialNetworkContent> parse(JSONObject json) throws JSONException {
		JSONArray jsonArray = json.getJSONArray("data");
		ArrayList<SocialNetworkContent> contentList = new ArrayList<>();
		
		for (int i = 0; i < jsonArray.length(); i++) {
			json = jsonArray.getJSONObject(i);
			Media media = InstagramObjectParser.parseMedia(json);
			Profile creator = InstagramObjectParser.parseProfile(json.getJSONObject("user"));
			ProfileFeed profileFeed = InstagramObjectParser.parseProfileFeed(json);
			
			profileFeed.setMediaId((int) media.getId());
			ArrayList<Long> profileFeedIdList = new ArrayList<>();
			profileFeedIdList.add(profileFeed.getId());
			creator.setProfileFeedIds(profileFeedIdList);
			
			contentList.add(media);
			contentList.add(creator);
			contentList.add(profileFeed);
		}
		
		return contentList;
	}

}
