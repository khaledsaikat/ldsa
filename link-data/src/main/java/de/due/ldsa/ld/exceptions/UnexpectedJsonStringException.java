/**
 * 
 */
package de.due.ldsa.ld.exceptions;

/**
 * @author firas
 *
 */
public class UnexpectedJsonStringException extends Exception {
	private static final long serialVersionUID = 1L;

	public UnexpectedJsonStringException() {
		super();
	}

	public UnexpectedJsonStringException(String message) {
		super(message);
	}

	public UnexpectedJsonStringException(String message, Throwable cause) {
		super(message, cause);
	}
}
