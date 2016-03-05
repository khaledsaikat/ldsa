package de.due.ldsa.bd.analysis;

import java.util.List;
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
import de.due.ldsa.bd.exceptions.AnalysisException;
import de.due.ldsa.bd.exceptions.SparkContextDataException;

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
	private DataFrame getTrainingDataFrame() throws SparkContextDataException {
		String path = "../big-data/src/main/resources/smsspamcollection/SMSSpamCollection";
		JavaRDD<Object> rdd = baseData.getSparkContext().textFile(path).map(line -> {
			String[] parts = line.split("\t");
			AnalysisModel model = new AnalysisModel(parts[1]);
			return model;
		});
		DataFrame dataFrame = baseData.rddToDataframe(rdd, new AnalysisModel());
		return dataFrame;
	}

	/**
	 * Combined Tokenizer, HashingTF, IDF and KMeans to a single pipeline
	 */
	private Pipeline getPipeline() throws AnalysisException {
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
	public List<String> analysis(DataFrame data) throws AnalysisException {
		DataFrame training = getTrainingDataFrame();
		Pipeline pipeline = getPipeline();
		PipelineModel model = pipeline.fit(training);
		DataFrame predictions = model.transform(data);
		return predictions.javaRDD().map(r -> r.getString(0) + ":" + String.valueOf(r.getInt(4))).collect();
	}
}
