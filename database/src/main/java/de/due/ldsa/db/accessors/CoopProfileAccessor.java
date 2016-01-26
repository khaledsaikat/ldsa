package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.CoopProfile;

/**
 * Created by  Romina
 */
@Accessor
public interface CoopProfileAccessor {
    @Query("SELECT * FROM ldsa.coopProfiles WHERE snId = :id")
    Result<CoopProfile> getAllFromSocialNetwork(@Param("id") int snId);
}
