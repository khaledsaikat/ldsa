package de.due.ldsa.ld;

import java.util.List;

import de.due.ldsa.db.Database;
import de.due.ldsa.model.*;
import de.due.ldsa.model.CoopLocation;

/**Stores Objects of certain supported types in the Database.
 * 
 * @author Jan Kowollik
 *
 * @param <T> the type of the object to be stored
 */
public class StoreInDatabaseAction<T> implements Action<T>{

	private Database database;
	
	/**Creates a new instance of this class, that stores objects into
	 * the supplied database.
	 * 
	 * @param database the database to store objects in
	 */
	public StoreInDatabaseAction(Database database) {
		this.database = database;
	}
	
	/**Saves a single object or a list of objects to the database.
	 * Allowed types of T are {@link Comment}, {@link CoopProfile},
	 * {@link Hashtag}, {@link HumanProfile}, {@link LocationImpl},
	 * {@link Media}, {@link CoopLocation}, {@link ProfileFeed},
	 * {@link SocialNetwork} and {@link List}, where the {@link List}
	 * contains only elements of types mentioned above(including other lists).
	 * 
	 * @param t the object to save in the database
	 */
	@Override
	public void onAction(T t) {
		save(t);
	}
	
	/**Determine the Type of t and then try to save it to the Database.
	 * If t is a {@link List}, this method will call itself with every
	 * element from that list.
	 * 
	 * @param t the Object to save
	 */
	public void save(Object t){
		if(t instanceof Comment){	// Either this or one new class for every save-method
			database.saveComment((Comment) t);
		}else if(t instanceof CoopProfile){
			database.saveCoopProfile((CoopProfile) t);
		}else if(t instanceof Hashtag){
			database.saveHashtag((Hashtag) t);
		}else if(t instanceof HumanProfile){
			database.saveHumanProfile((HumanProfile) t);
		}else if(t instanceof LocationImpl){
			database.saveLocation((LocationImpl) t);	
		}else if(t instanceof Media){
			database.saveMedia((Media) t);
		}else if(t instanceof CoopLocation){
			database.saveCoopLocation((CoopLocation) t);
		}else if(t instanceof ProfileFeed){
			database.saveProfileFeed((ProfileFeed) t);
		}else if(t instanceof SocialNetwork){
			database.saveSocialNetwork((SocialNetwork) t);
		}else if(t instanceof List<?>){
			List<?> list = (List<?>) t;
			for (int i = 0; i < list.size(); i++) {
				save(list.get(i));
			}
		}
	}

}
