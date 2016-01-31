import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.model.Comment;
import de.due.ldsa.model.CoopProfile;
import de.due.ldsa.model.HumanProfile;
import de.due.ldsa.model.ProfileFeed;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.URL;
import java.util.List;
import java.util.Random;

/**
 * Created by  Romina
 */
@Category(AllTestsExceptBenchmark.class)
public class ProfileTests {
    /**
     * Supposed to demonstrate how to fetch profile feeds from a profile
     *
     * @throws Exception
     */
    @Test
    public void testGetProfileProfileFeeds() throws Exception {
        Database db = DatabaseImpl.getInstance();
        Random rng = TestUtils.rng;
        db.truncateTable("humanProfiles");
        db.truncateTable("profileFeeds");

        final int numProfiles = 10;
        final int numProfileFeeds = 25;
        long[] profiles = new long[numProfiles];

        //Generate some Profiles
        for (int i = 0; i < numProfiles; i++) {
            HumanProfile hp = new HumanProfile();
            hp.setId(db.getNextProfileId());
            hp.setSex(TestUtils.getRandomSex());
            hp.setFullname(TestUtils.getRandomName());
            hp.setProfileURL(new URL("http://127.0.0.1"));
            profiles[i] = hp.getId();
            db.saveHumanProfile(hp);
        }

        long profileToCheck = profiles[rng.nextInt(numProfiles)];
        long expected = 0;

        //Generate some ProfileFeeds
        for (int i = 0; i < numProfileFeeds || expected == 0; i++) {
            ProfileFeed pf = new ProfileFeed();
            pf.setId(db.getNextProfileFeedId());
            pf.setProfileId(profiles[rng.nextInt(numProfiles)]);
            if (pf.getProfileId() == profileToCheck) {
                expected++;
            }
            db.saveProfileFeed(pf);
        }

        //Now see if it was correctly saved.
        long actual = 0;
        HumanProfile hp = db.getHumanProfile(profileToCheck);   //1. Fetch the profile we want to check
        hp.setProfileFeedIds(db.getProfileProfileFeeds(hp));    //2. Get it's feeds.
        for (Long l : hp.getProfileFeedIds()) {
            ProfileFeed pf = db.getProfileFeed(l);
            if (pf.getProfileId() == profileToCheck)            //3. ??? (Comparision in this case)
            {
                actual++;
            }
        }

        Assert.assertEquals(expected, actual);                   //4. PROFIT!
    }

    /**
     * Supposed to demonstrate how to fetch all comments made by a profile
     *
     * @throws Exception
     */
    @Test
    public void testGetProfileComments() throws Exception {
        Database db = DatabaseImpl.getInstance();
        Random rng = TestUtils.rng;
        db.truncateTable("coopProfiles");
        db.truncateTable("comments");

        final int numProfiles = 10;
        final int numComments = 25;
        long[] profiles = new long[numProfiles];

        //Generate some Profiles
        for (int i = 0; i < numProfiles; i++) {
            CoopProfile cp = new CoopProfile();
            cp.setId(db.getNextProfileId());
            cp.setFullname(TestUtils.getRandomName());
            cp.setProfileURL(new URL("http://127.0.0.1"));
            profiles[i] = cp.getId();
            db.saveCoopProfile(cp);
        }

        long profileToCheck = profiles[rng.nextInt(numProfiles)];
        long expected = 0;

        //Generate some ProfileFeeds
        for (int i = 0; i < numComments || expected == 0; i++) {
            Comment c = new Comment();
            c.setId(db.getNextCommentId());
            c.setCommenterId(profiles[rng.nextInt(numProfiles)]);
            if (c.getCommenterId() == profileToCheck) {
                expected++;
            }
            db.saveComment(c);
        }

        //Now see if it was correctly saved.
        long actual = 0;
        CoopProfile cp = db.getCoopProfile(profileToCheck);     //1. Fetch the profile we want to check
        cp.setAllCommentsId(db.getProfileAllComments(cp));
        for (Long l : cp.getAllCommentsId()) {
            Comment c = db.getComment(l);
            if (c.getCommenterId() == profileToCheck)            //3. ??? (Comparision in this case)
            {
                actual++;
            }
        }

        Assert.assertEquals(expected, actual);                   //4. PROFIT!
    }
}
