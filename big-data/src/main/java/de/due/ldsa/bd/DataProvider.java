package de.due.ldsa.bd;

import java.util.List;

/**
 * Data provider for Streaming class.
 * 
 * Link-Data group should call setSourceData method for providing data to
 * Streaming.
 * 
 * getSourceData has been called by CustomReceiver class.
 * 
 * @author Khaled Hossain
 */
public class DataProvider implements DataSource {
	private static DataProvider instance = null;
	private String stringSource = null;
	private List<?> listSource;

	/**
	 * Get singleton instance
	 */
	public static DataProvider getInstance() {
		if (instance == null) {
			instance = new DataProvider();
		}
		return instance;
	}

	/**
	 * Private constructor singleton object.
	 */
	private DataProvider() {
	}

	@Override
	public void setSourceData(String data) {
		stringSource = data;
	}

	@Override
	public void setSourceData(List<?> data) {
		listSource = data;
	}

	@Override
	public String getStringSourceData() {
		return stringSource;
	}

	@Override
	public List<?> getListSourceData() {
		return listSource;
	}
	
	@Override
	public void empty() {
		stringSource = null;
		listSource = null;
	}
}
