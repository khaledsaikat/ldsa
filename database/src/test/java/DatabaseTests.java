import com.datastax.driver.core.DataType;
import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.db.codecs.OffsetDateTimeCodec;
import de.due.ldsa.db.model.ProfileFeed;
import de.due.ldsa.db.model.SocialNetwork;
import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

/**
 *
 */

public class DatabaseTests
{
    @Test
    public void testSaveSocialNetwork() throws Exception
    {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("socialNetworks");

        SocialNetwork sn = TestUtils.createDummySocialNetwork(db);

        SocialNetwork result = db.getSocialNetwork(0);

        Assert.assertArrayEquals(sn.getLogo().array(),result.getLogo().array());
    }



    @Test
    public void testSaveProfileFeed() throws Exception
    {
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

        Assert.assertEquals(pf.getContentTimestamp(),pf2.getContentTimestamp());
    }
}
