package de.due.ldsa.ld.test;

import static org.junit.Assert.*;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import de.due.ldsa.ld.example.InstagramMediaMediaIdLikesParser;
import de.due.ldsa.model.Profile;

/**A Test case for {@link InstagramMediaMediaIdCommentsParser} and
 * {@link InstagramCommentParser}.
 * 
 * @author Maik Wosnitzka
 *
 */
public class InstagramMediaMediaIdLikesParserTest {

	@Test
	public void testParse() throws JSONException {
		List<Profile> likerList = InstagramMediaMediaIdLikesParser
				.INSTANCE.parse(new JSONObject("{\"data\": [{"
						+ "\"username\": \"jack\","
						+ "\"first_name\": \"Jack\","
						+ "\"last_name\": \"Dorsey\","
						+ "\"type\": \"user\","
						+ "\"id\": \"66\""
						+ "},"
						+ "{"
						+ "\"username\": \"sammyjack\","
						+ "\"first_name\": \"Sammy\","
						+ "\"last_name\": \"Jack\","
						+ "\"type\": \"user\","
						+ "\"id\": \"29648\""
						+ "}]"
						+ "}"));
		assertEquals("jack", likerList.get(0).getUsername());
		assertEquals("Jack Dorsey", likerList.get(0).getFullname());
		assertEquals(66, likerList.get(0).getId());
		assertEquals("sammyjack", likerList.get(1).getUsername());
		assertEquals("Sammy Jack", likerList.get(1).getFullname());
		assertEquals(29648, likerList.get(1).getId());
	}

}
