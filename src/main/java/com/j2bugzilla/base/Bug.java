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

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.j2bugzilla.base.Flag.Status;


/**
 * This class encapsulates all information about a bug report posted on a Bugzilla installation.
 * It provides getter methods for various properties, such as the bug summary and status. To
 * obtain a new {@code Bug} object, you must use the {@link BugFactory} class which provides a fluent
 * interface for bug creation. Note that the {@code BugFactory} does not submit a report for you --
 * to actually add the created bug to your Bugzilla installation, you must use the {@link ReportBug}
 * method.
 * 
 * @author Tom
 *
 */
public class Bug {
	
	/**
	 * The keys which <em>must</em> have non-blank values for a bug to be properly submitted
	 */
	private static String[] requiredKeys = {"product", "component", "summary", "version"};

	/**
	 * HashMap representing fields for each Bug. The Value for each Key is a <code>String</code>
	 * <em>except</em> for the CC: field, which is an array of <code>Strings</code>. 
	 */
	private Map<String, Object> internalState;
	
	/**
	 * Constructor for creating a new {@link Bug} to submit to an installation.
	 * The constructor ensures any required values in {@link #requiredKeys} are set, and throws
	 * an {@link IllegalStateException} if they are null.
	 * 
	 * @param state A <code>Map</code> pairing required keys to values
	 */
	Bug(Map<String, Object> state) {
		checkRequiredFields(state);
		internalState = state;
	}
	
	/**
	 * Internal method for determining whether a given <code>HashMap</code> is a valid
	 * representation of a {@link Bug} or not.
	 * @param state a collection of String keys and String values in a <code>HashMap</code>
	 * @throws IllegalStateException If a required key-value pair is null
	 */
	private static void checkRequiredFields(Map<String, Object> state) {
		for(String str : requiredKeys) {
			if(!state.containsKey(str)) {
				throw new IllegalStateException("Missing key/value pair: " + str);
			}
		}
	}

	/**
	 * Returns how highly this bug is ranked. Since this field can be edited between installations, you may wish to
	 * {@link com.j2bugzilla.rpc.GetLegalValues check its legal values}.
	 * @return a {@code String} describing the relative importance of this bug
	 */
	public String getPriority() {
		return (String)internalState.get("priority");
	}
	
	/**
	 * Returns How severe the bug is, or whether it's an enhancement. Since this field can be edited between installations, you may wish to
	 * {@link com.j2bugzilla.rpc.GetLegalValues check its legal values}.
	 * @return a {@code String} describing the relative severity of this bug
	 */
	public String getSeverity() {
		return (String)internalState.get("severity");
	}
	
	/**
	 * Returns the internal Bugzilla ID number for this bug. If it is not in the 
	 * Bugzilla database, this will return null.
	 * @return integer ID
	 */
	public int getID() {
		return (Integer)internalState.get("id");
	}
	
	/**
	 * Returns the unique alias of this {@link Bug}. If none is set, this method will return null.
	 * @return A {@code String} representing the unique alias for this bug.
	 */
	public String getAlias() {
		return (String)internalState.get("alias");
	}
	
	/**
	 * Sets the alias of this {@link Bug}. By default, Bugzilla restricts aliases to be 20 characters in length.
	 * @param alias A {@code String} representing a unique alias for this bug.
	 */
	public void setAlias(String alias) {
		internalState.put("alias", alias);
	}
	
	/**
	 * Returns the one-line summary included with the original bug report.
	 * @return A {@code String} representing the summary entered for this {@link Bug}.
	 */
	public String getSummary() {
		return (String)internalState.get("summary");
	}
	
	/**
	 * Sets the one-line summary of this {@link Bug}.
	 * @param summary A {@code String} representing the summary describing this bug.
	 */
	public void setSummary(String summary) {
		internalState.put("summary", summary);
	}
	
	/**
	 * Returns the product this {@link Bug} belongs to.
	 * @return the Product category this {@link Bug} is filed under.
	 */
	public String getProduct() {
		return (String)internalState.get("product");
	}
	
	/**
	 * Sets the product this {@link Bug} is associated with. Note that a nonexistent product name will result in an
	 * error from Bugzilla upon bug submission.
	 * @param product A {@code String} representing the product name.
	 */
	public void setProduct(String product) {
		internalState.put("product", product);
	}
	
	/**
	 * Returns the component this {@link Bug} is associated with.
	 * @return the component of the {@link Bug}'s parent Product
	 */
	public String getComponent() {
		return (String)internalState.get("component");
	}
	
	/**
	 * Sets the component this {@link Bug} is associated with. Note that a nonexistent component name will result
	 * in Bugzilla returning an error upon submission. Since this field can be edited between installations, you may wish to
	 * {@link com.j2bugzilla.rpc.GetLegalValues check its legal values}.
	 * @param component A {@code String} representing the component name.
	 */
	public void setComponent(String component) {
		internalState.put("component", component);
	}
	
	/**
	 * Returns the version number of the product this {@link Bug} is associated with.
	 * @return the version associated with this {@link Bug}
	 */
	public String getVersion() {
		return (String)internalState.get("version");
	}
	
	/**
	 * Sets the version number of the product this {@link Bug} is associated with. Note that a nonexistent version
	 * number will result in Bugzilla returning an error on submission. Since this field can be edited between installations, you may wish to
	 * {@link com.j2bugzilla.rpc.GetLegalValues check its legal values}.
	 * @param version A {@code String} describing the version number of the product affected by this bug.
	 */
	public void setVersion(String version) {
		internalState.put("version", version);
	}
	
