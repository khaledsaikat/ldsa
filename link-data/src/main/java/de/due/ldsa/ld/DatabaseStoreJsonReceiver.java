package de.due.ldsa.ld;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import de.due.ldsa.db.Database;

/**A {@link DatabaseStoreJsonReceiver} converts the received Json data
 * into one or more system model objects and stores them into a database. 
 * 
 * @author Jan Kowollik
 *
 */
public class DatabaseStoreJsonReceiver implements JsonReceiver {

	private StoreInDatabaseAction storeInDatabaseAction;
	
	/**Creates a new instance of this class using the supplied database
	 * instance to save objects.
	 * 
	 * @param database the {@link Database} instance to use
	 */
	public DatabaseStoreJsonReceiver(Database database) {
		storeInDatabaseAction = new StoreInDatabaseAction<>(database);
	}
	
	/**Parses a JsonObject using the supplied parser and then saves all
	 * objects created from the parser into the database.
	 * 
	 * @param jsonObject the to be processed JsonObject
	 * @param parser a {@link Parser} that can parse the correct Json structure
	 * @throws JSONException any {@link JSONException} occuring during the
	 * parsing step
	 */
	@Override
	public <T> void submitJsonObject(JSONObject jsonObject, Parser<T> parser) throws JSONException {
		T t = parser.parse(jsonObject);
		storeInDatabaseAction.onAction(t);
	}

}
