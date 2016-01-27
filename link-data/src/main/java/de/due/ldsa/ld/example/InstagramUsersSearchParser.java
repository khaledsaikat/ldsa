package de.due.ldsa.ld.example;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.ld.Parser;
import de.due.ldsa.model.Media;
import de.due.ldsa.model.Profile;

/**Creates a List of Profiles from an Instagram Api /users/search response.
 * 
 * @author Maik Wosnitzka
 *
 */
public class InstagramUsersSearchParser implements Parser<List<Profile>> {

	public static final InstagramUsersSearchParser INSTANCE = 
			new InstagramUsersSearchParser();
	
	@Override
	public List<Profile> parse(JSONObject json) throws JSONException {
		JSONArray jsonArray = json.getJSONArray("data");
		ArrayList<Profile> userList = new ArrayList<>(jsonArray.length());
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject userJson = jsonArray.getJSONObject(i);
			Profile user = InstagramUserParser.INSTANCE.parse(userJson);
			user = addProfilePictureTo(user, userJson.getString("profile_picture"));
			userList.add(user);
		}
		
		return userList;
	}
	
	private Profile addProfilePictureTo(Profile user, String path){
		Media photo = new Media();
		photo.setCrawlingPath(path);
		user.setProfilePhotoMediaId(photo.getId());
		return user;
	}

}
