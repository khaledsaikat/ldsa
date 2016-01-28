package de.due.ldsa.ld.example;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.model.Profile;
import de.due.ldsa.ld.Parser;

/**A Parser for an Instagram Api response from /users/user-id.
 * Creates a single new {@link Profile}.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramUsersUserIdParser implements Parser<Profile>{

	public static final InstagramUsersUserIdParser INSTANCE = new 
			InstagramUsersUserIdParser();
	
	@Override
	public Profile parse(JSONObject json) throws JSONException {
		return InstagramObjectParser.parseProfile(json.getJSONObject("data"));
	}
	
}
