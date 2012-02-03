package com.j2bugzilla.base;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


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
	 * Enum describing the legitimate values for a Bug's priority rank. Lower
	 * numbers indicate a higher relative importance compared to other bugs.
	 * @author Tom
	 *
	 */
	public enum Priority {P1, P2, P3, P4, P5};
	
	/**
	 * Enum describing the legitimate values for a Bug's status.
	 * @author Tom
	 *
	 */
	public enum Status {NEW, ASSIGNED, RESOLVED};
	
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
	 * Returns how highly this bug is ranked
	 * @return a {@link Priority} describing the relative importance of this bug
	 */
	public Priority getPriority() {
		String p = (String)internalState.get("priority");
		int level = Integer.valueOf(p.substring(1,2));
		switch(level) {
		case 1:	return Priority.P1; 
		case 2: return Priority.P2; 
		case 3: return Priority.P3; 
		case 4: return Priority.P4; 
		case 5: return Priority.P5; 
		default: return Priority.P3;
		}
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
	 * in Bugzilla returning an error upon submission.
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
	 * number will result in Bugzilla returning an error on submission.
	 * @param version A {@code String} describing the version number of the product affected by this bug.
	 */
	public void setVersion(String version) {
		internalState.put("version", version);
	}
	
	/**
	 * Returns the {@link Status} of this {@link Bug} indicating whether it is open or closed.
	 * @return the {@link Status} of a {@link Bug}
	 */
	public Status getStatus() {
		/*
		 * Bit of a hacky solution, but it avoids switching 
		 * over the enums to match them to Strings.
		 */
		return Status.valueOf(((String)internalState.get("status")).toUpperCase());
	}
	
	/**
	 * Sets the {@link Status} of this {@link Bug} indicating whether it is open or closed.
	 * @param status The {@code Status} of this bug.
	 */
	public void setStatus(Status status) {
		internalState.put("status", status.toString());
	}
	
	/**
	 * Returns the operating system this bug was discovered to affect.
	 * @return A {@code String} representing the name of the affected operating system.
	 */
	public String getOperatingSystem() {
		return (String) internalState.get("op_sys");
	}
	
	/**
	 * Sets the operating system this {@link Bug} was discovered to affect. Note that the default values
	 * provided by Bugzilla are "All," "Mac OS," "Windows," "Linux," or "Other."
	 * @param os A {@code String} representing the operating system name.
	 */
	public void setOperatingSystem(String os) {
		internalState.put("op_sys", os);
	}
	
	/**
	 * Returns the hardware platform this bug was discovered to affect.
	 * @return A {@code String} representing the name of the affected platform.
	 */
	public String getPlatform() {
		return (String) internalState.get("platform");
	}
	
	/**
	 * Sets the platform affected by this {@link Bug}. Note that the default values provided by Bugzilla are
	 * "All," "Macintosh," "PC," or "Other."
	 * @param platform A {@code String} representing the platform name.
	 */
	public void setPlatform(String platform) {
		internalState.put("platform", platform);
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