	/**
	 * Returns the status of this {@link Bug} indicating whether it is open or closed.
	 * @return A {@code String} representing the status of a {@link Bug}.
	 */
	public String getStatus() {
		return (String) internalState.get("status");
	}
	
	/**
	 * Sets the status of this {@link Bug} indicating whether it is open or closed. Since this field can be edited between installations, you may wish to
	 * {@link com.j2bugzilla.rpc.GetLegalValues check its legal values}.
	 * 
	 * If changing a bug's state from closed to open, the resolution must also be reset. Otherwise, the remote
	 * Bugzilla installation will throw an error. Clients must manually call {@link #clearResolution()}. Since the status
	 * and resolution fields are customizable, this library cannot safely determine when to call this method automatically,
	 * so managing the state of the bug is the responsibility of the caller.
	 * @param status A {@code String} representing the status of this bug.
	 * @see {@link #setResolution(String)}
	 */
	public void setStatus(String status) {
		internalState.put("status", status);
	}
	
	/**
	 * Returns the resolution of this {@link Bug} if it is closed, or null if it is still open.
	 * @return A {@code String} representing the resolution of a {@link Bug}.
	 * @see {@link @link com.j2bugzilla.rpc.GetLegalValues GetLegalValues} to retrieve a list of the defined resolutions for a specific installation.
	 */
	public String getResolution() {
		return (String) internalState.get("resolution");
	}
	
	/**
	 * Sets the resolution of this {@link Bug}. Since this field can be edited between installations, you may wish to
	 * {@link com.j2bugzilla.rpc.GetLegalValues check its legal values}.
	 * 
	 * If the status does not correspond to a closed value, this value is meaningless.
	 * Bugzilla allows the definition of custom workflows, so maintaining a correct correspondence between resolution and status is the
	 * responsibility of the caller.
	 * @param resolution A {@code String} representing the resolution of this bug.
	 * @see {@link #clearResolution()}
	 */
	public void setResolution(String resolution) {
		internalState.put("resolution", resolution);
	}
	
	/**
	 * Removes any existing resolution for this {@link Bug}.
	 * Since a resolution can only be applied to a closed bug, depending on the workflow defined by the particular
	 * Bugzilla installation, changing a bug's status from closed to open requires the caller also invoke this method.
	 * Because of this customization capability, it is impossible to safely determine automatically when to clear
	 * a set resolution.
	 */
	public void clearResolution() {
		internalState.remove("resolution");
	}
	
	/**
	 * Returns the operating system this bug was discovered to affect. 
	 * @return A {@code String} representing the name of the affected operating system.
	 */
	public String getOperatingSystem() {
		return (String) internalState.get("op_sys");
	}
	
	/**
	 * Sets the operating system this {@link Bug} was discovered to affect. Since this field can be edited between installations, you may wish to
	 * {@link com.j2bugzilla.rpc.GetLegalValues check its legal values}.
	 * @param os A {@code String} representing the operating system name.
	 */
	public void setOperatingSystem(String os) {
		internalState.put("op_sys", os);
	}
	
	/**
	 * Returns the hardware platform this bug was discovered to affect. Since this field can be edited between installations, you may wish to
	 * {@link com.j2bugzilla.rpc.GetLegalValues check its legal values}.
	 * @return A {@code String} representing the name of the affected platform.
	 */
	public String getPlatform() {
		return (String) internalState.get("platform");
	}
	
	/**
	 * Sets the platform affected by this {@link Bug}. Since this field can be edited between installations, you may wish to
	 * {@link com.j2bugzilla.rpc.GetLegalValues check its legal values}.
	 * @param platform A {@code String} representing the platform name.
	 */
	public void setPlatform(String platform) {
		internalState.put("platform", platform);
	}
	
	/**
	 * Returns the {@code Set} of all {@link Flag Flags} recorded for this {@link Bug}.
	 * @return A collection of {@code Flags} recorded by the Bugzilla installation against this {@code Bug}.
	 */
	public Set<Flag> getFlags() {
		Object[] flagObjs = (Object[])internalState.get("flags");
		Set<Flag> flags = new HashSet<Flag>();
		for(Object obj : flagObjs) {
			@SuppressWarnings("unchecked")
			Map<String, Object> flag = (Map<String, Object>)obj;
			String name = (String)flag.get("name");
			String status = (String)flag.get("status");
			Status s;
			if(status.equals(" ")) {
				s = Status.UNSET;
			} else if(status.equals("?")) {
				s = Status.UNKNOWN;
			} else if(status.equals("+")) {
				s = Status.POSITIVE;
			} else if(status.equals("-")) {
				s = Status.NEGATIVE;
			} else {
				throw new IllegalStateException("Unknown flag state");
			}
			flags.add(new Flag(name, s));
		}
		return Collections.unmodifiableSet(flags);
	}
	
	/**
	 * Used when a representation of this {@link Bug Bug's} internals must be passed via
	 * XML-RPC for a remote method. Regular users of this API should prefer the normal
	 * {@code getXxx()} methods.
	 * @return A read-only {@code Map} of key-value pairs corresponding to this {@code Bug's} properties.
	 */
	public Map<Object, Object> getParameterMap() {
		Map<Object, Object> params = new HashMap<Object, Object>();
		for(String key : internalState.keySet()) {
			params.put(key, internalState.get(key));
		}
		return Collections.unmodifiableMap(params);
	}
	
}
