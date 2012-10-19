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
import com.j2bugzilla.base.BugzillaMethod;

/**
 * The {@code UpdateBug} class allows clients to update an existing {@link Bug} on the installation with
 * new values. Currently, this method only allows one bug at a time to be updated.
 * @author Tom
 *
 */
public class UpdateBug implements BugzillaMethod {
	
	/**
	 * The method name for this webservice operation.
	 */
	private static final String METHOD_NAME = "Bug.update";

	/**
	 * A {@link Bug} to update on the installation.
	 */
	private final Bug bug;
	
	/**
	 * Creates a new {@link UpdateBug} object to submit to the Bugzilla webservice. The {@link Bug} on the
	 * installation identified by the id or alias of the bug provided will have its fields updated 
	 * to match those of the values in the provided bug.
	 * @param bug
	 */
	public UpdateBug(Bug bug) {
		this.bug = bug;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setResultMap(Map<Object, Object> hash) {
		Object[] modified = (Object[])hash.get("bugs");
		//For now, we only modify one bug at a time, thus this array should be a single element
		assert(modified.length == 1);
		//There aren't a ton of useful elements returned, so for now just discard the map.
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Object, Object> getParameterMap() {
		return Collections.unmodifiableMap(bug.getParameterMap());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMethodName() {
		return METHOD_NAME;
	}
}
