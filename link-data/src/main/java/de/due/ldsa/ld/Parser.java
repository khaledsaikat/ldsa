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
	
	public T parse(JSONObject json) throws JSONException;

}
