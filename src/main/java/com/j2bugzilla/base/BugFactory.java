package com.j2bugzilla.base;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code BugFactory} class allows users of the j2bugzilla API to construct new {@link Bug Bugs}
 * using a fluent interface. It also provides a method for creating a new {@code Bug} based off of
 * a {@code Map} provided from an XML-RPC method.
 * @author Tom
 *
 */
public class BugFactory {
	
	/**
	 * Private {@code Map} used to hold 
	 */
	private Map<String, Object> properties;

	/**
	 * Creates a new {@link Bug} based off of the provided {@code Map} of properties.
	 * @param properties A {@code Map<String, Object>} describing the internal structure of a bug.
	 * @return A new {@code Bug} object.
	 */
	public Bug createBug(Map<String, Object> properties) {
		Map<String, Object> copy = new HashMap<String, Object>();
		for(String key : properties.keySet()) {
			copy.put(key, properties.get(key));
		}
		return new Bug(copy);
	}
	
	/**
	 * Sets up this {@link BugFactory} to produce a new {@link Bug}.
	 * This method must be called before any {@code setXxx()} methods or the
	 * {@link #createBug()} method.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory newBug() {
		if(properties != null) { throw new IllegalStateException("Already creating a new Bug!"); }
		properties = new HashMap<String, Object>();
		return this;
	}
	
	/**
	 * Sets the alias of the {@link Bug} to be created by this {@link BugFactory}.
	 * Note that by default, Bugzilla limits aliases to 20 characters.
	 * @param alias A unique alias for a bug. 
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setAlias(String alias) {
		if(properties == null) { throw new IllegalStateException("Must call newBug() first!"); }
		properties.put("alias", alias);
		return this;
	}
	
	/**
	 * Sets the operating system of the {@link Bug} to be created by this {@link BugFactory}.
	 * @param os The operating system for a bug.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setOperatingSystem(String os) {
		if(properties == null) { throw new IllegalStateException("Must call newBug() first!"); }
		properties.put("op_sys", os);
		return this;
	}
	
	/**
	 * Sets the platform of the {@link Bug} to be created by this {@link BugFactory}.
	 * @param platform The platform for a bug.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setPlatform(String platform) {
		if(properties == null) { throw new IllegalStateException("Must call newBug() first!"); }
		properties.put("platform", platform);
		return this;
	}
	
	/**
	 * Sets the priority of the {@link Bug} to be created by this {@link BugFactory}.
	 * @param priority A {@link Priority} describing the relative importance of a bug.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setPriority(String priority) {
		if(properties == null) { throw new IllegalStateException("Must call newBug() first!"); }
		properties.put("priority", priority);
		return this;
	}
	
	/**
	 * Sets the product associated with the {@link Bug} to be created by this {@link BugFactory}.
	 * @param product A product name to associate with a bug.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setProduct(String product) {
		if(properties == null) { throw new IllegalStateException("Must call newBug() first!"); }
		properties.put("product", product);
		return this;
	}
	
	/**
	 * Sets the component associated with the {@link Bug} to be created by this {@link BugFactory}.
	 * @param component A component name to associate with a bug.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setComponent(String component) {
		if(properties == null) { throw new IllegalStateException("Must call newBug() first!"); }
		properties.put("component", component);
		return this;
	}
	
	/**
	 * Sets the summary associated with the {@link Bug} to be created by this {@link BugFactory}.
	 * @param summary A one-line summary to describe a bug.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setSummary(String summary) {
		if(properties == null) { throw new IllegalStateException("Must call newBug() first!"); }
		properties.put("summary", summary);
		return this;
	}
	
	/**
	 * Sets the version of the software associated with the {@link Bug} to be created by this {@link BugFactory}.
	 * @param version A version number to associate with a bug.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setVersion(String version) {
		if(properties == null) { throw new IllegalStateException("Must call newBug() first!"); }
		properties.put("version", version);
		return this;
	}
	
	/**
	 * Sets the longer description associated with the {@link Bug} to be created by this {@link BugFactory}.
	 * @param description The description used as the initial comment on a bug.
	 * @return A reference to the original {@code BugFactory}.
	 */
	public BugFactory setDescription(String description) {
		if(properties == null) { throw new IllegalStateException("Must call newBug() first!"); }
		properties.put("description", description);
		return this;
	}
	
	/**
	 * Creates a new {@link Bug} using the properties set in this {@link BugFactory}.
	 * This method must be called after {@link #newBug()}.
	 * @return A new {@code Bug} object.
	 */
	public Bug createBug() {
		if(properties == null) { throw new IllegalStateException("Must call newBug() first!"); }
		Bug bug = new Bug(properties);
		properties = null;
		return bug;
	}
	
}
