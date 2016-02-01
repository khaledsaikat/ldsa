package de.due.ldsa.ld.test;

import static org.junit.Assert.*;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import de.due.ldsa.ld.example.InstagramMediaSearchParser;
import de.due.ldsa.model.Media;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.ProfileFeed;
import de.due.ldsa.model.SocialNetworkContent;

/**A Test case for {@link InstagramMediaSearchParser}.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramMediaSearchParserTest {

	@Test
	public void testParse() throws JSONException {
		List<SocialNetworkContent> resultList = InstagramMediaSearchParser.INSTANCE.
				parse(new JSONObject("{ \"data\": [{ \"distance\": 41.741369194629698, \"type\": \"image\", \"users_in_photo\": [], \"filter\": \"Earlybird\", \"tags\": [], \"comments\": { \"count\": 2 }, \"caption\": null, \"likes\": { \"count\": 1 }, \"link\": \"http://instagr.am/p/BQEEq/\", \"user\": { \"username\": \"mahaface\", \"profile_picture\": \"http://distillery.s3.amazonaws.com/profiles/profile_1329896_75sq_1294131373.jpg\", \"id\": \"1329896\" }, \"created_time\": \"1296251679\", \"images\": { \"low_resolution\": { \"url\": \"http://distillery.s3.amazonaws.com/media/2011/01/28/0cc4f24f25654b1c8d655835c58b850a_6.jpg\", \"width\": 306, \"height\": 306 }, \"thumbnail\": { \"url\": \"http://distillery.s3.amazonaws.com/media/2011/01/28/0cc4f24f25654b1c8d655835c58b850a_5.jpg\", \"width\": 150, \"height\": 150 }, \"standard_resolution\": { \"url\": \"http://distillery.s3.amazonaws.com/media/2011/01/28/0cc4f24f25654b1c8d655835c58b850a_7.jpg\", \"width\": 612, \"height\": 612 } }, \"id\": \"20988202\", \"location\": null }, { \"distance\": 41.741369194629698, \"type\": \"video\", \"videos\": { \"low_resolution\": { \"url\": \"http://distilleryvesper9-13.ak.instagram.com/090d06dad9cd11e2aa0912313817975d_102.mp4\", \"width\": 480, \"height\": 480 }, \"standard_resolution\": { \"url\": \"http://distilleryvesper9-13.ak.instagram.com/090d06dad9cd11e2aa0912313817975d_101.mp4\", \"width\": 640, \"height\": 640 }}, \"users_in_photo\": null, \"filter\": \"Vesper\", \"tags\": [], \"comments\": { \"count\": 2 }, \"caption\": null, \"likes\": { \"count\": 1 }, \"link\": \"http://instagr.am/p/D/\", \"user\": { \"username\": \"kevin\", \"full_name\": \"Kevin S\", \"profile_picture\": \"...\", \"id\": \"3\" }, \"created_time\": \"1279340983\", \"images\": { \"low_resolution\": { \"url\": \"http://distilleryimage2.ak.instagram.com/11f75f1cd9cc11e2a0fd22000aa8039a_6.jpg\", \"width\": 306, \"height\": 306 }, \"thumbnail\": { \"url\": \"http://distilleryimage2.ak.instagram.com/11f75f1cd9cc11e2a0fd22000aa8039a_5.jpg\", \"width\": 150, \"height\": 150 }, \"standard_resolution\": { \"url\": \"http://distilleryimage2.ak.instagram.com/11f75f1cd9cc11e2a0fd22000aa8039a_7.jpg\", \"width\": 612, \"height\": 612 } }, \"id\": \"3\", \"location\": null }]}"));
						
		assertEquals(6, resultList.size());
		
		Media media = (Media) resultList.get(0);
		assertEquals(20988202, media.getId());
		assertEquals("http://instagr.am/p/BQEEq/", media.getCrawlingPath());
		
		Profile profile = (Profile) resultList.get(1);
		assertEquals(1329896, profile.getId());
		assertEquals("mahaface", profile.getUsername());
		assertEquals(1, profile.getProfileFeedIds().size());
		assertEquals(20988202, profile.getProfileFeedIds().get(0).longValue());
		
		ProfileFeed profileFeed = (ProfileFeed) resultList.get(2);
		assertEquals(20988202, profileFeed.getId());
		assertEquals(20988202, profileFeed.getMediaId());
		assertEquals(1329896, profileFeed.getProfileId());
		
		media = (Media) resultList.get(3);
		assertEquals(3, media.getId());
		assertEquals("http://instagr.am/p/D/", media.getCrawlingPath());
		
		profile = (Profile) resultList.get(4);
		assertEquals(3, profile.getId());
		assertEquals("kevin", profile.getUsername());
		assertEquals("Kevin S", profile.getFullname());
		assertEquals(1, profile.getProfileFeedIds().size());
		assertEquals(3, profile.getProfileFeedIds().get(0).longValue());
		
		profileFeed = (ProfileFeed) resultList.get(5);
		assertEquals(3, profileFeed.getId());
		assertEquals(3, profileFeed.getMediaId());
		assertEquals(3, profileFeed.getProfileId());
	}

}
