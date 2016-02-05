package de.due.ldsa.bd;

/**
 * Configurations for the Big-Data parts.
 * 
 * @author Khaled Hossain
 */
public class Config {
	/**
	 * Name of application to show in clusters.
	 */
	public final static String appName = "BigDataAnalysis";
	
	/**
	 * Master url for driver.
	 */
	public final static String master = "local[2]";
	
	/**
	 * Interval for streaming 
	 */
	public final static Integer interval = 2;
}
