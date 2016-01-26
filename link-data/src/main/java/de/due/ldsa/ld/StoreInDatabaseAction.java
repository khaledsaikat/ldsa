package de.due.ldsa.ld;

import java.util.List;

import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.model.Comment;
import de.due.ldsa.model.LocationImpl;
import de.due.ldsa.model.Profile;

/**Stores Objects of certain supported types in the Database.
 * 
 * @author Jan Kowollik
 *
 * @param <T> the type of the object to be stored
 */
public class StoreInDatabaseAction<T> implements Action<T>{

	@Override
	public void onAction(T t) {
		save(t);
	}
	
	/**Determine the Type of t and then try to save it to the Database.
	 * If t is {@link List}, this method will call itself with every
	 * element from that list.
	 * 
	 * @param t the Object to save
	 */
	public void save(Object t){
		if(t instanceof Profile){	// Either this or one new class for every save-method
			DatabaseImpl.getInstance().autoSaveProfile((Profile) t);
		}else if(t instanceof Comment){
			DatabaseImpl.getInstance().saveComment((Comment) t);
		}else if(t instanceof LocationImpl){
			DatabaseImpl.getInstance().saveLocation((LocationImpl) t);
		}else if(t instanceof List<?>){
			List<?> list = (List<?>) t;
			for (int i = 0; i < list.size(); i++) {
				save(list.get(i));
			}
		}
	}

}
