import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.model.Comment;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.Sex;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Created by  Romina
 */
@Category(AllTestsExceptBenchmark.class)
public class AccessorTests {
    @Test
    public void testHumanProfileAccessor()
            throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("humanProfiles");

        HumanProfile hp1 = new HumanProfile();
        hp1.setFullname(TestUtils.getRandomName());
        hp1.setId(db.getNextProfileId());
        hp1.setSex(Sex.FEMALE);
        db.autoSaveProfile(hp1);

        HumanProfile hp2 = new HumanProfile();
        hp2.setFullname(TestUtils.getRandomName());
        hp2.setId(db.getNextProfileId());
        hp2.setSex(Sex.MALE);
        db.autoSaveProfile(hp2);

        int result = 0;
        for (HumanProfile i : db.getAllHumanProfilesAsIterable()) {
            result++;
        }
        Assert.assertEquals(2, result);
    }

    @Test
    public void testCommentAccessor() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("comments");

        Comment c1 = new Comment();
        c1.setText(TestUtils.getRandomComment());
        c1.setId(db.getNextCommentId());
        db.saveComment(c1);

        Comment c2 = new Comment();
        c2.setText(TestUtils.getRandomComment());
        c2.setId(db.getNextCommentId());
        db.saveComment(c2);

        int result = 0;
        for (Comment i : db.getAllCommentsAsIterable()) {
            result++;
        }
        Assert.assertEquals(2, result);
    }
}
