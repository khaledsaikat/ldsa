import com.datastax.driver.core.ProtocolVersion;
import de.due.ldsa.db.codecs.*;
import de.due.ldsa.db.model.InterestKind;
import de.due.ldsa.db.model.RelationshipStatus;
import de.due.ldsa.db.model.Sex;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.nio.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 *
 */
public class CodecTests {
    @Test
    public void testLongArrayListCodec() throws Exception {
        ArrayList<Long> test = new ArrayList<>();
        test.add(TestUtils.getRandomLong());
        test.add(TestUtils.getRandomLong());
        test.add(TestUtils.getRandomLong());

        LongArrayListCodec lalc = new LongArrayListCodec();
        ByteBuffer ser = lalc.serialize(test, ProtocolVersion.V3);

        ArrayList<Long> deser = lalc.deserialize(ser, ProtocolVersion.V3);

        Assert.assertEquals(test, deser);
    }

    @Test
    public void testStringArrayListCodec() throws Exception {
        ArrayList<String> test = new ArrayList<>();
        test.add(TestUtils.getRandomForename());
        test.add(TestUtils.getRandomForename());
        test.add(TestUtils.getRandomForename());

        StringArrayListCodec salc = new StringArrayListCodec();
        ByteBuffer ser = salc.serialize(test, ProtocolVersion.V3);

        ArrayList<String> deser = salc.deserialize(ser, ProtocolVersion.V3);

        Assert.assertEquals(test, deser);
    }

    @Test
    public void testByteArrayCodec() throws Exception {
        ByteArrayCodec bac = new ByteArrayCodec();
        byte[] first = TestUtils.getRandomByteArray();

        ByteBuffer ser = bac.serialize(first, ProtocolVersion.V3);
        byte[] second = bac.deserialize(ser, ProtocolVersion.V3);

        Assert.assertEquals(first, second);
    }

    @Test
    public void testUrlCodec() throws Exception {
        UrlCodec codec = new UrlCodec();

        URL first = new URL("http://anidb.net");
        ByteBuffer ser = codec.serialize(first, ProtocolVersion.V3);

        URL second = codec.deserialize(ser, ProtocolVersion.V3);

        Assert.assertEquals(first, second);
    }

    @Test
    public void testLocalDateCodec() throws Exception {
        LocalDateCodec ldc = new LocalDateCodec();
        LocalDate first = TestUtils.getRandomLocalDate();
        ByteBuffer buf = ldc.serialize(first,ProtocolVersion.V3);
        LocalDate second = ldc.deserialize(buf,ProtocolVersion.V3);
        Assert.assertEquals(first,second);
    }

    @Test
    public void testRelationshipStatusCodec() throws Exception {
        RelationshipStatusCodec relationshipStatusCodec = new RelationshipStatusCodec();
        RelationshipStatus relationshipStatus = TestUtils.getRandomRelationshipStatus();
        ByteBuffer buff = relationshipStatusCodec.serialize(relationshipStatus,ProtocolVersion.V3);
        RelationshipStatus second = relationshipStatusCodec.deserialize(buff,ProtocolVersion.V3);
        Assert.assertEquals(relationshipStatus,second);
    }

    @Test
    public void testSexCodec() throws Exception {
        SexCodec sexCodec = new SexCodec();
        Sex sex = TestUtils.getRandomSex();
        ByteBuffer buff = sexCodec.serialize(sex,ProtocolVersion.V3);
        Sex second = sexCodec.deserialize(buff,ProtocolVersion.V3);
        Assert.assertEquals(sex,second);
    }

    @Test
    public void testInterestKindArrayListCodec() throws Exception {
        ArrayList<InterestKind> ik = new ArrayList<>();
        int m = TestUtils.rng.nextInt(9) + 1;
        for (int i = 0; i < m; i++)
        {
            ik.add(InterestKind.fromOrdinal(m));
        }

        InterestKindArrayListCodec interestKindArrayListCodec = new InterestKindArrayListCodec();
        ByteBuffer b = interestKindArrayListCodec.serialize(ik,ProtocolVersion.V3);

        ArrayList<InterestKind> second = interestKindArrayListCodec.deserialize(b,ProtocolVersion.V3);
        Assert.assertEquals(ik,second);
    }

    @Test
    public void testInterestKindCodec() throws Exception {
        InterestKind first = TestUtils.getRandomInterestKind();
        InterestKindCodec ik = new InterestKindCodec();
        ByteBuffer ser = ik.serialize(first,ProtocolVersion.V3);
        InterestKind second = ik.deserialize(ser,ProtocolVersion.V3);
        Assert.assertEquals(first,second);
    }

    @Test
    public void testOffsetDateTimeCodec() throws Exception {
        OffsetDateTime offsetDateTime = TestUtils.getRandomDateTime();
        OffsetDateTimeCodec offsetDateTimeCodec = new OffsetDateTimeCodec();
        ByteBuffer ser = offsetDateTimeCodec.serialize(offsetDateTime,ProtocolVersion.V3);
        OffsetDateTime second = offsetDateTimeCodec.deserialize(ser,ProtocolVersion.V3);
        Assert.assertEquals(offsetDateTime,second);
    }
}
