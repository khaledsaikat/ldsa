package de.due.ldsa.bd.exceptions;

/**
 * Exception handling for spark context and data.
 * 
 * @author Khaled Hossain
 *
 */
public class SparkContextDataException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SparkContextDataException() {
		super();
	}

	public SparkContextDataException(String message) {
		super(message);
	}

	public SparkContextDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public SparkContextDataException(Throwable cause) {
		super(cause);
	}
}
