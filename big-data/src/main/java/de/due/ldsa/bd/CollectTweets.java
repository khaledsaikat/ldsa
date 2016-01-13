package de.due.ldsa.bd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;
import twitter4j.Status;

/**
 * Collect sample tweets from twitter streams into project resources
 * 
 * @author Abdul Qadir
 *
 */
public class CollectTweets {

	private static JavaStreamingContext ssc;
	private static SparkConf conf;
	private static JavaSparkContext sc;
	private static JavaDStream<Status> storeTweetsDStream;

	/**
	 * Initializing spark context and streaming context
	 */
	private static void store() {
		conf = new SparkConf().setMaster(Config.master).setAppName(Config.appName);
		sc = new JavaSparkContext(conf);
		ssc = new JavaStreamingContext(sc, new Duration(5000));
		storeData();
	}

	/**
	 * Store tweets texts from twitter streams into project resources
	 */
	private static void storeData() {
		storeTweetsDStream = TwitterUtils.createStream(ssc);
		Helper.storeTweetsText(storeTweetsDStream);
		ssc.start();
		ssc.awaitTermination();
	}

	/**
	 * A main method to run collect sample
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		/**
		 * Setting Winutil property is only valid for Windows machine
		 */
		Helper.setProperty();

		/**
		 * Configuring Twitter credentials
		 */
		Helper.configureTwitterCredentials(Helper.getApiKey(), Helper.getApiSecret(), Helper.getAccessToken(),
				Helper.getAccessTokenSecret());
		store();
	}
}
