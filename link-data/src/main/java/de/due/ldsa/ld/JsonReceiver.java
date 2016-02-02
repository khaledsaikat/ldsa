package de.due.ldsa.ld;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**New interface between LinkDataLayer and TrackingService.
 * 
 * @author Jan Kowollik
 */
public interface JsonReceiver {

	/**Parses a JsonObject using the supplied parser.
	 * 
	 * @param jsonObject the to be processed JsonObject
	 * @param parser a {@link Parser} that can parse the correct Json structure
	 * @throws JSONException any {@link JSONException} occuring during the
	 * parsing step
	 */
	public <T> void submitJsonObject(JSONObject jsonObject, Parser<T> parser) throws JSONException;
	
}
