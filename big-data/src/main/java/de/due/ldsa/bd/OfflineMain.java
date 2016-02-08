package de.due.ldsa.bd;

/**
 * Example of running analysis on sample data.
 * 
 * @author Khaled Hossain
 */
public class OfflineMain {
	
	public static void main(String[] args) throws Exception {
		new SampleData().populateCommentsSample();
		Offline.getInstance().run();
	}
	
}