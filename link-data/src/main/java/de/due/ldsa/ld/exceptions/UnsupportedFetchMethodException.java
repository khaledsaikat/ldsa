/**
 * 
 */
package de.due.ldsa.ld.exceptions;

/**
 * @author firas
 *
 */
public class UnsupportedFetchMethodException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnsupportedFetchMethodException() {
		super();
	}

	public UnsupportedFetchMethodException(String message) {
		super(message);
	}

	public UnsupportedFetchMethodException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnsupportedFetchMethodException(Throwable cause) {
		super(cause);
	}
}
