package com.j2bugzilla.rpc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.j2bugzilla.base.BugzillaMethod;

public class GetStatusValues implements BugzillaMethod {

	private static final String METHOD_NAME = "Bug.fields";
	
	@Override
	public void setResultMap(Map<Object, Object> hash) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<Object, Object> getParameterMap() {
		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("names", new String[] { "status" });
		return Collections.unmodifiableMap(params);
	}

	@Override
	public String getMethodName() {
		return METHOD_NAME;
	}

}
