import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import de.due.ldsa.bd.DataProvider;
import de.due.ldsa.bd.DataSource;

/*
 * @author Khaled Hossain
 */
public class DataProviderTest {
	@Test
	public void testSetAndGetStringSourceData() {
		String data = "Hello World";
		DataSource source1 = DataProvider.getInstance();
		source1.setSourceData(data);
		DataSource source2 = DataProvider.getInstance();
		assertEquals(source2.getStringSourceData(), data);
	}

	@Test
	public void testSetAndGetListSourceData() {
		List<String> data = Arrays.asList("Hello World1", "Hello World2");
		DataSource source1 = DataProvider.getInstance();
		source1.setSourceData(data);
		DataSource source2 = DataProvider.getInstance();
		assertEquals(source2.getListSourceData(), data);
	}
}
