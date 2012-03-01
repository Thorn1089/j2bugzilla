package com.j2bugzilla.rpc;

import static org.mockito.Mockito.doAnswer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;

@RunWith(MockitoJUnitRunner.class)
public class TestLogOut {

	@Mock
	private BugzillaConnector conn;
	
	@Test
	public void test() throws BugzillaException {
		LogOut logOut = new LogOut();
		
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				return null;
			}
			
		}).when(conn).executeMethod(logOut);
		
		conn.executeMethod(logOut);
	}

}
