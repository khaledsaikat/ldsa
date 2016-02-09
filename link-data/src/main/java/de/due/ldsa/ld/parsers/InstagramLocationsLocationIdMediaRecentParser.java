package de.due.ldsa.ld.parsers;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.ld.Parser;
import de.due.ldsa.model.Media;

/**Creates a List of Media objects from an Instagram Api
 * /locations/location-id/media/recent response.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramLocationsLocationIdMediaRecentParser implements Parser<List<Media>>{

	public static final InstagramLocationsLocationIdMediaRecentParser INSTANCE =
			new InstagramLocationsLocationIdMediaRecentParser();
	
	@Override
	public List<Media> parse(JSONObject json) throws JSONException {
		JSONArray jsonArray = json.getJSONArray("data");
		ArrayList<Media> mediaList = new ArrayList<>(jsonArray.length());
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject mediaJson = jsonArray.getJSONObject(i);
			Media media = new Media();
			
			media.setId(mediaJson.getLong("id"));
			media.setCrawlingPath(mediaJson.getString("link"));
			mediaList.add(media);
		}
		
		return mediaList;
	}

}
