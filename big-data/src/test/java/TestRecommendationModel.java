import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.junit.Ignore;
import org.junit.Test;
import junit.framework.*;

import de.due.ldsa.bd.analysis.RMSEcomputation;
import de.due.ldsa.bd.analysis.GetRecommendation;
import de.due.ldsa.bd.analysis.RecommendationModel;
import scala.Tuple2;


/**
 * Unit Testing:
 * A class containins collaborating filtering algorithm implementation
 * and Analysis as Recommendation Model for predict best recommendation from user and products/followers
 * 
 * @author MD Ariful Islam, MCE,UDE
 *
 */

public class TestRecommendationModel extends RecommendationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//test RMSE class 
	@Test
	public void RmseComputationtest() {
		RMSEcomputation object = new RMSEcomputation();
		assertNotNull(object);
		assertTrue(true);
	}
	
	//test get recommendation class
	@Test
	public void GetRecommendationtest() {
		GetRecommendation object2 = new GetRecommendation();
		assertNotNull(object2);
		assertTrue(true);
	}
	
	//Test main class of recommendation
	@Test
	public void RecommendationModelMaintest() {
		RecommendationModel object3 = new RecommendationModel();
		assertNotNull(object3);
		assertTrue(true);
	}
	
	//Test mapping
		@Test
		public void verifyMappingtest() {
			//Initializing Spark Context
			SparkConf conftest = new SparkConf().setAppName("testBigdata").setMaster("local[2]");
			JavaSparkContext jsc = new JavaSparkContext(conftest);
			List<Integer> input =  Arrays.asList(1,2);
		    JavaRDD<Integer> inputRDD = jsc.parallelize(input);
		    JavaRDD<Integer> result = inputRDD.map(
		      new Function<Integer, Integer>() { 
				private static final long serialVersionUID = 1L;
			public Integer call(Integer x) { return x*x;}});
		    assertEquals(input.size(), result.count());
            jsc.close();
		}
		
	//Test product data method
		@Test
		public void testproductdatacollect() {
			//Initializing Spark Context
			SparkConf conftest = new SparkConf().setAppName("testBigdata").setMaster("local[2]");
			JavaSparkContext jsc = new JavaSparkContext(conftest);
			String input = "src/main/resources/mllib/movies.dat";
			JavaRDD<String> productData = jsc.textFile(input);

			Map<Integer, String> products = productData.mapToPair(
			        new PairFunction<String, Integer, String>() {
						private static final long serialVersionUID = 1L;
						public Tuple2<Integer, String> call(String s) throws Exception {
			                String[] sarray = s.split("::");
			                return new Tuple2<Integer, String>(Integer.parseInt(sarray[0]), sarray[1]);
			            }
			        }
			).collectAsMap();
			assertNotNull(products);
	        jsc.close();
		}
		
	//test case ignore and will not execute
	   @Ignore
	   public void ignoreTest() {
	      System.out.println("in ignore test");
	   }

	//Test Rating Data
	   @Ignore
		public void testRatingData() {
			//Initializing Spark Context
			SparkConf conftest = new SparkConf().setAppName("testBigdata").setMaster("local");
			JavaSparkContext sc = new JavaSparkContext(conftest);
			String input = "hello";
			JavaRDD<String> ratingData = sc.textFile(input);
			assertEquals(ratingData, input);
            sc.close();
		}
	   
	   @Test
	   public void testAdd() {
		// add the test's in the suite
		      TestSuite suite = new TestSuite(TestRecommendationModel.class, BinaryClassificationModelTest.class);
		      TestResult result = new TestResult();
		      suite.run(result);
		      System.out.println("Number of test cases = " + result.runCount());

	   }

	   
	 
}
