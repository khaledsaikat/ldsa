package de.due.ldsa.ld.parsers;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.ld.Parser;
import de.due.ldsa.model.Location;
import de.due.ldsa.model.Media;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.ProfileFeed;
import de.due.ldsa.model.SocialNetworkContent;

/**Parses a list of {@link profilefeed}'s by an Instagram Api /users/self/media/liked
 * response. Uses the {@link InstagramObjectParser} to parse the different parts.
 * 
 * @return an ArrayList with all parsed data from the response.
 * 
 * @author Maik Wosnitzka
 *
 */
public class InstagramUsersSelfMediaLikedParser implements Parser<ArrayList<SocialNetworkContent>>{
	
	public static final InstagramUsersSelfMediaLikedParser INSTANCE = 
			new InstagramUsersSelfMediaLikedParser();
	
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
			
			if(!json.isNull("location")){
				JSONObject jsonLocation = json.getJSONObject("location");
				Location location = InstagramObjectParser.parseLocation(jsonLocation);
				contentList.add(location);
				profileFeed.setLocationId((int) location.getId());
			}
		}
		
		return contentList;
	}

}
