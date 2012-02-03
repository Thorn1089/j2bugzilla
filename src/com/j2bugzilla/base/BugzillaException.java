package com.j2bugzilla.base;

/**
 * A {@code BugzillaException} indicates that Bugzilla has returned a fault rather 
 * than the expected return value for a method. It wraps the 
 * {@link org.apache.xmlrpc.XmlRpcException XmlRpcException} which caused the error.
 * @author Tom
 *
 */
public class BugzillaException extends Exception {

	/**
	 * Eclipse-generated SUID
	 */
	private static final long serialVersionUID = -5427986526722263296L;

	/**
	 * Constructs a new {@link BugzillaException} with the specified summary and cause.
	 * @param message A customized error message describing the issue
	 * @param cause The nested cause, typically a {@link org.apache.xmlrpc.XmlRpcException XmlRpcException}.
	 */
	public BugzillaException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Constructs a new {@link BugzillaException} with the specified summary
	 * @param message A short, descriptive message of the error
	 */
	public BugzillaException(String message) {
		super(message);
	}
}
