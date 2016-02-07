package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.ProfileFeed;

/**
 * @author scrobart
 * Provides an interface to work with HumanProfiles.
 * Normally you do not need to implement this interface. The Mapper will generate a class at run-time.
 */
@Accessor
public interface HumanProfileAccessor {
    /**
     * Gets all Human Profiles from a specific Social Network
     *
     * @param snId The social Network you want to get the Human Profiles from.
     * @return A Cassandra Result containing all HumanProfilesm from the specified Social Network
     */
    @Query("SELECT * FROM ldsa.humanProfiles WHERE snId = :id")
    Result<HumanProfile> getAllFromSocialNetwork(@Param("id") int snId);

    /**
     * Gets all Human PRofiles from all Social Networks.
     *
     * @return A Cassandra Result containing all HumanProfiles
     */
    @Query("SELECT * FROM ldsa.humanProfiles")
    Result<HumanProfile> getAll();


}
