package de.due.ldsa.bd;

import java.util.List;

/**
 * Data source for Streaming class.
 * 
 * Link-Data group should call DataSource.setSourceData(data) for providing data
 * to Streaming. Right now, we only accept data as sting.
 * 
 * getSourceData has been called by CustomReceiver class.
 * 
 * @author Khaled Hossain
 */
public class DataProvider implements DataSource {
	private static String stringSource;
	private static List<?> listSource;

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
}
