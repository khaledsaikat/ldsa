import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.URL;

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
        hp1.setProfileURL(new URL("http://127.0.0.1"));
        db.autoSaveProfile(hp1);

        HumanProfile hp2 = new HumanProfile();
        hp2.setFullname(TestUtils.getRandomName());
        hp2.setId(db.getNextProfileId());
        hp2.setSex(Sex.MALE);
        hp2.setProfileURL(new URL("http://127.0.0.1"));
        db.autoSaveProfile(hp2);

        int result = 0;
        for (HumanProfile i : db.getAllHumanProfiles()) {
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
        for (Comment i : db.getAllComments()) {
            result++;
        }
        Assert.assertEquals(2, result);
    }

    @Test
    public void testLocationAccessor() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("locations");
        db.truncateTable("organisationPlaces");

        LocationImpl l1 = new LocationImpl();
        l1.setId(db.getNextLocationId());
        l1.setName(TestUtils.getRandomLocationName());
        db.saveLocation(l1);

        LocationImpl l2 = new LocationImpl();
        l2.setId(db.getNextLocationId());
        l2.setName(TestUtils.getRandomLocationName());
        db.saveLocation(l2);

        OrganisationPlace op1 = new OrganisationPlace();
        op1.setId(db.getNextLocationId());
        op1.setName(TestUtils.getRandomLocationName());
        db.saveOrganisationPlace(op1);

        OrganisationPlace op2 = new OrganisationPlace();
        op2.setId(db.getNextLocationId());
        op2.setName(TestUtils.getRandomLocationName());
        db.saveOrganisationPlace(op2);

        int result = 0;
        for (Location l : db.getAllLocations()) {
            result++;
        }
        Assert.assertEquals(result, 4);
    }

    @Test
    public void testProfileFeedAccessor() throws Exception {
        long amount = TestUtils.rng.nextLong() % 25;
        if (amount < 0) amount /= -1;
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("profileFeeds");

        for (long l = 0; l < amount; l++) {
            ProfileFeed child = new ProfileFeed();
            child.setId(db.getNextProfileFeedId());
            child.setRawStoryText(TestUtils.getRandomComment());
            db.saveProfileFeed(child);
        }

        long result = 0;
        for (ProfileFeed pf : db.getAllProfileFeeds()) {
            result++;
        }
        Assert.assertEquals(amount, result);
    }

    @Test
    public void testMediaAccessor() throws Exception {
        long amount = TestUtils.rng.nextLong() % 25;
        if (amount < 0) amount /= -1;
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("media");

        for (long l = 0; l < amount; l++) {
            Media child = new Media();
            child.setId(db.getNextMediaId());
            child.setBytes(TestUtils.getRandomByteArray());
            db.saveMedia(child);
        }

        long result = 0;
        for (Media pf : db.getAllMedia()) {
            result++;
        }
        Assert.assertEquals(amount, result);
    }
}
