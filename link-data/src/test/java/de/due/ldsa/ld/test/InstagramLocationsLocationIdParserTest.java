package de.due.ldsa.ld.test;

import static org.junit.Assert.*;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import de.due.ldsa.ld.parsers.InstagramLocationsLocationIdParser;
import de.due.ldsa.model.Location;

/**Test case for {@link InstagramLocationsLocationIdParser}.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramLocationsLocationIdParserTest {

	@Test
	public void testParse() throws JSONException {
		Location testLocation = InstagramLocationsLocationIdParser.INSTANCE
				.parse(new JSONObject("{"
					+ "\"data\": {"
			        + "\"id\": \"1\","
			        + "\"name\": \"Dogpatch Labs\","
			        + "\"latitude\": 37.782,"
			        + "\"longitude\": -122.387,"
			        + "}"
			        + "}"));
		assertEquals(1, testLocation.getId());
		assertEquals("Dogpatch Labs", testLocation.getName());
		assertEquals(37.782, testLocation.getPositionLatidue(), 0.0001);
		assertEquals(-122.387, testLocation.getPositionLongitude(), 0.0001);
	}

}
