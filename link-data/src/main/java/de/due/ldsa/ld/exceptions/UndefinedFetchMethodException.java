/**
 * 
 */
package de.due.ldsa.ld.exceptions;

/**
 * @author firas
 *
 */
public class UndefinedFetchMethodException extends Exception {

	private static final long serialVersionUID = 1L;

	public UndefinedFetchMethodException() {
		super();
	}

	public UndefinedFetchMethodException(String message) {
		super(message);
	}

	public UndefinedFetchMethodException(String message, Throwable cause) {
		super(message, cause);
	}

	public UndefinedFetchMethodException(Throwable cause) {
		super(cause);
	}
}
