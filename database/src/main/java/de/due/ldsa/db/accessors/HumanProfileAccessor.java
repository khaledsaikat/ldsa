package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.ProfileFeed;

/**
 * Created by  Romina
 */
@Accessor
public interface HumanProfileAccessor {
    @Query("SELECT * FROM ldsa.humanProfiles WHERE snId = :id")
    Result<HumanProfile> getAllFromSocialNetwork(@Param("id") int snId);

    @Query("SELECT * FROM ldsa.humanProfiles")
    Result<HumanProfile> getAll();


}
