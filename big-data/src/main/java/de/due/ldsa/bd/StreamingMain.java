package de.due.ldsa.bd;

import java.util.Arrays;
import java.util.List;
import de.due.ldsa.bd.analysis.CommentSample;

/**
 * Example of running analysis on streaming sample data.
 * 
 * @author Khaled Hossain
 */
public class StreamingMain {
	public static void main(String[] args) {
		DataProvider.getInstance().setSourceData(getSample());
		Streaming.getInstance().run();
	}
	
	private static List<CommentSample> getSample() {
		return Arrays.asList(new CommentSample(
				"Thanks for your subscription to Ringtone. Your mobile will be charged £5/month Please confirm by replying YES or NO."));
	}
}