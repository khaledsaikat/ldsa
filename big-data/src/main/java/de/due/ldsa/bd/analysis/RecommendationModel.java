package de.due.ldsa.bd.analysis;

import scala.Tuple2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;


/**
 * A class containing collaborating filtering algorithm :
 * (which commonly use for recommend system as finding or predicting missing entries as user-item association matrix)
 * implementation and Analysis as Recommendation Model for predict best recommendation from user and products/followers
 * Use-case for Instagram is: we could predict followers for a new or existing Instagram users by it. 
 * MLlib currently support model based collaborating filters and we implement alternating least squares (ALS) algorithm 
 * to learn latent factors(users and products are described by a small set of latent factors that can be used to predict missing entries.).
 * Data info: Here i used 1 million ratings, 6000 users and 5000 products data
 * version used: spark 1.5.2
 * 
 * @author MD Ariful Islam, MCE,UDE
 *
 */

public class RecommendationModel implements Serializable{
	
	/**
	 *  Spark machine learning needs some functions as Serilizable
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String LINE_SEPERATOR = "::";
	//Absolute path to the directory that the data files are stored
    public static final String RESOURCE_PATH = "src/main/resources/mllib/"; 
    //this data is in this UserID::MovieID::Rating::Time-stamp format, for instagram it will be followers as example
    public static final String RATINGS_FILE_NAME = "ratings.dat";
    //this data is in MovieID::Title::Genres format, for instagram it will be users as example
    public static final String PRODUCTS_FILE_NAME = "movies.dat";
    public static final String APP_NAME = "BigDataAnalysis";
    public static final String CLUSTER = "local[2]";
	protected static JavaSparkContext sc;
	
	public static void main(String[] args) throws Exception{
		
		/**
		 * Set up environment
		 * Setting Winutil property is only valid for Windows machine
		 * for Linux OS we dont need it
		 */
		String exePath = "src/main/resources/WinUtils/";
		File exeFile = new File(exePath);
		System.setProperty("hadoop.home.dir", exeFile.getAbsolutePath());
		
