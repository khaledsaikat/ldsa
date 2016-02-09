package de.due.ldsa.bd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import de.due.ldsa.bd.exceptions.SparkContextDataException;

/**
 * Base class for creating spark context.
 * 
 * This abstract class has two direct childs: Streaming and Offline
 * 
 * @author Khaled Hossain
 */
public abstract class Base {
	protected SparkConf conf;
	protected JavaSparkContext sparkContext;
	protected SQLContext sqlContext;
	protected Data baseData;

	/**
	 * Initializing conf, sparkContext and sqlContext.
	 */
	public Base() throws SparkContextDataException {
		conf = new SparkConf().setMaster(Config.master).setAppName(Config.appName);
		sparkContext = new JavaSparkContext(conf);
		sqlContext = new SQLContext(sparkContext);
	}
}
