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
public class TestCommentBug {

	@Mock
	private BugzillaConnector conn;
	
	@Test
	public void test() throws BugzillaException {
		CommentBug comment = new CommentBug(1, "New Comment");
		
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				CommentBug rpcMethod = (CommentBug)invocation.getArguments()[0];
				
				Map<Object, Object> hash = new HashMap<Object, Object>();
				hash.put("id", 1);
				
				rpcMethod.setResultMap(hash);
				
				return null;
			}
		
		}).when(conn).executeMethod(comment);
		
		conn.executeMethod(comment);
		
		assertEquals("Incorrect comment ID", 1, comment.getCommentID());
	}

}
