package de.due.ldsa.ld;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**A generic parser interface for parsing {@link JSONObject}s.
 * Creates a single object of type T.
 * 
 * @author Jan Kowollik
 *
 * @param <T> the type of the returned instance
 */
public interface Parser<T> {
	
	/**Parses an instance of type {@link T} from an instance of a
	 * {@link JSONObject}.
	 * 
	 * @param json the {@link JSONObject} to parse from
	 * @return an instance of type {@link T}
	 * @throws JSONException in case of malformed JSON or JSON not parseable
	 * by this parser.
	 */
	public T parse(JSONObject json) throws JSONException;

}
