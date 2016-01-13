package de.due.ldsa.bd;

import java.util.Arrays;
import java.util.List;

/**
 * Run public main method for offline.
 * 
 * @author Khaled Hossain
 */
public class OfflineMain {
	public static void main(String[] args) throws Exception {
		/**
		 * Setting Winutil property is only valid for Windows machine
		 */
		Helper.setProperty();
		
		List<String> items = Arrays.asList("Hello World1", "Hello World2", "Hello World3");
		DataProvider source = new DataProvider();
		source.setSourceData(items);
		Offline app = new Offline();
		app.run();
	}
}