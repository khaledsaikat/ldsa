package de.due.ldsa.bd.analysis;

import java.util.List;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import de.due.ldsa.bd.Helper;
import twitter4j.Status;

/**
 * A KMeans Analysis Class
 * 
 * @author Abdul Qadir
 *
 */
public class KMeansClustering {
	/**
	 * This method takes sample tweets from project resources and prints out in
	 * different clusters based on KMeans for offline analysis
	 * 
	 * @param tweets
	 * @param sc
	 */
	public static void offlineKMeans(JavaRDD<Iterable<String>> tweets, JavaSparkContext sc) {
		KMeansModel model = Helper.loadModel(sc);
		List<Iterable<String>> someTweets = tweets.take(150);
		int i;
		for (i = 0; i < 5; i++) {
			int x = i;
			System.out.println("Cluster:" + (x + 1));
			someTweets.forEach(t -> {
				if (model.predict(Helper.featurizeVector(t)) == x) {
					System.out.println(t);
				}
			});
		}
	}

	/**
	 * This method filters live streaming tweets on given cluster number
	 * 
	 * @param tweets
	 * @param ssc
	 */
	public static void streamingKMeans(JavaDStream<Status> tweets, JavaStreamingContext ssc) {
		KMeansModel model = Helper.streamingLoadModel(ssc);
		KMeansModel modelObject = new KMeansModel(model.clusterCenters());
		JavaDStream<Iterable<String>> filteredTweets = Helper.getTweets(tweets)
				.filter(t -> modelObject.predict(Helper.featurizeVector(t)) == 0);
		filteredTweets.print();
	}

}
