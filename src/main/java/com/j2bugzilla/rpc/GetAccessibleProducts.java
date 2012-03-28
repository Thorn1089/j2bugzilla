package com.j2bugzilla.rpc;

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
		return ids;
	}
	
	@Override
	public void setResultMap(Map<Object, Object> hash) {
		ids = (int[]) hash.get("ids");
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
