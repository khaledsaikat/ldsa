package de.due.ldsa.bd.analysis;

import java.util.ArrayList;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
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
	private String inputFile;
	private double minSupport;
	private int numPartition;

	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
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
	public static void main(String[] args) {
		FPGrowthAnalysis analysis = new FPGrowthAnalysis();
		analysis.setInputFile("src/main/resources/FPGrowth");
		analysis.setMinSupport(0.3);
		analysis.setNumPartition(-1);

		SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("FPGrowth Analysis");
		JavaSparkContext sc = new JavaSparkContext(sparkConf);

		JavaRDD<ArrayList<String>> transactions = sc.textFile(analysis.getInputFile())
				.map(new Function<String, ArrayList<String>>() {
					@Override
					public ArrayList<String> call(String s) {
						return Lists.newArrayList(s.split(" "));
					}
				});

		FPGrowthModel<String> model = new FPGrowth().setMinSupport(analysis.getMinSupport())
				.setNumPartitions(analysis.getNumPartition()).run(transactions);

		for (FPGrowth.FreqItemset<String> s : model.freqItemsets().toJavaRDD().collect()) {
			System.out.println("[" + Joiner.on(",").join(s.javaItems()) + "], " + s.freq());
		}

		sc.stop();
	}
}