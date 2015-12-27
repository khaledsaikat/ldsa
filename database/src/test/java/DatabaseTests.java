import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.db.model.SocialNetwork;
import org.junit.Test;

import java.nio.ByteBuffer;

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

        SocialNetwork sn = new SocialNetwork();
        sn.setHomeURL("http://127.0.0.1");
        sn.setLogo(ByteBuffer.wrap(new byte[] {1,2,3,4,5}));
        sn.setName("Test");

        db.saveSocialNetwork(sn);
    }
}
