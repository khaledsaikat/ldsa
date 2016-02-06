import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.model.Comment;
import de.due.ldsa.model.Hashtag;
import de.due.ldsa.model.ProfileFeed;
import de.due.ldsa.model.SocialNetworkContent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  Romina
 */
@Category(AllTestsExceptBenchmark.class)
public class HashtagTests {
    @Test
    public void testGetTimesUsed() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("comments");
        db.truncateTable("profileFeeds");
        db.truncateTable("hashtags");

        int expected = 0;
        for (int i = 0; i < 25; i++) {
            ProfileFeed pf = new ProfileFeed();
            pf.setHashtagNames(TestUtils.getRandomHashtagArrayList());
            pf.setId(db.getNextProfileFeedId());
            db.saveProfileFeed(pf);
            for (String hashtag : pf.getHashtagNames()) {
                db.saveHashtag(new Hashtag(hashtag));
                if (hashtag.equals("#32c3"))
                    expected++;
            }

            Comment c = new Comment();
            c.setHashtagNames(TestUtils.getRandomHashtagArrayList());
            c.setId(db.getNextCommentId());
            db.saveComment(c);
            for (String hashtag : c.getHashtagNames()) {
                db.saveHashtag(new Hashtag(hashtag));
                if (hashtag.equals("#32c3"))
                    expected++;
            }
        }

        int actual = 0;
        List<SocialNetworkContent> snt = db.getHashtagUsedAtList("#32c3");
        for (SocialNetworkContent snc : snt) {
            actual++;
        }
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllHashtags() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("hashtags");

        String[] demoNames = TestUtils.getAllHashtags();
        Hashtag[] first = new Hashtag[demoNames.length];
        for (int i = 0; i < demoNames.length; i++) {
            first[i] = new Hashtag(demoNames[i]);
            db.saveHashtag(first[i]);
        }

        List<Hashtag> secondRaw = db.getAllHashtags();

        //We can't compare the array directly, because a Cassandra query can't guarantee the correct order.
        //Assert.assertArrayEquals(first,secondRaw.toArray());

        for (int i = 0; i < demoNames.length; i++) {
            if (!secondRaw.contains(first[i])) {
                Assert.fail("Could not find " + first[i] + " in the second list.");
            }
        }
    }
}
