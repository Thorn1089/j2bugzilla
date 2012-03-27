package com.j2bugzilla.rpc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.j2bugzilla.base.Bug;

/**
 * The {@code GetStatusValues} class is a helper method which wraps the {@code Bug.fields} webservice call, to
 * provide clients a list of legal status values from their installation of Bugzilla. Due to the workflow change
 * in Bugzilla 4.0, different installations may have different values. Thus, this method allows clients to
 * work against a predetermined correct set.
 * 
 * @author Tom
 *
 */
public class GetStatusValues extends GetLegalValues {


	/**
	 * Returns a {@code Set} of {@code Strings} representing the legal values for the status of a {@link Bug}.
	 * @return A set of values for a bug's status field.
	 */
	public Set<String> getLegalValues() {
		return super.getLegalValues();
	}

	@Override
	public Map<Object, Object> getParameterMap() {
		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("names", new String[] { "bug_status" });
		return Collections.unmodifiableMap(params);
	}
}
