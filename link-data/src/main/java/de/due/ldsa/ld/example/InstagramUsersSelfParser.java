package de.due.ldsa.ld.example;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.Media;
import de.due.ldsa.model.Profile;
import de.due.ldsa.ld.Parser;

/**A Parser for an Instagram Api response from /users/self.
 * Creates a single new {@link Profile}.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramUsersSelfParser implements Parser<Profile>{

	@Override
	public Profile parse(JSONObject json) throws JSONException {
		Profile profile = new HumanProfile();
		profile.setId(json.getLong("username"));
		profile.setFullname(json.getString("full_name"));
		profile.setBio(json.getString("bio"));
		profile.setUserWebsite(json.getString("website"));
		Media profilePhoto = new Media();
		profilePhoto.setCrawlingPath(json.getString("profile_picture"));
		profile.setProfilePhoto(profilePhoto);
		return profile;
	}
	
}
