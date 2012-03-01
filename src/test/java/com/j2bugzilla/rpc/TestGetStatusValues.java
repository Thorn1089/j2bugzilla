package com.j2bugzilla.rpc;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;

@RunWith(MockitoJUnitRunner.class)
public class TestGetStatusValues {

	@Mock
	private BugzillaConnector conn;
	
	@Test
	public void test() throws BugzillaException {
		GetStatusValues status = new GetStatusValues();
		
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				GetStatusValues rpcMethod = (GetStatusValues)invocation.getArguments()[0];
				
				Map<Object, Object> hash = new HashMap<Object, Object>();
				
				Object[] resultArray = new Object[1];
				Map<Object, Object> values = new HashMap<Object, Object>();
				resultArray[0] = values;
				
				Object[] fieldArray = new Object[3];
				
				Map<Object, Object> newStatus = new HashMap<Object, Object>();
				newStatus.put("name", "NEW");
				fieldArray[0] = newStatus;
				
				Map<Object, Object> assigned = new HashMap<Object, Object>();
				assigned.put("name", "ASSIGNED");
				fieldArray[1] = assigned;
				
				Map<Object, Object> resolved = new HashMap<Object, Object>();
				resolved.put("name", "RESOLVED");
				fieldArray[2] = resolved;
				
				values.put("values", fieldArray);
				hash.put("fields", resultArray);
				
				rpcMethod.setResultMap(hash);
				
				return null;
			}
			
		}).when(conn).executeMethod(status);
		
		conn.executeMethod(status);
		
		Set<String> values = status.getLegalValues();
		
		assertEquals("Wrong number of values", 3, values.size());
		
		assertTrue("Set is missing NEW", values.contains("NEW"));
		assertTrue("Set is missing ASSIGNED", values.contains("ASSIGNED"));
		assertTrue("Set is missing RESOLVED", values.contains("RESOLVED"));
	}

}
