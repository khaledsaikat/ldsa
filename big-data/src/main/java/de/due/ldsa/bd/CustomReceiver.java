package de.due.ldsa.bd;

import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.receiver.Receiver;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CustomReceiver class that collect data for Streaming. This class is calling
 * by JavaStreamingContext.receiverStream() method.
 * 
 * @author Khaled Hossain
 */
public class CustomReceiver extends Receiver<Object> {
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
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			receive();
		});
		try {
			executor.shutdown();
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("Tasks Interrupted");
		} finally {
			executor.shutdownNow();
		}
	}

	/**
	 * The method is an abstract method, that we should implement. The method
	 * get called on stop.
	 */
	public void onStop() {
		// DataProvider.getInstance().empty();
	}

	/**
	 * Get data from DataSource
	 */
	private void receive() {
		try {
			List<?> data = DataProvider.getInstance().getListSourceData();
			if (data != null) {
				for (Object single : data) {
					store(single);
				}
			}
			restart("Restarting");
		} catch (Exception e) {
			restart("Trying to connect again", e);
		}
	}
}