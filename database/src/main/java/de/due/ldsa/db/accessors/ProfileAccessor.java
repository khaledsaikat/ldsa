package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.Comment;
import de.due.ldsa.model.ProfileFeed;

/**
 * @author scrobart
 * Provides an interface to work with Profiles in general.
 * Normally you do not need to implement this interface. The Mapper will generate a class at run-time.
 */
@Accessor
public interface ProfileAccessor {
    /**
     * Gets all profile feeds related to a profile.
     *
     * @param profileId The ID of the profile you want to get profileFeeds from.
     * @return All Profile feeds related to a profile.
     */
    @Query("SELECT * FROM ldsa.profileFeeds WHERE profileId = :id")
    Result<ProfileFeed> getProfileFeeds(@Param("id") long profileId);

    /**
     * Gets all comments made by a profile.
     *
     * @param profileId The ID of the profile you need comments from.
     * @return All Comments related to a profile.
     */
    @Query("SELECT * FROM ldsa.comments WHERE commenter = :id")
    Result<Comment> getProfileComments(@Param("id") long profileId);

}
