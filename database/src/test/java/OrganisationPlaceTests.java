import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.model.CoopLocation;
import de.due.ldsa.model.CoopProfile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.URL;

/**
 * Created by  Romina
 */
@Category(AllTestsExceptBenchmark.class)
public class OrganisationPlaceTests {
    @Test
    public void testGetCoopProfile() throws Exception {
        Database db = DatabaseImpl.getInstance();
        db.truncateTable("coopProfiles");
        db.truncateTable("coopLocations");

        CoopProfile cp = new CoopProfile();
        cp.setFullname(TestUtils.getRandomCompanyName());
        cp.setId(db.getNextProfileId());
        cp.setProfileURL(new URL("http://127.0.0.1/what"));
        db.saveCoopProfile(cp);

        CoopLocation op = new CoopLocation();
        op.setName(TestUtils.getRandomCompanyName());
        op.setId(db.getNextLocationId());
        op.setOrganisationProfileId(cp.getId());
        db.saveCoopLocation(op);

        CoopLocation op2 = db.getCoopPlace(op.getId());
        if (!op2.equals(op)) {
            Assert.fail("Deserialisation of CoopLocation failed.");
        }

        CoopProfile cp2 = db.coopLocationGetCoopProfile(op2);
        Assert.assertEquals(cp, cp2);
    }
}
