package com.j2bugzilla.rpc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.j2bugzilla.base.BugzillaMethod;

public abstract class GetLegalValues implements BugzillaMethod {

	private static final String METHOD_NAME = "Bug.fields";
	
	private Set<String> legalValues = Collections.emptySet();
	
	public Set<String> getLegalValues() {
		return legalValues;
	}
	
	@Override
	public void setResultMap(Map<Object, Object> hash) {
		legalValues = new HashSet<String>();
		
		Object[] array = (Object[]) hash.get("fields");
		@SuppressWarnings("unchecked")//Cast to structure defined by webservice
		Map<Object, Object> fields = (Map<Object, Object>) array[0];
		
		Object[] values = (Object[]) fields.get("values");
		for(Object obj : values) {
			@SuppressWarnings("unchecked")//Cast to structure defined by webservice
			Map<Object, Object> map = (Map<Object, Object>) obj;
			String name = (String) map.get("name");
			legalValues.add(name);
		}
	}

	@Override
	public String getMethodName() {
		return METHOD_NAME;
	}

}
