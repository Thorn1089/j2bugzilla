package com.j2bugzilla.rpc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.j2bugzilla.base.BugzillaMethod;

public class AddAttachment implements BugzillaMethod {

	private static final String METHOD_NAME = "Bug.add_attachment";
	
	private Map<Object, Object> params = new HashMap<Object, Object>();
	private Map<Object, Object> hash = new HashMap<Object, Object>();
	
	@Override
	public void setResultMap(Map<Object, Object> hash) {
		this.hash = hash;
	}

	@Override
	public Map<Object, Object> getParameterMap() {
		return Collections.unmodifiableMap(params);
	}

	@Override
	public String getMethodName() {
		return METHOD_NAME;
	}

}
