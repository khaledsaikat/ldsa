package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.Event;

/**
 * @author scrobart
 * Provides an interface to access events.
 * Normally you do not need to implement this interface. The Mapper will generate a class at run-time.
 */
@Accessor
public interface EventAccessor {
    /**
     * Gets all Events from a specific social network.
     *
     * @param snId The Social Network you want to get all events from.
     * @return A Cassandra Result containing all Events from the specified social network.
     */
    @Query("SELECT * FROM ldsa.events WHERE snId = :id")
    Result<Event> getAllFromSocialNetwork(@Param("id") int snId);
}
