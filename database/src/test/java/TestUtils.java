import de.due.ldsa.db.Database;
import de.due.ldsa.model.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Random;

/**
 * Author: Romina (scrobart)
 *
 * Various utilities that generate dummy data for the tests.
 */
class TestUtils
{
    public static SocialNetwork getDummySocialNetwork(Database db)
            throws MalformedURLException {
        SocialNetwork sn = new SocialNetwork();
        sn.setHomeURL(new URL("http://127.0.0.1"));
        sn.setLogo(new byte[]{1, 2, 3, 4, 5});
        sn.setName("Test");

        db.saveSocialNetwork(sn);
        return sn;
    }

    static {
        rng = new Random();
    }

    public static OffsetDateTime getRandomDateTime()
    {
        int year = 1992 + rng.nextInt(23);
        int month = rng.nextInt(10) + 1;
        int day = rng.nextInt(27) + 1;
        int hour = rng.nextInt(23);
        int minutes = rng.nextInt(59);
        int seconds = rng.nextInt(59);
        return OffsetDateTime.of(year,month,day,hour,minutes,seconds,0, ZoneOffset.UTC);
    }

    public static LocalDate getRandomLocalDate() {
        int year = 1992 + rng.nextInt(23);
        int month = rng.nextInt(10) + 1;
        int day = rng.nextInt(27) + 1;
        return LocalDate.of(year, month, day);
    }

    static Random rng;

    public static byte[] getRandomByteArray() {
        int len = rng.nextInt(512);
        byte[] medium = new byte[len];
        rng.nextBytes(medium);
        return medium;
    }

    public static long getRandomLong() {
        return rng.nextLong();
    }

    public static String getRandomForename() {
        String[] pool = new String[]{"Justus", "Peter", "Bob", "Kyouma", "Daru", "Kurisu", "Moeka", "Akiha", "Alexandra",
                "Utena", "Himemiya", "Shinichi", "Ran"};

        return pool[rng.nextInt(pool.length)];
    }

    public static String getRandomSurname() {
        String[] pool = new String[]{"Jonas", "Shaw", "Andrews", "Houounin", "Hashida", "Makise", "Kiryu", "Rumiho", "Kitsune",
                "Tenjo", "Anthy", "Kudo", "Mori"};

        return pool[rng.nextInt(pool.length)];
    }

    public static String getRandomName() {
        return getRandomForename() + " " + getRandomSurname();
    }

    public static String getRandomFilename() {
        String[] poolA = new String[]{"file", "entry", "sector", "blob", "image"};
        String[] poolB = new String[]{".png", ".jpg", ".bmp", ".gif", ".mp4", ".webm", ".mkv"};

        StringBuilder sb = new StringBuilder();
        sb.append(poolA[rng.nextInt(poolA.length)]);
        sb.append(rng.nextInt());
        sb.append(poolB[rng.nextInt(poolA.length)]);
        return sb.toString();
    }

    public static String getRandomCityName() {
        String[] poolA = new String[]{"Duis", "Mül", "Düssel", "Mün", "Coes", "Reckling", "Bannen", "Zü", "Holds"};
        String[] poolB = new String[]{"burg", "heim", "dorf", "feld", "stadt", "hausen"};
        return poolA[rng.nextInt(poolA.length)] + poolB[rng.nextInt(poolB.length)];
    }

