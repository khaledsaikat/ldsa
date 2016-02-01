package de.due.ldsa.ld.example;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.model.Comment;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.Location;
import de.due.ldsa.model.LocationImpl;
import de.due.ldsa.model.Media;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.ProfileFeed;

/**This class contains static methods for parsing a single system model
 * object from an Instagram API JSON response sub structure. Often one JSON
 * response contains multiple objects of different types. 
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramObjectParser {
	
	/* This class contains only static methods, therefore we
	 * prevent creating an instance of this class.
	 */
	private InstagramObjectParser() {}

	/**Parses a single Comment object from a JSON structure.
	 * 
	 * @param json The {@link JSONObject} containing the {@link Comment} data.
	 * @return a Comment instance with as many fields as possible filled out
	 * @throws JSONException in case of malformed JSON data
	 */
	public static Comment parseComment(JSONObject json) throws JSONException{
		Comment comment = new Comment();
		
		comment.setText(json.getString("text"));
		comment.setId(json.getLong("id"));
		comment.setCommenterId(json.getJSONObject("from").getLong("id"));
		
		return comment;
	}
	
	/**Parses a single Location object from a JSON structure.
	 * 
	 * @param json The {@link JSONObject} containing the {@link Location} data.
	 * @return a Location instance with as many fields as possible filled out
	 * @throws JSONException in case of malformed JSON data
	 */
	public static Location parseLocation(JSONObject json) throws JSONException {
		Location location = new LocationImpl();
		
		location.setId(json.getLong("id"));
		location.setPositionLatidue(json.getDouble("latitude"));
		location.setPositionLongitude(json.getDouble("longitude"));
		location.setName(json.getString("name"));
		
		return location;
	}
	
	/**Parses a single Media object from a JSON structure.
	 * 
	 * @param json The {@link JSONObject} containing the {@link Media} data.
	 * @return a Media instance with as many fields as possible filled out
	 * @throws JSONException in case of malformed JSON data
	 */
	public static Media parseMedia(JSONObject json) throws JSONException {
		Media media = new Media();
		
		media.setId(json.getLong("id"));
		media.setCrawlingPath(json.getString("link"));
		
		return media;
	}
	
	/**Parses a single Profile object from a JSON structure. Can handle the
	 * users full name as a single attribute and split into two attributes
	 * ("first_name" and "last_name").
	 * 
	 * @param json The {@link JSONObject} containing the {@link Profile} data.
	 * @return a Profile instance with as many fields as possible filled out
	 * @throws JSONException in case of malformed JSON data
	 */
	public static Profile parseProfile(JSONObject json) throws JSONException {
		HumanProfile profile = new HumanProfile();
		
		profile.setId(json.getLong("id"));
		profile.setUsername(json.getString("username"));
		if(json.has("full_name")){
			profile.setFullname(json.getString("full_name"));
		}else if(json.has("first_name") && json.has("last_name")){
			profile.setFullname(json.getString("first_name") + " " 
					+ json.getString("last_name"));
		}
		profile.setBio(json.optString("bio"));
		profile.setUserWebsite(json.optString("website"));
		
		return profile;
	}
	
	/**Parses a single ProfileFeed object from a JSON structure.
	 * 
	 * @param json The {@link JSONObject} containing the {@link ProfileFeed} data.
	 * @return a ProfileFeed instance with as many fields as possible filled out
	 * @throws JSONException in case of malformed JSON data
	 */
	public static ProfileFeed parseProfileFeed(JSONObject json) throws JSONException {
		ProfileFeed profileFeed = new ProfileFeed();
		
		profileFeed.setId(json.getLong("id"));
		Profile profile = parseProfile(json.getJSONObject("user"));
		profileFeed.setProfileId(profile.getId());
		
		return profileFeed;
	}
	
}
