package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.Comment;

/**
 * @author scrobart
 * Provides an interface for accessing Comments.
 * Normally you do not need to implement this interface. The Mapper will generate a class at run-time.
 */
@Accessor
public interface CommentAccessor {
    /**
     * Gets all comments from all social networks.
     *
     * @return A Cassandra Result containing all Comments
     */
    @Query("SELECT * FROM ldsa.comments")
    Result<Comment> getAll();

    /**
     * Gets all comments from a specific social network.
     *
     * @param snId The social Network you want to get the comments from.
     * @return A Cassandra Result containing all comments from that specific social network.
     */
    @Query("SELECT * FROM ldsa.comments WHERE snId = :id")
    Result<Comment> getAllFromSocialNetwork(int snId);
}
