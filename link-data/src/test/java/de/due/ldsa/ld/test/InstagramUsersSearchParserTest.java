
package de.due.ldsa.ld.test;

import static org.junit.Assert.*;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import de.due.ldsa.ld.example.InstagramUsersSearchParser;
import de.due.ldsa.ld.example.InstagramUsersSelfParser;
import de.due.ldsa.model.Profile;

/**A test case for {@link InstagramUsersSearchParser}.
 * 
 * @author Maik Wosnitzka
 *
 */
public class InstagramUsersSearchParserTest {

	@Test
	public void testParse() throws JSONException {
		List<Profile> userList = InstagramUsersSearchParser.INSTANCE.parse(new
				JSONObject("{\n"+
						" \"data\": [{\n"+
						" \"username\": \"jack\",\n"+
						" \"first_name\": \"Jack\",\n"+
						" \"profile_picture\": \"http://distillery.s3.amazonaws.com/profiles/profile_66_75sq.jpg\",\n"+
						" \"id\": \"66\",\n"+
						" \"last_name\": \"Dorsey\"\n"+
						" },\n"+
						" {\n"+
						" \"username\": \"sammyjack\",\n"+
						" \"first_name\": \"Sammy\",\n"+
						" \"profile_picture\": \"http://distillery.s3.amazonaws.com/profiles/profile_29648_75sq_1294520029.jpg\",\n"+
						" \"id\": \"29648\",\n"+
						" \"last_name\": \"Jack\"\n"+
						" },\n"+
						" {\n"+
						" \"username\": \"jacktiddy\",\n"+
						" \"first_name\": \"Jack\",\n"+
						" \"profile_picture\": \"http://distillery.s3.amazonaws.com/profiles/profile_13096_75sq_1286441317.jpg\",\n"+
						" \"id\": \"13096\",\n"+
						" \"last_name\": \"Tiddy\"\n"+
						" }]\n"+
						"}"));
		assertEquals(66,			userList.get(0).getId());
		assertEquals("jack",		userList.get(0).getUsername());
		assertEquals("Jack Dorsey",	userList.get(0).getFullname());
		assertEquals(29648,			userList.get(1).getId());
		assertEquals("sammyjack",	userList.get(1).getUsername());
		assertEquals("Sammy Jack",	userList.get(1).getFullname());
		assertEquals(13096,			userList.get(2).getId());
		assertEquals("jacktiddy",	userList.get(2).getUsername());
		assertEquals("Jack Tiddy",	userList.get(2).getFullname());
	}

}
