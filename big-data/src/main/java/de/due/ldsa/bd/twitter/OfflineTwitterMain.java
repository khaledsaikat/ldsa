package de.due.ldsa.bd.twitter;

/**
 * Main Class for running offline clustering application
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

public class OfflineTwitterMain {

	public static void main(String[] args) throws Exception {
		/**
		 * Setting Winutil property is only valid for Windows machine
		 */
		Helper.setProperty();

		try {
			OfflineTwitter.Offline();
			OfflineTwitter.run();
		} catch (Exception e) {
			System.out.println("Please first execute CollectTweets and TwitterTrainAndSaveModel programs");
		}
	}

}
