package de.due.ldsa.ld.example;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.ld.Parser;
import de.due.ldsa.model.Location;

/**Parses a single Location from an Instagram Api /locations/location-id
 * response.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramLocationsLocationIdParser implements Parser<Location>{

	public static final InstagramLocationsLocationIdParser INSTANCE = new
			InstagramLocationsLocationIdParser();
	
	@Override
	public Location parse(JSONObject json) throws JSONException {
		return InstagramObjectParser.parseLocation(json.getJSONObject("data"));
	}
	
}
