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

import com.j2bugzilla.base.Bug;
import com.j2bugzilla.base.BugFactory;
import com.j2bugzilla.base.BugzillaMethod;


/**
 * Allows users to retrieve a specific bug for which the ID is already known
 * 
 * @author Tom
 *
 */
public class GetBug implements BugzillaMethod {

	/**
	 * The method name for this {@link BugzillaMethod}
	 */
	private static final String GET_BUG = "Bug.get";
	
	private Map<Object, Object> hash = new HashMap<Object, Object>();
	private Map<Object, Object> params = new HashMap<Object, Object>();
	
	/**
	 * Creates a new {@link GetBug} object to retrieve the {@code Bug} specified
	 * by the ID parameter
	 * @param id An {@code int} representing the ID of an existing bug in the
	 * installation connected to
	 */
	public GetBug(int id) {
		params.put("ids", id);
	}
	
	/**
	 * Creates a new {@link GetBug} object to retrieve the {@code Bug} specified by the
	 * unique alias.
	 * @param alias A {@code String} representing a bug alias in the Bugzilla installation.
	 */
	public GetBug(String alias) {
		params.put("ids", alias);
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
	 * Retrieves the {@link com.j2bugzilla.base.Bug} corresponding to the given ID
	 * @return A {@code Bug} matching the ID, or null if the returned hash
	 * does not contain a match
	 */
	public Bug getBug() {
		Bug result = null;
		if(hash.containsKey("bugs")) {
			
			//Map<String, Object>[] bugList = (Map<String, Object>[])hash.get("bugs");
			Object[] bugs = (Object[])hash.get("bugs");
			if(bugs.length == 0) { 
				return result; //early return if map is empty
			}
			
			for(Object o : bugs) {
				@SuppressWarnings("unchecked")
				Map<String, Object> bugMap = (HashMap<String, Object>)o;
				
				if(!bugMap.containsKey("version")) {
					//version required for bugs, but older versions of Bugzilla
					//didn't return it in the outer map -- check the 'internals'
					@SuppressWarnings("unchecked")
					Map<String, Object> internals = (Map<String, Object>)bugMap.get("internals");
					String version = (String)internals.get("version");
					bugMap.put("version", version);
				}
				
				result =  new BugFactory().createBug(bugMap);
			}
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMethodName() {
		return GET_BUG;
	}

}
