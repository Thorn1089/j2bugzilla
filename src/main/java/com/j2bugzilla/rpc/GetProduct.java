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
import java.util.Map;

import com.j2bugzilla.base.BugzillaMethod;
import com.j2bugzilla.base.Product;
import com.j2bugzilla.base.ProductVersion;

/**
 * The {@code GetProduct} class provides access to information on {@link Product Products} active in a particular
 * Bugzilla installation.
 * 
 * @author Tom
 *
 */
public class GetProduct implements BugzillaMethod {

	/**
	 * The method Bugzilla will execute via XML-RPC
	 */
	private static final String METHOD_NAME = "Product.get";
	
	private Map<Object, Object> params = new HashMap<Object, Object>();
	
	private Map<Object, Object> hash = Collections.emptyMap();
	
	/**
	 * Creates a new {@link GetProduct}, which can be used to retrieve the {@link Product} associated with the
	 * specified ID number.
	 * @param id A unique integer ID.
	 */
	public GetProduct(int id) {
		params.put("ids", new Integer[] { id });
	}
	
	/**
	 * Returns the product found in the Bugzilla installation matching the provided name or ID.
	 * @return A new {@link Product}, or null if there are no results to return.
	 */
	public Product getProduct() {
		Object products = hash.get("products");
		if(products == null) { return null; }
		
		Object[] arr = (Object[])products;
		if(arr.length == 0) { return null; }
		
		@SuppressWarnings("unchecked")//Cast to form specified by webservice
		Map<Object, Object> prodMap = (Map<Object, Object>)arr[0];
		Product product = new Product((Integer)prodMap.get("id"), (String)prodMap.get("name"));
		String desc = (String)prodMap.get("description");
		product.setDescription(desc);
		
		if(prodMap.get("versions") != null) {
			Object[] versions = (Object[]) prodMap.get("versions");
			for(Object version : versions){
				@SuppressWarnings("unchecked")//Cast to form specified by webservice
				Map<Object, Object> versionMap = (Map<Object, Object>) version;
				ProductVersion productVersion = new ProductVersion((Integer)versionMap.get("id"), 
						(String)versionMap.get("name"));
				product.addProductVersion(productVersion);
			}
		}
		
		return product;
	}
	
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
