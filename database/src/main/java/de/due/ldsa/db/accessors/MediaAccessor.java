package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.LocationImpl;
import de.due.ldsa.model.Media;

/**
 * Created by  Romina
 */

@Accessor
public interface MediaAccessor {
    @Query("SELECT * FROM ldsa.media")
    Result<Media> getAll();

    @Query("SELECT * FROM ldsa.media WHERE snId = :id")
    Result<Media> getAllFromSocialNetwork(@Param("id") int snId);
}
