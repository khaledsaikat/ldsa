package de.due.ldsa.bd.analysis;

import java.util.List;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.feature.HashingTF;
import org.apache.spark.ml.feature.Tokenizer;
import org.apache.spark.sql.DataFrame;
import de.due.ldsa.bd.Data;
import de.due.ldsa.bd.exceptions.AnalysisException;
import de.due.ldsa.bd.exceptions.SparkContextDataException;

/**
 * Binary classification analysis. We need to provide training data to train the
 * model. Based on trained model, this class can able to predict new binary
 * result.
 * 
 * @author Khaled Hossain
 */
public class BinaryClassification {
	private Data baseData;

	public BinaryClassification(Data data) {
		baseData = data;
	}

	/**
	 * @return DataFrame for training.
	 */
	private DataFrame getTrainingDataFrame() throws SparkContextDataException {
		String path = "../big-data/src/main/resources/smsspamcollection/SMSSpamCollection";
		JavaRDD<Object> rdd = baseData.getSparkContext().textFile(path).map(line -> {
			String[] parts = line.split("\t");
			Double label = parts[0].equals("spam") ? 1.0 : 0.0;
			BinaryClassificationModel model = new BinaryClassificationModel(label, parts[1]);
			return model;
		});
		DataFrame dataFrame = baseData.rddToDataframe(rdd, new BinaryClassificationModel());

		return dataFrame;
	}

	/**
	 * Combined Tokenizer, HashingTF and LogisticRegression to a single
	 * pipeline.
	 */
	private Pipeline getPipeline() throws AnalysisException {
		Tokenizer tokenizer = new Tokenizer().setInputCol("text").setOutputCol("words");
		HashingTF hashingTF = new HashingTF().setNumFeatures(1000).setInputCol(tokenizer.getOutputCol())
				.setOutputCol("features");
		LogisticRegression lr = new LogisticRegression().setMaxIter(10).setRegParam(0.001);
		Pipeline pipeline = new Pipeline().setStages(new PipelineStage[] { tokenizer, hashingTF, lr });

		return pipeline;
	}

	/**
	 * Train the model and based on trained model run test data
	 */
	public void analysisRandom() throws AnalysisException {
		DataFrame[] splits = getTrainingDataFrame().randomSplit(new double[] { 0.9, 0.1 });
		DataFrame training = splits[0];
		DataFrame test = splits[1];
		Pipeline pipeline = getPipeline();
		PipelineModel model = pipeline.fit(training);
		DataFrame predictions = model.transform(test);
		predictions.show();
	}

	/**
	 * Analysis comments based on trained model
	 */
	public List<String> analysis(DataFrame data) throws AnalysisException {
		DataFrame training = getTrainingDataFrame();
		Pipeline pipeline = getPipeline();
		PipelineModel model = pipeline.fit(training);
		DataFrame predictions = model.transform(data);
		return predictions.javaRDD().map(r -> r.getString(0) + ":" + String.valueOf((int) r.getDouble(5))).collect();
	}
}
