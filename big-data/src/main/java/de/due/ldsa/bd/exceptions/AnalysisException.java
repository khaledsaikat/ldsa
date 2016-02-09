package de.due.ldsa.bd.exceptions;

/**
 * Exception handling for analysis.
 * 
 * @author Khaled Hossain
 *
 */
public class AnalysisException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AnalysisException() {
		super();
	}

	public AnalysisException(String message) {
		super(message);
	}

	public AnalysisException(String message, Throwable cause) {
		super(message, cause);
	}

	public AnalysisException(Throwable cause) {
		super(cause);
	}
}
