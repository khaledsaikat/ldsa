package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.Comment;
import de.due.ldsa.model.ProfileFeed;

/**
 * @author scrobart
 * Provides an interface to work with Profile Feeds
 * Normally you do not need to implement this interface. The Mapper will generate a class at run-time.
 */
@Accessor
public interface ProfileFeedAccessor {
    /**
     * Gets all profile feeds from all social networks.
     *
     * @return A Cassandra Result containing all ProfileFeeds.
     */
    @Query("SELECT * FROM ldsa.profileFeeds")
    Result<ProfileFeed> getAll();

    /**
     * Gets all profile feeds from a specific social network.
     *
     * @param snId The ID of the social network
     * @return A Cassandra Result containing all ProfileFeeds from the specified social network.
     */
    @Query("SELECT * FROM ldsa.profileFeeds WHERE snId = :id")
    Result<ProfileFeed> getAllFromSocialNetwork(@Param("id") int snId);
}
