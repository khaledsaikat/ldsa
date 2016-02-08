package de.due.ldsa.bd.analysis;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.clustering.KMeans;
import org.apache.spark.ml.feature.HashingTF;
import org.apache.spark.ml.feature.IDF;
import org.apache.spark.ml.feature.Tokenizer;
import org.apache.spark.sql.DataFrame;
import de.due.ldsa.bd.Data;

/**
 * A KMeans Clustering Analysis Class
 * 
 * @author Abdul Qadir
 * @version 1.0
 */
public class KMeansClustering {
	private Data baseData;

	public KMeansClustering(Data data) {
		baseData = data;
	}

	/**
	 * @return DataFrame for training.
	 */
	private DataFrame getTrainingDataFrame() {
		String path = "../big-data/src/main/resources/smsspamcollection/SMSSpamCollection";
		JavaRDD<Object> rdd = baseData.getSparkContext().textFile(path).map(line -> {
			String[] parts = line.split("\t");
			CommentSample model = new CommentSample(parts[1]);
			return model;
		});
		DataFrame dataFrame = baseData.rddToDataframe(rdd, new CommentSample());

		return dataFrame;
	}

	/**
	 * Combined Tokenizer, HashingTF, IDF and KMeans to a single pipeline
	 */
	private Pipeline getPipeline() {
		Tokenizer tokenizer = new Tokenizer().setInputCol("text").setOutputCol("words");
		HashingTF hashingTF = new HashingTF().setNumFeatures(1000).setInputCol(tokenizer.getOutputCol())
				.setOutputCol("rawFeatures");
		IDF idf = new IDF().setInputCol(hashingTF.getOutputCol()).setOutputCol("features");
		KMeans kmeans = new KMeans().setK(3).setFeaturesCol(idf.getOutputCol()).setPredictionCol("prediction")
				.setMaxIter(10);
		Pipeline pipeline = new Pipeline().setStages(new PipelineStage[] { tokenizer, hashingTF, idf, kmeans });
		return pipeline;
	}

	/**
	 * KMeans Clustering on comments based on trained model
	 */
	public void analysis(DataFrame data) {
		DataFrame training = getTrainingDataFrame();
		training.cache().count();
		Pipeline pipeline = getPipeline();
		PipelineModel model = pipeline.fit(training);
		DataFrame predictions = model.transform(data);
		predictions.show();
	}
}
