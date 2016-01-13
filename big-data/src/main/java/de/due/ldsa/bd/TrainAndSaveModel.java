package de.due.ldsa.bd;

/**
 * A class containing main method to train and save KMeans model to project
 * resources for later analysis
 * 
 * @author Abdul Qadir
 *
 */
public class TrainAndSaveModel {

	public static void main(String[] args) throws Exception {
		/**
		 * Setting Winutil property is only valid for Windows machine
		 */
		Helper.setProperty();
		OfflineTwitter.Offline();
		OfflineTwitter.trainAndSaveModel();

	}

}
