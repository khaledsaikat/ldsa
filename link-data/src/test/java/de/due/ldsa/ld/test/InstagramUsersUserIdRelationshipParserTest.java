package de.due.ldsa.ld.test;

import static org.junit.Assert.*;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import de.due.ldsa.ld.parsers.InstagramUsersUserIdRelationshipParser;
import de.due.ldsa.model.SocialNetworkContent;

/**A test case for {@link InstagramUsersUserIdRelationshipParser}.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramUsersUserIdRelationshipParserTest {

	@Test
	public void testParse() throws JSONException {
		List<SocialNetworkContent> resultList = InstagramUsersUserIdRelationshipParser.INSTANCE.parse(new
				JSONObject("{}"));
		assertTrue(resultList.isEmpty());
	}

}
