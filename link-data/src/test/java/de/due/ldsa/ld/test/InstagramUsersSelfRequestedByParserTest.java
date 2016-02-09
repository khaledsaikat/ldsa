package de.due.ldsa.ld.test;

import static org.junit.Assert.*;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import de.due.ldsa.ld.parsers.InstagramUsersSelfRequestedByParser;
import de.due.ldsa.model.Profile;

/**A Test case for {@link InstagramUsersSelfRequestedByParser}.
 * 
 * @author Maik Wosnitzka
 *
 */
public class InstagramUsersSelfRequestedByParserTest {
	@Test
	public void testParse() throws JSONException {
		List<Profile> usersRequested = InstagramUsersSelfRequestedByParser.INSTANCE.
				parse(new JSONObject("{\"data\":[{\"username\":\"mikeyk\",\"profile_picture\":\"http://distillery.s3.amazonaws.com/profiles/profile_4_75sq_1292324747_debug.jpg\",\"id\": \"4\"}]}"));
		Profile user = usersRequested.get(0);
		assertEquals("mikeyk", user.getUsername());
		assertEquals(4, user.getId());
	}
}