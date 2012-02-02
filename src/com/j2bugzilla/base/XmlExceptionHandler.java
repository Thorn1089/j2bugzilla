package com.j2bugzilla.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;

/**
 * The {@code XmlExceptionHandler} provides a static utility method for translating
 * generic {@link XmlRpcException}s into subclasses of {@link BugzillaException}
 * to be handled by the client code.
 * 
 * @author Tom
 */
public final class XmlExceptionHandler {
	
	/**
	 * Private constructor to prevent instantiation.
	 */
	private XmlExceptionHandler() { }
	
	private static final Map<Integer, String> FAULT_CODES = new HashMap<Integer, String>();
	
	{
		FAULT_CODES.put(50, "You attempted to set a field as empty which must contain a value");
		FAULT_CODES.put(51, "The component you specified is not valid for this product");
		FAULT_CODES.put(52, "You attempted to supply a value which is not a number for a numerical field");
		FAULT_CODES.put(54, "You supplied a numerical field which is too large");
		FAULT_CODES.put(55, "You supplied a numerical field which is negative");
		FAULT_CODES.put(101, "The bug you attempted to retrieve does not exist");
		FAULT_CODES.put(102, "You do not have permission to view this bug (Are you logged in?)");
		FAULT_CODES.put(103, "The alias you specified is invalid");
		FAULT_CODES.put(104, "One of the fields you specified for this bug is invalid");
		FAULT_CODES.put(105, "No component was specified");
		FAULT_CODES.put(106, "The specified product is invalid");
		FAULT_CODES.put(107, "No summary was specified");
		FAULT_CODES.put(108, "You do not have permission to edit this bug");
		FAULT_CODES.put(115, "You do not have permission to modify this bug");
		FAULT_CODES.put(504, "An invalid user was specified");
	}

	/**
	 * Translates a {@link XmlRpcException} into a subclass of {@link BugzillaException}
	 * @param exception An exception wrapping a known fault code of Bugzilla's interface
	 * @return A subclass of {@code BugzillaException}
	 */
	public static BugzillaException handleFault(XmlRpcException exception) {
		
		String message = FAULT_CODES.get(exception.code);
		if(message == null) { message = "An unknown error was encountered"; }
		return new BugzillaException(message, exception);
	}
	
}
