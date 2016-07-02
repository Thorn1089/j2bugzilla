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
import java.util.List;
import java.util.LinkedList;

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
     * Creates a new {@link GetProduct}, which can be used to retrieve the collection of {@link Product} associated
     * with the specified ID numbers.
     * @param ids A collection of unique integer product IDs.
     */
    public GetProduct(int[] ids) {
        if (ids == null) {
            throw new IllegalArgumentException("ids array cannot be null.");
        }
        final Integer[] idsParam = new Integer[ids.length];
        int ind = 0;
        for (int id : ids) {
            idsParam[ind++] = id;
        }
        params.put("ids", idsParam);
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

    /**
     * Returns list of the products found in the Bugzilla installation matching the provided names or IDs.
     * @return A collection of new {@link Product} objects, or null if there are no results to return.
     */
    public List<Product> getProducts() {
        Object products = hash.get("products");
        if(products == null) {
            return null;
        }

        Object[] arr = (Object[])products;
        if(arr.length == 0) {
            return null;
        }

        final List<Product> result = new LinkedList<Product>();
        for (final Object curPrcProduct : arr) {
            @SuppressWarnings("unchecked")
            final Map<Object, Object> prodMap = (Map<Object, Object>)curPrcProduct;
            final Product product = makeProduct(prodMap);
            final String desc = (String)prodMap.get("description");
            product.setDescription(desc);
            if (prodMap.get("versions") != null) {
                fillProductVersions(prodMap, product);
            }
            result.add(product);
        }
        return result;
    }

    /**
     * Make new {@link Product} object base on the ID and description stored in the passed map object.
     *
     * @param prodMap {@link Map} object that contains id and name of the product that must be created.
     * @return new {@link Product} object.
     */
    private Product makeProduct(final Map<Object, Object> prodMap) {
        return new Product((Integer)prodMap.get("id"), (String)prodMap.get("name"));
    }

    /**
     * Fill list if the {@link ProductVersion} objects associated with passed {@link Product}.
     * @param prodMap {@link Map} contained information about versions.
     * @param product {@link Product} object to fill versions list in.
     */
    private void fillProductVersions(final Map<Object, Object> prodMap, final Product product) {
        final Object[] versions = (Object[]) prodMap.get("versions");
        for(Object version : versions){
            @SuppressWarnings("unchecked")//Cast to form specified by webservice
            final Map<Object, Object> versionMap = (Map<Object, Object>) version;
            final ProductVersion productVersion = new ProductVersion((Integer)versionMap.get("id"),
                (String)versionMap.get("name"));
            product.addProductVersion(productVersion);
        }
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
