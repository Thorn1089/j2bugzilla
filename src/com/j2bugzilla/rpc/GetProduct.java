package com.j2bugzilla.rpc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.j2bugzilla.base.BugzillaMethod;
import com.j2bugzilla.base.Product;

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
	
	public GetProduct(String name) {
		params.put("name", new String[] { name });
	}
	
	public GetProduct(int id) {
		params.put("ids", new Integer[] { id });
	}
	
	public Product getProduct() {
		return null;
	}
	
	@Override
	public void setResultMap(Map<Object, Object> hash) {
		this.hash = hash;
	}

	@Override
	public Map<Object, Object> getParameterMap() {
		return params;
	}

	@Override
	public String getMethodName() {
		return METHOD_NAME;
	}

}
