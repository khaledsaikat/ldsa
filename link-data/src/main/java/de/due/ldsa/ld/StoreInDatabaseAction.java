package de.due.ldsa.ld;

import java.util.List;

import de.due.ldsa.db.Database;
import de.due.ldsa.model.Comment;
import de.due.ldsa.model.CoopProfile;
import de.due.ldsa.model.Hashtag;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.LocationImpl;
import de.due.ldsa.model.Media;
import de.due.ldsa.model.OrganisationPlace;
import de.due.ldsa.model.ProfileFeed;
import de.due.ldsa.model.SocialNetwork;

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
		}else if(t instanceof OrganisationPlace){
			database.saveOrganisationPlace((OrganisationPlace) t);
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
