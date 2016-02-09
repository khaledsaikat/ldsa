package de.due.ldsa.ld.parsers;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.ld.Parser;
import de.due.ldsa.model.Location;

/**Creates a List of Locations from an Instagram API /locations/search response.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramLocationsSearchParser implements Parser<List<Location>>{

	public static final InstagramLocationsSearchParser INSTANCE = new
			InstagramLocationsSearchParser();
	
	@Override
	public List<Location> parse(JSONObject json) throws JSONException {
		JSONArray jsonArray = json.getJSONArray("data");
		ArrayList<Location> locationList = new ArrayList<>(jsonArray.length());
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject locationJson = jsonArray.getJSONObject(i);
			Location location = InstagramObjectParser.parseLocation(locationJson);
			locationList.add(location);
		}
		
		return locationList;
	}

	
	
}
