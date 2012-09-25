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
	 * webservice from the request, whether it succeeds or returns a fault. This {@code Map}
	 * is considered immutable and cannot be written to, only read from.
	 */
	void setResultMap(Map<Object, Object> hash);
	
	/**
	 * Allows the {@link BugzillaConnector} to access this {@link BugzillaMethod}'s
	 * parameters in a form that can be passed to the Bugzilla webservice over
	 * XML-RPC.
	 * 
	 * @return A {@link Map} of <code>Objects</code> required as parameters for the Bugzilla
	 * webservice method. This {@code Map} is considered immutable and cannot be written to, only read from.
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
