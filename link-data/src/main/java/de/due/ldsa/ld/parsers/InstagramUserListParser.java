package de.due.ldsa.ld.parsers;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet.THEAD;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.ld.Parser;
import de.due.ldsa.model.Media;
import de.due.ldsa.model.Profile;

/**Creates a List of Profiles from different Instagram Api responses.
 * 
 * Used by {@link InstagramUsersSelfFollowedByParser},
 * {@link InstagramUsersSelfRequestedByParser}, the
 * {@link InstagramUsersSelfFollowsParser} and the
 * {@link InstagramUsersSearchParser}.
 * 
 * We can parse the different responses in this way and
 * reduce code redundancy.
 * 
 * @author Maik Wosnitzka
 *
 */
public class InstagramUserListParser implements Parser<List<Profile>> {

	public static final InstagramUserListParser INSTANCE = 
			new InstagramUserListParser();
	
	@Override
	public List<Profile> parse(JSONObject json) throws JSONException {
		JSONArray jsonArray = json.getJSONArray("data");
		ArrayList<Profile> userList = new ArrayList<>(jsonArray.length());
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject userJson = jsonArray.getJSONObject(i);
			Profile user = InstagramObjectParser.parseProfile(userJson);
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
