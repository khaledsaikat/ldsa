package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.Comment;
import de.due.ldsa.model.ProfileFeed;

/**
 * Created by  Romina
 */
@Accessor
public interface HashtagAccessor {
    @Query("SELECT * FROM comments WHERE hashtagNames CONTAINS :name")
    Result<Comment> getCommentsUsedIn(String s);

    @Query("SELECT * FROM profileFeeds WHERE hashtags CONTAINS :name")
    Result<ProfileFeed> getProfileFeedsUsedIn(String s);
}
