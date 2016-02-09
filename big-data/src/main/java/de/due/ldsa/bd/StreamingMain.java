package de.due.ldsa.bd;

/**
 * Example of running analysis on streaming sample data.
 * 
 * @author Khaled Hossain
 */
public class StreamingMain {

	public static void main(String[] args) throws Exception {
		new SampleData().populateCommentsSample();
		Streaming.getInstance().run();
	}

}