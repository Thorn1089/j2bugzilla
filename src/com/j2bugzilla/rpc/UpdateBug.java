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
		Map<Object, Object> params = new HashMap<Object, Object>();
		
		Map<Object, Object> internals = bug.getParameterMap();
		
		params.put("ids", bug.getID());
		
		params.put("alias", internals.get("alias"));
		params.put("assigned_to", internals.get("assigned_to"));
		params.put("component", internals.get("component"));
		params.put("op_sys", internals.get("op_sys"));
		params.put("platform", internals.get("platform"));
		params.put("priority", internals.get("priority"));
		params.put("product", internals.get("product"));
		params.put("status", internals.get("status"));
		params.put("summary", internals.get("summary"));
		params.put("version", internals.get("version"));
		
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
