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
package com.j2bugzilla.rpc;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.j2bugzilla.base.BugzillaMethod;


/**
 * The <code>BugzillaVersion</code> class allows clients to query a particular
 * Bugzilla installation as to its version.
 * 
 * @author Tom
 *
 */
public class BugzillaVersion implements BugzillaMethod {
	
	/**
	 * The method Bugzilla will execute via XML-RPC
	 */
	private static final String METHOD_NAME = "Bugzilla.version";
	
	private Map<Object, Object> params = new HashMap<Object, Object>();
	private Map<Object, Object> hash = new HashMap<Object, Object>();
	
	/**
	 * Constructs a new {@link BugzillaVersion} object for querying an installation
	 * as to the version of the Bugzilla software it is running
	 */
	public BugzillaVersion() { }
	
	/**
	 * Returns a <code>String</code> representing the Bugzilla software version
	 * @return A <code>String</code> representing the Bugzilla software version
	 */
	public String getVersion() {
		if(hash.containsKey("version")) {
			return (String)hash.get("version");
		} else {
			return "";
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setResultMap(Map<Object, Object> hash) {
		this.hash = hash;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Object, Object> getParameterMap() {
		return Collections.unmodifiableMap(params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMethodName() {
		return METHOD_NAME;
	}

}
