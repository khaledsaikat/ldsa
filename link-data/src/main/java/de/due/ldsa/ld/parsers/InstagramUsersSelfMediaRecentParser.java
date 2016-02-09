package de.due.ldsa.ld.parsers;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.model.Location;
import de.due.ldsa.model.Media;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.ProfileFeed;
import de.due.ldsa.model.SocialNetworkContent;
import de.due.ldsa.ld.Parser;

/**A Parser for an Instagram Api response from /users/self/media/recent. 
 * Creates a List of a media objects, profiles, profilefeeds and also
 * Locations, if those are included in the response.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramUsersSelfMediaRecentParser implements Parser<ArrayList<SocialNetworkContent>>{

	public static final InstagramUsersSelfMediaRecentParser INSTANCE = new
			InstagramUsersSelfMediaRecentParser();
	
	@Override
	public ArrayList<SocialNetworkContent> parse(JSONObject json) throws JSONException {
		JSONArray jsonArray = json.getJSONArray("data");
		ArrayList<SocialNetworkContent> contentList = new ArrayList<>();
		
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject elementJson = jsonArray.getJSONObject(i);
			Media media = InstagramObjectParser.parseMedia(elementJson);
			Profile creator = InstagramObjectParser.parseProfile(elementJson.getJSONObject("user"));
			ProfileFeed profileFeed = InstagramObjectParser.parseProfileFeed(elementJson);
			
			profileFeed.setMediaId((int) media.getId());
			ArrayList<Long> profileFeedIdList = new ArrayList<>();
			profileFeedIdList.add(profileFeed.getId());
			creator.setProfileFeedIds(profileFeedIdList);
			
			contentList.add(media);
			contentList.add(creator);
			contentList.add(profileFeed);
			
			if(!elementJson.isNull("location")){
				JSONObject locationJson = elementJson.getJSONObject("location");
				Location location = InstagramObjectParser.parseLocation(locationJson);
				profileFeed.setLocationId((int) location.getId());
				contentList.add(location);
			}
		}
		
		return contentList;
	}

}
