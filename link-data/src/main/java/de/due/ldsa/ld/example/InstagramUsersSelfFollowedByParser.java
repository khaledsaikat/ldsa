package de.due.ldsa.ld.example;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.ld.Parser;
import de.due.ldsa.model.Profile;

/**Parses a single profile from an Instagram Api /users/self/followed-by
 * response. Uses the {@link InstagramUserListParser}.
 * 
 * @author Maik Wosnitzka
 *
 */
public class InstagramUsersSelfFollowedByParser implements Parser<List<Profile>> {
	
	public static final InstagramUsersSelfFollowedByParser INSTANCE = 
			new InstagramUsersSelfFollowedByParser();

	@Override
	public List<Profile> parse(JSONObject json) throws JSONException {
		List<Profile> followedByList = InstagramUserListParser.INSTANCE.parse(json);
		return followedByList;
	}

}

