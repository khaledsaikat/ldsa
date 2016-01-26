package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.Event;

/**
 * Created by  Romina
 */
@Accessor
public interface EventAccessor {
    @Query("SELECT * FROM ldsa.events WHERE snId = :id")
    Result<Event> getAllFromSocialNetwork(@Param("id") int snId);
}