		/*
		 * Setting up log levels
		 * for checking information asynchronously for warn, debugging and other info clearly
		 */
        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);
        
		//Initializing Spark Context
		SparkConf conf = new SparkConf().setAppName(APP_NAME).setMaster(CLUSTER);
		sc = new JavaSparkContext(conf);
	
		/*
		 * Reading External Data
		 * Here we we put rating/likes and product/movie data for later train
		 */
		JavaRDD<String> ratingData = sc.textFile(RESOURCE_PATH + RATINGS_FILE_NAME);
		JavaRDD<String> productData = sc.textFile(RESOURCE_PATH + PRODUCTS_FILE_NAME);
		
		JavaRDD<Tuple2<Integer, Rating>> ratings = ratingData.map(
		        new Function<String, Tuple2<Integer, Rating>>() {
					private static final long serialVersionUID = 1L;
					public Tuple2<Integer, Rating> call(String s) throws Exception {
		                String[] row = s.split(LINE_SEPERATOR);
		                Integer cacheStamp = Integer.parseInt(row[3]) % 10;
		                Rating rating = new Rating(Integer.parseInt(row[0]), Integer.parseInt(row[1]), Double.parseDouble(row[2]));
		                return new Tuple2<Integer, Rating>(cacheStamp, rating);
		            }
		        }
		);
	 
	    //Read in movie ids and titles, collect them into a movie id to title map.	
		Map<Integer, String> products = productData.mapToPair(
		        new PairFunction<String, Integer, String>() {
					private static final long serialVersionUID = 1L;
					public Tuple2<Integer, String> call(String s) throws Exception {
		                String[] sarray = s.split(LINE_SEPERATOR);
		                return new Tuple2<Integer, String>(Integer.parseInt(sarray[0]), sarray[1]);
		            }
		        }
		).collectAsMap();	
		
		/*
		 * Rating summary of ratings data
		 */
		long ratingCount = ratings.count();
		long userCount = ratings.map(
		        new Function<Tuple2<Integer, Rating>, Object>() {
					private static final long serialVersionUID = 1L;
					public Object call(Tuple2<Integer, Rating> tuple) throws Exception {
		                return tuple._2().user();
		            }
		        }
		).distinct().count();

		long movieCount = ratings.map(
		        new Function<Tuple2<Integer, Rating>, Object>() {
					private static final long serialVersionUID = 1L;
					public Object call(Tuple2<Integer, Rating> tuple) throws Exception {
		                return tuple._2().product();
		            }
		        }
		).distinct().count();
		
		System.out.println("Got " + ratingCount + " ratings from "+ userCount + " users on " + movieCount + " products.");
        
		/* Splitting training data as:
		 * Training: for train multiple model
		 * Validation: for select the best model by RMSE(Root Mean Squared Error)
		 * Test : for evaluate the best model on test set
		 * then cache them so we could use it several time just by loading it
		 */
		int numPartitions = 10;
		//Training data set
		JavaRDD<Rating> training = ratings.filter(
		        new Function<Tuple2<Integer, Rating>, Boolean>() {
					private static final long serialVersionUID = 1L;
					public Boolean call(Tuple2<Integer, Rating> tuple) throws Exception {
		                return tuple._1() < 6;
		            }
		        }
		).map(
		        new Function<Tuple2<Integer, Rating>, Rating>() {
					private static final long serialVersionUID = 1L;
					public Rating call(Tuple2<Integer, Rating> tuple) throws Exception {
		                return tuple._2();
		            }
		        }
		).repartition(numPartitions).cache();

		StorageLevel storageLevel = new StorageLevel();
		
		//Validation data set
		JavaRDD<Rating> validation = ratings.filter(
		        new Function<Tuple2<Integer, Rating>, Boolean>() {
					private static final long serialVersionUID = 1L;
					public Boolean call(Tuple2<Integer, Rating> tuple) throws Exception {
		                return tuple._1() >= 6 && tuple._1() < 8;
		            }
		        }
		).map(
		        new Function<Tuple2<Integer, Rating>, Rating>() {
					private static final long serialVersionUID = 1L;
					public Rating call(Tuple2<Integer, Rating> tuple) throws Exception {
		                return tuple._2();
		            }
		        }
		).repartition(numPartitions).persist(storageLevel);

		//Test data set
		JavaRDD<Rating> test = ratings.filter(
		        new Function<Tuple2<Integer, Rating>, Boolean>() {
					private static final long serialVersionUID = 1L;
					public Boolean call(Tuple2<Integer, Rating> tuple) throws Exception {
		                return tuple._1() >= 8;
		            }
		        }
		).map(
		        new Function<Tuple2<Integer, Rating>, Rating>() {
					private static final long serialVersionUID = 1L;
					public Rating call(Tuple2<Integer, Rating> tuple) throws Exception {
		                return tuple._2();
		            }
		        }
		).persist(storageLevel);

		long numTraining = training.count();
		long numValidation = validation.count();
		long numTest = test.count();
		
		System.out.println("After Spliting Data we have three Non-Overlapping subset of training parameter");
		System.out.println("Training set: " + numTraining);
		System.out.println("Validation set: " + numValidation);
		System.out.println("Test set: " + numTest);

        /*
         * Training the Model based on these sets of data
         * Using ALS.train we will train some models and find the best one
         * ALS best parameter we need are:
         * Rank:rank is the number of latent factors in the model.,
         * Lambda(lambda specifies the regularization parameter in ALS)&	
         * numIterations:iterations is the number of iterations to run. Here its also count as user id					
         */  
	    int rank = 10;
	    int numIterations = 2;
	    MatrixFactorizationModel model = ALS.train(JavaRDD.toRDD(training), rank, numIterations, 0.01);
	    
		/*
		 * (RMSE)Computing Root Mean Square Error in the Test data set
		 */
		Double testRmse = RMSEcomputation.computeRMSE(model, test);
        RDD<Tuple2<Object, double[]>> features = model.productFeatures();
        System.out.println("Model is saving now....");
        System.out.println("Checking Path for model metadata and features....");
        
        //Configuration should contain reference to your namenode
        FileSystem fs = FileSystem.get(new Configuration());
        // True stands for recursively deleting the folder you gave
        fs.delete(new Path(RESOURCE_PATH + "/model"), true);
        fs.delete(new Path(RESOURCE_PATH + "features"), true);

        model.save(sc.sc(), RESOURCE_PATH + "/model");
        features.saveAsTextFile(RESOURCE_PATH + "features");

		System.out.println("The Computation Result:");
		System.out.println("The Model was trained with rank = " + rank);
		System.out.println("and Lambda = 0.01");
		System.out.println(", and number of Iterations = " + numIterations);
		System.out.println("So, the RMSE(Root Mean Square Error) on the test set is " + testRmse + ".");
        
		System.out.println("Loading model from saved one....");
        model = MatrixFactorizationModel.load(sc.sc(), RESOURCE_PATH + "/model");

		//get 50s best recommendation from GetRecommendation
		List<Rating> listOfRecoomendations = GetRecommendation.getRecommendations(numIterations, model, ratings, products);
		System.out.println("Predictive Data list results: " + listOfRecoomendations);
		
		/*
		 *  Recommendations for Best 50s best rating movies for user by userid
		 */
		System.out.println("Best Recommended 50s Movies by user Ratings");
        for (Rating recommendation : listOfRecoomendations) {
            if (products.containsKey(recommendation.product())) {
                System.out.println(recommendation.product() + " " + products.get(recommendation.product()));
            }
        }
		sc.stop();
	
	}
}