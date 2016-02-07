import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.model.CoopProfile;
import de.due.ldsa.model.OrganisationPlace;
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
        db.truncateTable("organisationPlaces");

        CoopProfile cp = new CoopProfile();
        cp.setFullname(TestUtils.getRandomCompanyName());
        cp.setId(db.getNextProfileId());
        cp.setProfileURL(new URL("http://127.0.0.1/what"));
        db.saveCoopProfile(cp);

        OrganisationPlace op = new OrganisationPlace();
        op.setName(TestUtils.getRandomCompanyName());
        op.setId(db.getNextLocationId());
        op.setOrganisationProfileId(cp.getId());
        db.saveOrganisationPlace(op);

        OrganisationPlace op2 = db.getOrganisationPlace(op.getId());
        if (!op2.equals(op)) {
            Assert.fail("Deserialisation of OrganisationPlace failed.");
        }

        CoopProfile cp2 = db.organisationPlaceGetCoopProfile(op2);
        Assert.assertEquals(cp, cp2);
    }
}
