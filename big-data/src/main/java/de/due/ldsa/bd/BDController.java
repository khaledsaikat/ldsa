package de.due.ldsa.bd;

import java.util.List;

/**
 * Controller interface for Big Data section
 * 
 * @author Khaled Hossain
 */
public interface BDController {
	/**
	 * @param analysisType
	 *            Possible value: Offline, Real-Time
	 * @param analysisName
	 *            Possible value: BC, KC
	 */
	public void analysis(String analysisType, String analysisName);

	/**
	 * Collect analysis result. For Real-Time, call this method by separate
	 * thread in a loop on every x second to collect updated result.
	 * 
	 * @return List of analysis result as string
	 */
	public List<String> getResult();
}
