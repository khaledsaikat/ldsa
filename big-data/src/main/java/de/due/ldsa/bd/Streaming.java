package de.due.ldsa.bd;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import de.due.ldsa.bd.analysis.BinaryClassification;
import de.due.ldsa.bd.analysis.CommentSample;

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
	 * Private constructor for singleton object to create all necessary context and populate baseDStream.
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
	}
	
	private void runBinaryClassification() {
		baseData.getDstream().foreachRDD(rdd -> {	
			DataFrame data = sqlContext.createDataFrame(rdd, CommentSample.class);
			BinaryClassification binaryClassification = new BinaryClassification();
			binaryClassification.setSparkContext(sparkContext);
			binaryClassification.setSqlContext(sqlContext);
			binaryClassification.analysis(data);
		});
	}
	
	/**
	 * Run analysis.
	 */
	public void run() {	
		runBinaryClassification();
		streamingContext.start();
		streamingContext.awaitTermination();
	}
}