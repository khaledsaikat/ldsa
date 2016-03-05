package de.due.ldsa.bd;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains analysis results
 * 
 * @author Khaled Hossain
 */
public class ResultContainer {
	private static ResultContainer instance = null;
	private List<String> results = new ArrayList<String>();

	/**
	 * Get singleton instance
	 */
	public static ResultContainer getInstance() {
		if (instance == null) {
			instance = new ResultContainer();
		}
		return instance;
	}

	/**
	 * Private constructor singleton object.
	 */
	private ResultContainer() {
	}

	public List<String> getResults() {
		return results;
	}

	public void setResults(List<String> results) {
		this.results.addAll(results);
	}

}
