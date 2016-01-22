import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.model.CoopProfile;
import de.due.ldsa.model.Hashtag;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.ProfileFeed;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Created by  Romina
 */
@Category(AllTestsExceptBenchmark.class)
public class ProfileFeedTests {
    @Test
    public void testRelatedProfile() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("profileFeeds");
        db.truncateTable("humanProfiles");
        db.truncateTable("coopProfiles");

        HumanProfile hp = new HumanProfile();
        hp.setId(db.getNextProfileId());
        hp.setFullname(TestUtils.getRandomName());
        db.saveHumanProfile(hp);

        ProfileFeed master = new ProfileFeed();
        master.setId(db.getNextProfileFeedId());
        master.setProfile(hp);
        db.saveProfileFeed(master);

        ProfileFeed slave = db.getProfileFeed(master.getId());
        HumanProfile hpSlave = (HumanProfile) slave.getProfile();
        Assert.assertEquals(hp, hpSlave);
    }

    @Test
    public void testSaveLikers() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("humanProfiles");
        db.truncateTable("profileFeeds");

        HumanProfile hp1 = new HumanProfile();
        hp1.setId(db.getNextProfileId());
        hp1.setFullname(TestUtils.getRandomName());
        db.saveHumanProfile(hp1);

        HumanProfile hp2 = new HumanProfile();
        hp2.setId(db.getNextProfileId());
        hp2.setFullname(TestUtils.getRandomName());
        db.saveHumanProfile(hp2);

        HumanProfile hp3 = new HumanProfile();
        hp3.setId(db.getNextProfileId());
        hp3.setFullname(TestUtils.getRandomName());
        db.saveHumanProfile(hp3);

        ProfileFeed master = new ProfileFeed();
        master.getLiker().add(hp1);
        master.getLiker().add(hp2);
        master.getLiker().add(hp3);
        master.setId(db.getNextProfileFeedId());
        db.saveProfileFeed(master);

        ProfileFeed slave = db.getProfileFeed(master.getId());
        Assert.assertArrayEquals(master.getLiker().toArray(), slave.getLiker().toArray());
    }

    @Test
    public void testSaveSharers() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("profileFeeds");
        db.truncateTable("humanProfiles");
        db.truncateTable("coopProfiles");

        ProfileFeed master = new ProfileFeed();
        master.setId(db.getNextProfileFeedId());
        for (int i = 0; i < 5; i++) {
            CoopProfile cp = new CoopProfile();
            cp.setFullname(TestUtils.getRandomCompanyName());
            cp.setId(db.getNextProfileId());
            db.autoSaveProfile(cp);
            master.getSharers().add(cp);
        }
        db.saveProfileFeed(master);

        ProfileFeed slave = db.getProfileFeed(master.getId());
        Assert.assertArrayEquals(master.getSharers().toArray(), slave.getSharers().toArray());
    }

    @Test
    public void testSaveHashtags() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("profileFeeds");

        ProfileFeed master = new ProfileFeed();
        master.getHashtags().add(new Hashtag("#gollum"));
        master.getHashtags().add(new Hashtag("#unblockTattoofrei"));
        master.getHashtags().add(new Hashtag("#freeTorben"));
        master.setId(db.getNextProfileFeedId());
        db.saveProfileFeed(master);

        ProfileFeed slave = db.getProfileFeed(master.getId());
        Assert.assertArrayEquals(master.getHashtags().toArray(), slave.getHashtags().toArray());
    }
}
