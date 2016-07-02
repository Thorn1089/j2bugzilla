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

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;
import com.j2bugzilla.base.ProductVersion;
import com.j2bugzilla.base.Product;

@RunWith(MockitoJUnitRunner.class)
public class TestGetProduct {

	@Mock
	private BugzillaConnector conn;
	
	@Test
	public void test() throws BugzillaException {
		GetProduct product = new GetProduct(1);
		
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				GetProduct rpcMethod = (GetProduct)invocation.getArguments()[0];
				
				Map<Object, Object> hash = new HashMap<Object, Object>();
				Object[] productArray = new Object[1];
				
				Map<Object, Object> product = new HashMap<Object, Object>();
				product.put("id", 1);
				product.put("name", "Test");
				
				productArray[0]	 = product;
				
				//versions
				Map<String, Object> version = new HashMap<String, Object>();
				version.put("id", 17);
				version.put("name", "1.0");

				Object[] versions = { version};
				product.put("versions", versions);
					
				hash.put("products", productArray);
				rpcMethod.setResultMap(hash);
				
				return null;
			}
			
		}).when(conn).executeMethod(product);
		
		conn.executeMethod(product);
		
		assertEquals("Product ID is incorrect", 1, product.getProduct().getID());
		assertEquals("Product name is incorrect", "Test", product.getProduct().getName());
		assertEquals("versions number is incorrect", 1, product.getProduct().getProductVersions().size());
		
		ProductVersion version = product.getProduct().getProductVersions().get(0);
		assertEquals("Product version ID is incorrect", 17, version.getID());
		assertEquals("Product version name is incorrect", "1.0", version.getName());
		
	}

	@Test
	public void testManyProducts() throws BugzillaException {
		GetProduct product = new GetProduct(new int[]{1, 2, 7});
		
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				GetProduct rpcMethod = (GetProduct)invocation.getArguments()[0];
				
				Map<Object, Object> hash = new HashMap<Object, Object>();
				Object[] productArray = new Object[3];
				
				Map<Object, Object> product;

				//versions
				Map<String, Object> version = new HashMap<String, Object>();
				version.put("id", 17);
				version.put("name", "1.0");
				Object[] versions = {version};

				//				
				product = new HashMap<Object, Object>();
				product.put("id", 1);
				product.put("name", "Test1");
				product.put("versions", versions);
				productArray[0]	 = product;
				//
				product = new HashMap<Object, Object>();
				product.put("id", 2);
				product.put("name", "Test2");
				product.put("versions", versions);
				productArray[1]	 = product;
				//
				product = new HashMap<Object, Object>();
				product.put("id", 7);
				product.put("name", "Test7");
				product.put("versions", versions);
				productArray[2]	 = product;

				hash.put("products", productArray);
				rpcMethod.setResultMap(hash);
				
				return null;
			}
			
		}).when(conn).executeMethod(product);
		
		conn.executeMethod(product);

		assertEquals("Result size is incorrect", 3, product.getProducts().size());
		List<Product> products = product.getProducts();

		assertEquals("Product1 ID is incorrect", 1, products.get(0).getID());
		assertEquals("Product1 name is incorrect", "Test1", products.get(0).getName());
		assertEquals("Product1 versions number is incorrect", 1, products.get(0).getProductVersions().size());

		assertEquals("Product2 ID is incorrect", 2, products.get(1).getID());
		assertEquals("Product2 name is incorrect", "Test2", products.get(1).getName());
		assertEquals("Product2 versions number is incorrect", 1, products.get(1).getProductVersions().size());

		assertEquals("Product3 ID is incorrect", 7, products.get(2).getID());
		assertEquals("Product3 name is incorrect", "Test7", products.get(2).getName());
		assertEquals("Product3 versions number is incorrect", 1, products.get(2).getProductVersions().size());

		ProductVersion version = products.get(0).getProductVersions().get(0);
		assertEquals("Product version ID is incorrect", 17, version.getID());
		assertEquals("Product version name is incorrect", "1.0", version.getName());
	}
	
	@Test
	public void testNoProducts() throws BugzillaException {
		GetProduct product = new GetProduct(-1);
		
		assertNull("Product returned before execution should be null", product.getProduct());
		
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				GetProduct rpcMethod = (GetProduct)invocation.getArguments()[0];
				
				Map<Object, Object> hash = new HashMap<Object, Object>();
				hash.put("products", new Object[0]);
				rpcMethod.setResultMap(hash);
				return null;
			}
		}).when(conn).executeMethod(product);
		
		conn.executeMethod(product);
		
		assertNull("Product returned should be null", product.getProduct());
	}

}
