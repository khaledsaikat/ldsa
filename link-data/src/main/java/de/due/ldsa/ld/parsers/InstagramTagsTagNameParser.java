package de.due.ldsa.ld.parsers;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.model.Hashtag;
import de.due.ldsa.ld.Parser;


/**A Parser for an Instagram Api response from /tags/tag-name.
 * Creates a single new {@link Hashtag}.
 * 
 * @author Maik Wosnitzka
 *
 */

public class InstagramTagsTagNameParser implements Parser<Hashtag>{

	public static final InstagramTagsTagNameParser INSTANCE = new 
			InstagramTagsTagNameParser();
	
	@Override
	public Hashtag parse(JSONObject json) throws JSONException {
		json = json.getJSONObject("data");
		Hashtag hashtag = new Hashtag(json.getString("name"));
		return hashtag;
	}

	
	
}
