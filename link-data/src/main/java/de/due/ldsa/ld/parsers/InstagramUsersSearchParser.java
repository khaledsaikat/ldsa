package de.due.ldsa.ld.parsers;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.ld.Parser;
import de.due.ldsa.model.Profile;

/**Creates a List of Profiles from an Instagram Api /users/search response.
 * Uses the {@link InstagramUserListParser}.
 * 
 * @author Maik Wosnitzka
 *
 */
public class InstagramUsersSearchParser implements Parser<List<Profile>> {

	public static final InstagramUsersSearchParser INSTANCE = 
			new InstagramUsersSearchParser();
	
	@Override
	public List<Profile> parse(JSONObject json) throws JSONException {
		List<Profile> userList = InstagramUserListParser.INSTANCE.parse(json);
		return userList;
	}

}
