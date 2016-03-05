import org.junit.Test;
import static org.junit.Assert.assertEquals;
import de.due.ldsa.bd.analysis.AnalysisModel;

/*
 * @author Khaled Hossain
 */
public class AnalysisModelTest {
	@Test
	public void testGetLabel() {
		Double label = 1.0;
		AnalysisModel model = new AnalysisModel(label, "text");
		assertEquals(model.getLabel(), label);
	}

	@Test
	public void testGetText() {
		AnalysisModel model = new AnalysisModel(1.0, "text");
		assertEquals(model.getText(), "text");
	}

	@Test
	public void testLabelGetterSetter() {
		Double label = 1.0;
		AnalysisModel model = new AnalysisModel();
		model.setLabel(label);
		assertEquals(model.getLabel(), label);
	}

	@Test
	public void testTextGetterSetter() {
		AnalysisModel model = new AnalysisModel();
		model.setText("text");
		assertEquals(model.getText(), "text");
	}
}
