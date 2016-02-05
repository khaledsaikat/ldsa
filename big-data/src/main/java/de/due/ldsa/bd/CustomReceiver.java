package de.due.ldsa.bd;

import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.receiver.Receiver;
import java.util.List;

/**
 * CustomReceiver class that collect data for Streaming. This class is calling
 * by JavaStreamingContext.receiverStream() method.
 * 
 * @author Khaled Hossain
 */
public class CustomReceiver extends Receiver<List<?>> {
	private static final long serialVersionUID = -1187714361055563697L;

	/**
	 * Set StorageLevel.
	 */
	public CustomReceiver() {
		super(StorageLevel.MEMORY_AND_DISK_2());
	}

	/**
	 * This method will run every time on start receiving data.
	 */
	public void onStart() {
		new Thread() {
			@Override
			public void run() {
				receive();
			}
		}.start();
	}

	/**
	 * The method is an abstract method, that we should implement. The method
	 * get called on stop.
	 */
	public void onStop() {
		//DataProvider.getInstance().empty();
	}

	/**
	 * Get data from DataSource
	 */
	private void receive() {
		try {
			List<?> data = DataProvider.getInstance().getListSourceData();
			if (data != null) {
				store(data);			
			}
			restart("Restarting");
		} catch (Exception e) {
			restart("Trying to connect again", e);
		}
	}
}