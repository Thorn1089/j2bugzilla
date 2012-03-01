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

import com.j2bugzilla.base.Bug;
import com.j2bugzilla.base.BugFactory;
import com.j2bugzilla.base.BugzillaConnector;
import com.j2bugzilla.base.BugzillaException;

@RunWith(MockitoJUnitRunner.class)
public class TestReportBug {

	@Mock
	private BugzillaConnector conn;
	
	@Test
	public void test() throws BugzillaException {
		Bug bug = new BugFactory().newBug()
				.setComponent("Test")
				.setProduct("Test")
				.setSummary("Testing report")
				.setVersion("1.0.6")
				.createBug();
		
		ReportBug report = new ReportBug(bug);
		
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				ReportBug rpcMethod = (ReportBug)invocation.getArguments()[0];
				
				Map<Object, Object> hash = new HashMap<Object, Object>();
				hash.put("id", 1);
				
				rpcMethod.setResultMap(hash);
				
				return null;
			}
			
		}).when(conn).executeMethod(report);
		
		conn.executeMethod(report);
		
		assertEquals("Bug ID is incorrect", 1, report.getID());
	}

}
