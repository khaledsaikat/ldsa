package de.due.ldsa.bd;

/**
 * Run public main method for streaming.
 * 
 * @author Khaled Hossain
 */
public class StreamingMain {
	public static void main(String[] args) {
		DataSource source = DataProvider.getInstance();
		source.setSourceData("Hello World");
		Streaming app = Streaming.getInstance();
		app.run();
	}
}