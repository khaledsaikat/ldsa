import org.junit.Test;
import static org.junit.Assert.assertEquals;
import de.due.ldsa.bd.analysis.BinaryClassificationModel;

/*
 * @author Khaled Hossain
 */
public class BinaryClassificationModelTest {
	@Test
	public void testGetLabel() {
		Double label = 1.0;
		BinaryClassificationModel model = new BinaryClassificationModel(label, "text");
		assertEquals(model.getLabel(), label);
	}

	@Test
	public void testGetText() {
		BinaryClassificationModel model = new BinaryClassificationModel(1.0, "text");
		assertEquals(model.getText(), "text");
	}

	@Test
	public void testLabelGetterSetter() {
		Double label = 1.0;
		BinaryClassificationModel model = new BinaryClassificationModel();
		model.setLabel(label);
		assertEquals(model.getLabel(), label);
	}

	@Test
	public void testTextGetterSetter() {
		BinaryClassificationModel model = new BinaryClassificationModel();
		model.setText("text");
		assertEquals(model.getText(), "text");
	}
}
