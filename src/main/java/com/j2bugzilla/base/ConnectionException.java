package com.j2bugzilla.base;

/**
 * <code>ConnectionException</code> is thrown any time there is an error trying to access
 * the Bugzilla installation over the network. It may be a network-related problem such as
 * a timeout or malformed URL, or it may be an underlying XML-RPC exception, but it
 * generally indicates that any further Bugzilla calls will fail.
 * 
 * ConnectionException will always be a wrapper for a nested <code>Exception</code> which
 * indicates the cause of the error.
 * @author Tom
 *
 */
public class ConnectionException extends Exception {

	/**
	 * Eclipse-generated SUID
	 */
	private static final long serialVersionUID = 2957676868743832929L;

	/**
	 * Public constructor which calls super()
	 * @param message A custom error message describing the issue
	 * @param cause The root cause of the exception
	 */
	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
