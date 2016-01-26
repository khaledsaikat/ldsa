package de.due.ldsa.ld.test;

import static org.junit.Assert.*;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import de.due.ldsa.ld.example.InstagramLocationsSearchParser;
import de.due.ldsa.model.Location;

/**A test case for {@link InstagramLocationsSearchParser}.
 * 
 * @author Jan Kowollik
 *
 */
public class InstagramLocationsSearchParserTest {

	@Test
	public void testParse() throws JSONException {
		List<Location> locationList = InstagramLocationsSearchParser.INSTANCE
				.parse(new JSONObject("{ \"data\": [{ \"id\": \"788029\", \"latitude\": 48.858844300000001, \"longitude\": 2.2943506, \"name\": \"Eiffel Tower, Paris\" }, { \"id\": \"545331\", \"latitude\": 48.858334059662262, \"longitude\": 2.2943401336669909, \"name\": \"Restaurant 58 Tour Eiffel\" }, { \"id\": \"421930\", \"latitude\": 48.858325999999998, \"longitude\": 2.294505, \"name\": \"American Library in Paris\" }] }"));
		assertEquals(3, locationList.size());
		assertEquals(788029, locationList.get(0).getId());
		assertEquals(48.858844300000001, locationList.get(0).getPositionLatidue(), 0.00001);
		assertEquals(2.2943506, locationList.get(0).getPositionLongitude(), 0.00001);
		assertEquals("Eiffel Tower, Paris", locationList.get(0).getName());
		assertEquals(545331, locationList.get(1).getId());
		assertEquals(48.858334059662262, locationList.get(1).getPositionLatidue(), 0.00001);
		assertEquals(2.2943401336669909, locationList.get(1).getPositionLongitude(), 0.00001);
		assertEquals("Restaurant 58 Tour Eiffel", locationList.get(1).getName());
		assertEquals(421930, locationList.get(2).getId());
		assertEquals(48.858325999999998, locationList.get(2).getPositionLatidue(), 0.00001);
		assertEquals(2.294505, locationList.get(2).getPositionLongitude(), 0.00001);
		assertEquals("American Library in Paris", locationList.get(2).getName());
	}

}
