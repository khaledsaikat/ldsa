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
import java.util.ArrayList;

/**
 * Created by  Romina
 */
@Category(AllTestsExceptBenchmark.class)
public class CoopProfileTests {
    /**
     * Supposed to test if coopProfileCountInteraction works as expected
     *
     * @throws Exception
     */
    @Test
    public void testCountInteractions() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("coopProfiles");
        db.truncateTable("humanProfiles");
        db.truncateTable("profileFeeds");
        db.truncateTable("comments");

        HumanProfile natPerson = new HumanProfile();
        natPerson.setId(db.getNextProfileId());
        natPerson.setSex(TestUtils.getRandomSex());
        natPerson.setFullname(TestUtils.getRandomName());
        natPerson.setProfileURL(new URL("http://127.0.0.1"));
        db.saveHumanProfile(natPerson);

        CoopProfile jurPerson = new CoopProfile();
        jurPerson.setId(db.getNextProfileId());
        jurPerson.setProfileURL(new URL("http://127.0.0.1"));
        jurPerson.setFullname(TestUtils.getRandomCompanyName());
        db.saveCoopProfile(jurPerson);

        ProfileFeed pf1 = new ProfileFeed();
        pf1.setId(db.getNextProfileFeedId());
        pf1.setRawStoryText("Feed 1 mit like");
        pf1.setProfileId(jurPerson.getId());
        pf1.setLikerIds(TestUtils.getArrayListFrom(natPerson.getId()));     //First interaction
        db.saveProfileFeed(pf1);

        ProfileFeed pf2 = new ProfileFeed();
        pf2.setId(db.getNextProfileFeedId());
        pf2.setRawStoryText("Feed 2 mit share");
        pf2.setProfileId(jurPerson.getId());
        pf2.setSharerIds(TestUtils.getArrayListFrom(natPerson.getId()));    //Second interaction
        db.saveProfileFeed(pf2);

        ProfileFeed pf3 = new ProfileFeed();
        pf3.setId(db.getNextProfileFeedId());
        pf3.setRawStoryText("Feed 3 mit kommentaren");
        pf3.setProfileId(jurPerson.getId());
        Comment c1 = new Comment();
        c1.setId(db.getNextCommentId());
        c1.setCommenterId(natPerson.getId());                           //Third interaction
        c1.setLikerIds(TestUtils.getArrayListFrom(natPerson.getId()));  //Fourth interaction
        db.saveComment(c1);
        Comment c2 = new Comment();
        c2.setId(db.getNextCommentId());
        c2.setCommenterId(natPerson.getId());                       //Fifth interaction
        c2.setLikerIds(TestUtils.getArrayListFrom(natPerson.getId()));  //Sixth interaction
        db.saveComment(c2);
        c1.setCommentIds(TestUtils.getArrayListFrom(c2.getId()));
        db.saveComment(c1);
        pf3.setCommentIds(TestUtils.getArrayListFrom(c1.getId()));
        db.saveProfileFeed(pf3);

        jurPerson.setFollowedByIds(TestUtils.getArrayListFrom(natPerson.getId()));  //Seventh interaction
        jurPerson.setProfileFeedIds(TestUtils.getArrayListFrom(pf1.getId(), pf2.getId(), pf3.getId()));
        int result = db.coopProfileCountInteraction(jurPerson, natPerson);
        Assert.assertEquals(7, result);
    }
}
