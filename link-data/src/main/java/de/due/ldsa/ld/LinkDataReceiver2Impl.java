package de.due.ldsa.ld;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**Default implementation for the {@link LinkDataReceiver2} interface, that
 * does not yet use the big data framework.
 * 
 * @author Jan Kowollik
 *
 */
public class LinkDataReceiver2Impl implements LinkDataReceiver2 {

	@Override
	public <T> void submitJsonObject(JSONObject jsonObject, Parser<T> parser,
			Action<T>... actions) throws JSONException {
		T t = parser.parse(jsonObject);
		for (int i = 0; i < actions.length; i++) {
			actions[i].onAction(t);
		}
	}

}
