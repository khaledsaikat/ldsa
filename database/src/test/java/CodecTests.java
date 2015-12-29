import com.datastax.driver.core.ProtocolVersion;
import de.due.ldsa.db.codecs.ByteArrayCodec;
import de.due.ldsa.db.codecs.LongArrayListCodec;
import de.due.ldsa.db.codecs.StringArrayListCodec;
import de.due.ldsa.db.codecs.UrlCodec;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 *
 */
public class CodecTests {
    @Test
    public void testLongArrayListCodec() throws Exception {
        ArrayList<Long> test = new ArrayList<>();
        test.add(TestUtils.randomLong());
        test.add(TestUtils.randomLong());
        test.add(TestUtils.randomLong());

        LongArrayListCodec lalc = new LongArrayListCodec();
        ByteBuffer ser = lalc.serialize(test, ProtocolVersion.V3);

        ArrayList<Long> deser = lalc.deserialize(ser, ProtocolVersion.V3);

        Assert.assertEquals(test, deser);
    }

    @Test
    public void testStringArrayListCodec() throws Exception {
        ArrayList<String> test = new ArrayList<>();
        test.add(TestUtils.randomForename());
        test.add(TestUtils.randomForename());
        test.add(TestUtils.randomForename());

        StringArrayListCodec salc = new StringArrayListCodec();
        ByteBuffer ser = salc.serialize(test, ProtocolVersion.V3);

        ArrayList<String> deser = salc.deserialize(ser, ProtocolVersion.V3);

        Assert.assertEquals(test, deser);
    }

    @Test
    public void testByteArrayCodec() throws Exception {
        ByteArrayCodec bac = new ByteArrayCodec();
        byte[] first = TestUtils.createRandomMedia();

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
}
