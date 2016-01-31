package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.Comment;
import de.due.ldsa.model.ProfileFeed;

/**
 * Created by  Romina
 */
@Accessor
public interface ProfileFeedAccessor {
    @Query("SELECT * FROM ldsa.profileFeeds")
    Result<ProfileFeed> getAll();

    @Query("SELECT * FROM ldsa.profileFeeds WHERE snId = :id")
    Result<ProfileFeed> getAllFromSocialNetwork(@Param("id") int snId);
}
