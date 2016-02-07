package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.CoopProfile;

/**
 * @author scrobart
 * Provides an interface to access CoopProfiles
 * Normally you do not need to implement this interface. The Mapper will generate a class at run-time.
 */
@Accessor
public interface CoopProfileAccessor {
    /**
     * Gets all CoopProfiles from a specific social network
     *
     * @param snId The ID of the social network you need the CoopProfiles from.
     * @return A Cassandra Result containing all the CoopProfiles from that SocialNetwork
     */
    @Query("SELECT * FROM ldsa.coopProfiles WHERE snId = :id")
    Result<CoopProfile> getAllFromSocialNetwork(@Param("id") int snId);
}
