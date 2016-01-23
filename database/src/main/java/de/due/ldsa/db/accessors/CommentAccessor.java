package de.due.ldsa.db.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import de.due.ldsa.model.Comment;

/**
 * Created by  Romina
 */
@Accessor
public interface CommentAccessor {
    @Query("SELECT * FROM ldsa.comments")
    public Result<Comment> getAll();
}
