package de.due.ldsa.bd;

import java.util.List;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import de.due.ldsa.bd.exceptions.SparkContextDataException;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

/**
 * Contains data in various spark format for analysis.
 * 
 * @author Khaled Hossain
 */
public class Data {
	private JavaSparkContext sparkContext;
	private SQLContext sqlContext;
	private List<?> rawList = null;
	private JavaRDD<?> rdd = null;
	private JavaReceiverInputDStream<?> dstream;
	
	public Data(List<?> rawList) throws SparkContextDataException {
		this.rawList = rawList;
	}

	public Data(JavaRDD<?> rdd) throws SparkContextDataException {
		this.rdd = rdd;
		this.rdd.cache();
	}

	public Data(JavaReceiverInputDStream<?> dstream) throws SparkContextDataException {
		this.dstream = dstream;
		this.dstream.cache();
	}
	
	public void setSparkContext(JavaSparkContext sparkContext) throws SparkContextDataException {
		this.sparkContext = sparkContext;
	}

	public void setSqlContext(SQLContext sqlContext) throws SparkContextDataException {
		this.sqlContext = sqlContext;
	}
	
	public JavaSparkContext getSparkContext() throws SparkContextDataException {
		return sparkContext;
	}
	
	public SQLContext getSqlContext() throws SparkContextDataException {
		return sqlContext;
	}
	
	public List<?> getRawList() {
		return rawList;
	}

	public JavaRDD<?> getRdd() throws SparkContextDataException {
		if (rdd == null && rawList != null) {
			rdd = sparkContext.parallelize(rawList);
			rdd.cache();
		}
		return rdd;
	}

	public JavaReceiverInputDStream<?> getDstream() throws SparkContextDataException {
		return dstream;
	}

	public DataFrame rddToDataframe(JavaRDD<?> rdd, Object SampleClass) throws SparkContextDataException {
		return sqlContext.createDataFrame(rdd, SampleClass.getClass());
	}

}
