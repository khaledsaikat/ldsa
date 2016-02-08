package de.due.ldsa.bd.twitter;

import java.util.Arrays;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;

import de.due.ldsa.bd.Base;
import de.due.ldsa.bd.Config;

/**
 * Class for Offline Tweets Analysis
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
public class OfflineTwitter extends Base {
	private static JavaRDD<Iterable<String>> tweetsRDD;
	private static JavaRDD<Vector> parsedTweets;
	private static KMeansModel model;
	private static SparkConf conf;
	private static JavaSparkContext sc;

	/**
	 * Initializing spark context and populate base RDD
	 */
	public static void Offline() {
		conf = new SparkConf().setMaster(Config.master).setAppName(Config.appName);
		sc = new JavaSparkContext(conf);
		populateBaseRDD();
	}

	/**
	 * Create base RDD from tweets stored in project resources
	 */
	private static void populateBaseRDD() {
		tweetsRDD = sc.textFile("src/main/resources/tweets_*/part-*").map(new Function<String, Iterable<String>>() {
			private static final long serialVersionUID = 1L;

			public Iterable<String> call(String s) throws Exception {
				return Arrays.asList(s.split("\n"));
			}
		});
	}

	/**
	 * Train and save KMeans model
	 */
	public static void trainAndSaveModel() {
		parsedTweets = Helper.parsedTweets(tweetsRDD);
		model = Helper.trainModel(parsedTweets);
		Helper.saveModel(sc, model);
	}

	/**
	 * Run analysis.
	 */
	static void run() {
		TwitterKMeansClustering.offlineKMeans(tweetsRDD, sc);
	}
}
