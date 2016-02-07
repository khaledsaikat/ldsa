package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.Comment;
import de.due.ldsa.model.Hashtag;
import de.due.ldsa.model.ProfileFeed;

/**
 * @author scrobart
 * Provides an interface to work with hashtags-
 * Normally you do not need to implement this interface. The Mapper will generate a class at run-time.
 */
@Accessor
public interface HashtagAccessor {
    /**
     * Gets all comments that mention this hashtag.
     *
     * @param s The hashtag you need information about.
     * @return A Cassandra Result containing All the Comments this Hashtag is
     */
    @Query("SELECT * FROM comments WHERE hashtagNames CONTAINS :name")
    Result<Comment> getCommentsUsedIn(String s);

    /**
     * Gets all ProfileFeeds that mention this hashtag.
     *
     * @param s The hashtag you need information about.
     * @return A Cassandra Result containing all ProfileFeeds that specific Hashtag is used in.
     */
    @Query("SELECT * FROM profileFeeds WHERE hashtags CONTAINS :name")
    Result<ProfileFeed> getProfileFeedsUsedIn(String s);

    /**
     * Gets all Hashtags.
     *
     * @return A Cassandra Result containing all Hashtags.
     */
    @Query("SELECT * FROM hashtags")
    Result<Hashtag> getHashtags();
}
