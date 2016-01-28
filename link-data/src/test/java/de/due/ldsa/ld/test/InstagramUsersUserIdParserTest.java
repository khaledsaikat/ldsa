package de.due.ldsa.ld.test;

import static org.junit.Assert.*;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import de.due.ldsa.ld.example.InstagramUsersUserIdParser;
import de.due.ldsa.model.Profile;

/**A test case for {@link InstagramUsersUserIdParser}.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramUsersUserIdParserTest {

	@Test
	public void testParse() throws JSONException {
		Profile profile = InstagramUsersUserIdParser.INSTANCE.parse(new
				JSONObject("{\"data\":{"
						+ "\"id\": \"1574083\","
						+ "\"username\": \"snoopdogg\","
						+ "\"full_name\": \"Snoop Dogg\","
						+ "\"profile_picture\": \"http://distillery.s3.amazonaws.com/profiles/profile_1574083_75sq_1295469061.jpg\","
						+ "\"bio\": \"This is my bio\","
						+ "\"website\": \"http://snoopdogg.com\","
						+ "\"counts\": {"
						+ "    \"media\": 1320,"
						+ "    \"follows\": 420,"
						+ "    \"followed_by\": 3410"
						+ "}}"
						+ "}"));
		assertEquals(1574083, profile.getId());
		assertEquals("snoopdogg", profile.getUsername());
		assertEquals("Snoop Dogg", profile.getFullname());
		assertEquals("http://snoopdogg.com",
				profile.getUserWebsite());
		assertEquals("This is my bio", profile.getBio());
		
	}

}
