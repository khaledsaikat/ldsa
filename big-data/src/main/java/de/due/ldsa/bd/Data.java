package de.due.ldsa.bd;

import java.util.List;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
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
	
	public Data(List<?> rawList) {
		this.rawList = rawList;
	}

	public Data(JavaRDD<?> rdd) {
		this.rdd = rdd;
	}

	public Data(JavaReceiverInputDStream<?> dstream) {
		this.dstream = dstream;
	}
	
	public void setSparkContext(JavaSparkContext sparkContext) {
		this.sparkContext = sparkContext;
	}

	public void setSqlContext(SQLContext sqlContext) {
		this.sqlContext = sqlContext;
	}
	
	public JavaSparkContext getSparkContext() {
		return sparkContext;
	}
	
	public SQLContext getSqlContext() {
		return sqlContext;
	}

	public JavaRDD<?> getRdd() {
		if (rdd == null && rawList != null) {
			rdd = sparkContext.parallelize(rawList);
		}
		return rdd;
	}

	public JavaReceiverInputDStream<?> getDstream() {
		return dstream;
	}

	public DataFrame rddToDataframe(JavaRDD<?> rdd, Object SampleClass) {
		return sqlContext.createDataFrame(rdd, SampleClass.getClass());
	}

}
