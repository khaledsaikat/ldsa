package de.due.ldsa.ld.example;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.model.SocialNetworkContent;
import de.due.ldsa.ld.Parser;

/**A Parser for an Instagram Api response from /users/user-id/media/recent. 
 * Creates a List of a media objects, profiles, profilefeeds and also
 * Locations, if those are included in the response.
 * Currently the same as {@link InstagramUsersSelfMediaRecentParser}.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramUsersUserIdMediaRecentParser implements Parser<ArrayList<SocialNetworkContent>>{

	public static final InstagramUsersUserIdMediaRecentParser INSTANCE = new
			InstagramUsersUserIdMediaRecentParser();
	
	@Override
	public ArrayList<SocialNetworkContent> parse(JSONObject json) throws JSONException {
		return InstagramUsersSelfMediaRecentParser.INSTANCE.parse(json);
	}

}
