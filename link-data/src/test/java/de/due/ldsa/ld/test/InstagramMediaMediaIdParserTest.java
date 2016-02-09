package de.due.ldsa.ld.test;

import static org.junit.Assert.*;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import de.due.ldsa.ld.parsers.InstagramMediaMediaIdParser;
import de.due.ldsa.model.Media;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.ProfileFeed;
import de.due.ldsa.model.SocialNetworkContent;

/**A Test case for {@link InstagramMediaMediaIdParser}.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramMediaMediaIdParserTest {

	@Test
	public void testParse() throws JSONException {
		List<SocialNetworkContent> resultList = InstagramMediaMediaIdParser.INSTANCE.
				parse(new JSONObject("{ \"data\": { \"type\": \"image\", \"users_in_photo\": [{ \"user\": { \"username\": \"kevin\", \"full_name\": \"Kevin S\", \"id\": \"3\", \"profile_picture\": \"...\" }, \"position\": { \"x\": 0.315, \"y\": 0.9111 } }], \"filter\": \"Walden\", \"tags\": [], \"comments\": { \"count\": 2 }, \"caption\": null, \"likes\": { \"count\": 1 }, \"link\": \"http://instagr.am/p/D/\", \"user\": { \"username\": \"kevin\", \"full_name\": \"Kevin S\", \"profile_picture\": \"...\", \"id\": \"3\" }, \"created_time\": \"1279340983\", \"images\": { \"low_resolution\": { \"url\": \"http://distillery.s3.amazonaws.com/media/2010/07/16/4de37e03aa4b4372843a7eb33fa41cad_6.jpg\", \"width\": 306, \"height\": 306 }, \"thumbnail\": { \"url\": \"http://distillery.s3.amazonaws.com/media/2010/07/16/4de37e03aa4b4372843a7eb33fa41cad_5.jpg\", \"width\": 150, \"height\": 150 }, \"standard_resolution\": { \"url\": \"http://distillery.s3.amazonaws.com/media/2010/07/16/4de37e03aa4b4372843a7eb33fa41cad_7.jpg\", \"width\": 612, \"height\": 612 } }, \"id\": \"3\", \"location\": null } }"));
		
		assertEquals(3, resultList.size());
		
		Media media = (Media) resultList.get(0);
		assertEquals(3, media.getId());
		assertEquals("http://instagr.am/p/D/", media.getCrawlingPath());
		
		Profile profile = (Profile) resultList.get(1);
		assertEquals(3, profile.getId());
		assertEquals("kevin", profile.getUsername());
		assertEquals("Kevin S", profile.getFullname());
		assertEquals(1, profile.getProfileFeedIds().size());
		assertEquals(3, profile.getProfileFeedIds().get(0).longValue());
		
		ProfileFeed profileFeed = (ProfileFeed) resultList.get(2);
		assertEquals(3, profileFeed.getId());
		assertEquals(3, profileFeed.getMediaId());
		assertEquals(3, profileFeed.getProfileId());
	}

}
