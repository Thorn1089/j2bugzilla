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
package com.j2bugzilla.rpc;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.j2bugzilla.base.Bug;
import com.j2bugzilla.base.BugzillaMethod;
import com.j2bugzilla.base.Product;

/**
 * The {@code GetLegalValues} class allows clients to query their installation for information on the allowed values for fields
 * in bug reports, which may be edited to be installation-specific. For example, the default workflow has changed from Bugzilla 3.x
 * to 4.x. Additionally, custom priority and severity values may be defined.
 * @author Tom
 *
 */
public class GetLegalValues implements BugzillaMethod {
	
	private static final String METHOD_NAME = "Bug.fields";
	
	private final Map<Object, Object> params = new HashMap<Object, Object>();
	
	/**
	 * The {@code Fields} enum defines the valid fields within a {@link Bug} which have a limited set of valid values.
	 * @author Tom
	 *
	 */
	public enum Fields { COMPONENT, VERSION, REP_PLATFORM, OP_SYS, PRIORITY, SEVERITY, STATUS, RESOLUTION }
	
	private Set<String> legalValues = Collections.emptySet();
	
	private Product product;
	
	/**
	 * Creates a new {@link GetLegalValues} instance on the specified {@link Fields field}.
	 * @param field A {@link Fields} enum value describing which field's values should be retrieved.
	 */
	public GetLegalValues(Fields field) {
		params.put("names", field.toString());
	}
	
	/**
	 * Creates a new {@link GetLegalValues} instance which will retrieve the valid values for a per-{@link Product} {@link Fields Field}.
	 * @param field A {@link Fields} enum value describing which field's values should be retrieved.
	 * @param product A {@link Product} to limit the returned legal values by.
	 */
	public GetLegalValues(Fields field, Product product) {
		this(field);
		this.product = product;
	}
	
	/**
	 * Returns the {@code Set} of legal strings which the given {@link Fields field} may be assigned.
	 * @return A set of {@code Strings}.
	 */
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
			
			if(product == null) {
				legalValues.add(name);
			} else {
				//check the 'visibility_values' field
				Object[] productNames = (Object[]) map.get("visibility_values");
				for(Object curr : productNames) {
					if(curr.equals(product.getName())) {
						legalValues.add(name);
						break;
					}
				}
			}
			
		}
	}

	@Override
	public String getMethodName() {
		return METHOD_NAME;
	}

	@Override
	public Map<Object, Object> getParameterMap() {
		return Collections.unmodifiableMap(params);
	}

}
