/*
 * Copyright 2011 Thomas Golden
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	
	private static final int NO_FILE_DATA = 606;
	private static final int NO_FILE_SUMMARY = 604;
	private static final int NO_FILE_NAME = 603;
	private static final int INVALID_MIME = 601;
	private static final int ATTACHMENT_TOO_LARGE = 600;
	private static final int INVALID_USER = 504;
	private static final int BAD_CREDENTIALS = 300;
	private static final int MODIFY_PERMISSION_DENIED = 115;
	private static final int EDIT_PERMISSION_DENIED = 108;
	private static final int NO_SUMMARY = 107;
	private static final int INVALID_PRODUCT = 106;
	private static final int NO_COMPONENT = 105;
	private static final int INVALID_FIELD = 104;
	private static final int INVALID_ALIAS = 103;
	private static final int VIEW_PERMISSION_DENIED = 102;
	private static final int NONEXISTENT_BUG = 101;
	private static final int NUM_NOT_POSITIVE = 55;
	private static final int NUM_TOO_LARGE = 54;
	private static final int NAN = 52;
	private static final int INVALID_FIELD_VALUE = 51;
	private static final int EMPTY_FIELD = 50;

	/**
	 * Private constructor to prevent instantiation.
	 */
	private XmlExceptionHandler() { }
	
	private static final Map<Integer, String> FAULT_CODES = new HashMap<Integer, String>();
	
	static {
		FAULT_CODES.put(EMPTY_FIELD, "You attempted to set a field as empty which must contain a value");
		FAULT_CODES.put(INVALID_FIELD_VALUE, "You attempted to supply a field value that is not valid");
		FAULT_CODES.put(NAN, "You attempted to supply a value which is not a number for a numerical field");
		FAULT_CODES.put(NUM_TOO_LARGE, "You supplied a numerical field which is too large");
		FAULT_CODES.put(NUM_NOT_POSITIVE, "You supplied a numerical field which is negative");
		FAULT_CODES.put(NONEXISTENT_BUG, "The bug you attempted to retrieve does not exist");
		FAULT_CODES.put(VIEW_PERMISSION_DENIED, "You do not have permission to view this bug (Are you logged in?)");
		FAULT_CODES.put(INVALID_ALIAS, "The alias you specified is invalid");
		FAULT_CODES.put(INVALID_FIELD, "One of the fields you specified for this bug is invalid");
		FAULT_CODES.put(NO_COMPONENT, "No component was specified");
		FAULT_CODES.put(INVALID_PRODUCT, "The specified product is invalid");
		FAULT_CODES.put(NO_SUMMARY, "No summary was specified");
		FAULT_CODES.put(EDIT_PERMISSION_DENIED, "You do not have permission to edit this bug");
		FAULT_CODES.put(MODIFY_PERMISSION_DENIED, "You do not have permission to modify this bug");
		FAULT_CODES.put(INVALID_USER, "An invalid user was specified");
		FAULT_CODES.put(ATTACHMENT_TOO_LARGE, "The submitted attachment was too large");
		FAULT_CODES.put(INVALID_MIME, "The MIME type specified was invalid");
		FAULT_CODES.put(NO_FILE_NAME, "You did not specify a file name");
		FAULT_CODES.put(NO_FILE_SUMMARY, "You did not specify a file summary");
		FAULT_CODES.put(NO_FILE_DATA, "You did not specify any file data");
		FAULT_CODES.put(BAD_CREDENTIALS, "Your credentials are not valid; check the account exists");
	}

	/**
	 * Translates a {@link XmlRpcException} into a subclass of {@link BugzillaException}
	 * @param exception An exception wrapping a known fault code of Bugzilla's interface
	 * @return A subclass of {@code BugzillaException}
	 */
	public static BugzillaException handleFault(XmlRpcException exception) {
		
		String message = FAULT_CODES.get(exception.code);
		if(message == null) { 
			message = "An unknown error was encountered; fault code: " + exception.code; 
		}
		return new BugzillaException(message, exception);
	}
	
}
