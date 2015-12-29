import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.db.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 *
 */

public class DatabaseTests
{
    @Test
    public void testSaveSocialNetwork() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");

        SocialNetwork sn = TestUtils.createDummySocialNetwork(db);

        SocialNetwork result = db.getSocialNetwork(0);

        Assert.assertArrayEquals(sn.getLogo().array(),result.getLogo().array());
    }

    @Test
    public void testSaveProfileFeed() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");
        db.truncateTable("profileFeeds");

        SocialNetwork sn = TestUtils.createDummySocialNetwork(db);

        ProfileFeed pf = new ProfileFeed();
        pf.setRawStoryText("bin shoppen :D");
        pf.setProfileId(1);
        pf.setId(1);
        pf.setContentMeta(TestUtils.createRandomDateTime(),TestUtils.createRandomDateTime(),sn);

        ArrayList<String> hashtags = new ArrayList<>();
        hashtags.add("#kommerz");
        hashtags.add("#geld");
        hashtags.add("#frischeluft");
        pf.setHashtags(hashtags);
        db.saveProfileFeed(pf);

        ProfileFeed pf2 = db.getProfileFeed(1);

        Assert.assertEquals(pf.getHashtags(), pf2.getHashtags());
    }

    @Test
    public void testSaveMedia() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");
        db.truncateTable("media");

        SocialNetwork sn = TestUtils.createDummySocialNetwork(db);

        Media first = new Media();
        first.setContentMeta(TestUtils.createRandomDateTime(), TestUtils.createRandomDateTime(), sn);
        first.setBytes(TestUtils.createRandomMedia());
        first.setFilename(TestUtils.randomFilename());
        first.setCrawlingPath("/");
        first.setId(1);
        db.saveMedia(first);

        Media second = db.getMedia(1);
        Assert.assertArrayEquals(first.getBytes(), second.getBytes());
    }

    @Test
    public void testSaveLocation() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");
        db.truncateTable("locations");

        SocialNetwork sn = TestUtils.createDummySocialNetwork(db);

        LocationImpl l = new LocationImpl();
        l.setCity(TestUtils.randomCityName());
        l.setContentMeta(TestUtils.createRandomDateTime(), TestUtils.createRandomDateTime(), sn);
        l.setCountry(TestUtils.randomCountry());
        l.setId(1);
        l.setName(TestUtils.getRandomLocationName());
        l.setPosition(TestUtils.getRandomPosition());

        db.saveLocation(l);

        Location l2 = db.getLocation(1);

        Assert.assertEquals(l.getName(), l2.getName());
    }

    @Test
    public void testSaveOrganisationPlace() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");
        db.truncateTable("organisationPlaces");

        SocialNetwork sn = TestUtils.createDummySocialNetwork(db);

        OrganisationPlace op = new OrganisationPlace();
        op.setCity(TestUtils.randomCityName());
        op.setContentMeta(TestUtils.createRandomDateTime(), TestUtils.createRandomDateTime(), sn);
        op.setCountry(TestUtils.randomCountry());
        op.setId(1);
        op.setIsInId(2);
        op.setName(TestUtils.getRandomLocationName());
        op.setOrganisationProfileId(3);
        op.setPosition(TestUtils.getRandomPosition());

        db.saveOrganisationPlace(op);

        OrganisationPlace second = db.getOrganisationPlace(1);

        Assert.assertEquals(op.getOrganisationProfileId(), second.getOrganisationProfileId());
    }
}
