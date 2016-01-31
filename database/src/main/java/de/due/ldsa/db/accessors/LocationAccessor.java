package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.Event;
import de.due.ldsa.model.Location;
import de.due.ldsa.model.LocationImpl;
import de.due.ldsa.model.OrganisationPlace;

/**
 * Created by  Romina
 */

@Accessor
public interface LocationAccessor {
    @Query("SELECT * FROM ldsa.locations")
    Result<LocationImpl> getAll();

    @Query("SELECT * FROM ldsa.locations WHERE snId = :id")
    Result<LocationImpl> getAllFromSocialNetwork(int snId);

    /**
     * Gets all the events this location was used for.
     * @param locationId The Id of the location you want to check.
     * @return All the events this location was used for.
     */
    @Query("SELECT * FROM ldsa.events WHERE locationId = :id")
    Result<Event> getEvents(@Param("id") long locationId);

}
