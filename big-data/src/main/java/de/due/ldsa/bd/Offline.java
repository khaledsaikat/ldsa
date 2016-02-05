package de.due.ldsa.bd;

import java.util.Arrays;
import java.util.List;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.DataFrame;

import de.due.ldsa.bd.analysis.BinaryClassification;
import de.due.ldsa.bd.analysis.CommentSample;
import de.due.ldsa.bd.analysis.Top;

/**
 * Class for offline analysis.
 * 
 * @author Khaled Hossain
 */
public class Offline extends Base {
	/**
	 * Create all necessary context and populate baseRDD.
	 */
	public Offline() {
		super();
		populateData();
	}

	private void populateData() {
		baseData = new Data(sparkContext.parallelize(DataProvider.getInstance().getListSourceData()));
	}

	@SuppressWarnings("unchecked")
	private void runTopWords() {
		Top.topWords((JavaRDD<String>) baseData.getRdd(), 10);
	}

	/**
	 * Run binary classification for finding ham or spam form Comment object.
	 */
	private void runBinaryClassification() {
		// Tracking service is not still ready, we are using sample data.
		List<CommentSample> samples = Arrays.asList(new CommentSample("Some sample comments"),
				new CommentSample(
						"Thanks for your subscription to Ringtone. Your mobile will be charged £5/month Please confirm by replying YES or NO."),
				new CommentSample("Other sample comment"));
		DataFrame data = sqlContext.createDataFrame(sparkContext.parallelize(samples), CommentSample.class);

		BinaryClassification binaryClassification = new BinaryClassification();
		binaryClassification.setSparkContext(sparkContext);
		binaryClassification.setSqlContext(sqlContext);
		binaryClassification.analysis(data);
	}

	/**
	 * Run analysis.
	 */
	public void run() {
		runTopWords();
		runBinaryClassification();
		sparkContext.stop();
	}
}
