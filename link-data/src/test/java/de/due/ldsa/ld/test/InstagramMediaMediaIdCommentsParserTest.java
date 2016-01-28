package de.due.ldsa.ld.test;

import static org.junit.Assert.*;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import de.due.ldsa.ld.example.InstagramMediaMediaIdCommentsParser;
import de.due.ldsa.model.Comment;

/**A Test case for {@link InstagramMediaMediaIdCommentsParser}.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramMediaMediaIdCommentsParserTest {

	@Test
	public void testParse() throws JSONException {
		List<Comment> commentList = InstagramMediaMediaIdCommentsParser
				.INSTANCE.parse(new JSONObject("{ \"data\": [ { \"created_time\": \"1280780324\", \"text\": \"Really amazing photo!\", \"from\": { \"username\": \"snoopdogg\", \"profile_picture\": \"http://images.instagram.com/profiles/profile_16_75sq_1305612434.jpg\", \"id\": \"1574083\", \"full_name\": \"Snoop Dogg\" }, \"id\": \"420\" }]}"));
		assertEquals(1, commentList.size());
		assertEquals(420, commentList.get(0).getId());
		assertEquals("Really amazing photo!", commentList.get(0).getText());
		assertEquals(1574083, commentList.get(0).getCommenterId());
	}

}
