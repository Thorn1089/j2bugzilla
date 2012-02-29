package com.j2bugzilla.rpc;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class TestLogIn extends TestCase {

	@Mock
	private BugzillaConnector conn;
	
	@Test
	public void testLogIn() throws BugzillaException {
		LogIn logIn = new LogIn("test@test.org", "password");
		
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				LogIn rpcMethod = (LogIn)args[0];
				
				Map<Object, Object> results = new HashMap<Object, Object>();
				results.put("id", 1);
				rpcMethod.setResultMap(results);
				return null;
			}
			
		}).when(conn).executeMethod(logIn);
		
		conn.executeMethod(logIn);
		
		assertEquals("Incorrect user ID returned", 1, logIn.getUserID());
	}
	
}
