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

	private Database database;
	private StoreInDatabaseAction storeInDatabaseAction;
	
	
	public DatabaseStoreJsonReceiver(Database database) {
		this.database = database;
		storeInDatabaseAction = new StoreInDatabaseAction<>(database);
	}
	
	
	@Override
	public <T> void submitJsonObject(JSONObject jsonObject, Parser<T> parser) throws JSONException {
		T t = parser.parse(jsonObject);
		storeInDatabaseAction.onAction(t);
	}

}
