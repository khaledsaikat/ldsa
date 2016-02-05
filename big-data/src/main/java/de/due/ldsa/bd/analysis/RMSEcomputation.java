package de.due.ldsa.bd.analysis;

import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;

import scala.Tuple2;

public class RMSEcomputation extends RecommendationModel{

	/**
	 * Adding serialUID as base class
	 */
	private static final long serialVersionUID = 1L;

	/**
	* Calculating the Root Mean Squared Error (computeRMSE)
	* then return MSE value as square root for finding best prediction
	* @param model best model generated.
	* @param data  rating data.
	* @return      Root Mean Squared Error
	* @author MD Ariful Islam, MCE, UDE
	*/
	public static Double computeRMSE(MatrixFactorizationModel model, JavaRDD<Rating> data) {
	JavaRDD<Tuple2<Object, Object>> userProducts = data.map(
	        new Function<Rating, Tuple2<Object, Object>>() {
				private static final long serialVersionUID = 1L;
				public Tuple2<Object, Object> call(Rating r) {
	                return new Tuple2<Object, Object>(r.user(), r.product());
	            }
	        }
	);

	JavaPairRDD<Tuple2<Integer, Integer>, Double> predictions = JavaPairRDD.fromJavaRDD(
	        model.predict(JavaRDD.toRDD(userProducts)).toJavaRDD().map(
	                new Function<Rating, Tuple2<Tuple2<Integer, Integer>, Double>>() {
						private static final long serialVersionUID = 1L;
						public Tuple2<Tuple2<Integer, Integer>, Double> call(Rating r) {
	                        return new Tuple2<Tuple2<Integer, Integer>, Double>(
	                                new Tuple2<Integer, Integer>(r.user(), r.product()), r.rating());
	                    }
	                }
	        ));
	JavaRDD<Tuple2<Double, Double>> predictionsAndRatings =
	        JavaPairRDD.fromJavaRDD(data.map(
	                new Function<Rating, Tuple2<Tuple2<Integer, Integer>, Double>>() {
						private static final long serialVersionUID = 1L;
						public Tuple2<Tuple2<Integer, Integer>, Double> call(Rating r) {
	                        return new Tuple2<Tuple2<Integer, Integer>, Double>(
	                                new Tuple2<Integer, Integer>(r.user(), r.product()), r.rating());
	                    }
	                }
	        )).join(predictions).values();

	double mse =  JavaDoubleRDD.fromRDD(predictionsAndRatings.map(
	        new Function<Tuple2<Double, Double>, Object>() {
				private static final long serialVersionUID = 1L;
				public Object call(Tuple2<Double, Double> pair) {
	                Double err = pair._1() - pair._2();
	                return err * err;
	            }
	        }
	).rdd()).mean();

	return Math.sqrt(mse);
	
	}

}
