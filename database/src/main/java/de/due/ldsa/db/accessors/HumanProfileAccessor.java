package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.HumanProfile;

/**
 * Created by  Romina
 */

@Accessor
public interface HumanProfileAccessor {
    @Query("SELECT * FROM ldsa.humanProfiles")
    public Result<HumanProfile> getAll();
}
