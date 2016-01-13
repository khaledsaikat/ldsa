package de.due.ldsa.bd;

/**
 * Main Class for running offline clustering application
 * 
 * @author Abdul Qadir
 */

public class OfflineTwitterMain {

	public static void main(String[] args) throws Exception {
		/**
		 * Setting Winutil property is only valid for Windows machine
		 */
		Helper.setProperty();
		OfflineTwitter.Offline();
		OfflineTwitter.run();

	}

}
