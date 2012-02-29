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
public class TestGetBug {

	@Mock
	private BugzillaConnector conn;
	
	@Test
	public void test() throws BugzillaException {
		GetBug getBug = new GetBug(1);
		
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				GetBug rpcMethod = (GetBug)invocation.getArguments()[0];
				
				Map<Object, Object> hash = new HashMap<Object, Object>();
				
				Object[] bugArray = new Object[1];
				Map<String, Object> bug = new HashMap<String, Object>();
				bug.put("product", "Test");
				bug.put("component", "Test");
				bug.put("summary", "Testing the get method");
				bug.put("version", "1.0.1");
				
				bugArray[0] = bug;
				
				hash.put("bugs", bugArray);
				
				rpcMethod.setResultMap(hash);
				
				return null;
			}
			
		}).when(conn).executeMethod(getBug);
		
		conn.executeMethod(getBug);
		
		assertEquals("Bug product is incorrect", "Test", getBug.getBug().getProduct());
		assertEquals("Bug component is incorrect", "Test", getBug.getBug().getComponent());
		assertEquals("Bug summary is incorrect", "Testing the get method", getBug.getBug().getSummary());
		assertEquals("Bug version is incorrect", "1.0.1", getBug.getBug().getVersion());
	}

}
