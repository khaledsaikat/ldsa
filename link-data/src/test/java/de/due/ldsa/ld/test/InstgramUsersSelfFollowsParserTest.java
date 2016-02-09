package de.due.ldsa.ld.test;

import static org.junit.Assert.*;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import de.due.ldsa.ld.parsers.InstagramUsersSelfFollowsParser;
import de.due.ldsa.model.Profile;

/**A Test case for {@link InstgramUsersSelfFollowsParser}.
 * 
 * @author Maik Wosnitzka
 *
 */
public class InstgramUsersSelfFollowsParserTest {
	@Test
	public void testParse() throws JSONException {
		List<Profile> usersRequested = InstagramUsersSelfFollowsParser.INSTANCE.
				parse(new JSONObject("{\"data\": [{\"username\": \"kevin\",\"profile_picture\": \"http://images.ak.instagram.com/profiles/profile_3_75sq_1325536697.jpg\",\"full_name\": \"Kevin Systrom\",\"id\": \"3\"},{\"username\": \"instagram\",\"profile_picture\": \"http://images.ak.instagram.com/profiles/profile_25025320_75sq_1340929272.jpg\",\"full_name\":\"Instagram\",\"id\": \"25025320\"}]}"));
		
		Profile userA = usersRequested.get(0);
		assertEquals("kevin", userA.getUsername());
		assertEquals("Kevin Systrom", userA.getFullname());
		assertEquals(3, userA.getId());
		
		Profile userB = usersRequested.get(1);
		assertEquals("instagram", userB.getUsername());
		assertEquals("Instagram", userB.getFullname());
		assertEquals(25025320, userB.getId());
	}
}

