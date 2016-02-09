package de.due.ldsa.ld.parsers;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.model.SocialNetworkContent;
import de.due.ldsa.ld.Parser;

/**A Parser for an Instagram Api response from /media/shortcode/shortcode. 
 * Creates a List of a media object, a profile and a profilefeed.
 * Currently the same as {@link InstagramMediaMediaIdParser}.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramMediaShortcodeShortcodeParser implements Parser<ArrayList<SocialNetworkContent>>{

	public static final InstagramMediaShortcodeShortcodeParser INSTANCE = new
			InstagramMediaShortcodeShortcodeParser();
	
	@Override
	public ArrayList<SocialNetworkContent> parse(JSONObject json) throws JSONException {
		return InstagramMediaMediaIdParser.INSTANCE.parse(json);
	}

}
