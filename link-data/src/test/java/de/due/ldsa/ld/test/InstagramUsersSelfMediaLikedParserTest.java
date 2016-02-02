package de.due.ldsa.ld.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import de.due.ldsa.ld.example.InstagramUsersSelfFollowedByParser;
import de.due.ldsa.ld.example.InstagramUsersSelfMediaLikedParser;
import de.due.ldsa.model.Location;
import de.due.ldsa.model.Media;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.ProfileFeed;
import de.due.ldsa.model.SocialNetworkContent;

/**A Test case for {@link InstagramUsersSelfMediaLikedParser}.
 * 
 * @author Maik Wosnitzka
 *
 */
public class InstagramUsersSelfMediaLikedParserTest {
	
	@Test
	public void testParse() throws JSONException {
		String instagramTestResponse = "{\n"+
				" \"data\": [{\n"+
				" \"location\": {\n"+
				" \"id\": \"833\",\n"+
				" \"latitude\": 37.77956816727314,\n"+
				" \"longitude\": -122.41387367248539,\n"+
				" \"name\": \"Civic Center BART\"\n"+
				" },\n"+
				" \"comments\": {\n"+
				" \"count\": 16\n"+
				" },\n"+
				" \"caption\": null,\n"+
				" \"link\": \"http://instagr.am/p/BXsFz/\",\n"+
				" \"likes\": {\n"+
				" \"count\": 190\n"+
				" },\n"+
				" \"created_time\": \"1296748524\",\n"+
				" \"images\": {\n"+
				" \"low_resolution\": {\n"+
				" \"url\": \"http://distillery.s3.amazonaws.com/media/2011/02/03/efc502667a554329b52d9a6bab35b24a_6.jpg\",\n"+
				" \"width\": 306,\n"+
				" \"height\": 306\n"+
				" },\n"+
				" \"thumbnail\": {\n"+
				" \"url\": \"http://distillery.s3.amazonaws.com/media/2011/02/03/efc502667a554329b52d9a6bab35b24a_5.jpg\",\n"+
				" \"width\": 150,\n"+
				" \"height\": 150\n"+
				" },\n"+
				" \"standard_resolution\": {\n"+
				" \"url\": \"http://distillery.s3.amazonaws.com/media/2011/02/03/efc502667a554329b52d9a6bab35b24a_7.jpg\",\n"+
				" \"width\": 612,\n"+
				" \"height\": 612\n"+
				" }\n"+
				" },\n"+
				" \"type\": \"image\",\n"+
				" \"users_in_photo\": [],\n"+
				" \"filter\": \"Earlybird\",\n"+
				" \"tags\": [],\n"+
				" \"id\": \"22987123\",\n"+
				" \"user\": {\n"+
				" \"username\": \"kevin\",\n"+
				" \"full_name\": \"Kevin S\",\n"+
				" \"profile_picture\": \"http://distillery.s3.amazonaws.com/profiles/profile_3_75sq_1295574122.jpg\",\n"+
				" \"id\": \"3\"\n"+
				" }\n"+
				" },\n"+
				" {\n"+
				" \"videos\": {\n"+
				" \"low_resolution\": {\n"+
				" \"url\": \"http://distilleryvesper9-13.ak.instagram.com/090d06dad9cd11e2aa0912313817975d_102.mp4\",\n"+
				" \"width\": 480,\n"+
				" \"height\": 480\n"+
				" },\n"+
				" \"standard_resolution\": {\n"+
				" \"url\": \"http://distilleryvesper9-13.ak.instagram.com/090d06dad9cd11e2aa0912313817975d_101.mp4\",\n"+
				" \"width\": 640,\n"+
				" \"height\": 640\n"+
				" }\n"+
				" },\n"+
				" \"comments\": {\n"+
				" \"count\": 2\n"+
				" },\n"+
				" \"caption\": null,\n"+
				" \"likes\": {\n"+
				" \"count\": 1\n"+
				" },\n"+
				" \"link\": \"http://instagr.am/p/D/\",\n"+
				" \"created_time\": \"1279340983\",\n"+
				" \"images\": {\n"+
				" \"low_resolution\": {\n"+
				" \"url\": \"http://distilleryimage2.ak.instagram.com/11f75f1cd9cc11e2a0fd22000aa8039a_6.jpg\",\n"+
				" \"width\": 306,\n"+
				" \"height\": 306\n"+
				" },\n"+
				" \"thumbnail\": {\n"+
				" \"url\": \"http://distilleryimage2.ak.instagram.com/11f75f1cd9cc11e2a0fd22000aa8039a_5.jpg\",\n"+
				" \"width\": 150,\n"+
				" \"height\": 150\n"+
				" },\n"+
				" \"standard_resolution\": {\n"+
				" \"url\": \"http://distilleryimage2.ak.instagram.com/11f75f1cd9cc11e2a0fd22000aa8039a_7.jpg\",\n"+
				" \"width\": 612,\n"+
				" \"height\": 612\n"+
				" }\n"+
				" },\n"+
				" \"type\": \"video\",\n"+
				" \"users_in_photo\": null,\n"+
				" \"filter\": \"Vesper\",\n"+
				" \"tags\": [],\n"+
				" \"id\": \"363839373298\",\n"+
				" \"user\": {\n"+
				" \"username\": \"kevin\",\n"+
				" \"full_name\": \"Kevin S\",\n"+
				" \"profile_picture\": \"http://distillery.s3.amazonaws.com/profiles/profile_3_75sq_1295574122.jpg\",\n"+
				" \"id\": \"3\"\n"+
				" },\n"+
				" \"location\": null\n"+
				" }\n"+
				"]\n"+
				"}";
		
		ArrayList<SocialNetworkContent> resultContent = InstagramUsersSelfMediaLikedParser.INSTANCE.
				parse(new JSONObject(instagramTestResponse));
		
		assertEquals(7, resultContent.size());
		
		Media media = (Media) resultContent.get(0);
		assertEquals(22987123, media.getId());
		assertEquals("http://instagr.am/p/BXsFz/", media.getCrawlingPath());
		
		Profile profile = (Profile) resultContent.get(1);
		assertEquals(3, profile.getId());
		assertEquals("kevin", profile.getUsername());
		assertEquals("Kevin S", profile.getFullname());
		assertEquals(1, profile.getProfileFeedIds().size());
		assertEquals(22987123, profile.getProfileFeedIds().get(0).longValue());
		
		ProfileFeed profileFeed = (ProfileFeed) resultContent.get(2);
		assertEquals(22987123, profileFeed.getId());
		assertEquals(22987123, profileFeed.getMediaId());
		assertEquals(3, profileFeed.getProfileId());
		
		Location location = (Location) resultContent.get(3);
		assertEquals(833, location.getId());
		assertEquals("Civic Center BART", location.getName());
		assertEquals(37.77956816727314, location.getPositionLatidue(), 0.0001);
		assertEquals(-122.41387367248539, location.getPositionLongitude(), 0.0001);
		
		
		media = (Media) resultContent.get(4);
		assertEquals(363839373298l, media.getId());
		assertEquals("http://instagr.am/p/D/", media.getCrawlingPath());
		
		profile = (Profile) resultContent.get(5);
		assertEquals(3, profile.getId());
		assertEquals("kevin", profile.getUsername());
		assertEquals("Kevin S", profile.getFullname());
		assertEquals(1, profile.getProfileFeedIds().size());
		assertEquals(363839373298l, profile.getProfileFeedIds().get(0).longValue());
		
		profileFeed = (ProfileFeed) resultContent.get(6);
		assertEquals(363839373298l, profileFeed.getId());
		assertEquals((int)363839373298l, profileFeed.getMediaId());
		assertEquals(3, profileFeed.getProfileId());
	
	}
}
