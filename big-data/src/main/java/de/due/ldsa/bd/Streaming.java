package de.due.ldsa.bd;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import de.due.ldsa.bd.analysis.BinaryClassification;
import de.due.ldsa.bd.analysis.CommentSample;
import de.due.ldsa.bd.analysis.KMeansClustering;

/**
 * Running a streaming program continuously for online analysis.
 * 
 * Check CustomReceiver in every x interval for streaming data. Then apply some
 * analysis on it and export results into database.
 * 
 * @author Khaled Hossain
 */
public class Streaming extends Base {
	private static Streaming instance = null;
	private JavaStreamingContext streamingContext;

	/**
	 * Get singleton instance
	 */
	public static Streaming getInstance() {
		if (instance == null) {
			instance = new Streaming();
		}
		return instance;
	}

	/**
	 * Private constructor for singleton object to create all necessary context
	 * and populate baseDStream.
	 */
	private Streaming() {
		super();
		streamingContext = new JavaStreamingContext(sparkContext, Durations.seconds(Config.interval));
		populateBaseData();
	}

	/**
	 * Set baseData with dStream
	 */
	private void populateBaseData() {
		baseData = new Data(streamingContext.receiverStream(new CustomReceiver()));
		baseData.setSparkContext(sparkContext);
		baseData.setSqlContext(sqlContext);	
	}

	/**
	 * Run binary classification for finding ham or spam form Comment object.
	 */
	private void runBinaryClassification() {
		baseData.getDstream().foreachRDD(rdd -> {
			DataFrame dataFrame = baseData.rddToDataframe(rdd, new CommentSample());
			BinaryClassification binaryClassification = new BinaryClassification(baseData);
			binaryClassification.analysis(dataFrame);
		});
	}

	/**
	 * Run KMeans Clustering to divide Comment object into different clusters.
	 * 
	 * @author Abdul Qadir
	 */
	private void runKMeansClustering() {
		baseData.getDstream().foreachRDD(rdd -> {
			DataFrame dataFrame = baseData.rddToDataframe(rdd, new CommentSample());
			KMeansClustering kmeans = new KMeansClustering(baseData);
			kmeans.analysis(dataFrame);
		});
	}

	/**
	 * Run analysis.
	 */
	public void run() {
		runKMeansClustering();
		runBinaryClassification();
		streamingContext.start();
		streamingContext.awaitTermination();
	}
}