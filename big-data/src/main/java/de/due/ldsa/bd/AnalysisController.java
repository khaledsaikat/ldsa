package de.due.ldsa.bd;

import java.util.List;
import de.due.ldsa.bd.exceptions.AnalysisException;
import de.due.ldsa.bd.exceptions.SparkContextDataException;
import de.due.ldsa.db.Database;
import de.due.ldsa.db.DatabaseImpl;

/**
 * Implementation for BDController
 * 
 * @author Khaled Hossain
 */
public class AnalysisController implements BDController {

	@Override
	public void analysis(String analysisType, String analysisName) throws SparkContextDataException {
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
	private void runStreaming(String analysisName) throws SparkContextDataException {
		new Thread() {
			@Override
			public void run() {
				Streaming.getInstance().run(analysisName);
			}
		}.start();
	}

	/**
	 * Pull data from database. For exception, populate sample data.
	 */
	private void populateOfflineDataSource() {
		try {
			Database database = DatabaseImpl.getInstance();
			DataProvider.getInstance().setSourceData(database.getAllComments());
		} catch (Exception e) {
			new SampleData().populateCommentsSample();
		}
	}

	@Override
	public List<String> getResult() throws AnalysisException {
		return ResultContainer.getInstance().getResults();
	}

}
