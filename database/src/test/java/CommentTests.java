import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.db.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;

/**
 *
 */
@Category(AllTestsExceptBenchmark.class)
public class CommentTests {
    @Test
    public void testGetMedia() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("comments");
        db.truncateTable("media");

        Media media1 = new Media();
        media1.setBytes(TestUtils.getRandomByteArray());
        media1.setId(db.getNextMediaId());

        Comment parent1 = new Comment();
        parent1.setMedia(media1);
        parent1.setId(db.getNextCommentId());

        db.saveMedia(media1);
        db.saveComment(parent1);

        Comment parent2 = db.getComment(parent1.getId());
        Media media2 = parent2.getMedia();

        Assert.assertEquals(media1, media2);
    }

    @Test
    public void testGetLikers() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("comments");
        db.truncateTable("coopProfiles");
        db.truncateTable("humanProfiles");

        CoopProfile profile1 = new CoopProfile();
        profile1.setUsername("Teewurstversand24.de");
        profile1.setId(db.getNextProfileId());
        db.saveCoopProfile(profile1);

        HumanProfile profile2 = new HumanProfile();
        profile2.setUsername(TestUtils.getRandomUsername());
        profile2.setId(db.getNextProfileId());
        db.saveHumanProfile(profile2);

        HumanProfile profile3 = new HumanProfile();
        profile3.setUsername(TestUtils.getRandomUsername());
        profile3.setId(db.getNextProfileId());
        db.saveHumanProfile(profile3);

        // Test getLiker()
        Comment parent1 = new Comment();
        parent1.setId(db.getNextCommentId());
        parent1.getLiker().add(profile1);
        parent1.getLiker().add(profile2);
        parent1.getLiker().add(profile3);
        db.saveComment(parent1);

        Comment parent2 = db.getComment(parent1.getId());
        Assert.assertArrayEquals(parent1.getLiker().toArray(), parent2.getLiker().toArray());
    }

    @Test
    // You can comment Comments on facebook
    public void testGetSubComments() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("comments");

        Comment sub1 = new Comment();
        sub1.setText("Hey, tolles Video, aber guck mal hier, hier gibt's billig Teewurst.");
        sub1.setId(db.getNextCommentId());
        db.saveComment(sub1);

        Comment sub2 = new Comment();
        sub2.setText("Bestes Känguru ever");
        sub2.setId(db.getNextCommentId());
        db.saveComment(sub2);

        Comment sub3 = new Comment();
        sub3.setText("Genial, wie das Känguru das Klo putzt!");
        sub3.setId(db.getNextCommentId());
        db.saveComment(sub3);

        Comment parent1 = new Comment();
        parent1.getComments().add(sub1);
        parent1.getComments().add(sub2);
        parent1.getComments().add(sub3);
        parent1.setId(db.getNextCommentId());
        parent1.setText("Kleinkünstler versklavt Känguru!");
        db.saveComment(parent1);

        Comment parent2 = db.getComment(parent1.getId());
        Assert.assertArrayEquals(parent1.getComments().toArray(), parent2.getComments().toArray());
    }
}
