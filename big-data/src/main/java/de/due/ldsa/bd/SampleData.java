package de.due.ldsa.bd;

import java.util.Arrays;
import java.util.List;
import de.due.ldsa.bd.analysis.CommentSample;

/**
 * This class can provide some sample data for various running analysis. The
 * class is useful when our real data is not available.
 * 
 * @author Khaled Hossain
 *
 */
public class SampleData {
	/**
	 * @return List of CommentSample object.
	 */
	public List<CommentSample> getCommentsSamples() {
		return Arrays.asList(new CommentSample("Some sample comments"),
				new CommentSample(
						"Thanks for your subscription to Ringtone. Your mobile will be charged £5/month Please confirm by replying YES or NO."),
				new CommentSample("Other sample comment"));
	}

	/**
	 * @return List of sample string
	 */
	public List<String> getTextSamples() {
		return Arrays.asList("Sample text", "Some other sample text", "One more sample text");
	}

	/**
	 * Populate List of CommentSample object into DataProvider.
	 */
	public void populateCommentsSample() {
		DataProvider.getInstance().setSourceData(getCommentsSamples());
	}

}
