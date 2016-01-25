package de.due.ldsa.ld.example;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.ld.Parser;
import de.due.ldsa.model.Location;
import de.due.ldsa.model.LocationImpl;

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
		return parseLocation(json = json.getJSONObject("data"));
	}
	
	public static Location parseLocation(JSONObject json) throws JSONException {
		Location location = new LocationImpl();
		location.setId(json.getLong("id"));
		location.setPositionLatidue(json.getDouble("latitude"));
		location.setPositionLongitude(json.getDouble("longitude"));
		location.setName(json.getString("name"));
		return location;
	}
	
}
