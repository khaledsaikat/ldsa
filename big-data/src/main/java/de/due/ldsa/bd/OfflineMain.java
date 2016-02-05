package de.due.ldsa.bd;

import java.util.Arrays;
import java.util.List;

import de.due.ldsa.bd.analysis.CommentSample;

/**
 * Example of running analysis on sample data.
 * 
 * @author Khaled Hossain
 */
public class OfflineMain {
	public static void main(String[] args) throws Exception {
		Helper.setProperty(); // Setting Winutil property for Windows os
		setSample();
		Offline.getInstance().run();
	}

	public static void setSample() {
		// Tracking service is not still ready, we are using sample data.
		List<CommentSample> samples = Arrays.asList(new CommentSample("Some sample comments"),
				new CommentSample(
						"Thanks for your subscription to Ringtone. Your mobile will be charged £5/month Please confirm by replying YES or NO."),
				new CommentSample("Other sample comment"));
		DataProvider.getInstance().setSourceData(samples);
	}
}