    public static String getRandomCountry() {
        String[] pool = new String[]{"Germany", "Switzerland", "Austria", "Netherlands", "France", "Japan", "India"};
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

    public static ArrayList<Long> getRandomArrayList() {
        ArrayList<Long> result = new ArrayList<>();
        int amount = rng.nextInt(10) + 1;
        for (int i = 0; i < amount; i++) {
            result.add(new Long(rng.nextInt(100)));
        }
        return result;
    }

    public static RelationshipStatus getRandomRelationshipStatus() {
        RelationshipStatus[] values = RelationshipStatus.values();

        return values[rng.nextInt(values.length)];
    }

    public static Sex getRandomSex() {
        return rng.nextInt(100) > 50 ? Sex.FEMALE : Sex.MALE;
    }

    public static String getRandomUsername() {
        String[] poolA = new String[]{"Puma", "Dark", "Troll", "Light", "Fire", "Master", "Meme", "Fresh ", "Stick"};
        String[] poolB = new String[]{"Lord", "Master", "Galaxy", "Warrior", "Knight"};

        return poolA[rng.nextInt(poolA.length)] + poolB[rng.nextInt(poolB.length)] + new Integer(rng.nextInt(100)).toString();
    }

    //Googled for trending hashtags to come up with these.
    static String[] hashtagPool = new String[]
            {"#DoItLikeDeMaiziere", "#Weihnachten", "#32c3", "#berlin", "#kalt", "#germany",
                    "#follow", "#instapic", "#deutschland", "#wether", "#enjoy", "#love", "#tourist", "#goodlife", "#traveling",
                    "#blond", "#blonde", "#berlinermauer", "#trip", "#sightseeing", "#winter", "#travel", "#christmastime",
                    "#amazing", "#czechgirl", "#amerika", "#england", "#portugal", "#frankreich", "#heilbronn", "#italien",
                    "#nürnberg", "#brasilien"};

    public static String getRandomHashtag() {
        return hashtagPool[rng.nextInt(hashtagPool.length)];
    }

    public static String[] getAllHashtags() {
        return hashtagPool;
    }

    public static ArrayList<String> getRandomHashtagArrayList() {
        int m = rng.nextInt(6) + 1;
        int d = 0;
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String addMe = getRandomHashtag();
            if (!result.contains(addMe))
                result.add(addMe);
            else
            {
                d++;
            }
        }

        return result;
    }

    public static InterestKind getRandomInterestKind() {
        InterestKind[] values = InterestKind.values();

        return values[rng.nextInt(values.length)];
    }

    public static String getRandomCompanyName() {
        String[] poolA = new String[]{"Methan", "Akane", "Neptune", "Tangent", "Telia", "Bob's", "Bernd", "Bubbles"};
        String[] poolB = new String[]{"Industries", "Records", "International", "Pizza", "Computers", "Burgers", "Data"};
        String[] poolC = new String[]{"KG", "UG", "GbR", "GmbH", "OHG", "AG", "e.V.", "GmbH & Co. KG", "Limited"};

        return String.format("%s %s %s", poolA[rng.nextInt(poolA.length)], poolB[rng.nextInt(poolB.length)], poolC[rng.nextInt(poolC.length)]);
    }

    public static String getRandomComment() {
        String[] poolA = new String[]{"total", "voll", "übelst", "", ""};
        String[] poolB = new String[]{"witzig", "doof", "cool", "geil", "nice", "mies", "nett"};

        return String.format("%s %s", poolA[rng.nextInt(poolA.length)], poolB[rng.nextInt(poolB.length)]);
    }

    public static String getRandomEventName() {
        String[] poolA = new String[]{"Comic", "The Vocaloid", "Idol", "Trek", "Cave"};
        String[] poolB = new String[]{"ket", "con", "master", " market", " Matsuri"};
        String[] poolC = new String[]{"97,98,99,2000,2001,2015,2016"};
        return String.format("%s %s %s", poolA[rng.nextInt(poolA.length)], poolB[rng.nextInt(poolB.length)], poolC[rng.nextInt(poolC.length)]);
    }

    public static ArrayList<Long> getArrayListFrom(long l) {
        ArrayList<Long> result = new ArrayList<>();
        result.add(l);
        return result;
    }

    public static ArrayList<Long> getArrayListFrom(long l, long l2) {
        ArrayList<Long> result = new ArrayList<>();
        result.add(l);
        result.add(l2);
        return result;
    }

    public static ArrayList<Long> getArrayListFrom(long l, long l2, long l3) {
        ArrayList<Long> result = new ArrayList<>();
        result.add(l);
        result.add(l2);
        result.add(l3);
        return result;
    }
}
