package com.j2bugzilla.rpc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.j2bugzilla.base.BugzillaMethod;

/**
 * The {@code GetPriorityValues} {@link BugzillaMethod} allows clients to retrieve the set of valid priority items
 * for a {@link com.j2bugzilla.base.Bug Bug} in Bugzilla. Since these values can be edited, they may not be
 * consistent across all installations. If you intend to transfer a bug between installations programmatically,
 * you will need to ensure the same values are used or convert bug priorities yourself.
 * 
 * @author Tom
 *
 */
public class GetPriorityValues extends GetLegalValues {

	public Set<String> getLegalValues() {
		return super.getLegalValues();
	}

	@Override
	public Map<Object, Object> getParameterMap() {
		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("names", new String[] { "priority" });
		return Collections.unmodifiableMap(params);
	}
}
