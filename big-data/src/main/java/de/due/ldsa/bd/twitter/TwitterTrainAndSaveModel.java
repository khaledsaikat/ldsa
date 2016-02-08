package de.due.ldsa.bd.twitter;

/**
 * A class containing main method to train and save KMeans model to project
 * resources for later analysis
 * 
 * (This is the sample class which is implemented to run machine learning
 * analysis using Spark Twitter API. So, it serves as a part of separate
 * application and its not directly a part of the project in integration.
 * Therefore, "Unit Testing" and "Exception Handling" is not done for this
 * class)
 * 
 * @author Abdul Qadir
 * @version 1.0
 */
public class TwitterTrainAndSaveModel {

	public static void main(String[] args) throws Exception {
		/**
		 * Setting Winutil property is only valid for Windows machine
		 */
		Helper.setProperty();

		try {
			OfflineTwitter.Offline();
			OfflineTwitter.trainAndSaveModel();
		} catch (Exception e) {
			System.out.println("Please first execute CollectTweets program");
		}

	}

}
