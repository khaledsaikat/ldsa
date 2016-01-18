import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.db.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Author: Romina (scrobart)
 *
 * Tests all the fancy methods in Profile
 */
@Category(AllTestsExceptBenchmark.class)
public class ProfileTests {
    @Test
    public void testTestSaveInterest() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");
        db.truncateTable("coopProfiles");
        db.truncateTable("humanProfiles");
        db.truncateTable("interests");

        SocialNetwork sn = TestUtils.getDummySocialNetwork(db);

        SocialNetworkInterestImpl interestA = new SocialNetworkInterestImpl();
        interestA.setId(db.getNextInterestId());
        interestA.addInterestKind(InterestKind.ALC_DRINK);
        db.saveInterest(interestA);

        SocialNetworkInterestImpl interestB = new SocialNetworkInterestImpl();
        interestB.setId(db.getNextInterestId());
        interestB.addInterestKind(InterestKind.BAR);
        db.saveInterest(interestB);

        CoopProfile cp1 = new CoopProfile();
        cp1.setSocialNetworkId(sn.getId());
        cp1.setId(db.getNextProfileId());
        cp1.getInterests().add(interestA);
        cp1.getInterests().add(interestB);
        db.saveCoopProfile(cp1);

        HumanProfile hp1 = new HumanProfile();
        hp1.setSocialNetworkId(sn.getId());
        hp1.setId(db.getNextProfileId());
        hp1.getInterests().add(interestA);
        hp1.getInterests().add(interestB);
        db.saveHumanProfile(hp1);

        CoopProfile cp2 = db.getCoopProfile(cp1.getId());
        HumanProfile hp2 = db.getHumanProfile(hp1.getId());

        if (!cp1.getInterests().equals(cp2.getInterests()) || !hp1.getInterests().equals(hp2.getInterests())) {
            Assert.fail("Restored objects from Database do not match =(");
        }
    }

    @Test
    public void testSaveProfilePhoto() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");
        db.truncateTable("humanProfiles");
        db.truncateTable("coopProfiles");
        db.truncateTable("media");

        SocialNetwork sn = TestUtils.getDummySocialNetwork(db);

        Media m1 = new Media();
        m1.setBytes(TestUtils.getRandomByteArray());
        m1.setSocialNetworkId(sn.getId());
        m1.setId(db.getNextMediaId());
        db.saveMedia(m1);

        HumanProfile hp1 = new HumanProfile();
        hp1.setProfilePhoto(m1);
        hp1.setSocialNetworkId(sn.getId());
        hp1.setId(db.getNextProfileId());
        db.saveHumanProfile(hp1);

        HumanProfile hp2 = db.getHumanProfile(hp1.getId());

        Assert.assertEquals(hp1.getProfilePhoto(), hp2.getProfilePhoto());
    }

    @Test
    public void testSaveLastUpdate() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");
        db.truncateTable("humanProfiles");
        db.truncateTable("coopProfiles");
        db.truncateTable("profileFeeds");

        SocialNetwork sn = TestUtils.getDummySocialNetwork(db);

        ProfileFeed pf1 = new ProfileFeed();
        pf1.setSocialNetworkId(sn.getId());
        pf1.setRawStoryText("Ich wohne in " + TestUtils.getRandomCityName());
        pf1.setId(db.getNextProfileFeedId());
        db.saveProfileFeed(pf1);

        HumanProfile hp1 = new HumanProfile();
        hp1.setSocialNetworkId(sn.getId());
        hp1.setLastUpdate(pf1);
        hp1.setId(db.getNextProfileId());
        db.saveHumanProfile(hp1);

        HumanProfile hp2 = db.getHumanProfile(hp1.getId());

        Assert.assertEquals(hp1.getLastUpdate(), hp2.getLastUpdate());
    }

    @Test
    public void testSaveHomeTown() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");
        db.truncateTable("humanProfiles");
        db.truncateTable("coopProfiles");
        db.truncateTable("locations");

        SocialNetwork sn = TestUtils.getDummySocialNetwork(db);

        LocationImpl l1 = new LocationImpl();
        l1.setCity(TestUtils.getRandomCityName());
        l1.setSocialNetworkId(sn.getId());
        l1.setId(db.getNextLocationId());
        l1.setName(l1.getCity());
        db.saveLocation(l1);

        HumanProfile hp1 = new HumanProfile();
        hp1.setId(db.getNextProfileId());
        hp1.setHomeTown(l1);
        db.saveHumanProfile(hp1);

        HumanProfile hp2 = db.getHumanProfile(hp1.getId());

        Assert.assertEquals(hp1.getHomeTown(), hp2.getHomeTown());
    }

    @Test
    public void testSaveFollows() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");
        db.truncateTable("humanProfiles");
        db.truncateTable("coopProfiles");
        db.truncateTable("locations");

        SocialNetwork sn = TestUtils.getDummySocialNetwork(db);

        HumanProfile follow1 = new HumanProfile();
        follow1.setSocialNetworkId(sn.getId());
        follow1.setId(db.getNextProfileId());
        follow1.setFullname(TestUtils.getRandomName());
        db.saveHumanProfile(follow1);

        CoopProfile follow2 = new CoopProfile();
        follow2.setSocialNetworkId(sn.getId());
        follow2.setId(db.getNextProfileId());
        follow2.setFullname(TestUtils.getRandomLocationName());
        db.saveCoopProfile(follow2);

        HumanProfile parentA = new HumanProfile();
        parentA.setSocialNetworkId(sn.getId());
        parentA.setId(db.getNextProfileId());
        parentA.getFollows().add(follow1);
        parentA.getFollows().add(follow2);
        parentA.setFullname(TestUtils.getRandomName());
        db.saveHumanProfile(parentA);

        HumanProfile parentB = db.getHumanProfile(parentA.getId());

        Assert.assertArrayEquals(parentA.getFollows().toArray(), parentB.getFollows().toArray());
    }
}
