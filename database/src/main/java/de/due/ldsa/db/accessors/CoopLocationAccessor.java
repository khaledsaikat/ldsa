package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.CoopLocation;

/**
 * @author scrobart
 * Provides an interface to OrganisationPlaces.
 * Normally you do not need to implement this interface. The Mapper will generate a class at run-time.
 */
@Accessor
public interface CoopLocationAccessor {
    /**
     * Gets all Organisation Places from all Social Networks.
     *
     * @return A Cassandra Result containing all Organisation Places.
     */
    @Query("SELECT * FROM ldsa.coopLocations")
    Result<CoopLocation> getAll();

    /**
     * Gets all Organisation Places from a specific Social Network.
     *
     * @param snId The ID of the social network you need the organisation places from
     * @return A Cassandra Result containing all Organisation Places from the specified social network.
     */
    @Query("SELECT * FROM ldsa.coopLocations WHERE snId = :id")
    Result<CoopLocation> getAllFromSocialNetwork(int snId);
}
