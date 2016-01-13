package de.due.ldsa.bd;

/**
 * Setting Windows Property and Configuring Twitter credentials
 * 
 * @author Abdul Qadir
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
		StreamingTwitter app = new StreamingTwitter();
		app.run();
	}
}
