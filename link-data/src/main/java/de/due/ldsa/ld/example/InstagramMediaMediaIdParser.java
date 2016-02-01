package de.due.ldsa.ld.example;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.model.Media;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.ProfileFeed;
import de.due.ldsa.model.SocialNetworkContent;
import de.due.ldsa.ld.Parser;

/**A Parser for an Instagram Api response from /media/media-id. 
 * Creates a List of a media object, a profile and a profilefeed.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramMediaMediaIdParser implements Parser<ArrayList<SocialNetworkContent>>{

	public static final InstagramMediaMediaIdParser INSTANCE = new
			InstagramMediaMediaIdParser();
	
	@Override
	public ArrayList<SocialNetworkContent> parse(JSONObject json) throws JSONException {
		json = json.getJSONObject("data");
		ArrayList<SocialNetworkContent> contentList = new ArrayList<>();
		
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
		
		return contentList;
	}

}
