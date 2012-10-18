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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The {@code BugFactory} class allows users of the j2bugzilla API to construct new {@link Bug Bugs}
 * using a fluent interface. It also provides a method for creating a new {@code Bug} based off of
 * a {@code Map} provided from an XML-RPC method.
 * 
 * The {@code BugFactory} is not thread-safe. Callers must provide their own synchronization when
 * using a {@code BugFactory} shared across multiple threads of execution.
 * @author Tom
 *
 */
public class BugFactory {
	
	private static final String CALL_NEW = "Must call newBug() first!";
	/**
	 * Private {@code Map} used to hold 
	 */
	private Map<String, Object> properties;
	
	private Set<Flag> flags;
	
	private boolean locked = false;

	/**
	 * Creates a new {@link Bug} based off of the provided {@code Map} of properties.
	 * @param properties A {@code Map<String, Object>} describing the internal structure of a bug.
	 * @return A new {@code Bug} object.
	 */
	public Bug createBug(Map<String, Object> properties) {
		Map<String, Object> copyProps = new HashMap<String, Object>();
		copyProps.putAll(properties);
		
		return new Bug(copyProps);
	}
	
	/**
	 * Sets up this {@link BugFactory} to produce a new {@link Bug}.
	 * This method must be called before any {@code setXxx()} methods or the
	 * {@link #createBug()} method.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory newBug() {
		if(locked) { throw new IllegalStateException("Already creating a new Bug!"); }
		locked = true;
		properties = new HashMap<String, Object>();
		flags = new HashSet<Flag>();
		return this;
	}
	
	/**
	 * Sets the alias of the {@link Bug} to be created by this {@link BugFactory}.
	 * Note that by default, Bugzilla limits aliases to 20 characters.
	 * @param alias A unique alias for a bug. 
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setAlias(String alias) {
		if(!locked) { throw new IllegalStateException(CALL_NEW); }
		properties.put("alias", alias);
		return this;
	}
	
	/**
	 * Sets the operating system of the {@link Bug} to be created by this {@link BugFactory}.
	 * @param os The operating system for a bug.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setOperatingSystem(String os) {
		if(!locked) { throw new IllegalStateException(CALL_NEW); }
		properties.put("op_sys", os);
		return this;
	}
	
	/**
	 * Sets the platform of the {@link Bug} to be created by this {@link BugFactory}.
	 * @param platform The platform for a bug.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setPlatform(String platform) {
		if(!locked) { throw new IllegalStateException(CALL_NEW); }
		properties.put("platform", platform);
		return this;
	}
	
	/**
	 * Sets the priority of the {@link Bug} to be created by this {@link BugFactory}.
	 * @param priority A {@link Priority} describing the relative importance of a bug.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setPriority(String priority) {
		if(!locked) { throw new IllegalStateException(CALL_NEW); }
		properties.put("priority", priority);
		return this;
	}
	
	/**
	 * Sets the product associated with the {@link Bug} to be created by this {@link BugFactory}.
	 * @param product A product name to associate with a bug.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setProduct(String product) {
		if(!locked) { throw new IllegalStateException(CALL_NEW); }
		properties.put("product", product);
		return this;
	}
	
	/**
	 * Sets the component associated with the {@link Bug} to be created by this {@link BugFactory}.
	 * @param component A component name to associate with a bug.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setComponent(String component) {
		if(!locked) { throw new IllegalStateException(CALL_NEW); }
		properties.put("component", component);
		return this;
	}
	
	/**
	 * Sets the summary associated with the {@link Bug} to be created by this {@link BugFactory}.
	 * @param summary A one-line summary to describe a bug.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setSummary(String summary) {
		if(!locked) { throw new IllegalStateException(CALL_NEW); }
		properties.put("summary", summary);
		return this;
	}
	
	/**
	 * Sets the version of the software associated with the {@link Bug} to be created by this {@link BugFactory}.
	 * @param version A version number to associate with a bug.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setVersion(String version) {
		if(!locked) { throw new IllegalStateException(CALL_NEW); }
		properties.put("version", version);
		return this;
	}
	
	/**
	 * Sets the longer description associated with the {@link Bug} to be created by this {@link BugFactory}.
	 * @param description The description used as the initial comment on a bug.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setDescription(String description) {
		if(!locked) { throw new IllegalStateException(CALL_NEW); }
		properties.put("description", description);
		return this;
	}
	
	/**
	 * Adds a custom {@link Flag} to the {@link Bug} under construction.
	 * @param flag A {@code Flag} describing bug metadata.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory addFlag(Flag flag) {
		if(!locked) { throw new IllegalStateException(CALL_NEW); }
		flags.add(flag);
		return this;
	}
	
	/**
	 * Creates a new {@link Bug} using the properties set in this {@link BugFactory}.
	 * This method must be called after {@link #newBug()}.
	 * @return A new {@code Bug} object.
	 */
	public Bug createBug() {
		if(!locked) { throw new IllegalStateException(CALL_NEW); }
		locked = false;
		@SuppressWarnings("unchecked")
		HashMap<String, Object>[] flagArray = flags.toArray(new HashMap[0]);
		properties.put("flags", flagArray);
		return new Bug(properties);
	}
	
}
