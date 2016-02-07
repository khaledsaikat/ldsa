package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.Event;
import de.due.ldsa.model.LocationImpl;

/**
 * @author scrobart
 * Provides an interface to work with Locations.
 * Normally you do not need to implement this interface. The Mapper will generate a class at run-time.
 */
@Accessor
public interface LocationAccessor {
    /**
     * Gets all Locations from all Social Networks.
     *
     * @return A Cassandra Result containing all Locations (excluding OrganisationPlaces)
     */
    @Query("SELECT * FROM ldsa.locations")
    Result<LocationImpl> getAll();

    /**
     * Gets all Locations from a specific social network.
     *
     * @param snId The Social Network you want to analyze.
     * @return A Cassandra Result containing all Locations (excluding OrganisationPlaces) from the specified Social
     * Network.
     */
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
