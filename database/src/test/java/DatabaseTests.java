import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.URL;
import java.util.ArrayList;

/**
 * Author: Romina (scrobart)
 *
 * Tests basic read/write operations in the database.
 */
@Category(AllTestsExceptBenchmark.class)
public class DatabaseTests
{
    @Test
    public void testSaveSocialNetwork() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");

        SocialNetwork sn = TestUtils.getDummySocialNetwork(db);

        SocialNetwork result = db.getSocialNetwork(0);

        Assert.assertEquals(sn, result);
    }

    @Test
    public void testSaveProfileFeed() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");
        db.truncateTable("profileFeeds");

        SocialNetwork sn = TestUtils.getDummySocialNetwork(db);

        ProfileFeed pf = new ProfileFeed();
        pf.setRawStoryText("bin shoppen :D");
        pf.setProfileId(1);
        pf.setId(1);
        pf.setContentMeta(TestUtils.getRandomDateTime(), TestUtils.getRandomDateTime(), sn);

        ArrayList<String> hashtags = new ArrayList<>();
        hashtags.add("#kommerz");
        hashtags.add("#geld");
        hashtags.add("#frischeluft");
        pf.setHashtagNames(hashtags);
        db.saveProfileFeed(pf);

        ProfileFeed pf2 = db.getProfileFeed(1);

        Assert.assertEquals(pf, pf2);
    }

    @Test
    public void testSaveMedia() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");
        db.truncateTable("media");

        SocialNetwork sn = TestUtils.getDummySocialNetwork(db);

        Media first = new Media();
        first.setContentMeta(TestUtils.getRandomDateTime(), TestUtils.getRandomDateTime(), sn);
        first.setBytes(TestUtils.getRandomByteArray());
        first.setFilename(TestUtils.getRandomFilename());
        first.setCrawlingPath("/"); // found in the model BWD-37
        first.setId(1);
        db.saveMedia(first);

        Media second = db.getMedia(1);
        Assert.assertEquals(first, second);
    }

    @Test
    public void testSaveLocation() throws Exception {

        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");
        db.truncateTable("locations");

        SocialNetwork sn = TestUtils.getDummySocialNetwork(db);

        LocationImpl l = new LocationImpl();
        l.setCity(TestUtils.getRandomCityName());
        l.setContentMeta(TestUtils.getRandomDateTime(), TestUtils.getRandomDateTime(), sn);
        l.setCountry(TestUtils.getRandomCountry());
        l.setId(1);
        l.setName(TestUtils.getRandomLocationName());
        l.setPosition(TestUtils.getRandomPosition());

        db.saveLocation(l);

        Location l2 = db.getLocation(1);

        Assert.assertEquals(l, l2);
    }

    @Test
    public void testSaveOrganisationPlace() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");
        db.truncateTable("organisationPlaces");

        SocialNetwork sn = TestUtils.getDummySocialNetwork(db);

        OrganisationPlace op = new OrganisationPlace();
        op.setCity(TestUtils.getRandomCityName());
        op.setContentMeta(TestUtils.getRandomDateTime(), TestUtils.getRandomDateTime(), sn);
        op.setCountry(TestUtils.getRandomCountry());
        op.setId(1);
        op.setIsInId(2);
        op.setName(TestUtils.getRandomLocationName());
        op.setOrganisationProfileId(3);
        op.setPosition(TestUtils.getRandomPosition());

        db.saveOrganisationPlace(op);

        OrganisationPlace second = db.getOrganisationPlace(1);

        Assert.assertEquals(op, second);
    }

    @Test
    public void testSaveCoopProfile() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");
        db.truncateTable("coopProfiles");

        SocialNetwork sn = TestUtils.getDummySocialNetwork(db);
        CoopProfile first = new CoopProfile();
        first.setAttendingEventIds(TestUtils.getRandomArrayList());
        first.setBio("A company that manufactures everything.");
        first.setContentMeta(TestUtils.getRandomDateTime(), TestUtils.getRandomDateTime(), sn);
        first.setDateFounded(TestUtils.getRandomLocalDate());
        first.setFollowedByIds(TestUtils.getRandomArrayList());
        first.setFriendIds(TestUtils.getRandomArrayList());
        first.setFullname("ACME");
        first.setHometownLocationId(1);
        first.setId(2);
        first.setInterestIds(TestUtils.getRandomArrayList());
        first.setLastUpdateProfileFeedId(3);
        first.setLinkedOtherSocialNetworkProfileIds(TestUtils.getRandomArrayList());
        first.setProfileFeedIds(TestUtils.getRandomArrayList());
        first.setProfilePhotoMediaId(4);
        first.setProfileURL(new URL("http://127.0.0.1/profile.php?id=5"));
        first.setSocialNetworkId(0);
        first.setUserEmail("somebody@somewhere.moe");
        first.setUsername("somebody");
        first.setUserWebsite("http://www.somewhere.moe");

        db.saveCoopProfile(first);

        CoopProfile second = db.getCoopProfile(2);
        Assert.assertEquals(first, second);

    }

    @Test
    public void testSaveHumanProfile() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");
        db.truncateTable("humanProfiles");
        db.truncateTable("coopProfiles");

        SocialNetwork sn = TestUtils.getDummySocialNetwork(db);

        HumanProfile hp = new HumanProfile();
        hp.setAttendingEventIds(TestUtils.getRandomArrayList());
        hp.setBio("Hi. I'm a regular person.");
        hp.setBirthday(TestUtils.getRandomLocalDate());
        hp.setContentMeta(TestUtils.getRandomDateTime(), TestUtils.getRandomDateTime(), sn);
        hp.setFollowedByIds(TestUtils.getRandomArrayList());
        hp.setFollowsIds(TestUtils.getRandomArrayList());
        hp.setFriendIds(TestUtils.getRandomArrayList());
        hp.setFullname(TestUtils.getRandomName());
        hp.setHometownLocationId(TestUtils.getRandomLong());
        hp.setId(1);
        hp.setInterestIds(TestUtils.getRandomArrayList());
        hp.setLastUpdateProfileFeedId(TestUtils.getRandomLong());
        hp.setLinkedOtherSocialNetworkProfileIds(TestUtils.getRandomArrayList());
        hp.setProfileFeedIds(TestUtils.getRandomArrayList());
        hp.setProfilePhotoMediaId(TestUtils.getRandomLong());
        hp.setProfileURL(new URL("http://9.0.0.1/what.ever"));
        hp.setRelationshipPersons(TestUtils.getRandomArrayList());
        hp.setRelationshipStatus(TestUtils.getRandomRelationshipStatus());
        hp.setSex(TestUtils.getRandomSex());
        hp.setUserEmail("some@du.de");
        hp.setUsername(TestUtils.getRandomUsername());
        hp.setUserWebsite("http://endoftheinternet.com");

        db.saveHumanProfile(hp);

        HumanProfile second = db.getHumanProfile(1);
        Assert.assertEquals(hp, second);
    }

    @Test
    public void testSaveEvent() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");
        db.truncateTable("events");

        SocialNetwork sn = TestUtils.getDummySocialNetwork(db);

        Event first = new Event();
        first.setAttendingIds(TestUtils.getRandomArrayList());
        first.setContentMeta(TestUtils.getRandomDateTime(), TestUtils.getRandomDateTime(), sn);
        first.setEventText("We meet to play some CAVE games.");
        first.setHostIds(TestUtils.getRandomArrayList());
        first.setId(3);
        first.setInvitedIds(TestUtils.getRandomArrayList());
        first.setLocationId(2);
        first.setName("CAVE Matsuri");
        first.setSocialNetworkId(sn.getId());

        db.saveEvent(first);

        Event second = db.getEvent(3);
        Assert.assertEquals(first, second);
    }

    @Test
    public void testSaveComment() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");
        db.truncateTable("comments");

        SocialNetwork sn = TestUtils.getDummySocialNetwork(db);

        Comment first = new Comment();
        first.setCommenterId(1);
        first.setContentMeta(TestUtils.getRandomDateTime(), TestUtils.getRandomDateTime(), sn);
        first.setHashtagNames(TestUtils.getRandomHashtagArrayList());
        first.setId(2);
        first.setLikerIds(TestUtils.getRandomArrayList());
        first.setMediaId(3);
        first.setText("I don't know how to comment this.");

        db.saveComment(first);

        Comment c = db.getComment(2);
        Assert.assertEquals(first, c);
    }

    @Test
    public void testNullIfNonExistant() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");

        SocialNetwork sn = db.getSocialNetwork(9001);
        Assert.assertEquals(sn, null);
    }

    @Test
    public void testWriteTenProfiles() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("humanProfiles");
        db.truncateTable("coopProfiles");
        for (int i = 0; i < 10; i++) {
            HumanProfile hp = new HumanProfile();
            hp.setId(i);
            hp.setSex(TestUtils.getRandomSex());
            hp.setFullname(TestUtils.getRandomName());
            hp.setProfileURL(new URL(String.format("http://127.0.0.1/whatever.php?id=%s", Integer.toString(i))));
            db.saveHumanProfile(hp);

            HumanProfile hp2 = db.getHumanProfile(i);
            if (!hp.equals(hp2)) {
                Assert.assertEquals(hp, hp2);    //Yes, we know the test should fail, but we do assertEquals here
                //anyway, so we can see the differences in IntelliJ ;)
            }
        }
    }

    @Test
    public void testProfileIdSequenceGenerator() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("humanProfiles");
        db.truncateTable("coopProfiles");
        db.truncateTable("locations");
        db.truncateTable("events");
        db.truncateTable("organisationPlaces");

        long nId = db.getNextProfileId();
        if (nId != 1) Assert.fail("First ID should be 1 if there is nothing in the database.");
        HumanProfile hp = new HumanProfile();
        hp.setId(nId);
        db.saveHumanProfile(hp);

        nId = db.getNextProfileId();
        if (nId != 2) Assert.fail("The Profile ID generator failed.");
        CoopProfile cp = new CoopProfile();
        cp.setId(nId);
        db.saveCoopProfile(cp);

        Assert.assertEquals(db.getNextProfileId(), 3);
    }

    @Test
    public void testUpdateRow() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("profileFeeds");

        ProfileFeed pf = new ProfileFeed();
        pf.setId(db.getNextProfileFeedId());
        pf.setRawStoryText("Bald Prüfung...");
        db.saveProfileFeed(pf);

        pf.setRawStoryText("Yay, bestanden! :)");
        db.saveProfileFeed(pf);
    }
}
