import java.util.List;

import de.due.ldsa.bd.AnalysisController;
import de.due.ldsa.bd.BDController;
import de.due.ldsa.ld.LinkDataReceiver;
import de.due.ldsa.ld.LinkDataReceiverImpl;

/**
 * @author Jannik Stach
 *
 */

public class TopLevelController {
	
	private static TopLevelController tlController;
	
	private LinkDataReceiver ldController = LinkDataReceiverImpl.getInstance();
	private BDController bdController = new AnalysisController();
	
	public static TopLevelController getInstance() {
		if (tlController == null) {
			tlController = new TopLevelController();
		}
		return tlController;
	}
	
	/**
	 * @param onlineAnalysis
	 * @param analysisName
	 *            Possible value: BC, KC
	 */
	public void analysisData(boolean onlineAnalysis, String analysisName) {
		String analysisType = "Offline";
		if(onlineAnalysis = true) {
			ldController.setOnlineAnalysis(true);
			analysisType = "Real-Time";
		}
		bdController.analysis(analysisType, analysisName);
	}
	
	public void fetchData() {
		ldController.setOnlineAnalysis(false);
	}
	
	/**
	 * Collect analysis result. For Real-Time, call this method by separate
	 * thread in a loop on every x second to collect updated result.
	 * 
	 * @return List of analysis result as string
	 */
	public List<String> getResult() {
		return bdController.getResult();
	}
	
}
