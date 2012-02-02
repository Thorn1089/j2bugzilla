package com.j2bugzilla.base;

import java.util.Map;

/**
 * This interface defines the methods an object must provide to be executed
 * as a remote method on a Bugzilla installation via XML-RPC.
 * 
 * @author Tom
 *
 */
public interface BugzillaMethod {

	/**
	 * Allows the {@link BugzillaConnector} to set the returned {@link Map} for this
	 * {@link BugzillaMethod} so that its implementation can provide access
	 * to any returned data from the webservice
	 * 
	 * @param hash A {@link Map} of other <code>Objects</code> as returned by the Bugzilla
	 * webservice from the request, whether it succeeds or returns a fault
	 */
	void setResultMap(Map<Object, Object> hash);
	
	/**
	 * Allows the {@link BugzillaConnector} to access this {@link BugzillaMethod}'s
	 * parameters in a form that can be passed to the Bugzilla webservice over
	 * XML-RPC
	 * 
	 * @return A {@link Map} of <code>Objects</code> required as parameters for the Bugzilla
	 * webservice method
	 */
	Map<Object, Object> getParameterMap();
	
	/**
	 * Allows the {@link BugzillaConnector} to access this {@link BugzillaMethod}'s
	 * name to pass in the XML-RPC request along with the parameters
	 * 
	 * @return A <code>String</code> representing the appropriate webservice method
	 * to call on the Bugzilla installation
	 */
	String getMethodName();
	
}
