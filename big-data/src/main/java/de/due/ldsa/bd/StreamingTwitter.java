package de.due.ldsa.bd;

import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
//import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;
import de.due.ldsa.bd.analysis.KMeansClustering;
//import de.due.ldsa.bd.analysis.Top;
import twitter4j.Status;

/**
 * Running a streaming program continuously for online analysis. Create Twitter
 * Streams and apply KMeans Clustering on it
 * 
 * @author Abdul Qadir
 */
public class StreamingTwitter extends Base {
	private static JavaStreamingContext streamingContext;
	private static JavaDStream<Status> tweetsDStream;

	/**
	 * Create all necessary context and populate baseDStream.
	 */
	public StreamingTwitter() {
		super();
		streamingContext = new JavaStreamingContext(sparkContext, Durations.seconds(Config.interval));
		populateBaseDStream();
	}

	/**
	 * Set baseDStream
	 */
	private void populateBaseDStream() {
		tweetsDStream = TwitterUtils.createStream(streamingContext);
	}

	/**
	 * Run analysis.
	 */
	public void run() {
		KMeansClustering.streamingKMeans(tweetsDStream, streamingContext);
		streamingContext.start();
		streamingContext.awaitTermination();
	}

}