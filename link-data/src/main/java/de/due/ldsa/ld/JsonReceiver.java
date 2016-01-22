package de.due.ldsa.ld;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**Proposal for new interface between LinkDataLayer and TrackingService.
 * 
 * @author Jan Kowollik
 */
public interface JsonReceiver {

	/**Parses a JsonObject using the supplied parser and then carries out
	 * all supplied actions on the parsing result.
	 * 
	 * @param jsonObject the to be processed JsonObject
	 * @param parser a {@link Parser} that can parse the correct Json structure
	 * @param actions actions to do with the result e.g. 
	 * {@link StoreInDatabaseAction}
	 * @throws JSONException any {@link JSONException} occuring during the
	 * parsing step
	 */
	public <T> void submitJsonObject(JSONObject jsonObject, Parser<T> parser,
			Action<T>... actions) throws JSONException;
	
}
