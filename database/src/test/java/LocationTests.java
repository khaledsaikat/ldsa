import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;
import de.due.ldsa.model.Event;
import de.due.ldsa.model.Location;
import de.due.ldsa.model.LocationImpl;
import de.due.ldsa.model.OrganisationPlace;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Random;

/**
 * Created by  Romina
 */
@Category(AllTestsExceptBenchmark.class)
public class LocationTests {
    @Test
    public void testTimesUsed() throws Exception {
        final int locationCount = 5;
        final int eventCount = 30;
        long[] locationIds = new long[locationCount * 2];

        Database db = DatabaseImpl.getInstance();
        Random rng = TestUtils.rng;
        db.truncateTable("events");
        db.truncateTable("locations");
        db.truncateTable("organisationPlaces");

        for (int i = 0; i < locationCount; i++) {
            LocationImpl li = new LocationImpl();
            li.setId(db.getNextLocationId());
            li.setName(TestUtils.getRandomCityName());
            db.saveLocation(li);

            OrganisationPlace op = new OrganisationPlace();
            op.setId(db.getNextLocationId());
            op.setName(TestUtils.getRandomLocationName());
            db.saveOrganisationPlace(op);

            locationIds[i * 2 + 0] = li.getId();
            locationIds[i * 2 + 1] = op.getId();
        }

        long locationToCheck = locationIds[rng.nextInt(locationIds.length)];
        int expected = 0;
        for (int i = 0; i < eventCount; i++) {
            Event e = new Event();
            e.setId(db.getNextEventId());
            e.setName(TestUtils.getRandomEventName());
            e.setLocationId(locationIds[rng.nextInt(locationIds.length)]);
            if (e.getLocationId() == locationToCheck) {
                expected++;
            }
            db.saveEvent(e);
        }

        Location l = db.autoGetLocation(locationToCheck);   //1. Get the location.
        long result = db.locationTimesUsed(l);              //2. How often was the location used?
        //3. ???
        Assert.assertEquals(expected, result);               //4. PROFIT
    }
}
