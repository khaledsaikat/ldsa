package de.due.ldsa.ld.test;

import static org.junit.Assert.*;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import de.due.ldsa.ld.example.InstagramUsersSelfMediaRecentParser;
import de.due.ldsa.model.Location;
import de.due.ldsa.model.Media;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.ProfileFeed;
import de.due.ldsa.model.SocialNetworkContent;

/**A Test case for {@link InstagramUsersSelfMediaRecentParser}.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramUsersSelfMediaRecentParserTest {

	@Test
	public void testParse() throws JSONException {
		List<SocialNetworkContent> resultList = InstagramUsersSelfMediaRecentParser
				.INSTANCE.parse(new JSONObject("{ \"data\": [{ \"comments\": { \"count\": 0 }, \"caption\": { \"created_time\": \"1296710352\", \"text\": \"Inside le truc #foodtruck\", \"from\": { \"username\": \"kevin\", \"full_name\": \"Kevin Systrom\", \"type\": \"user\", \"id\": \"3\" }, \"id\": \"26621408\" }, \"likes\": { \"count\": 15 }, \"link\": \"http://instagr.am/p/BWrVZ/\", \"user\": { \"username\": \"kevin\", \"profile_picture\": \"http://distillery.s3.amazonaws.com/profiles/profile_3_75sq_1295574122.jpg\", \"id\": \"3\" }, \"created_time\": \"1296710327\", \"images\": { \"low_resolution\": { \"url\": \"http://distillery.s3.amazonaws.com/media/2011/02/02/6ea7baea55774c5e81e7e3e1f6e791a7_6.jpg\", \"width\": 306, \"height\": 306 }, \"thumbnail\": { \"url\": \"http://distillery.s3.amazonaws.com/media/2011/02/02/6ea7baea55774c5e81e7e3e1f6e791a7_5.jpg\", \"width\": 150, \"height\": 150 }, \"standard_resolution\": { \"url\": \"http://distillery.s3.amazonaws.com/media/2011/02/02/6ea7baea55774c5e81e7e3e1f6e791a7_7.jpg\", \"width\": 612, \"height\": 612 } }, \"type\": \"image\", \"users_in_photo\": [], \"filter\": \"Earlybird\", \"tags\": [\"foodtruck\"], \"id\": \"22721881\", \"location\": { \"latitude\": 37.778720183610183, \"longitude\": -122.3962783813477, \"id\": \"520640\", \"street_address\": \"\", \"name\": \"Le Truc\" } }, { \"videos\": { \"low_resolution\": { \"url\": \"http://distilleryvesper9-13.ak.instagram.com/090d06dad9cd11e2aa0912313817975d_102.mp4\", \"width\": 480, \"height\": 480 }, \"standard_resolution\": { \"url\": \"http://distilleryvesper9-13.ak.instagram.com/090d06dad9cd11e2aa0912313817975d_101.mp4\", \"width\": 640, \"height\": 640 }}, \"comments\": { \"count\": 2 }, \"caption\": null, \"likes\": { \"count\": 1 }, \"link\": \"http://instagr.am/p/D/\", \"created_time\": \"1279340983\", \"images\": { \"low_resolution\": { \"url\": \"http://distilleryimage2.ak.instagram.com/11f75f1cd9cc11e2a0fd22000aa8039a_6.jpg\", \"width\": 306, \"height\": 306 }, \"thumbnail\": { \"url\": \"http://distilleryimage2.ak.instagram.com/11f75f1cd9cc11e2a0fd22000aa8039a_5.jpg\", \"width\": 150, \"height\": 150 }, \"standard_resolution\": { \"url\": \"http://distilleryimage2.ak.instagram.com/11f75f1cd9cc11e2a0fd22000aa8039a_7.jpg\", \"width\": 612, \"height\": 612 } }, \"type\": \"video\", \"users_in_photo\": null, \"filter\": \"Vesper\", \"tags\": [], \"id\": \"363839373298\", \"user\": { \"username\": \"kevin\", \"full_name\": \"Kevin S\", \"profile_picture\": \"http://distillery.s3.amazonaws.com/profiles/profile_3_75sq_1295574122.jpg\", \"id\": \"3\" }, \"location\": null }, ] }"));
		
		assertEquals(7, resultList.size());
		
		Media media = (Media) resultList.get(0);
		assertEquals(22721881, media.getId());
		assertEquals("http://instagr.am/p/BWrVZ/", media.getCrawlingPath());
		
		Profile profile = (Profile) resultList.get(1);
		assertEquals(3, profile.getId());
		assertEquals("kevin", profile.getUsername());
		assertEquals(1, profile.getProfileFeedIds().size());
		assertEquals(22721881, profile.getProfileFeedIds().get(0).longValue());
		
		ProfileFeed profileFeed = (ProfileFeed) resultList.get(2);
		assertEquals(22721881, profileFeed.getId());
		assertEquals(22721881, profileFeed.getMediaId());
		assertEquals(520640, profileFeed.getLocationId());
		assertEquals(3, profileFeed.getProfileId());
		
		Location location = (Location) resultList.get(3);
		assertEquals(520640, location.getId());
		assertEquals("Le Truc", location.getName());
		assertEquals(37.778720183610183, location.getPositionLatidue(), 0.0001);
		assertEquals(-122.3962783813477, location.getPositionLongitude(), 0.0001);
		
		media = (Media) resultList.get(4);
		assertEquals(363839373298L, media.getId());
		assertEquals("http://instagr.am/p/D/", media.getCrawlingPath());
		
		profile = (Profile) resultList.get(5);
		assertEquals(3, profile.getId());
		assertEquals("kevin", profile.getUsername());
		assertEquals("Kevin S", profile.getFullname());
		assertEquals(1, profile.getProfileFeedIds().size());
		assertEquals(363839373298L, profile.getProfileFeedIds().get(0).longValue());
		
		profileFeed = (ProfileFeed) resultList.get(6);
		assertEquals(363839373298L, profileFeed.getId());
		//assertEquals(363839373298L, profileFeed.getMediaId());	// TODO system model change media id type from int to long
		assertEquals(3, profileFeed.getProfileId());
		
	}

}
