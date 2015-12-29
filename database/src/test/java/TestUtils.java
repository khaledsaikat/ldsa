import de.due.ldsa.db.Database;
import de.due.ldsa.db.model.Position;
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
        int month = rng.nextInt(10) + 1;
        int day = rng.nextInt(27) + 1;
        int hour = rng.nextInt(23);
        int minutes = rng.nextInt(59);
        int seconds = rng.nextInt(59);
        return OffsetDateTime.of(year,month,day,hour,minutes,seconds,0, ZoneOffset.UTC);
    }

    static Random rng;

    public static byte[] createRandomMedia() {
        int len = rng.nextInt(512);
        byte[] medium = new byte[len];
        rng.nextBytes(medium);
        return medium;
    }

    public static long randomLong() {
        return rng.nextLong();
    }

    public static String randomForename() {
        String[] pool = new String[]{"Justus", "Peter", "Bob", "Kyouma", "Daru", "Kurisu", "Moeka", "Akiha", "Alexandra",
                "Utena", "Himemiya", "Shinichi", "Ran"};

        return pool[rng.nextInt(pool.length)];
    }

    public static String randomSurname() {
        String[] pool = new String[]{"Jonas", "Shaw", "Andrews", "Houounin", "Hashida", "Makise", "Kiryu", "Rumiho", "Kitsune",
                "Tenjo", "Anthy", "Kudo", "Mori"};

        return pool[rng.nextInt(pool.length)];
    }

    public static String randomName() {
        return randomForename() + " " + randomSurname();
    }

    public static String randomFilename() {
        String[] poolA = new String[]{"file", "entry", "sector"};
        String[] poolB = new String[]{".png", ".jpg", ".bmp", ".gif", ".mp4", ".webm", ".mkv"};

        StringBuilder sb = new StringBuilder();
        sb.append(poolA[rng.nextInt(poolA.length)]);
        sb.append(rng.nextInt());
        sb.append(poolB[rng.nextInt(poolA.length)]);
        return sb.toString();
    }

    public static String randomCityName() {
        String[] poolA = new String[]{"Duis", "Mül", "Düssel", "Mün", "Coes", "Reckling", "Bannen", "Zü", "Holds"};
        String[] poolB = new String[]{"burg", "heim", "dorf", "feld", "stadt", "hausen"};
        return poolA[rng.nextInt(poolA.length)] + poolB[rng.nextInt(poolB.length)];
    }

    public static String randomCountry() {
        String[] pool = new String[]{"Germany", "Switzerland", "Austria", "Netherlands", "France", "Japan"};
        return pool[rng.nextInt(pool.length)];
    }

    public static Position getRandomPosition() {
        Position result = new Position();
        result.setLatidue((rng.nextDouble() - 0.5) * 180.0);
        result.setLongitude((rng.nextDouble() - 0.5) * 180.0);
        return result;
    }

    public static String getRandomLocationName() {
        String[] poolA = new String[]{"Pizzeria", "Restaurant", "Music Shop", "Tierhandel", "Bäckerei", "Retro",
                "Aquaristik", "Office Center", "Halber Meter"};
        String[] poolB = new String[]{"Izmir Übel", "Bosniac", "Hondo", "Zayak", "Kamps", "GAMERS", "Peter", "Peters",
                "Erkrath", "Brechmann"};

        return poolA[rng.nextInt(poolA.length)] + poolB[rng.nextInt(poolB.length)];
    }
}
