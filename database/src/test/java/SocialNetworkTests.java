import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.model.*;
import org.junit.*;
import org.junit.experimental.categories.Category;

import java.net.URL;

/**
 * Created by  Romina
 */
@Category(AllTestsExceptBenchmark.class)
public class SocialNetworkTests {
    /**
     * Supposed to test Accessors with a parameter.
     *
     * @throws Exception
     */
    @Test
    public void testGetAllProfiles() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("humanProfiles");
        db.truncateTable("coopProfiles");

        HumanProfile hp1 = new HumanProfile();
        hp1.setId(db.getNextProfileId());
        hp1.setFullname(TestUtils.getRandomName());
        hp1.setSocialNetworkId(5);
        hp1.setProfileURL(new URL("http://127.0.0.1"));
        hp1.setSex(TestUtils.getRandomSex());
        db.autoSaveProfile(hp1);

        HumanProfile hp2 = new HumanProfile();
        hp2.setId(db.getNextProfileId());
        hp2.setFullname(TestUtils.getRandomName());
        hp2.setSocialNetworkId(3);
        hp2.setProfileURL(new URL("http://127.0.0.1"));
        hp2.setSex(TestUtils.getRandomSex());
        db.autoSaveProfile(hp2);

        CoopProfile cp1 = new CoopProfile();
        cp1.setId(db.getNextProfileId());
        cp1.setFullname(TestUtils.getRandomCompanyName());
        cp1.setSocialNetworkId(5);
        cp1.setProfileURL(new URL("http://127.0.0.1"));
        db.autoSaveProfile(cp1);

        CoopProfile cp2 = new CoopProfile();
        cp2.setId(db.getNextProfileId());
        cp2.setFullname(TestUtils.getRandomCompanyName());
        cp2.setSocialNetworkId(3);
        cp2.setProfileURL(new URL("http://127.0.0.1"));
        db.autoSaveProfile(cp2);

        int result = 0;
        for (Profile p : db.getAllProfilesFromSocialNetwork(3)) {
            result++;
        }
        Assert.assertEquals(2, result);
    }

    @Test
    public void testGetAllProfileFeedsFromSocialNetwork() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("profileFeeds");

        int expectation = 0;
        for (int i = 0; i < 100; i++) {
            ProfileFeed pf1 = new ProfileFeed();
            pf1.setId(db.getNextProfileFeedId());
            pf1.setSocialNetworkId(TestUtils.rng.nextInt(10));
            pf1.setRawStoryText(TestUtils.getRandomComment());
            if (pf1.getSocialNetworkId() == 1) expectation++;
            db.saveProfileFeed(pf1);
        }

        int actual = 0;
        for (ProfileFeed pf : db.getAllProfileFeedsFromSocialNetwork(1)) {
            actual++;
        }
        Assert.assertEquals(expectation, actual);
    }

    @Test
    public void testGetAllMediaFromSocialNetwork() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("media");

        int expectation = 0;
        for (int i = 0; i < 100; i++) {
            Media m = new Media();
            m.setId(db.getNextMediaId());
            m.setSocialNetworkId(TestUtils.rng.nextInt(10));
            m.setBytes(TestUtils.getRandomByteArray());
            if (m.getSocialNetworkId() == 2) expectation++;
            db.saveMedia(m);
        }

        int actual = 0;
        for (Media m : db.getAllMediaFromSocialNetwork(2)) {
            actual++;
        }
        Assert.assertEquals(expectation, actual);
    }

    @Test
    public void testGetAllContent() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("events");
        db.truncateTable("locations");
        db.truncateTable("coopLocations");
        db.truncateTable("coopProfiles");
        db.truncateTable("humanProfiles");
        db.truncateTable("profileFeeds");
        db.truncateTable("comments");
        db.truncateTable("media");
        final int targetSn = 4;

        int expected = 0;
        for (int i = 0; i < 100; i++) {
            //
            Event e = new Event();
            e.setId(db.getNextEventId());
            e.setSocialNetworkId(TestUtils.rng.nextInt(10));
            if (e.getSocialNetworkId() == targetSn) expected++;
            db.saveEvent(e);

            //
            LocationImpl li = new LocationImpl();
            li.setId(db.getNextLocationId());
            li.setSocialNetworkId(TestUtils.rng.nextInt(10));
            if (li.getSocialNetworkId() == targetSn) expected++;
            db.saveLocation(li);

            //
            CoopLocation op = new CoopLocation();
            op.setId(db.getNextLocationId());
            op.setSocialNetworkId(TestUtils.rng.nextInt(10));
            if (op.getSocialNetworkId() == targetSn) expected++;
            db.saveCoopLocation(op);

            //
            HumanProfile hp = new HumanProfile();
            hp.setId(db.getNextProfileId());
            hp.setSocialNetworkId(TestUtils.rng.nextInt(10));
            hp.setSex(TestUtils.getRandomSex());
            hp.setProfileURL(new URL("http://127.0.0.1"));
            if (hp.getSocialNetworkId() == targetSn) expected++;
            db.saveHumanProfile(hp);

            //
            CoopProfile cp = new CoopProfile();
            cp.setId(db.getNextProfileId());
            cp.setSocialNetworkId(TestUtils.rng.nextInt(10));
            cp.setProfileURL(new URL("http://127.0.0.1"));
            if (cp.getSocialNetworkId() == targetSn) expected++;
            db.saveCoopProfile(cp);

            //
            ProfileFeed pf = new ProfileFeed();
            pf.setId(db.getNextProfileFeedId());
            pf.setSocialNetworkId(TestUtils.rng.nextInt(10));
            if (pf.getSocialNetworkId() == targetSn) expected++;
            db.saveProfileFeed(pf);

            //
            Comment c = new Comment();
            c.setId(db.getNextCommentId());
            c.setSocialNetworkId(TestUtils.rng.nextInt(10));
            if (c.getSocialNetworkId() == targetSn) expected++;
            db.saveComment(c);

            //
            Media m = new Media();
            m.setId(db.getNextMediaId());
            m.setSocialNetworkId(TestUtils.rng.nextInt(10));
            if (m.getSocialNetworkId() == targetSn) expected++;
            db.saveMedia(m);
        }

        int actual = 0;
        for (SocialNetworkContent snc : db.getAllContentFromSocialNetwork(targetSn)) {
            actual++;
        }

        Assert.assertEquals(expected, actual);
    }
}
