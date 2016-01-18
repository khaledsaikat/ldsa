package de.due.ldsa.ld;

import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.db.model.Profile;

/**Stores Objects of certain supported types in the Database.
 * 
 * @author Jan Kowollik
 *
 * @param <T> the type of the object to be stored
 */
public class StoreInDatabaseAction<T> implements Action<T>{

	@Override
	public void onAction(T t) {
		if(t.getClass() == Profile.class){	// Either this or one new class for every save-method
			DatabaseImpl.getInstance().autoSaveProfile((Profile) t);
		}
	}

}
