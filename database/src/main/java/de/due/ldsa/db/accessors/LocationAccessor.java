package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.LocationImpl;

/**
 * Created by  Romina
 */

@Accessor
public interface LocationAccessor {
    @Query("SELECT * FROM ldsa.locations")
    Result<LocationImpl> getAll();

    @Query("SELECT * FROM ldsa.locations WHERE snId = :id")
    Result<LocationImpl> getAllFromSocialNetwork(int snId);
}
