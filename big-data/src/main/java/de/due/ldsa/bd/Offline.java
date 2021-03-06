package de.due.ldsa.bd;

import org.apache.spark.sql.DataFrame;
import de.due.ldsa.bd.analysis.BinaryClassification;
import de.due.ldsa.bd.analysis.CommentSample;
import de.due.ldsa.bd.analysis.FPGrowthAnalysis;
import de.due.ldsa.bd.analysis.KMeansClustering;

/**
 * Class for offline analysis.
 * 
 * @author Khaled Hossain
 */
public class Offline extends Base {
	private static Offline instance = null;

	/**
	 * Get singleton instance
	 */
	public static Offline getInstance() {
		if (instance == null) {
			instance = new Offline();
		}
		return instance;
	}

	/**
	 * Private constructor for singleton object to create all necessary context
	 * and populate baseRDD.
	 */
	private Offline() {
		super();
		populateBaseData();
	}

	private void populateBaseData() {
		baseData = new Data(DataProvider.getInstance().getListSourceData());
		baseData.setSparkContext(sparkContext);
		baseData.setSqlContext(sqlContext);
	}

	/**
	 * Run KMeans Clustering to divide Comment object into different clusters.
	 * 
	 * @author Abdul Qadir
	 */
	private void runKMeansClustering() {
		DataFrame dataFrame = baseData.rddToDataframe(baseData.getRdd(), new CommentSample());
		KMeansClustering kmeans = new KMeansClustering(baseData);
		ResultContainer.getInstance().setResults(kmeans.analysis(dataFrame));
	}

	/**
	 * Run binary classification for finding ham or spam form Comment object.
	 */
	private void runBinaryClassification() {
		DataFrame dataFrame = baseData.rddToDataframe(baseData.getRdd(), new CommentSample());
		BinaryClassification binaryClassification = new BinaryClassification(baseData);
		ResultContainer.getInstance().setResults(binaryClassification.analysis(dataFrame));
	}
	
	private void runFPGrowth() {
		ResultContainer.getInstance().setResults(FPGrowthAnalysis.analysis(baseData));
	}

	/**
	 * Run analysis.
	 */
	public void run(String analysisName) {
		switch (analysisName) {
		case "KC":
			runKMeansClustering();
			break;
		case "BC":
			runBinaryClassification();
			break;
		case "GA":
			runFPGrowth();	
			break;
		}
		sparkContext.stop();
	}

}
