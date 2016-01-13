package de.due.ldsa.bd;

import java.util.Arrays;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import de.due.ldsa.bd.analysis.KMeansClustering;

/**
 * Class for Offline Tweets Analysis
 * 
 * @author Abdul Qadir
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
		KMeansClustering.offlineKMeans(tweetsRDD, sc);
	}
}
