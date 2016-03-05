package de.due.ldsa.bd;

import java.util.List;

/**
 * Implementation for BDController
 * 
 * @author Khaled Hossain
 */
public class AnalysisController implements BDController {

	@Override
	public void analysis(String analysisType, String analysisName) {
		if (analysisType == "Offline") {
			populateOfflineDataSource();
			Offline.getInstance().run(analysisName);
		} else if (analysisType == "Real-Time") {
			runStreaming(analysisName);
		}
	}

	/**
	 * ExecutorService is somehow is not working here, so i went for simple
	 * Thread implementation.
	 * 
	 * @param analysisName
	 */
	private void runStreaming(String analysisName) {
		new Thread() {
			@Override
			public void run() {
				Streaming.getInstance().run(analysisName);
			}
		}.start();
	}

	/**
	 * @todo Pull data from database
	 */
	private void populateOfflineDataSource() {
		new SampleData().populateCommentsSample();
	}

	@Override
	public List<String> getResult() {
		return ResultContainer.getInstance().getResults();
	}

	/**
	 * This method is only provided for local run case.
	 */
	public static void main(String[] args) throws Exception {
		new SampleData().populateCommentsSample();
		AnalysisController controller = new AnalysisController();
		controller.analysis("Offline", "BC");
		// controller.analysis("Real-Time", "BC");
		List<String> result = controller.getResult();
		System.out.println(result);
	}

}
