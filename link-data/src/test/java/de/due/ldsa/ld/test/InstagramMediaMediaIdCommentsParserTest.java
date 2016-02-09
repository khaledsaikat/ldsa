package de.due.ldsa.ld.test;

import static org.junit.Assert.*;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import de.due.ldsa.ld.parsers.InstagramMediaMediaIdCommentsParser;
import de.due.ldsa.model.Comment;
import de.due.ldsa.model.Profile;
import de.due.ldsa.model.SocialNetworkContent;

/**A Test case for {@link InstagramMediaMediaIdCommentsParser}.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramMediaMediaIdCommentsParserTest {

	@Test
	public void testParse() throws JSONException {
		List<SocialNetworkContent> resultList = InstagramMediaMediaIdCommentsParser
				.INSTANCE.parse(new JSONObject("{ \"data\": [ { \"created_time\": \"1280780324\", \"text\": \"Really amazing photo!\", \"from\": { \"username\": \"snoopdogg\", \"profile_picture\": \"http://images.instagram.com/profiles/profile_16_75sq_1305612434.jpg\", \"id\": \"1574083\", \"full_name\": \"Snoop Dogg\" }, \"id\": \"420\" }]}"));
		assertEquals(2, resultList.size());
		Comment comment = (Comment) resultList.get(0);
		assertEquals(420, comment.getId());
		assertEquals("Really amazing photo!", comment.getText());
		assertEquals(1574083, comment.getCommenterId());
		Profile sender = (Profile) resultList.get(1);
		assertEquals(1574083, sender.getId());
		assertEquals("snoopdogg", sender.getUsername());
		assertEquals("Snoop Dogg", sender.getFullname());
		assertEquals(1, sender.getAllCommentsId().size());
		assertEquals(1574083, sender.getAllCommentsId().get(0).longValue());
	}

}
