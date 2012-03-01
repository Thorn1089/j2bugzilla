package com.j2bugzilla.rpc;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;

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
				
				hash.put("products", productArray);
				
				rpcMethod.setResultMap(hash);
				
				return null;
			}
			
		}).when(conn).executeMethod(product);
		
		conn.executeMethod(product);
		
		assertEquals("Product ID is incorrect", 1, product.getProduct().getID());
		assertEquals("Product name is incorrect", "Test", product.getProduct().getName());
	}

}
