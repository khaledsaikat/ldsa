package de.due.ldsa.bd;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.sql.DataFrame;

/**
 * Contains data in various spark format for analysis.
 * 
 * @author Khaled Hossain
 */
public class Data {
	private JavaRDD<?> rdd;
	private JavaReceiverInputDStream<?> dstream;
	private DataFrame dataFrame;

	public Data(JavaRDD<?> rdd) {
		this.rdd = rdd;
	}

	public Data(JavaReceiverInputDStream<?> dstream) {
		this.dstream = dstream;
	}

	public Data(DataFrame dataFrame) {
		this.dataFrame = dataFrame;
	}

	public JavaRDD<?> getRdd() {
		return rdd;
	}

	public JavaReceiverInputDStream<?> getDstream() {
		return dstream;
	}

	public DataFrame getDataFrame() {
		return dataFrame;
	}
}
