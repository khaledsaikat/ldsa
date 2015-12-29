package de.due.ldsa.bd;

import org.apache.spark.api.java.JavaRDD;
import de.due.ldsa.bd.analysis.Top;

/**
 * Class for offline analysis.
 * 
 * @author Khaled Hossain
 */
public class Offline extends Base {
	private JavaRDD<?> baseRDD;

	/**
	 * Create all necessary context and populate baseRDD.
	 */
	public Offline() {
		super();
		populateBaseRDD();
	}

	/**
	 * Making base rdd and assign it to into baseRDD property.
	 */
	private void populateBaseRDD() {
		DataProvider source = new DataProvider();
		baseRDD = sparkContext.parallelize(source.getListSourceData());
	}

	/**
	 * Run analysis.
	 */
	public void run() {
		Top.topWords((JavaRDD<String>) baseRDD, 10);
	}
}
