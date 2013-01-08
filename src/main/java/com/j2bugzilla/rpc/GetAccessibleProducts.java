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

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import com.j2bugzilla.base.BugzillaMethod;

/**
 * The {@code GetAccessibleProducts} class allows clients to access the set of {@link com.j2bugzilla.base.Product Products} which can
 * be searched against or have bugs entered against them for the current user.
 * @author Tom
 *
 */
public class GetAccessibleProducts implements BugzillaMethod {

	private static final String METHOD_NAME = "Product.get_accessible_products";
	
	private int[] ids = new int[0];
	
	/**
	 * Returns an array of integers representing the accessible product IDs. These IDs can be further queried with the
	 * {@link GetProduct} class.
	 * @return An array of {@code ints}.
	 */
	public int[] getProductIDs() {
		return Arrays.copyOf(ids, ids.length);
	}
	
	@Override
	public void setResultMap(Map<Object, Object> hash) {
		Object[] objs = (Object[]) hash.get("ids");
		int[] ints = new int[objs.length];
		for(int i = 0; i < objs.length; i++) {
			ints[i] = (Integer)objs[i];
		}
		ids = ints;
	}

	@Override
	public Map<Object, Object> getParameterMap() {
		return Collections.emptyMap();
	}

	@Override
	public String getMethodName() {
		return METHOD_NAME;
	}

}
