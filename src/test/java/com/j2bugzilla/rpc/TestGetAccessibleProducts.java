package com.j2bugzilla.rpc;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;

@RunWith(MockitoJUnitRunner.class)
public class TestGetAccessibleProducts {

	@Mock
	private BugzillaConnector conn;
	
	@Test
	public void testGetProductIDs() throws BugzillaException {
		GetAccessibleProducts getProducts = new GetAccessibleProducts();
		
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				GetAccessibleProducts rpcMethod = (GetAccessibleProducts)invocation.getArguments()[0];
				
				Map<Object, Object> hash = new HashMap<Object, Object>();
				Object[] ids = new Object[] { 1, 2, 3};
				hash.put("ids", ids);
				
				rpcMethod.setResultMap(hash);
				
				return null;
			}
			
		}).when(conn).executeMethod(getProducts);
		
		conn.executeMethod(getProducts);
		
		int[] IDs = getProducts.getProductIDs();
		
		assertEquals("ID missing", 1, IDs[0]);
		assertEquals("ID missing", 2, IDs[1]);
		assertEquals("ID missing", 3, IDs[2]);
	}

}
