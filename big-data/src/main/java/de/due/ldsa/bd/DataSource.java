package de.due.ldsa.bd;

import java.util.List;

/**
 * Interface for data provider.
 * 
 * @author Khaled Hossain
 */
public interface DataSource {
	/**
	 * Set string data to source.
	 * 
	 * @param data
	 */
	public void setSourceData(String data);

	/**
	 * Set List data to source.
	 * 
	 * @param data
	 */
	public void setSourceData(List<?> data);

	/**
	 * Get string data from source.
	 * 
	 * @return String
	 */
	public String getStringSourceData();

	/**
	 * Get List data from source.
	 * 
	 * @return List
	 */
	public List<?> getListSourceData();
	
	/**
	 * Make the data as empty
	 */
	public void empty();
}
