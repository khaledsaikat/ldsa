package de.due.ldsa.ld.example;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.ld.Parser;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.Profile;

/**Parses a liker from an Instagram /media/media-id/likes query.
 * Currently used by {@link InstagramMediaMediaIdLikeParser}
 * and not to be used directly.
 * 
 * @author Maik Wosnitzka
 *
 */
public class InstagramUserParser implements Parser<Profile> {

	public static final InstagramUserParser INSTANCE = 
			new InstagramUserParser();

	@Override
	public Profile parse(JSONObject json) throws JSONException {
		Profile profile = new HumanProfile();
		profile.setUsername(json.getString("username"));
		profile.setFullname(json.getString("first_name")
				+ " " + json.getString("last_name"));
		profile.setId(json.getLong("id"));
		
		return profile;
	}

}
