package de.due.ldsa.ld.example;

import java.util.Collections;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.model.SocialNetworkContent;
import de.due.ldsa.ld.Parser;

/**A Parser for an Instagram Api response from /users/user-id/relationship.
 * This endpoint does not contain any useable information as both user ids are
 * only present in the request made to the Api, not in the response we get.
 * Therefore this parser returns an empty List.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramUsersUserIdRelationshipParser implements Parser<List<SocialNetworkContent>>{

	public static final InstagramUsersUserIdRelationshipParser INSTANCE = new 
			InstagramUsersUserIdRelationshipParser();
	
	@Override
	public List<SocialNetworkContent> parse(JSONObject json) throws JSONException {
		return Collections.emptyList();
	}
	
}
