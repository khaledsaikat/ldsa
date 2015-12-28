import de.due.ldsa.db.Database;
import de.due.ldsa.db.model.SocialNetwork;

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Random;

/**
 *
 */
class TestUtils
{
    public static SocialNetwork createDummySocialNetwork(Database db) {
        SocialNetwork sn = new SocialNetwork();
        sn.setHomeURL("http://127.0.0.1");
        sn.setLogo(ByteBuffer.wrap(new byte[] {1,2,3,4,5}));
        sn.setName("Test");

        db.saveSocialNetwork(sn);
        return sn;
    }

    static {
        rng = new Random();
    }

    public static OffsetDateTime createRandomDateTime()
    {
        int year = 1992 + rng.nextInt(23);
        int month = rng.nextInt(11);
        int day = rng.nextInt(28);
        int hour = rng.nextInt(23);
        int minutes = rng.nextInt(59);
        int seconds = rng.nextInt(59);
        return OffsetDateTime.of(year,month,day,hour,minutes,seconds,0, ZoneOffset.UTC);
    }

    static Random rng;

}
