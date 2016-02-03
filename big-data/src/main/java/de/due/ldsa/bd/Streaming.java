package de.due.ldsa.bd;

import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import de.due.ldsa.bd.analysis.Top;

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
	private JavaReceiverInputDStream<String> baseDStream;

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
	 * Private constructor singleton object. for Create all necessary context and populate baseDStream.
	 */
	private Streaming() {
		super();
		streamingContext = new JavaStreamingContext(sparkContext, Durations.seconds(Config.interval));
		populateBaseDStream();
	}

	/**
	 * Set baseDStream
	 */
	private void populateBaseDStream() {
		baseDStream = streamingContext.receiverStream(new CustomReceiver());
	}

	/**
	 * Run analysis.
	 */
	public void run() {
		Top.wordCounts(baseDStream);
		streamingContext.start();
		streamingContext.awaitTermination();
	}
}