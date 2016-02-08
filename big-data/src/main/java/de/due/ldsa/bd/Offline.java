package de.due.ldsa.bd;

import org.apache.spark.sql.DataFrame;
import de.due.ldsa.bd.analysis.BinaryClassification;
import de.due.ldsa.bd.analysis.CommentSample;
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
		baseData = new Data(sparkContext.parallelize(DataProvider.getInstance().getListSourceData()));
	}

	/**
	 * Run KMeans Clustering to divide Comment object into different clusters.
	 * 
	 * @author Abdul Qadir
	 */
	private void runKMeansClustering() {
		DataFrame data = sqlContext.createDataFrame(baseData.getRdd(), CommentSample.class);
		KMeansClustering kmeans = new KMeansClustering();
		kmeans.setSparkContext(sparkContext);
		kmeans.setSqlContext(sqlContext);
		kmeans.analysis(data);
	}

	/**
	 * Run binary classification for finding ham or spam form Comment object.
	 */
	private void runBinaryClassification() {
		DataFrame data = sqlContext.createDataFrame(baseData.getRdd(), CommentSample.class);
		BinaryClassification binaryClassification = new BinaryClassification();
		binaryClassification.setSparkContext(sparkContext);
		binaryClassification.setSqlContext(sqlContext);
		binaryClassification.analysis(data);
	}

	/**
	 * Run analysis.
	 */
	public void run() {
		runKMeansClustering();
		runBinaryClassification();
		sparkContext.stop();
	}
}
