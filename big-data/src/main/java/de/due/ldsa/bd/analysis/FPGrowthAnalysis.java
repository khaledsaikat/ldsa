package de.due.ldsa.bd.analysis;

import java.util.ArrayList;
import java.util.List;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import de.due.ldsa.bd.Data;
import de.due.ldsa.bd.SampleData;
import de.due.ldsa.bd.exceptions.AnalysisException;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.fpm.FPGrowth;
import org.apache.spark.mllib.fpm.FPGrowthModel;

/**
 * @author Md Masum Billah
 */
public class FPGrowthAnalysis {
	/**
	 * minSupport: the minimum support for an itemset to be identified as
	 * frequent. Such as if an item occur 5 out of 7 transactions, it has a
	 * supporting of 5/6 = 0.7. numPartitions: it used to distributed the
	 * itemsets
	 */
	private double minSupport;
	private int numPartition;
	private Data baseData;

	public FPGrowthAnalysis(Data data) {
		baseData = data;
	}

	public double getMinSupport() {
		return minSupport;
	}

	public void setMinSupport(double minSupport) {
		this.minSupport = minSupport;
	}

	public int getNumPartition() {
		return numPartition;
	}

	public void setNumPartition(int numPartition) {
		this.numPartition = numPartition;
	}

	/**
	 * Java example for mining frequent itemsets using FP-growth. Example usage:
	 * Counting occurrence of items Store frequent pattern tree structure by
	 * inserting instance Each instance stored sorted by descending order of
	 * their frequency in the data set FP Growth take an JavaRDD set of
	 * transactions Iteration of array of items are generic type
	 */
	public static void analysis(Data data) throws AnalysisException {
		FPGrowthAnalysis analysis = new FPGrowthAnalysis(data);
		analysis.setMinSupport(0.3);
		analysis.setNumPartition(-1);
		List<String> list = new SampleData().getTextSamples();
		JavaRDD<String> rdd = analysis.baseData.getSparkContext().parallelize(list);
		JavaRDD<ArrayList<String>> transactions1 = rdd.map(r -> Lists.newArrayList(r.split(" ")));
		FPGrowthModel<String> model = new FPGrowth().setMinSupport(analysis.getMinSupport())
				.setNumPartitions(analysis.getNumPartition()).run(transactions1);

		for (FPGrowth.FreqItemset<String> s : model.freqItemsets().toJavaRDD().collect()) {
			System.out.println("[" + Joiner.on(",").join(s.javaItems()) + "], " + s.freq());
		}
	}
}