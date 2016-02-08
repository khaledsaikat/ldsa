package de.due.ldsa.bd.twitter;

import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
//import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;

import de.due.ldsa.bd.Base;
import de.due.ldsa.bd.Config;
//import de.due.ldsa.bd.analysis.Top;
import twitter4j.Status;

/**
 * Running a streaming program continuously for online analysis. Create Twitter
 * Streams and apply KMeans Clustering on it
 * 
 * (This is the sample class which is implemented to run machine learning
 * analysis using Spark Twitter API. So, it serves as a part of separate
 * application and its not directly a part of the project in integration.
 * Therefore, "Unit Testing" and "Exception Handling" is not done for this
 * class)
 * 
 * @author Abdul Qadir
 * @version 1.0
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
		TwitterKMeansClustering.streamingKMeans(tweetsDStream, streamingContext);
		streamingContext.start();
		streamingContext.awaitTermination();
	}

}