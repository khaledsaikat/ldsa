package de.due.ldsa.bd.twitter;

/**
 * Main Class for running online streaming clustering application
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
public class StreamingTwitterMain {
	public static void main(String[] args) throws Exception {
		/**
		 * Setting Winutil property is only valid for Windows machine
		 */
		Helper.setProperty();

		/**
		 * Configuring Twitter credentials
		 */
		Helper.configureTwitterCredentials(Helper.getApiKey(), Helper.getApiSecret(), Helper.getAccessToken(),
				Helper.getAccessTokenSecret());

		try {
			StreamingTwitter app = new StreamingTwitter();
			app.run();
		} catch (Exception e) {
			System.out.println("Please first execute TwitterTrainAndSaveModel program");
		}
	}
}
