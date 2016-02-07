package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.LocationImpl;
import de.due.ldsa.model.Media;

/**
 * @author scrobart
 * Provides an interface to work with Media Objects.
 * Normally you do not need to implement this interface. The Mapper will generate a class at run-time.
 */
@Accessor
public interface MediaAccessor {
    /**
     * Gets all Media from all Social Networks
     *
     * @return A Cassandra Result containing all media
     */
    @Query("SELECT * FROM ldsa.media")
    Result<Media> getAll();

    /**
     * Gets all Media from a specific Social Network
     *
     * @param snId The Social Network you need to get the Media from.
     * @return A Cassandra Result containing all media from the specified social network.
     */
    @Query("SELECT * FROM ldsa.media WHERE snId = :id")
    Result<Media> getAllFromSocialNetwork(@Param("id") int snId);
}
