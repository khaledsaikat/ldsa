package de.due.ldsa.ld.test;

import static org.junit.Assert.assertEquals;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import de.due.ldsa.ld.parsers.InstagramTagsTagNameParser;
import de.due.ldsa.model.Hashtag;

public class InstagramTagsTagNameParserTest {

	@Test
	public void testParse() throws JSONException {
		Hashtag hashtag = InstagramTagsTagNameParser.INSTANCE
				.parse(new JSONObject("{"
					+ "\"data\": {"
			        + "\"media_count\": 472,"
			        + "\"name\": \"nofilter\","
			        + "}"
			        + "}"));
		assertEquals("nofilter", hashtag.getTitle());
	}
	
}
