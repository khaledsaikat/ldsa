package de.due.ldsa.ld.parsers;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.ld.Parser;
import de.due.ldsa.model.Profile;

/**Parses a single profile from an Instagram Api /users/self/follows
 * response. Uses the {@link InstagramUserListParser}.
 * 
 * @author Maik Wosnitzka
 *
 */
public class InstagramUsersSelfFollowsParser implements Parser<List<Profile>> {
	
	public static final InstagramUsersSelfFollowsParser INSTANCE = 
			new InstagramUsersSelfFollowsParser();

	@Override
	public List<Profile> parse(JSONObject json) throws JSONException {
		List<Profile> followsList = InstagramUserListParser.INSTANCE.parse(json);
		return followsList;
	}

}

